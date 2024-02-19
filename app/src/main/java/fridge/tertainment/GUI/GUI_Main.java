package fridge.tertainment.GUI;

import javax.swing.*;
public class GUI_Main {
    GUI_frame frame;
    public GUI_Main(){
        frame = new GUI_frame();
        JButton button = new JButton("click");
        button.setBounds(130,100,100,40);

        frame.add(button);

    }

}
