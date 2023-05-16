
package com.aversifunciona.backend2501.controller;

import com.aversifunciona.backend2501.dto.DtoExperiencia;
import com.aversifunciona.backend2501.entinty.Experiencia;
import com.aversifunciona.backend2501.service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("explab")
@CrossOrigin(origins = "https://frontend-ap-3c836.web.app")
public class CExperiencia {
    
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("lista")
    public ResponseEntity<List<Experiencia>> list(){
        
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity (list,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExp){
    
        if (StringUtils.isBlank(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        if (sExperiencia.existsByNombreE(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("esa experiencia existe"),HttpStatus.BAD_REQUEST);
        Experiencia experiencia = new Experiencia(dtoExp.getNombreE(),dtoExp.getDescripcionE());
        sExperiencia.save(experiencia);
            return new ResponseEntity(new Mensaje("experiencia agregada"),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id,@RequestBody DtoExperiencia dtoExp) {
        //validamos si existe el id
        if (!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("el id no existe"),HttpStatus.BAD_REQUEST);
        //compara nombre de experiencias
        if(sExperiencia.existsByNombreE(dtoExp.getNombreE())&& sExperiencia.getByNombreE(dtoExp.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese experiencia ya existe"),HttpStatus.BAD_REQUEST);
        //no puede estar vacio 
        if (StringUtils.isBlank(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoExp.getNombreE());
        experiencia.setDescripcionE(dtoExp.getDescripcionE());
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("expeiencia actualizada"),HttpStatus.OK);
    
    }
    
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        //validamos si existe el id
        if (!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("el id no existe"),HttpStatus.BAD_REQUEST);
        
        sExperiencia.delete(id);
        
        return new ResponseEntity(new Mensaje("experiencia eliminada"), HttpStatus.OK);
    }
}