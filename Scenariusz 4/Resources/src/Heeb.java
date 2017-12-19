import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by W7HP on 2017-11-30.
 */
public class Heeb2 {
        int epoka;
        double wsp_uczenia;
        double wsp_zapominania;

        double blad_globalny;
        double blad_lokalny;
        double MSE;
        double MAPE;

        double pom;
        double pom1;

        double[][] dane_uczace;
        double[][] wagi;
        double[] a;
        double[] deltaw;

        double[][] dane_testujace;

        public Heeb2(){
            epoka=0;
            wsp_uczenia=0.5;
            wsp_zapominania=0.1;
            blad_globalny=0;
            blad_lokalny=0;
            MSE=0;
            MAPE=0;
            pom=0;
            pom1=0;

            dane_uczace=new double[35][26];
            wagi=new double[35][26];
            a=new double[35];
            deltaw=new double[35];

            dane_testujace=new double[35][2];

            wczytajDane(dane_uczace,26,35,"dane.txt");
            wczytajDane(dane_testujace,2,35,"test.txt");
            ustawWagi(wagi);
        }

        public void ucz(){
            //bez zapominania
           /* do{
                System.out.println("Epoka: "+epoka);
                for(int i=0;i<26;++i){
                    blad_globalny = 0.;
                    for(int k=0;k<35;++k)
                         pom += a[k];
                    for(int j=0;j<35;++j){
                        //pom = a[j];
                        a[j] = wagi[j][i]*dane_uczace[j][i];
                        wagi[j][i] = wagi[j][i]+wsp_uczenia*a[j]*dane_uczace[j][i];

                        if(blad_lokalny==Math.abs(pom-a[j])) break;
                        blad_lokalny = Math.abs(pom - a[j]);
                        blad_globalny = blad_globalny + Math.pow(blad_lokalny,2);

                    }

                    MSE = Math.pow(blad_globalny,2)/35;
                    MAPE = (blad_globalny*10)/35;
                    System.out.println("MSE: "+MSE+"\tMAPE: "+MAPE);

                }
            epoka++;
            }while(blad_globalny !=0 && epoka<1000);*/

            do{
                System.out.println("Epoka: "+epoka);
                for(int i=0;i<26;++i){
                    blad_globalny = 0.;
                    for(int j=0;j<35;++j){
                        pom = a[j];
                        a[j] = wagi[j][i]*dane_uczace[j][i];
                        wagi[j][i] = wagi[j][i]*(1-wsp_zapominania)+wsp_uczenia*a[j]*dane_uczace[j][i];

                        if(blad_lokalny==Math.abs(pom-a[j])) break;
                        blad_lokalny = Math.abs(pom - a[j]);
                        blad_globalny = blad_globalny + Math.pow(blad_lokalny,2);

                    }

                    MSE = Math.pow(blad_globalny,2)/35;
                    MAPE = (blad_globalny*10)/35;
                    //System.out.println("MSE: "+MSE+"\tMAPE: "+MAPE);

                }
                epoka++;
            }while(blad_globalny >0.1 && epoka<10000);
        }


        public void test(){

        System.out.println("Test");
        double tab[][]=new double[2][35];
        double a_test[]=new double[35];

        for(int i=0;i<2;i++){
            for(int j=0;j<35;j++){
                a[j]=wagi[j][0]*dane_testujace[j][i];

                a_test[j]=fun(a[j]);


            }
            System.out.println("-------------");
            porownaj(a_test);
        }

        }

        public void porownaj(double[] a_test){
            double[] pom=new double[26];
            for(int i=0;i<26;i++) pom[i]=0;

            for(int i=0;i<26;i++){
                for(int j=0;j<35;j++){
                    if(a_test[j]==dane_uczace[j][i]) pom[i]++;
                }
            }

            for(int j=0;j<26;j++){
               System.out.println((char)(65+j)+": "+(int)(pom[j]/35*100)+"%");
            }
        }
        public double fun(double a){
            double wynik;
            if(a>0) wynik=1;
            else wynik=0;

            return wynik;
        }
        public void ustawWagi(double[][] w){
            Random random=new Random();
            for(int i=0;i<26;i++){           //petla po literach
                for(int j=0;j<35;j++){       //petla po matrycy
                   w[j][i]=random.nextDouble()-0.5;
                }


            }

        }

        public void wczytajDane(double[][] dane,int l,int m,String plik){
            try {
                Scanner scanner =new Scanner(new File(plik));
                for(int i=0;i<l;i++){           //petla po literach
                    scanner.next();
                    for(int j=0;j<m;j++){       //petla po matrycy
                        dane[j][i]=scanner.nextInt();
                    }


                }
                scanner.close();
            }catch (Exception e){e.printStackTrace();}

        }

}
