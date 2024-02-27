package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
public class GUI_Main {
    GUI_frame frame;
    JLabel label;
    JButton button;
    public GUI_Main(){
        frame = new GUI_frame();
        frame.changeBackgroundColor(new Color(64, 64, 64));

        frame.setVisible(true);
    }

}
