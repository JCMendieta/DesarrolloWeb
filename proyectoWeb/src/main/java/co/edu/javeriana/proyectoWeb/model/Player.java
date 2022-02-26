package co.edu.javeriana.proyectoWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    String last_updated;
    Long attack_level;
    Long defence_slash;
    Long size;
    Long hitpoints;
    //ArrayList<String> category;
    //String examine;
    //String wiki_url;
    @ManyToOne
    Room room;

    public Player() 
    {

    }

    public Player(String name, String last_updated, Long attack_level, Long defence_slash, Long size, Long hitpoints)
    {
        this.name = name;
        this.last_updated = last_updated;
        this.attack_level = attack_level;
        this.defence_slash = defence_slash;
        this.size = size;
        this.hitpoints = hitpoints;
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

    public Room getRoomP() 
    {
        return room;
    }

    public void setRoomP(Room roomP) 
    {
        this.room = roomP;
    }
}