package co.edu.javeriana.proyectoWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DecorativeItem 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @ManyToOne
    Room room;

    public DecorativeItem() 
    {

    }

    public DecorativeItem(String name) 
    {  
        this.name = name;  
    }

    public long getId() 
    {
        return id;
    }

    public void setId(long id) 
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Room getRoomDI() 
    {
        return room;
    }

    public void setRoomDI(Room roomDI) 
    {
        this.room = roomDI;
    }
}
