import java.util.Scanner;

/**
 * Created by W7HP on 2017-11-16.
 */
public class Litera {
    char id;
    double[] matryca;
    int rozmiar_matrycy;

    public Litera(){
        rozmiar_matrycy=35;
        matryca=new double[rozmiar_matrycy];
    }

    /*public Litera(int rozmiar,double[] matryca){
        rozmiar_matrycy=rozmiar;
        this.matryca=new double[rozmiar_matrycy];
        this.matryca=matryca;
    }
    */
    public void wczytajLitere(Scanner s){
        id=s.next().charAt(0);
        for (int i=0;i<rozmiar_matrycy;i++)
            matryca[i]=s.nextInt();
    }
    public void wyswietlLitere(){
        int k=0;
        System.out.println("  "+id);
        for(int i=0;i<rozmiar_matrycy;i++) {
            if(i%5==0 && i!=0) System.out.println();
            System.out.print((int)matryca[i]);

        }
        System.out.println();
    }
}
