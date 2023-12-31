/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.services.filters.BlueprintFilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
@Component
public class BlueprintsServices {

    @Autowired
    BlueprintsPersistence bpp;

    @Autowired
    BlueprintFilter flt;
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints(){
        try {
            return bpp.getAllBlueprints();
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        return bpp.getBlueprint(author, name);
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public List<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return bpp.getBlueprintsByAuthor(author);
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public Set<Blueprint> getAllFilteredBlueprints(){
        try {
            Set<Blueprint> rawSet = bpp.getAllBlueprints();
            Set<Blueprint> filteredSet = new HashSet<>();
            for(Blueprint bp : rawSet){
                filteredSet.add(flt.filterBlueprint(bp));
            }
            return filteredSet;
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getFilteredBlueprint(String author,String name) throws BlueprintNotFoundException{
        return flt.filterBlueprint(bpp.getBlueprint(author, name));
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public List<Blueprint> getFilteredBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        List<Blueprint> rawList = bpp.getBlueprintsByAuthor(author);
        ArrayList<Blueprint> filteredList = new ArrayList<>();
        for(Blueprint bp : rawList){
            filteredList.add(flt.filterBlueprint(bp));
        }
        return filteredList;
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
}
