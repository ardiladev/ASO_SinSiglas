package co.edu.udistrital.modelo;

import java.util.Random;

public class Distribucion {

    private Random random;

    public Distribucion(long semilla) {
        this.random = new Random(semilla);
    }

    public int[] generarUniforme(int m) {
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = 1 + random.nextInt(m);
        }
        return arr;
    }

    public int[] generarCasiOrdenado(int m) {
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = i + random.nextInt(10);
        }
        return arr;
    }

    public int[] generarInverso(int m) {
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = m - i;
        }
        return arr;
    }
}
