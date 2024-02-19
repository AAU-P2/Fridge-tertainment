package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_frame extends JFrame {
    GUI_frame() {
        this.setTitle("Fridge-tertainment");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setVisible(true);

    }

    public void changeBackgroundColor(Color c) {
        this.getContentPane().setBackground(c);
    }

}
