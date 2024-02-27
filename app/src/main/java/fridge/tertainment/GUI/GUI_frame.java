package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_frame extends JFrame {

    protected NavigationBar navBar;

    GUI_page currentPage;

    GUI_frame() {
        currentPage = PageManager.pages[0];
        this.add(currentPage);
        this.setTitle("Fridge-tertainment");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        navBar = new NavigationBar();
        this.add(navBar);
        this.setLayout(null);
        PageManager.setGUI_Frame(this);
    }

    public void changeBackgroundColor(Color c) {
        this.getContentPane().setBackground(c);
    }

    public void redrawFrame() {
        repaint();
        revalidate();
    }

    public void setCurrentPage(GUI_page newPage) {
        this.remove(currentPage);
        currentPage = newPage;
        this.add(currentPage);
        redrawFrame();
    }


}
