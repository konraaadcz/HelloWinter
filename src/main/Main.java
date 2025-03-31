
package main;

import javax.swing.*;
import gui.SnowAnimationFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnowAnimationFrame frame = new SnowAnimationFrame();
            frame.setVisible(true);
        });
    }
}
