import java.util.Random;

public class MonteCarlo {
    // funkcja opisująca brzegi figury
    static double curve(double x) {
        return Math.sin(x); // -cos(x + Math.PI/2) == sin(x)
    }

    public static void main(String[] args) {
        int N = 150;                 // liczba punktów losowych
        long seed = 42L;             // dla powtarzalności (możesz usunąć/zmienić)
        Random rng = new Random(seed);

        int inside = 0;
        for (int i = 0; i < N; i++) {
            double x = rng.nextDouble(); // w [0,1)
            double y = rng.nextDouble(); // w [0,1)
            if (y <= curve(x)) {
                inside++;
            }
        }

        double estimatedArea = (double) inside / (double) N; // pole prostokąta = 1, więc stosunek = pole figury
        double exactArea = 1.0 - Math.cos(1.0);

        System.out.println("Liczba punktów: " + N);
        System.out.println("Punktów wewnątrz figury: " + inside);
        System.out.println("Przybliżone pole (Monte Carlo): " + estimatedArea);
        System.out.println("Dokładne pole (1 - cos(1)): " + exactArea);
        System.out.println("Różnica (est - dokładne): " + (estimatedArea - exactArea));
    }
}