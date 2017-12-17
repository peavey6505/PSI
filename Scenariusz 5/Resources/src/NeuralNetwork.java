import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NeuralNetwork
{
    final static double lFactor = 0.5; // wspolczynnik uczenia
    private final static int maxIterations = 1000;

    private final static int learningInputs = 150;
    private final static int testingInputs = 45;
    final static int inputSize = 4;

    private Data[] learningData;
    private Data[] testingData;

    private Layer layer;

    NeuralNetwork(int neuronsCount)
    {
        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));
            Scanner scanner2 = new Scanner(new File("src/testinput.txt"));

            layer = new Layer(neuronsCount);
            learningData = new Data[learningInputs];
            testingData = new Data[testingInputs];
            loadData(scanner, false);
            loadData(scanner2, true);
            scanner.close();
            showData();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku input.txt lub test.txt");
        }
    }

    private void loadData(Scanner s, boolean isTest) {
        int inputsCount = isTest ? testingInputs : learningInputs;
        Data[] dummy = new Data[inputsCount];
        for(int i = 0; i < inputsCount; i++) {
            dummy[i] = new Data(inputSize);
            for(int j = 0; j < inputSize; j++) {
                dummy[i].setXi(j, Double.parseDouble(s.next()));
            }
            dummy[i].setY(s.next());
        }
        if (isTest) {
            testingData = dummy;
        } else {
            learningData = dummy;
        }
    }


    private void showData() {
        System.out.println("Prezentacja wszystkich wczytanych danych uczacych");
        for (int i = 0; i < learningInputs; i++) {
            for(int j = 0; j < inputSize; j++) {
                System.out.print(learningData[i].getXi(j) + " ");
            }
            System.out.println(learningData[i].getY());
        }

    }

    public void learn() {
        normalize(learningData);
        normalize(testingData);

        int iterations = 0;
        ArrayList<Double> result;

        while (iterations < maxIterations) {
            for (int i = 0; i < learningInputs; i++) {
                result = layer.compute(learningData[i]);
                layer.modify(result.indexOf(Collections.max(result)));
                result.clear();
            }
            ++iterations;
        }

        System.out.println("Uczenie zakobczone sukcesem.\nLiczba epok uczenia = " + iterations + "\n\n");
    }

    public void test(){
        ArrayList<Double> result;
        ArrayList<Integer> group = new ArrayList<>();
        int winner;
        for(int i = 0; i < testingInputs; i++) {

            result = layer.compute(testingData[i]);
            winner = result.indexOf(Collections.max(result));

            if(!group.contains(winner)){
                group.add(result.indexOf(Collections.max(result)));
            }
            System.out.println("ID: " + i + "\t Rodzaj kwiata " + testingData[i].getY() + ", grupa " + winner);

        }

        System.out.println("\nLista zwycieskich grup");
        for(Integer el: group)
            System.out.println(el.toString());
    }

    private void normalize(Data[] data){
        for (Data aData : data) {
            double lenght = aData.getXi(0) * aData.getXi(0) +
                    aData.getXi(1) * aData.getXi(1) +
                    aData.getXi(2) * aData.getXi(2) +
                    aData.getXi(3) * aData.getXi(3);
            lenght = Math.sqrt(lenght);

            aData.setXi(0, aData.getXi(0) / lenght);
            aData.setXi(1, aData.getXi(1) / lenght);
            aData.setXi(2, aData.getXi(2) / lenght);
            aData.setXi(3, aData.getXi(3) / lenght);
        }
    }
}
