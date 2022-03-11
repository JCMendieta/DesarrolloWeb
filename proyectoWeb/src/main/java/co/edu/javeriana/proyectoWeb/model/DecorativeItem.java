package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class DecorativeItem 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @ManyToMany
    @ElementCollection(targetClass = Room.class)
    List<Room> idRoom = new ArrayList<>();

    public DecorativeItem() 
    {
    }

    public DecorativeItem(String name) 
    {
        this.name = name;
    }

    public DecorativeItem(String name, List<Room> idRoom) 
    {
        this.name = name;
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

    public List<Room> getIdRoom() 
    {
        return idRoom;
    }

    public void setIdRoom(List<Room> idRoom) 
    {
        this.idRoom = idRoom;
    }
}