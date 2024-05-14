package fridge.tertainment.GUI;

import java.sql.SQLException;

import javax.swing.*;

public class GUI_entry {
    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("Fridge-tertainment");
        frame.setResizable(false);
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setContentPane(new GUI_ContentPane());

    }
}
