package co.edu.javeriana.proyectoWeb.baseDeDatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DB {
    private Map<Long, Monster> data = new HashMap<>();

    public DB() {
        ArrayList<String> category = new ArrayList<String>();
        category.add("molanisks");
        data.put(Long.valueOf(1), new Monster(1, "Molanisk", "2021-09-02", Long.valueOf(40), Long.valueOf(45), Long.valueOf(1), Long.valueOf(52), category ,"A strange mole-like being.", "https://oldschool.runescape.wiki/w/Molanisk"));
        ArrayList<String> category1 = new ArrayList<String>();
        category.add("aberrant spectres");
        data.put(Long.valueOf(2), new Monster(2, "Aberrant spectre", "2021-09-02", Long.valueOf(1), Long.valueOf(20), Long.valueOf(2), Long.valueOf(90), category1 ,"A very smelly ghost.", "https://oldschool.runescape.wiki/w/Aberrant_spectre")); 
    }

    Monster findMonster( Long key){
        return data.get(key);
    }

    Collection<Monster> findAllMonsters(){
        return data.values();
    } 

  
    


}
