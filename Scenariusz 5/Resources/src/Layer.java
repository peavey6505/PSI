import java.util.ArrayList;

public class Layer {

    public Neuron[] neurons;
    private int neuronCount;
    private Data data;






    public void modify(int id) {
        neurons[id].modifyWeights(data);
    }


    public ArrayList<Double> compute(Data input){
        ArrayList<Double> results = new ArrayList<>();
        this.data = input;

        for (int i = 0; i < neurons.length; i++){
            results.add(neurons[i].compute(input));
        }

        return results;
    }

    public Layer(int neuronCount){

        this.neuronCount = neuronCount;

        neurons = new Neuron[neuronCount];
        for(int i = 0; i < neuronCount; i++){
            neurons[i] = new Neuron();
        }
    }

    public int getNeuronCount() {
        return neuronCount;
    }
}