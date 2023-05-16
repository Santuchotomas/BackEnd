
package com.aversifunciona.backend2501.repository;
 
import com.aversifunciona.backend2501.entinty.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RExperiencia extends JpaRepository<Experiencia, Integer>{
    
    public Optional<Experiencia> findByNombreE(String nombreE);
    public boolean existsByNombreE (String nombreE);
    
}
