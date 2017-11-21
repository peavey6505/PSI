using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSI_scenariusz_3._1
{
    class Tester
    {
        void start()
        {
            /*
             0 0 0 - 0
             0 0 1 = 1
             0 1 0 = 1 
             0 1 1 = 0
             1 0 0 = 1
             1 0 1 = 0
             1 1 0 = 0
             1 1 1 = 1
              
             */
            NeuralNetwork net = new NeuralNetwork(new int[] { 3,25,25,1 });

            for(int i =0; i< 5000; i++)
            {
                net.FeedForward(new float[] { 0, 0, 0 });
                net.BackProp(new float[] { 0 });

                net.FeedForward(new float[] { 0, 0, 1 });
                net.BackProp(new float[] { 0 });

                net.FeedForward(new float[] { 0, 1, 0 });
                net.BackProp(new float[] { 0 });

                UnityEngine 
            }
        }
    }
}
