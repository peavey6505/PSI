import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NeuralNetwork
{
     int[][] x;
     int[] y;
    double[] wagi;
    
    
   
    
    double wspolczynnikUczenia = 0.1; 



     int rozmiarLitery = 35; 
     int iloscLiter = 10; 
     String wejsciePath;
     String wejscieTestPath;
     double wynik;
     double blad;

    private final char litery[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'c', 'd', 'b', 'h', 'i', 'k', 'm', 'n', 'o', 'u'};

    NeuralNetwork(String wejsciePath, String wejscieTestPath)
    {
    	this.wejsciePath = wejsciePath;
    	this.wejscieTestPath = wejscieTestPath;
        try {
            Scanner scanner = new Scanner(new File(wejsciePath));
            

            x = new int[20][35];
            y = new int[20];
            wagi = new double[rozmiarLitery+1]; // ostatni bias

            loadData(scanner, false);
            showData();
            losujWagi();
            learn();
            test();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku input.txt");
        }

    }

    private void loadData(Scanner s, boolean isTest) {
        for(int i = 0;i < 20;i++) {
            for(int j = 0;j < 35; j++) {
                x[i][j] = s.nextInt();
            }
            if (!isTest) {
                y[i] = s.nextInt();
            }
        }
    }

    private void showData() {
        System.out.println("Prezentacja wszystkich wczytanych liter");
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.println(litery[i]);
            for (int j = 1; j <= 35; j++) {
                System.out.print(x[i][j-1]);
                if (j % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    private void losujWagi() {
        Random r = new Random();
        for (int i = 0;i <= rozmiarLitery; ++i) {
            wagi[i] = r.nextDouble() - 0.5;
            System.out.println("Waga[" + i + "] = " + wagi[i]);
        }
    }

    private void aktualizujWagi(double error, int letter[]) {
        for (int i = 0; i < rozmiarLitery; ++i) {
            wagi[i] += error * wspolczynnikUczenia * letter[i];
        }
        wagi[rozmiarLitery] = error * wspolczynnikUczenia; // bias
    }

    private double sum(int x[]) {
        double sum = 0;
        for (int i = 0; i < rozmiarLitery; ++i) {
            sum += x[i] * wagi[i];
        }
        return 1/(1 + Math.exp(-sum));
    }

    private void learn() {
        int epoch = 0;
        while (epoch < 10000) {
            for (int i = 0; i < iloscLiter; ++i) {
                wynik = sum(x[i]);

                blad = y[i] - wynik;

                if (blad * blad > 0.25) {
                    aktualizujWagi(blad, x[i]);
                }
            }
            ++epoch;
        }
    }

    private void test(){
        try {
            Scanner testScanner = new Scanner(new File(wejscieTestPath));
            loadData(testScanner, true);

            System.out.println();
            System.out.println("Rozpoczecie wykonywania testu");

            for (int i = 0; i < 20; ++i) {
                int tmp[] = x[i];

                System.out.println();
                System.out.println();
                printLetterDigits(tmp);
                System.out.print(" WYNIK: " + sum(tmp) + ": ");
                if (sum(tmp) >= 0.5) {
                    System.out.print("du¿a litera.");
                } else {
                    System.out.print("ma³a litera.");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku testinput.txt");
        }

    }

    private void printLetterDigits(int digits[]) {
        for (int j = 1; j <= 35; j++) {
            System.out.print(digits[j-1]);
            if (j % 5 == 0) {
                System.out.println();
            }
        }
    }
}