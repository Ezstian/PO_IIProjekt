package MonteCarlo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class monteCarlo {
    public static void main(String[] args){
        InputStreamReader str = new InputStreamReader(System.in);
        Random losuj = new Random();
        final int[] objetosci = {3, 5, 5, 5, 2, 1,3};
        final int[] wartosc = {1, 2, 3, 2, 1, 6};
        final int LICZBA_LOSOWAN = 10000;
        final int max = 10;
        int przedmiot;
        int sumaWartosci = 0;
        int maxSumaWartosci = 0;
        for (int i = 0; i < LICZBA_LOSOWAN; i++) {
            boolean[] listaIndexow = new boolean[10];
            int suma_objetosci = 0;
            sumaWartosci = 0;
            while (true) {
                int l = losuj.nextInt(objetosci.length);
                przedmiot = objetosci[l];
                if(listaIndexow[l]) continue;
                if (przedmiot <= max - suma_objetosci) {
                    suma_objetosci += przedmiot;
                    sumaWartosci += wartosc[l];
                    listaIndexow[l] = true;
                } else {
                    break;
                }
            }


            if (sumaWartosci > maxSumaWartosci) {
                maxSumaWartosci = sumaWartosci;
            }


        }
        System.out.println(maxSumaWartosci);
    }
}