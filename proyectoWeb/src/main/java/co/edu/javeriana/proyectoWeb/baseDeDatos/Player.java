package co.edu.javeriana.proyectoWeb.baseDeDatos;

public class Player {
    long id;
    String name;
    String last_updated;
    Long attack_level;
    long defence_slash;
    Long size;
    Long hitpoints;
    //ArrayList<String> category;
    //String examine;
    //String wiki_url;
    public Player() {
    }
    public Player(long id, String name, String last_updated, Long attack_level, long defence_slash, Long size,
            Long hitpoints) {
        this.id = id;
        this.name = name;
        this.last_updated = last_updated;
        this.attack_level = attack_level;
        this.defence_slash = defence_slash;
        this.size = size;
        this.hitpoints = hitpoints;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLast_updated() {
        return last_updated;
    }
    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
    public Long getAttack_level() {
        return attack_level;
    }
    public void setAttack_level(Long attack_level) {
        this.attack_level = attack_level;
    }
    public long getDefence_slash() {
        return defence_slash;
    }
    public void setDefence_slash(long defence_slash) {
        this.defence_slash = defence_slash;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public Long getHitpoints() {
        return hitpoints;
    }
    public void setHitpoints(Long hitpoints) {
        this.hitpoints = hitpoints;
    }

    
    
    


}
