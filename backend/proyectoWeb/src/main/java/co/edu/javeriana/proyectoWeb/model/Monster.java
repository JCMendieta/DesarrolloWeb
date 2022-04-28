package co.edu.javeriana.proyectoWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Monster
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long hitpoints;

    @JsonIgnore
    @OneToOne
    Room idRoom;

    @ManyToOne
    MonsterType idMonsterType;

    public Monster()
    {
    }

    public Monster(Long hitpoints) 
    {
        this.hitpoints = hitpoints;
    }

    public Monster(Long hitpoints, Room idRoom) 
    {
        this.hitpoints = hitpoints;
        this.idRoom = idRoom;
    }

    public Monster(Long hitpoints, MonsterType idMonsterType) 
    {
        this.hitpoints = hitpoints;
        this.idMonsterType = idMonsterType;
    }

    public Monster(Long hitpoints, Room idRoom, MonsterType idMonsterType) 
    {
        this.hitpoints = hitpoints;
        this.idRoom = idRoom;
        this.idMonsterType = idMonsterType;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getHitpoints() 
    {
        return hitpoints;
    }

    public void setHitpoints(Long hitpoints) 
    {
        this.hitpoints = hitpoints;
    }

    public Room getIdRoom() 
    {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) 
    {
        this.idRoom = idRoom;
    }

    @JsonBackReference(value = "monster-list")
    public MonsterType getIdMonsterType() 
    {
        return idMonsterType;
    }

    public void setIdMonsterType(MonsterType idMonsterType) 
    {
        this.idMonsterType = idMonsterType;
    }

    public void unlinkRoomMonster(Room r){
        this.idRoom=null;
        r.setrMonster(null); 
    }
    public void unlinkMonster(Room r){
        this.idRoom=null;
        this.idMonsterType =null; 
        r.setrMonster(null);
    }
}