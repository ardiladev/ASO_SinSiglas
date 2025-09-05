package co.edu.udistrital.modelo;

public class Candidato {

    private int id;
    private String nombre;
    private int[] atributos; // arreglo de 5 atributos: [distancia, horas, prebendas, sobornos, corrupcion]

    public Candidato(int id, String nombre, int[] atributos) {
        this.id = id;
        this.nombre = nombre;
        this.atributos = atributos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getAtributos() {
        return atributos;
    }

    // Método para obtener un atributo específico
    public int getAtributo(int index) {
        if (index >= 0 && index < atributos.length) {
            return atributos[index];
        }
        throw new IndexOutOfBoundsException("Índice de atributo inválido");
    }

    // Para imprimir el candidato
    @Override
    public String toString() {
        return "ID: " + id
                + " | Nombre: " + nombre
                + " | Atributos: [" + atributos[0] + ", " + atributos[1] + ", "
                + atributos[2] + ", " + atributos[3] + ", " + atributos[4] + "]";
    }
}
