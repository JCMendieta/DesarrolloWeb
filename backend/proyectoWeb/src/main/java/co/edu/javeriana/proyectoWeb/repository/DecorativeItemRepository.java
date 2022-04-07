package co.edu.javeriana.proyectoWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.proyectoWeb.model.DecorativeItem;

@Repository
public interface DecorativeItemRepository extends JpaRepository<DecorativeItem, Long>
{
    
}