package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GUI_ContentPane extends JPanel {

    JTabbedPane tabbedPane;
    GUI_ContentPane() throws SQLException {

        this.setLayout(new GridLayout(1, 1, 0,0));
        this.setSize(1280,720);
        this.setPreferredSize(new Dimension(1280, 720));
        UIManager.put("TabbedPane.selected", Color.DARK_GRAY);
        initTabbedPane();
        this.add(tabbedPane);
    }

    void initTabbedPane() throws SQLException {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.GRAY);
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFocusable(false);

        GUI_FrontPage frontPage = new GUI_FrontPage();
        tabbedPane.addTab(frontPage.title, frontPage);

        GUI_RecipePage recipePage = new GUI_RecipePage();
        tabbedPane.addTab(recipePage.title, recipePage);

    }
}
