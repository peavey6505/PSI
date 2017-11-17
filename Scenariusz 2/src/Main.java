public class Main {

    public static void main(String[] args) {
        System.out.println("Scenariusz 2 - Budowa i dzialanie sieci jednowarstwowej");

        String wejsciePath = "src/wejscie.txt";
        String wejscieTestPath = "src/daneTestowe.txt";
        
            NeuralNetwork network = new NeuralNetwork(wejsciePath, wejscieTestPath);

    }
    
}