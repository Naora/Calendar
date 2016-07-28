/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.dnd;

import calendar.naora.recipe.Recipe;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author u139279
 */
public class RecipeTransferable implements Transferable {

    public static final DataFlavor RECIPE_FLAVOR = new DataFlavor(Recipe.class, "java/Recipe");
    private Recipe recipe;
    
    public RecipeTransferable(Recipe recipe){
        this.recipe = recipe;
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{RECIPE_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(RECIPE_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return recipe;
    }
}
