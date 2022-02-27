package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Monster 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    String last_updated;
    Long attack_level;
    Long defence_slash;
    Long size;
    Long hitpoints;

    @ElementCollection(targetClass = String.class)
    List<String> category = new ArrayList<>();
    
    String examine;
    String wiki_url;

    @OneToOne
    Room idRoom;

    public Monster()
    {

    }

    public Monster(String name, Long size) 
    {
        this.name = name;
        this.size = size;
    }

    public Monster(String name, String last_updated, Long attack_level, Long defence_slash, Long size, Long hitpoints,
            List<String> category, String examine, String wiki_url, Room idRoom) 
    {
        this.name = name;
        this.last_updated = last_updated;
        this.attack_level = attack_level;
        this.defence_slash = defence_slash;
        this.size = size;
        this.hitpoints = hitpoints;
        this.category = category;
        this.examine = examine;
        this.wiki_url = wiki_url;
        this.idRoom = idRoom;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getLast_updated() 
    {
        return last_updated;
    }

    public void setLast_updated(String last_updated) 
    {
        this.last_updated = last_updated;
    }

    public Long getAttack_level() 
    {
        return attack_level;
    }

    public void setAttack_level(Long attack_level) 
    {
        this.attack_level = attack_level;
    }

    public Long getDefence_slash() 
    {
        return defence_slash;
    }

    public void setDefence_slash(Long defence_slash) 
    {
        this.defence_slash = defence_slash;
    }

    public Long getSize() 
    {
        return size;
    }

    public void setSize(Long size) 
    {
        this.size = size;
    }

    public Long getHitpoints() 
    {
        return hitpoints;
    }

    public void setHitpoints(Long hitpoints) 
    {
        this.hitpoints = hitpoints;
    }

    public List<String> getCategory() 
    {
        return category;
    }

    public void setCategory(List<String> category) 
    {
        this.category = category;
    }

    public String getExamine() 
    {
        return examine;
    }

    public void setExamine(String examine) 
    {
        this.examine = examine;
    }

    public String getWiki_url() {
        return wiki_url;
    }

    public void setWiki_url(String wiki_url) 
    {
        this.wiki_url = wiki_url;
    }

    public Room getIdRoom() 
    {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) 
    {
        this.idRoom = idRoom;
    }
}