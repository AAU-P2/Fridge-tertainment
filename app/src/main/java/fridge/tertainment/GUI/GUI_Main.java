package fridge.tertainment.GUI;

import java.awt.Color;

public class GUI_Main {
    GUI_frame frame;

    public GUI_Main(){
        frame = new GUI_frame();
        frame.changeBackgroundColor(new Color(64, 64, 64));

        frame.setVisible(true);
    }

}
