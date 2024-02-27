package fridge.tertainment.GUI;

import javax.swing.*;

public class GUI_page extends JPanel {

    JLabel testLabel;
    public String pageTitle;
    public GUI_page(String title) {
        setBounds(0,30, 1280, 720);
        pageTitle = title;
        testLabel = new JLabel();
        testLabel.setText(title);
        add(testLabel);
        setVisible(true);
        setOpaque(true);
    }
}
