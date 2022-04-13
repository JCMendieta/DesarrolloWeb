package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Room 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToMany
    List<Item> rItems = new ArrayList<>();

    @ManyToMany
    List<DecorativeItem> idDecorativeItem = new ArrayList<>();
    
    @OneToOne
    Monster rMonster;

    @OneToMany(mappedBy = "idFRoom")
    List<Exit> rExits = new ArrayList<>();

    @OneToMany(mappedBy = "idRoom")
    List<Player> rPlayers = new ArrayList<>();

    public Room() 
    {
    }

    public Room(Monster rMonster) 
    {
        this.rMonster = rMonster;
    }
    
    public Room(List<Exit> rExits) 
    {
        this.rExits = rExits;
    }

    public Room(List<DecorativeItem> idDecorativeItem, Monster rMonster) 
    {
        this.idDecorativeItem = idDecorativeItem;
        this.rMonster = rMonster;
    }

    public Room(List<Item> rItems, List<DecorativeItem> idDecorativeItem, Monster rMonster, List<Exit> rExits,
            List<Player> rPlayers) 
    {
        this.rItems = rItems;
        this.idDecorativeItem = idDecorativeItem;
        this.rMonster = rMonster;
        this.rExits = rExits;
        this.rPlayers = rPlayers;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    @JsonManagedReference
    public List<Item> getrItems() 
    {
        return rItems;
    }

    public void setrItems(List<Item> rItems) 
    {
        this.rItems = rItems;
    }

    @JsonManagedReference
    public List<DecorativeItem> getIdDecorativeItem() 
    {
        return idDecorativeItem;
    }

    public void setIdDecorativeItem(List<DecorativeItem> idDecorativeItem) 
    {
        this.idDecorativeItem = idDecorativeItem;
    }

    public Monster getrMonster() 
    {
        return rMonster;
    }

    public void setrMonster(Monster rMonster) 
    {
        this.rMonster = rMonster;
    }

    @JsonManagedReference
    public List<Exit> getrExits()
    {
        return rExits;
    }

    public void setrExits(List<Exit> rExits) 
    {
        this.rExits = rExits;
    }

    @JsonManagedReference
    public List<Player> getrPlayers() 
    {
        return rPlayers;
    }

    public void setrPlayers(List<Player> rPlayers) 
    {
        this.rPlayers = rPlayers;
    }

    public void unlinkRoomAttributes()
    {
        if(getrMonster()!= null)
        {
            getrMonster().setIdRoom(null);
            setrMonster(null);
        } 

        getIdDecorativeItem().removeAll(idDecorativeItem);
        getrExits().removeAll(rExits);
        getrItems().removeAll(rItems);
        getrPlayers().removeAll(rPlayers);
    }
}