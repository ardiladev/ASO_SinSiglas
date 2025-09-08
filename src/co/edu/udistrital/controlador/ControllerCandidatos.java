package co.edu.udistrital.controlador;

import co.edu.udistrital.modelo.*;
import java.util.List;

public class ControllerCandidatos {

    public String generarYOrdenar(
            int numeroCandidatos,
            int rangoMaximo,
            long semilla,
            int distribucionSeleccionada,
            int tipoOrdenamientoSeleccionado,
            int atributoSeleccionado
    ) throws Exception {

        // Se crea el generador de datos y banco
        GeneradorDatos generador = new GeneradorDatos(semilla, distribucionSeleccionada);
        BancoCandidatos banco = generador.generar(numeroCandidatos, rangoMaximo, distribucionSeleccionada);
        List<Candidato> listaCandidatos = banco.getCandidatos();

        // Crear los arreglos con atributos e índices
        int[] arregloAtributos = new int[numeroCandidatos];
        int[] arregloIndices = new int[numeroCandidatos];

        for (int i = 0; i < numeroCandidatos; i++) {
            arregloAtributos[i] = listaCandidatos.get(i).getAtributo(atributoSeleccionado);
            arregloIndices[i] = i;
        }

        // Métricas
        Metrica metricaOrdenamiento = new Metrica();
        metricaOrdenamiento.iniciarTiempo();

        // Selección de ordenamiento
        switch (tipoOrdenamientoSeleccionado) {
            case 0 ->
                Ordenamientos.burbujaIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
            case 1 ->
                Ordenamientos.insercionIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
            case 2 ->
                Ordenamientos.seleccionIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
            case 3 ->
                Ordenamientos.mergeSortIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
            case 4 ->
                Ordenamientos.quickSortIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
            default ->
                throw new IllegalArgumentException("Opción de ordenamiento no válida");
        }

        metricaOrdenamiento.finalizarTiempo();

        // Banco ordenado
        BancoCandidatos bancoOrdenado = new BancoCandidatos();
        for (int i = 0; i < numeroCandidatos; i++) {
            bancoOrdenado.agregarCandidato(listaCandidatos.get(arregloIndices[i]));
        }

        // Construir texto de salida
        StringBuilder textoSalida = new StringBuilder();
        textoSalida.append("Arreglo ordenado del atributo seleccionado:\n");
        for (int i = 0; i < numeroCandidatos; i++) {
            textoSalida.append(bancoOrdenado.getCandidatoPorId(i + 1).getAtributo(atributoSeleccionado)).append(" ");
        }
        textoSalida.append("\n\nMétricas:\n")
                .append("Comparaciones: ").append(metricaOrdenamiento.comparaciones)
                .append("\nIntercambios: ").append(metricaOrdenamiento.intercambios)
                .append("\nTiempo: ").append(metricaOrdenamiento.getTiempoMilisegundos()).append(" ms\n\n");

        textoSalida.append("Candidatos ordenados:\n");
        for (Candidato candidato : bancoOrdenado.getCandidatos()) {
            textoSalida.append(candidato).append("\n");
        }

        return textoSalida.toString();
    }
}