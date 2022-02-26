package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Room 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "room")
    @ElementCollection(targetClass = Item.class)
    List <Item> rItems = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    @ElementCollection(targetClass = DecorativeItem.class)
    List <DecorativeItem> rDecorativeItems = new ArrayList<>();
    
    @OneToOne
    Monster rMonster;

    //@ManyToMany
    @ElementCollection(targetClass = Room.class)
    List<Room> rExits = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    @ElementCollection(targetClass = Player.class)
    List <Player> rPlayers = new ArrayList<>();

    public Room() 
    {

    }

    public Room(Monster rMonster) 
    {
        this.rMonster = rMonster;
    }

    public Room(ArrayList<Item> rItems, ArrayList<DecorativeItem> rDecorativeItems, Monster rMonster,
            ArrayList<Room> rExits, ArrayList<Player> rPlayers) 
    {
        this.rItems = rItems;
        this.rDecorativeItems = rDecorativeItems;
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

    public Monster getrMonster()
    {
        return rMonster;
    }

    public void setrMonster(Monster rMonster) 
    {
        this.rMonster = rMonster;
    }

    public List<Item> getrItems() 
    {
        return rItems;
    }

    public void setrItems(List<Item> rItems) 
    {
        this.rItems = rItems;
    }

    public List<DecorativeItem> getrDecorativeItems() 
    {
        return rDecorativeItems;
    }

    public void setrDecorativeItems(List<DecorativeItem> rDecorativeItems) 
    {
        this.rDecorativeItems = rDecorativeItems;
    }

    public List<Room> getrExits() 
    {
        return rExits;
    }

    public void setrExits(List<Room> rExits) 
    {
        this.rExits = rExits;
    }

    public List<Player> getrPlayers() 
    {
        return rPlayers;
    }

    public void setrPlayers(List<Player> rPlayers) 
    {
        this.rPlayers = rPlayers;
    }
}