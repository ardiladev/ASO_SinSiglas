package co.edu.udistrital.controlador;

import co.edu.udistrital.vista.PanelCandidatos;
import co.edu.udistrital.vista.PanelCandidatos;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PanelCandidatos().setVisible(true);
        });
    }
}
