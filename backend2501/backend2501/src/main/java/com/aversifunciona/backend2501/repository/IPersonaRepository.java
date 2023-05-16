
package com.aversifunciona.backend2501.repository;

import com.aversifunciona.backend2501.entinty.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Long>{
    
}