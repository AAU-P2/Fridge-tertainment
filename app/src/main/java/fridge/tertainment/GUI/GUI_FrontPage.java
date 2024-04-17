package fridge.tertainment.GUI;

import fridge.tertainment.DataBase.DTO.RecipeDTO;
import fridge.tertainment.sqlConnector.Repository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GUI_FrontPage extends GUI_Page{

    JLabel titleLabel;
    JList<String> recipeList;
    JScrollPane recipeListScrollPane;

    Repository repo;


    GUI_FrontPage() {
        super("Home Page");

        try {
            repo = new Repository();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        titleLabel = new JLabel("Welcome to Fridge-tertainment");
        titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), Font.BOLD, 25));
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(titleLabel, 0, 0, 0, 1, 1, 5);

        recipeListScrollPane = new JScrollPane();
        recipeListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel scrollView = new JPanel();
        Vector<String> r = new Vector<String>();

        try {
            ArrayList<RecipeDTO> recipes = repo.recipes.GetAll();

            for (RecipeDTO recipe : recipes) {
                r.add(recipe.name);
            }

            recipeList = new JList<>(r);
            scrollView.add(recipeList);
            recipeListScrollPane.setViewportView(scrollView);

            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.fill = GridBagConstraints.VERTICAL;
            addComponent(recipeListScrollPane, 1, 1, 1, 1, 0, 95);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
