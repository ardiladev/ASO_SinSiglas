package co.edu.udistrital.modelo;

public class Metrica {

    public int comparaciones;
    public int intercambios;
    public long tiempoEjecucion; // en nanosegundos

    private long inicio;

    public Metrica() {
        this.comparaciones = 0;
        this.intercambios = 0;
        this.tiempoEjecucion = 0;
    }

    public void reset() {
        this.comparaciones = 0;
        this.intercambios = 0;
        this.tiempoEjecucion = 0;
    }

    // === Métodos para medir tiempo ===
    public void iniciarTiempo() {
        inicio = System.nanoTime();
    }

    public void finalizarTiempo() {
        tiempoEjecucion = System.nanoTime() - inicio;
    }

    // Retornar tiempo en milisegundos
    public double getTiempoMilisegundos() {
        return tiempoEjecucion / 1_000_000.0;
    }

    @Override
    public String toString() {
        return "Comparaciones: " + comparaciones +
               ", Intercambios: " + intercambios +
               ", Tiempo: " + getTiempoMilisegundos() + " ms";
    }
}
