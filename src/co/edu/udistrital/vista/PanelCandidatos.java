package co.edu.udistrital.vista;

// Clases Necesarias
import co.edu.udistrital.modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

//(JFrame)
public class PanelCandidatos extends JFrame {

    // Declaro las cajas de texto
    private JTextField campoTextoNumeroCandidatos; // número de candidatos
    private JTextField campoTextoRangoMaximo; //  rango máximo de atributos
    private JTextField campoTextoSemilla; // semilla

    // Declaro las listas desplegables (combo box)
    private JComboBox<String> comboBoxDistribucion;   // distribución de datos
    private JComboBox<String> comboBoxOrdenamiento;  // tipo de ordenamiento
    private JComboBox<String> comboBoxAtributo;      // atributo a ordenar

    // botón  del usuario
    private JButton botonGenerarYOrdenar;

    // Área de texto
    private JTextArea areaTextoResultados;

    // Constructor de la ventana
    public PanelCandidatos() {
        // Título de la ventana
        setTitle("Generador de Candidatos");
        // Tamaño fijo de la ventana
        setSize(1370, 720);
        // Que se cierre el programa cuando cierre la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel de entrada 
        JPanel panelEntrada = new JPanel(new GridLayout(7, 2, 5, 5)); // 7 filas, 2 columnas

        // campos de texto
        campoTextoNumeroCandidatos = new JTextField(); // número de candidatos
        campoTextoRangoMaximo = new JTextField(); // rango máximo
        campoTextoSemilla = new JTextField(); //  semilla

        // Inicializo los combos con opciones
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

        // componentes al panel de entrada con etiquetas
        panelEntrada.add(new JLabel("Número de candidatos:")); // Etiqueta
        panelEntrada.add(campoTextoNumeroCandidatos); // Campo de texto

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

        panelEntrada.add(new JLabel()); // Celda vacía
        panelEntrada.add(botonGenerarYOrdenar); // Agrego el botón

        // Agrego el panel de entrada a la parte superior de la ventana
        add(panelEntrada, BorderLayout.NORTH);

        // Panel de resultados
        areaTextoResultados = new JTextArea(); // Área para mostrar texto
        areaTextoResultados.setEditable(false); // El usuario no puede escribir
        JScrollPane scrollPane = new JScrollPane(areaTextoResultados);
        add(scrollPane, BorderLayout.CENTER);

        botonGenerarYOrdenar.addActionListener(e -> generarYOrdenar());
    }

    // Método que se ejecuta cuando el usuario hace clic en "Generar y Ordenar"
    private void generarYOrdenar() {
        try {
            // se leen los valores que escribió el usuario en los campos de texto
            int numeroCandidatos = Integer.parseInt(campoTextoNumeroCandidatos.getText());
            int rangoMaximo = Integer.parseInt(campoTextoRangoMaximo.getText());
            long semilla = Long.parseLong(campoTextoSemilla.getText());

            // se leen las opciones seleccionadas en los combos
            int distribucionSeleccionada = comboBoxDistribucion.getSelectedIndex();
            int tipoOrdenamientoSeleccionado = comboBoxOrdenamiento.getSelectedIndex();
            int atributoSeleccionado = comboBoxAtributo.getSelectedIndex();

            // se crea el generador de datos 
            GeneradorDatos generador = new GeneradorDatos(semilla);
            BancoCandidatos banco = generador.generar(numeroCandidatos, rangoMaximo);
            List<Candidato> listaCandidatos = banco.getCandidatos();

            // se crean los arreglos con los valores del atributo y los índices
            int[] arregloAtributos = new int[numeroCandidatos];
            int[] arregloIndices = new int[numeroCandidatos];

            for (int i = 0; i < numeroCandidatos; i++) {
                arregloAtributos[i] = listaCandidatos.get(i).getAtributo(atributoSeleccionado);
                arregloIndices[i] = i; // El índice es simplemente el orden original
            }

            // se crea el objeto que guarda las métricas
            Metrica metricaOrdenamiento = new Metrica();
            metricaOrdenamiento.iniciarTiempo(); // Cronometro arranca 

            // Seleccion de tipo de ordenamiento
            switch (tipoOrdenamientoSeleccionado) {
                case 0:
                    Ordenamientos.burbujaIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
                    break;
                case 1:
                    Ordenamientos.insercionIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
                    break;
                case 2:
                    Ordenamientos.seleccionIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
                    break;
                case 3:
                    Ordenamientos.mergeSortIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
                    break;
                case 4:
                    Ordenamientos.quickSortIndices(arregloAtributos, arregloIndices, metricaOrdenamiento);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Opción no válida.");
            }

            metricaOrdenamiento.finalizarTiempo(); // Finalizo el cronometro

            // Creo un nuevo banco de candidatos ya ordenados
            BancoCandidatos bancoOrdenado = new BancoCandidatos();
            for (int i = 0; i < numeroCandidatos; i++) {
                bancoOrdenado.agregarCandidato(listaCandidatos.get(arregloIndices[i]));
            }

            // se construye  el texto de salida para mostrar en el área de resultados
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

            //  muestra el texto en el área de resultados
            areaTextoResultados.setText(textoSalida.toString());

        } catch (Exception ex) {
            // error mensaje 
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
