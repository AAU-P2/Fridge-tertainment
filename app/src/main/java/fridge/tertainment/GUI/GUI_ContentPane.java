package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_ContentPane extends JPanel {

    JTabbedPane tabbedPane;
    GUI_ContentPane() {

        this.setLayout(new GridLayout(1, 1, 0,0));
        this.setSize(1280,720);
        UIManager.put("TabbedPane.selected", Color.DARK_GRAY);
        initTabbedPane();
        this.add(tabbedPane);
    }

    void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.GRAY);
        tabbedPane.setForeground(Color.WHITE);

        GUI_RecipePage recipePage = new GUI_RecipePage();
        tabbedPane.addTab(recipePage.title, recipePage);
    }
}
