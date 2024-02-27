package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutTestPage extends GUI_page{
    GridBagLayoutTestPage(String title) {
        super(title);
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        JLabel label = new JLabel("Dette er en test");
        JLabel label2 = new JLabel("Test2");
        JLabel label3 = new JLabel("Test3");
        label.setBackground(Color.BLUE);
        label.setOpaque(true);

        label2.setBackground(Color.RED);
        label2.setOpaque(true);

        label3.setBackground(Color.GREEN);
        label3.setOpaque(true);
        add(label, constraints);
        add(label2);
        add(label3);

    }
}
