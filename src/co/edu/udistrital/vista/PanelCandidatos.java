package co.edu.udistrital.vista;

import co.edu.udistrital.controlador.ControllerCandidatos;
import javax.swing.*;
import java.awt.*;

public class PanelCandidatos extends JFrame {

    private JTextField campoTextoNumeroCandidatos;
    private JTextField campoTextoRangoMaximo;
    private JTextField campoTextoSemilla;

    private JComboBox<String> comboBoxDistribucion;
    private JComboBox<String> comboBoxOrdenamiento;
    private JComboBox<String> comboBoxAtributo;

    private JButton botonGenerarYOrdenar;
    private JTextArea areaTextoResultados;

    private ControllerCandidatos controller; // referencia al controlador

    public PanelCandidatos() {
        setTitle("Generador de Candidatos");
        setSize(1370, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controller = new ControllerCandidatos();

        // Panel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(7, 2, 5, 5));

        campoTextoNumeroCandidatos = new JTextField();
        campoTextoRangoMaximo = new JTextField();
        campoTextoSemilla = new JTextField();

        comboBoxDistribucion = new JComboBox<>(new String[]{
            "Aleatoria uniforme", "Semi ordenada", "Orden inverso"
        });

        comboBoxOrdenamiento = new JComboBox<>(new String[]{
            "Burbuja", "Inserción", "Selección", "Merge", "Quick"
        });

        comboBoxAtributo = new JComboBox<>(new String[]{
            "Distancia Marchas", "Horas Perdidas", "Valor Prebendas",
            "Número Sobornos", "Valor Corrupción"
        });

        botonGenerarYOrdenar = new JButton("Generar y Ordenar");

        panelEntrada.add(new JLabel("Número de candidatos:"));
        panelEntrada.add(campoTextoNumeroCandidatos);

        panelEntrada.add(new JLabel("Rango máximo de atributos (m):"));
        panelEntrada.add(campoTextoRangoMaximo);

        panelEntrada.add(new JLabel("Semilla:"));
        panelEntrada.add(campoTextoSemilla);

        panelEntrada.add(new JLabel("Distribución:"));
        panelEntrada.add(comboBoxDistribucion);

        panelEntrada.add(new JLabel("Tipo de ordenamiento:"));
        panelEntrada.add(comboBoxOrdenamiento);

        panelEntrada.add(new JLabel("Atributo a ordenar:"));
        panelEntrada.add(comboBoxAtributo);

        panelEntrada.add(new JLabel());
        panelEntrada.add(botonGenerarYOrdenar);

        add(panelEntrada, BorderLayout.NORTH);

        areaTextoResultados = new JTextArea();
        areaTextoResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTextoResultados);
        add(scrollPane, BorderLayout.CENTER);

        // Evento del botón
        botonGenerarYOrdenar.addActionListener(e -> ejecutarAccion());
    }

    private void ejecutarAccion() {
        try {
            int numeroCandidatos = Integer.parseInt(campoTextoNumeroCandidatos.getText());
            int rangoMaximo = Integer.parseInt(campoTextoRangoMaximo.getText());
            long semilla = Long.parseLong(campoTextoSemilla.getText());
            int distribucionSeleccionada = comboBoxDistribucion.getSelectedIndex();
            int tipoOrdenamientoSeleccionado = comboBoxOrdenamiento.getSelectedIndex();
            int atributoSeleccionado = comboBoxAtributo.getSelectedIndex();

            String resultado = controller.generarYOrdenar(
                    numeroCandidatos,
                    rangoMaximo,
                    semilla,
                    distribucionSeleccionada,
                    tipoOrdenamientoSeleccionado,
                    atributoSeleccionado
            );

            areaTextoResultados.setText(resultado);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
