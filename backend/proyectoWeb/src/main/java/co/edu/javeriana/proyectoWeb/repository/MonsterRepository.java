package co.edu.javeriana.proyectoWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.proyectoWeb.model.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long>
{
    
}