
package com.aversifunciona.backend2501.interfac;

import com.aversifunciona.backend2501.entinty.Persona;
import java.util.List;


public interface IPersonaService {
    //traer una lista de personas
    public List<Persona> getPersona();
        
    //guardar un objeto de tipo persona
    public void savePersona(Persona persona);
    
    //eliminar un objeto pero lo buscmos por ID
    public void deletePersona(Long id);
    
    //buscar un objeto por ID
    public Persona findPersona(Long id);
}
