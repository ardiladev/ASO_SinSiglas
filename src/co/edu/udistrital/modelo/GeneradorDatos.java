package co.edu.udistrital.modelo;

import java.util.Random;

public class GeneradorDatos {

    private Random rand;
    private int tipoDistribucion;
    private Distribucion distribucion; //Objeto para generar distribuciones 

    public GeneradorDatos(long semilla, int distribucionSeleccionada) {
        this.rand = new Random(semilla);
        this.tipoDistribucion = distribucionSeleccionada;
        this.distribucion = new Distribucion(semilla); // se trae la misma semilla 
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
    public BancoCandidatos generar(int n, int m, int tipoDistribucion) {
        BancoCandidatos banco = new BancoCandidatos();
        Distribucion distribucion = new Distribucion(rand.nextLong()); // nueva cada ejecución

        if (n <= 0) {
            return banco;
        }
        if (m <= 0) {
            m = 1;  // Evita la división por cero
        }

        for (int i = 1; i <= n; i++) {
            int[] atributos = new int[5];

            for (int j = 0; j < atributos.length; j++) {
                switch (tipoDistribucion) {
                    case 0: // Uniforme
                        atributos[j] = distribucion.generarUniforme(m);
                        break;
                    case 1: // Casi ordenado
                        atributos[j] = distribucion.generarCasiOrdenado(i, m); // usa 
                        break;
                    case 2: // Inverso
                        atributos[j] = distribucion.generarInverso(i, m); // usa 
                        break;
                    default:
                        atributos[j] = distribucion.generarUniforme(m);
                        break;
                }

            }

            // Aquí sí agregamos el candidato al banco
            banco.agregarCandidato(
                    new Candidato(generarID(i), generarNombreAleatorio(), atributos)
            );
        }

        return banco;
    }
}
