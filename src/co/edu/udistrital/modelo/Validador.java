package co.edu.udistrital.modelo;

public class Validador {

    // Verifica orden ascendente
    public static boolean verificarOrdenAsc(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    // Verifica orden descendente
    public static boolean verificarOrdenDesc(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
