package co.edu.udistrital.modelo;

import java.util.Random;

public class GeneradorDatos {

    private Random rand;

    public GeneradorDatos(long semilla) {
        this.rand = new Random(semilla);
    }

    public int generarID(int i) {
        return i;
    }

    public String generarNombreAleatorio() {
        String[] nombres = {"Andrea", "Camilo", "Daniel", "Jhon", "Helbert", "Heiby", "Katerine", "Daniela", "Karen", "Valentina", "Felipe"};
        String[] apellidos = {"Puerto", "Ardila", "Perez", "Ramirez", "Rodriguez", "Gavilan", "Garcia", "Quesada", "Hurtado", "Fernandez", "Contreras"};

        return nombres[rand.nextInt(nombres.length)] + " " + apellidos[rand.nextInt(apellidos.length)];
    }

    // Genera un banco de candidatos con N registros y valores aleatorios
    public BancoCandidatos generar(int n, int m) {
        BancoCandidatos banco = new BancoCandidatos();

        for (int i = 1; i <= n; i++) {
            int[] atributos = new int[5];
            for (int j = 0; j < 5; j++) {
                atributos[j] = rand.nextInt(m) + 1; // valores entre 1 y m
            }
            banco.agregarCandidato(new Candidato(generarID(i), generarNombreAleatorio(), atributos));
        }

        return banco;
    }
}
