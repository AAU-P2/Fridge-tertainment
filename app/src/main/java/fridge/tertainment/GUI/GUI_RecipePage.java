package fridge.tertainment.GUI;

import fridge.tertainment.DataBase.DTO.RecipeIngredienceDTO;
import fridge.tertainment.sqlConnector.Repository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GUI_RecipePage extends GUI_Page{

    JLabel titleLabel;
    JLabel descLabel;
    JPanel instructions;
    JList<String> ingredients;
    JScrollPane instructionsScroll;
    JScrollPane ingredientsScroll;

    Repository repo;


    GUI_RecipePage() {
        super("Recipe");
        try {
            repo = new Repository();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            titleLabel = new JLabel(repo.recipes.Get(1).name);
            titleLabel.setVerticalAlignment(JLabel.CENTER);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), Font.BOLD, 25));

            gbc.ipadx = 50;
            gbc.ipady = 25;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;

            addComponent(titleLabel, 0, 0, 2, 1, 1, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        descLabel = new JLabel("Do we even have a description?");
        descLabel.setVerticalAlignment(JLabel.CENTER);
        descLabel.setHorizontalAlignment(JLabel.CENTER);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.ipadx = 50;
        gbc.ipady = 0;
        addComponent(descLabel, 0, 1, 1, 1, 1, 0);

        try {
            JLabel instructions = new JLabel(repo.recipes.Get(1).text);
            instructions.setVerticalAlignment(JLabel.CENTER);
            instructions.setHorizontalAlignment(JLabel.CENTER);
            instructionsScroll = new JScrollPane();
            instructionsScroll.setViewportView(instructions);
            gbc.fill = GridBagConstraints.NONE;
            gbc.ipady = 50;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            addComponent(instructions, 0, 2, 1, 1, 0, 1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            ingredientsScroll = new JScrollPane();
            ingredients = new JList<String>();
            Vector<String> ingredientsVec = new Vector<String>();
            ArrayList<RecipeIngredienceDTO> RIs = repo.recipeIngrediences.GetAllById(1, null);

            for (RecipeIngredienceDTO RI : RIs) {
                ingredientsVec.add(String.format("%s %.1f %s", repo.ingrediences.Get(RI.id_ingredience).name, RI.amount, RI.type));
            }

            ingredients.setListData(ingredientsVec);
            ingredientsScroll.setViewportView(ingredients);

            gbc.ipadx = 25;
            gbc.ipady = 0;
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.anchor = GridBagConstraints.LINE_END;
            addComponent(ingredientsScroll, 1, 1, 1, 2, 0, 1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
