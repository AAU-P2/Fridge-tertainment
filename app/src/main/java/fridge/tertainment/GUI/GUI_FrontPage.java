package fridge.tertainment.GUI;

import fridge.tertainment.DataBase.DTO.IngredienceDTO;
import fridge.tertainment.DataBase.DTO.RecipeDTO;
import fridge.tertainment.DataBase.Models.Recipe;
import fridge.tertainment.DataBase.Models.RecipeIngredience;
import fridge.tertainment.sqlConnector.Repository;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class GUI_FrontPage extends GUI_Page{

    JLabel titleLabel;
    JList<String> recipeList;
    JScrollPane recipeListScrollPane;
    JLabel recipeFilterLabel;
    JButton filterRecipiesButton;
    JList<IngredienceDTO> ingredientsList;

    Repository repo;

    GUI_FrontPage() throws SQLException{
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
        
        IngredienceDTO[] ingredients = new IngredienceDTO[]{};
            ingredients = (IngredienceDTO[]) repo.ingrediences.GetAll().toArray(ingredients);

        JList<IngredienceDTO> ingredientsList = new JList<IngredienceDTO>(ingredients);

        ingredientsList.setCellRenderer(new DefaultListCellRenderer(){
            JLabel rv = new JLabel();
            @Override
            public Component getListCellRendererComponent(JList ingredientsList, Object value, int index, boolean isSelected, boolean hasCellFocus){
                String s = (value != null && value instanceof IngredienceDTO)? ((IngredienceDTO)value).name:"";
                rv.setText(s);
                if (isSelected) {
                    rv.setBackground(ingredientsList.getSelectionBackground());
                    rv.setForeground(ingredientsList.getSelectionForeground());
                } else {
                    rv.setBackground(ingredientsList.getBackground());
                    rv.setForeground(ingredientsList.getForeground());
                }
                rv.setEnabled(ingredientsList.isEnabled());
                rv.setFont(ingredientsList.getFont());
                rv.setOpaque(true);
                return rv;
            }
        });

        JPanel ingredientsScrollView = new JPanel();
        JScrollPane ingredientsListScrollPane = new JScrollPane();

        ingredientsScrollView.add(ingredientsList);
        ingredientsListScrollPane.setViewportView(ingredientsScrollView);
        ingredientsListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        ingredientsList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                IngredienceDTO selectedValue = (IngredienceDTO)((JList)lse.getSource()).getSelectedValue();
                //now you can do something with the IngredienceDTO Object that was just selected
                
        }});

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        addComponent(ingredientsListScrollPane, 0, 2, 1, 2, 1, 5);
        
        recipeFilterLabel = new JLabel("Filter Recipies based on Selected Ingredient");
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.ipady = 5;
        addComponent(recipeFilterLabel, 0, 1, 1, 1, 1, 1);

        recipeListScrollPane = new JScrollPane();
        recipeListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel recipeScrollView = new JPanel();

        RecipeDTO[] recipies = new RecipeDTO[]{};
            recipies = (RecipeDTO[]) repo.recipes.GetAll().toArray(recipies);

        JList<RecipeDTO> recipeList = new JList<RecipeDTO>(recipies);

        recipeList.setCellRenderer(new DefaultListCellRenderer(){
            JLabel rv = new JLabel();
            @Override
            public Component getListCellRendererComponent (JList recipeList, Object value, int index, boolean isSelected, boolean hasCellFocus){
                String s = (value != null && value instanceof RecipeDTO)? ((RecipeDTO)value).name:"";
                rv.setText(s);
                rv.setEnabled(true);
                rv.setFont(recipeList.getFont());
                rv.setOpaque(true);
                return rv;
            }

        });


            recipeScrollView.add(recipeList);
            recipeListScrollPane.setViewportView(recipeScrollView);

            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.fill = GridBagConstraints.VERTICAL;
            addComponent(recipeListScrollPane, 3, 1, 1, 4, 0, 95);

        

    }
}
