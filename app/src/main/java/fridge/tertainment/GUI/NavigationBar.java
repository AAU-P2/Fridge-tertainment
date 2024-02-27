package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {

    private int buttonWidth = 1280 / PageManager.getPageCount();

    NavButton[] buttons = new NavButton[PageManager.getPageCount()];

    public NavigationBar() {
        setLayout(null);
        setSize(1280, 30);
        setBackground(new Color(128, 128, 128));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new NavButton(PageManager.pages[i].pageTitle, i);
            buttons[i].setBounds(buttonWidth*i, 0, buttonWidth, 30);
            buttons[i].setBackground(new Color(128,128,128));
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.add(buttons[i]);
        }

    }
}
