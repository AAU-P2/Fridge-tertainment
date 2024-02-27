package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavButton extends JButton {
    private int pageIndex;
    public NavButton(String title, int index) {
        setText(title);
        pageIndex = index;
        addActionListener(new NavButtonPressListener());
    }

    class NavButtonPressListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PageManager.changePage(pageIndex);
        }
    }
}
