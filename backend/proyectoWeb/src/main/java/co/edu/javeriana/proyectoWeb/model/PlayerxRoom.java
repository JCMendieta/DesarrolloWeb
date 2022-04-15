package co.edu.javeriana.proyectoWeb.model;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class PlayerxRoom 
{
    @Id
    @GeneratedValue
    Long id;

    @OneToMany(mappedBy = "idPlayerxRoom")
    List<Player> rPlayers = new ArrayList<>();

    @OneToMany(mappedBy = "idPlayerxRoom")
    List<Room> rooms = new ArrayList<>();

    public PlayerxRoom() 
    {

    }

    @JsonBackReference
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public PlayerxRoom(List<Player> rPlayers) 
    {
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

    @JsonBackReference
    public List<Player> getrPlayers() 
    {
        return rPlayers;
    }

    public void setrPlayers(List<Player> rPlayers) 
    {
        this.rPlayers = rPlayers;
    } 
}
