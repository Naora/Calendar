/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.recipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractListModel;

/**
 *
 * @author Nao
 */
public class RecipeModel extends AbstractListModel<Recipe> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Recipe> recipes;
    private String filter;
    private int searchType;
    private ArrayList<Recipe> filteredRecipes;
    private Pattern regex;
    
    public static final int SEARCH_NAME = 0;
    public static final int SEARCH_TAG = 1;

    public RecipeModel() {
        recipes = new ArrayList<>();
        filteredRecipes = new ArrayList<>();
        filter = "";
        regex = Pattern.compile(filter);
    }

    public void setFilter(String filter, int searchType) {
        this.filter = filter;
        this.searchType = searchType;
        updateFilter();
        fireContentsChanged(this, 0, 0);
    }
    
    private void updateFilter() {
        filteredRecipes.clear();
        regex = Pattern.compile("^"+filter+".*$");
        
        recipes.stream().forEach((r) -> {
            Matcher m;
            // name search;
            if(searchType == SEARCH_NAME) {
                m = regex.matcher(r.getName());
                if (m.matches()) {
                    filteredRecipes.add(r);
                }
            }
            // search tags
            if(searchType == SEARCH_TAG) {
                boolean tagExists = false;
                ArrayList<String> tags = r.getTags();
                for(String t : tags) {
                    m = regex.matcher(t);
                    if(m.matches()){
                        tagExists = true;
                    }
                }
                if(tagExists){
                    filteredRecipes.add(r);
                }
            }
            
        });
        
    }
    
    public void add(Recipe r) {
        if(recipes.add(r)) {
            updateFilter();
            fireContentsChanged(this, 0, 0);
        }
    }
    
    public void delete(int i){
        Recipe r = recipes.remove(i);
        updateFilter();
        if(r != null)
            fireContentsChanged(this, 0, 0);
    }

    @Override
    public int getSize() {
        return filter.isEmpty() ?  recipes.size() : filteredRecipes.size();
    }

    @Override
    public Recipe getElementAt(int index) {
        return filter.isEmpty() ? recipes.get(index) : filteredRecipes.get(index) ;
    }
    
}
