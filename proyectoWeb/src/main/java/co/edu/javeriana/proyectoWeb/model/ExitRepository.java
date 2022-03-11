package co.edu.javeriana.proyectoWeb.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitRepository extends CrudRepository<Exit, Long>
{
    
}