/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.recipe;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractListModel;

/**
 *
 * @author Nao
 */
public class RecipeModel extends AbstractListModel<Recipe> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private String filter = "";
    private int searchType;
    private ArrayList<Recipe> filteredRecipes = new ArrayList<>();
    private Pattern regex = Pattern.compile(filter);
    private transient ActionListener saveListener;
    
    //last delete element
    private Recipe recipeDeleted = null;
    private Recipe recipeUpdated = null;
    
    //comparator
    private RecipeComparator comparator = new RecipeComparator();
    
    public static final int SEARCH_NAME = 0;
    public static final int SEARCH_TAG = 1;
    
    private class RecipeComparator implements Comparator<Recipe>, Serializable{
        
        private static final long serialVersionUID = 1L;
        
        @Override
        public int compare(Recipe o1, Recipe o2) {
            return o1.compareTo(o2);
        }
        
    }

    public RecipeModel() {
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
            recipes.sort(comparator);
            updateFilter();
            fireContentsChanged(this, 0, 0);
            saveListener.actionPerformed(null);
        }
    }
    
    public void addSaveListener(ActionListener l) {
        saveListener = l;
    }
    
    public void delete(int index){
        recipeDeleted = filter.isEmpty() ? recipes.get(index) : filteredRecipes.get(index);
        recipes.remove(recipeDeleted);
        updateFilter();
        if(recipeDeleted != null)
            fireIntervalRemoved(this, index, index);        
        saveListener.actionPerformed(null);
    }

    @Override
    public int getSize() {
        return filter.isEmpty() ?  recipes.size() : filteredRecipes.size();
    }

    @Override
    public Recipe getElementAt(int index) {
        return filter.isEmpty() ? recipes.get(index) : filteredRecipes.get(index);
    }
    
    public void recipeUpdated(int index){
        recipeUpdated = filter.isEmpty() ? recipes.get(index) : filteredRecipes.get(index);
        fireContentsChanged(this, index, index);        
        saveListener.actionPerformed(null);
    }
    
    public Recipe getUpdatedRecipe(){
        return recipeUpdated;
    }
    
    public Recipe getDeletedRecipe(){
        return recipeDeleted;
    }
}
