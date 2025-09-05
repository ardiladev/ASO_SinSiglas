package co.edu.udistrital.modelo;

import java.util.ArrayList;
import java.util.List;

public class BancoCandidatos {

    private List<Candidato> candidatos;

    public BancoCandidatos() {
        candidatos = new ArrayList<>();
    }

    // Agregar un candidato al banco
    public void agregarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    // Devolver todos los candidatos
    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    // Obtener un candidato específico por ID
    public Candidato getCandidatoPorId(int id) {
        for (Candidato c : candidatos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // si no existe
    }

    // Tamaño del banco
    public int size() {
        return candidatos.size();
    }
}
