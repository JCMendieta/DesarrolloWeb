package co.edu.javeriana.proyectoWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.proyectoWeb.model.MonsterType;

@Repository
public interface MonsterTypeRepository extends JpaRepository<MonsterType, Long>
{
    
}