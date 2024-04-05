package fridge.tertainment.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_Panel {
    private JPanel backgroundPanel;

    private JPanel homePage;
    private JTabbedPane tabbedPane;
    private JPanel settingsPage;
    private JPanel fridgePage;
    private JPanel recipePage;

    public GUI_Panel() {
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridLayout(1,1, 0, 0));
        backgroundPanel.setSize(1280,720);
        backgroundPanel.setPreferredSize(new Dimension(1280, 720));
        UIManager.put("TabbedPane.selected", Color.DARK_GRAY);
        tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        tabbedPane.setBackground(Color.GRAY);
        tabbedPane.setForeground(Color.WHITE);
        backgroundPanel.add(tabbedPane);

        homePage = new JPanel();
        homePage.setBackground(Color.RED);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        homePage.setLayout(layout);

        JLabel homePageTitle = new JLabel("Welcome to Fridge-tertainment");
        homePageTitle.setFont(new Font(homePageTitle.getFont().getFontName(), Font.BOLD, 25));
        homePageTitle.setVerticalAlignment(JLabel.TOP);
        homePageTitle.setHorizontalAlignment(JLabel.CENTER);
        homePageTitle.setBorder(BorderFactory.createLineBorder(Color.black));

        JScrollPane recipeList = new JScrollPane();
        JPanel scrollView = new JPanel();
        scrollView.add(new JLabel("Dette er et langt stykke tekst der gerne skulle kunne scrolles i."));
        recipeList.setViewportView(scrollView);
        recipeList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 5;

        addComponent(homePageTitle, homePage, layout, gbc, 0,0,2,1);

        gbc.weighty = 95;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.VERTICAL;
        addComponent(recipeList, homePage, layout, gbc, 1,1,1,1);


        tabbedPane.addTab("Home", homePage);


        settingsPage = new JPanel();
        settingsPage.setBackground(Color.WHITE);
        tabbedPane.addTab("Settings", settingsPage);

        initRecipePage();

    }

    private void initRecipePage() {
        recipePage = new JPanel();
        recipePage.setBackground(Color.LIGHT_GRAY);

        GridBagLayout recipeLayout = new GridBagLayout();
        recipePage.setLayout(recipeLayout);
        GridBagConstraints recipeGBC = new GridBagConstraints();
        JTable ingredients = new JTable();
        JScrollPane ingredientsScrollView = new JScrollPane();
        ingredientsScrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        ingredientsScrollView.setViewportView(ingredients);
        JLabel recipeTitle = new JLabel("View Recipe");

        recipeTitle.setVerticalAlignment(JLabel.CENTER);
        recipeTitle.setHorizontalAlignment(JLabel.CENTER);
        recipeTitle.setFont(new Font(recipeTitle.getFont().getFontName(), Font.BOLD, 25));


        recipeGBC.weighty = 0;
        recipeGBC.weightx = 1;
        recipeGBC.ipadx = 50;
        recipeGBC.ipady = 25;
        recipeGBC.fill = GridBagConstraints.NONE;
        recipeGBC.anchor = GridBagConstraints.FIRST_LINE_START;
        addComponent(recipeTitle, recipePage, recipeLayout, recipeGBC, 0,0,2,1);


        JLabel recipeDescription = new JLabel("Description here");
        recipeDescription.setHorizontalAlignment(JLabel.CENTER);
        recipeDescription.setVerticalAlignment(JLabel.CENTER);
        recipeGBC.ipady = 0;
        addComponent(recipeDescription, recipePage, recipeLayout, recipeGBC, 0,1,2,1);


        ingredientsScrollView.setViewportView(ingredients);
        recipeGBC.ipadx = 0;
        recipeGBC.ipady = 0;
        recipeGBC.fill = GridBagConstraints.VERTICAL;
        recipeGBC.weighty = 1;
        recipeGBC.weightx = 0;
        recipeGBC.anchor = GridBagConstraints.LINE_END;
        addComponent(ingredientsScrollView, recipePage, recipeLayout, recipeGBC, 1,1,1,1);


        tabbedPane.addTab("View Recipe", recipePage);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(1280,720);
        frame.setContentPane(new GUI_Panel().backgroundPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    void addComponent(Component comp, Container container, GridBagLayout layout, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;

        layout.setConstraints(comp, gbc);
        container.add(comp);

    }
}
