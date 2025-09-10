package co.edu.udistrital.modelo;

import java.util.Random;

public class Distribucion {

    private Random random;

    public Distribucion(long semilla) {
        this.random = new Random(semilla);
    }

    public int generarUniforme(int m) {
        return random.nextInt(m) + 1;
    }

    // Casi ordenado: valores cercanos al índice i
    public int generarCasiOrdenado(int i, int m) {
        int ruido = random.nextInt(3) - 1; // -1, 0 o 1
        int valor = i + ruido;
        if (valor < 1) {
            valor = 1;
        }
        if (valor > m) {
            valor = m;
        }
        return valor;
    }

        // Inverso: valores decrecientes desde m hacia 1
    public int generarInverso(int i, int m) {
        return m - i;
    }

    public int generarInverso(int i, int m, int n) {
        int paso = m / n; // cuánto decrece cada vez
        if (paso < 1) {
            paso = 1;
        }
        return m - i * paso;
    }

}
