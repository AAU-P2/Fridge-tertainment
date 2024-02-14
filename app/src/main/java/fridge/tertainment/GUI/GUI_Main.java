package fridge.tertainment.GUI;

import javax.swing.*;

public class GUI_Main {
    JFrame frame;
    public GUI_Main(){
        frame = new JFrame();
        JButton button = new JButton("click");
        button.setBounds(130,100,100,40);

        frame.add(button);

        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
