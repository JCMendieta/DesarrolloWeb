package co.edu.javeriana.proyectoWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.proyectoWeb.model.Exit;

@Repository
public interface ExitRepository extends JpaRepository<Exit, Long>
{
    
}