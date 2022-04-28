package co.edu.javeriana.proyectoWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Exit 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    Room idFRoom;

    @OneToOne
    Room idSRoom;

    public Exit() 
    {

    }

    public Exit(Room idSRoom) 
    {
        this.idSRoom = idSRoom;
    }

    public Exit(Room idFRoom, Room idSRoom) 
    {
        this.idFRoom = idFRoom;
        this.idSRoom = idSRoom;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    @JsonBackReference(value = "exit-list")
    public Room getIdFRoom() 
    {
        return idFRoom;
    }

    public void setIdFRoom(Room idFRoom) 
    {
        this.idFRoom = idFRoom;
    }
    
    @JsonBackReference
    public Room getIdSRoom() 
    {
        return idSRoom;
    }

    public void setIdSRoom(Room idSRoom)
    {
        this.idSRoom = idSRoom;
    }

    public void unlinkRoomExit(Room r)
    {
        if(idFRoom!= null){
            this.idFRoom=null;
        }
        if(idSRoom!=null){
            this.idSRoom=null;
        }        
    }

}