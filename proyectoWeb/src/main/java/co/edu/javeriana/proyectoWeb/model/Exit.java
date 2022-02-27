package co.edu.javeriana.proyectoWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

    public Exit(Room idFRoom, Room idSRoom) 
    {
        this.idFRoom = idFRoom;
        this.idSRoom = idSRoom;
    }
}