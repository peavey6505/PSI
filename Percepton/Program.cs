
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Encog.Neural.Networks;
using Encog.Neural.Networks.Layers;
using Encog.Neural.NeuralData;
using Encog.Neural.Networks.Training;
using Encog.Neural.Networks.Training.Propagation.Resilient;
using Encog.Neural.Data.Basic;
using Encog.Neural.Activation;
using Encog.Neural.Data;

namespace XORExample
{
   
    public class Program
    {
       
        /// <summary>
        /// Program entry point.
        /// </summary>
        /// <param name="args">Not used.</param>
        public static void Main()
        {
            BasicNetwork network = new BasicNetwork();
            network.AddLayer(new BasicLayer(new ActivationSigmoid(), true, 2));
            network.AddLayer(new BasicLayer(new ActivationSigmoid(), true, 6));
            network.AddLayer(new BasicLayer(new ActivationSigmoid(), true, 1));
            network.Structure.FinalizeStructure();
            network.Reset();



            double[][] XOR_IDEAL = {
            new double[1] { 0.0 },
            new double[1] { 1.0 },
            new double[1] { 1.0 },
            new double[1] { 0.0 } };


            double[][] XOR_INPUT ={
            new double[2] { 0.0, 0.0 },
            new double[2] { 1.0, 0.0 },
            new double[2] { 0.0, 1.0 },
            new double[2] { 1.0, 1.0 } };

            INeuralDataSet trainingSet = new BasicNeuralDataSet(XOR_INPUT, XOR_IDEAL);
            ITrain train = new ResilientPropagation(network, trainingSet);


            int iteracja = 1;

            do
            {
                train.Iteration();
                Console.WriteLine("Iteracja#" + iteracja + " Error:" + train.Error);
                iteracja++;
            } while ((iteracja < 5000) && (train.Error > 0.001));


            Console.WriteLine("\nWyniki:");
            foreach (INeuralDataPair pair in trainingSet)
            {
                INeuralData output = network.Compute(pair.Input);
                Console.WriteLine(pair.Input[0] + "," + pair.Input[1]
                        + ", aktualne=" + output[0] + ",idealne=" + pair.Ideal[0]);
            }

            Console.ReadLine();

        }
    }
}
