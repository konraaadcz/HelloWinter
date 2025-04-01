
package main;

import javax.swing.*;
import gui.SnowA;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnowA fr = new SnowA();
            fr.setVisible(true);
        });
    }
}
