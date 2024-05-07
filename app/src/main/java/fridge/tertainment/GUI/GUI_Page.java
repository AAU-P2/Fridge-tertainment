package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_Page extends JPanel {

    String title;
    GridBagLayout layout;
    GridBagConstraints gbc;

    GUI_Page(String title) {
        this.title = title;
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(layout);
        setBackground(Color.LIGHT_GRAY);
    }

    public void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        layout.setConstraints(component, gbc);
        add(component);
    }

}
