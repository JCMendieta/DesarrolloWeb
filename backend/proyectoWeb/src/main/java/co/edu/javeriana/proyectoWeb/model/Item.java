package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    String last_updated;
    Long cost;
    Long weight;
    String examine;
    String wiki_url;

    @JsonIgnore
    @ManyToMany(mappedBy = "rItems")
    List<Room> idRoom = new ArrayList<>();

    @ManyToOne
    Player idPlayer;

    public Item() 
    {
    }

    public Item(String name) 
    {
        this.name = name;
    }

    public Item(String name, String last_updated, Long cost, Long weight, String examine, String wiki_url,
            List<Room> idRoom, Player idPlayer) 
    {
        this.name = name;
        this.last_updated = last_updated;
        this.cost = cost;
        this.weight = weight;
        this.examine = examine;
        this.wiki_url = wiki_url;
        this.idRoom = idRoom;
        this.idPlayer = idPlayer;
    }
    public Item(String name, String last_updated, Long cost, Long weight, String examine, String wiki_url) 
    {
        this.name = name;
        this.last_updated = last_updated;
        this.cost = cost;
        this.weight = weight;
        this.examine = examine;
        this.wiki_url = wiki_url;
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

    public Long getCost() 
    {
        return cost;
    }

    public void setCost(Long cost) 
    {
        this.cost = cost;
    }

    public Long getWeight() 
    {
        return weight;
    }

    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public String getExamine() 
    {
        return examine;
    }

    public void setExamine(String examine) 
    {
        this.examine = examine;
    }

    public String getWiki_url() 
    {
        return wiki_url;
    }

    public void setWiki_url(String wiki_url) 
    {
        this.wiki_url = wiki_url;
    }

    public List<Room> getIdRoom() 
    {
        return idRoom;
    }

    public void setIdRoom(List<Room> idRoom) 
    {
        this.idRoom = idRoom;
    }

    public Player getIdPlayer() 
    {
        return idPlayer;
    }

    public void setIdPlayer(Player idPlayer) 
    {
        this.idPlayer = idPlayer;
    }

    public void unlinkRoomItem(Room r){
        this.idRoom.remove(r);
        r.getrItems().remove(this);
    }
}