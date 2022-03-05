package co.edu.javeriana.proyectoWeb.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MonsterType
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
    String last_updated;
    Long attack_level;
    Long defence_slash;
    Long size;
    Long hitpoints;

    @ElementCollection(targetClass = String.class)
    List<String> category = new ArrayList<>();

    @OneToMany(mappedBy = "idMonsterType")
    @ElementCollection(targetClass = Monster.class)
    List<Monster> monsters = new ArrayList<>();
    
    String examine;
    String wiki_url;

    public MonsterType() 
    {

    }

    public MonsterType(String name) 
    {
        this.name = name;
    }

    public MonsterType(String name, String last_updated, Long attack_level, Long defence_slash, Long size,
            Long hitpoints, List<String> category, List<Monster> monsters, String examine, String wiki_url) 
    {
        this.name = name;
        this.last_updated = last_updated;
        this.attack_level = attack_level;
        this.defence_slash = defence_slash;
        this.size = size;
        this.hitpoints = hitpoints;
        this.category = category;
        this.monsters = monsters;
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

    public Long getAttack_level() 
    {
        return attack_level;
    }

    public void setAttack_level(Long attack_level) 
    {

        this.attack_level = attack_level;
    }

    public Long getDefence_slash() {
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

    public Long getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(Long hitpoints) 
    {
        this.hitpoints = hitpoints;
    }

    public List<String> getCategory() 
    {
        return category;
    }

    public void setCategory(List<String> category) 
    {
        this.category = category;
    }

    public List<Monster> getMonsters() 
    {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) 
    {
        this.monsters = monsters;
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

    
}