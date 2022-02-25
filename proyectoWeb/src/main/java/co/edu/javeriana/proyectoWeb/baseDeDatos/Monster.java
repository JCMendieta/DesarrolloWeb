package co.edu.javeriana.proyectoWeb.baseDeDatos;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
@Controller
public class Monster {
    long id;
    String name;
    String last_updated;
    Long attack_level;
    long defence_slash;
    Long size;
    Long hitpoints;
    ArrayList<String> category;
    String examine;
    String wiki_url;

    

    public Monster() {
    }

    public Monster(long id, String name, String last_updated, Long attack_level, long defence_slash, Long size,
            Long hitpoints, ArrayList<String> category, String examine, String wiki_url) {
        this.id = id;
        this.name = name;
        this.last_updated = last_updated;
        this.attack_level = attack_level;
        this.defence_slash = defence_slash;
        this.size = size;
        this.hitpoints = hitpoints;
        this.category = category;
        this.examine = examine;
        this.wiki_url = wiki_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Long getAttack_level() {
        return attack_level;
    }

    public void setAttack_level(Long attack_level) {
        this.attack_level = attack_level;
    }

    public long getDefence_slash() {
        return defence_slash;
    }

    public void setDefence_slash(long defence_slash) {
        this.defence_slash = defence_slash;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Long hitpoints) {
        this.hitpoints = hitpoints;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getWiki_url() {
        return wiki_url;
    }

    public void setWiki_url(String wiki_url) {
        this.wiki_url = wiki_url;
    }

    


}
