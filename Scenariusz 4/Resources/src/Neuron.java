import java.util.Random;

/**
 * Created by W7HP on 2017-11-16.
 */
public class Neuron {
    int ID;
    double wyj;
    double[] wej;
    double[] wagi;
    double bias;
    double wsp;
    double wyjN;
    double blad;
    double l_wej;
    double pochodna;


    public Neuron(int ID,int l_wej){
    this.ID=ID;
    this.l_wej=l_wej;
    this.wyjN=0;
    wej=new double[l_wej];
    wagi=new double[l_wej];
    double wyj=0;
    wsp=0.5;
    blad=0;
    }

    public void losujWagi(){
        Random random = new Random();
        for (int i = 0; i < l_wej; i++)
            wagi[i] = random.nextDouble() - 0.5;

        bias=random.nextDouble() - 0.5;
    }

    public double fAktywacji(double sum){

        double wynik=0;
        double beta=1;
        //wynik =(1/(1+Math.exp(-beta*sum)));
        //wynik=(1-Math.exp(-sum))/(1+Math.exp(-sum));
        if(sum>=0) wynik=1;
        //pochodna=wynik * ( 1 - wynik );

        return wynik;
    }

    public double suma(Litera l){
        double suma=0;
        for(int i=0; i<wej.length; i++) {
            suma += l.matryca[i] * wagi[i];
        }
        //suma+=bias;

        return suma;
    }

    public double licz(Litera l){
        double suma=suma(l);
        return fAktywacji(suma);        //zwaraca wyjscie neurona

    }

    public void zmienWagi(double a,Litera l){
        //bez zapominania
        double delta=0;
        for (int i = 0; i < l_wej; i++) {
            delta=wsp*wagi[i]*l.matryca[i]*l.matryca[i];
            wagi[i]=wagi[i]*(1-0.09)+delta;
        }
        bias=bias+wsp*a;
        System.out.println("Waga 1: "+delta);
    }


        /*for (int i = 0; i < l_wej; i++) {
            wagi[i] = wagi[i] + wsp * wyj * wej[i];
        }
        bias = bias - wsp * blad; //Modyfikacja wagi BIAS'u
    }*/

}
