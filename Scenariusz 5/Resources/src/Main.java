public class Main {

    public static void main(String[] args) {
        System.out.println("Scenariusz 5 - Budowa i dzialanie sieci Kohonena dla WTA");

        NeuralNetwork network = new NeuralNetwork(30);

        network.learn();
        network.test();
    }
}