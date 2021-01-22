package br.com.b2wapi.b2wapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2wapi.b2wapi.entity.Planeta;
import br.com.b2wapi.b2wapi.repository.PlanetaRepository;

@RestController
public class PlanetaController {

    @Autowired
    private PlanetaRepository _planetaRepository;

    @RequestMapping(value = "/planeta", method = RequestMethod.GET)
    public List<Planeta> Get() {
        return _planetaRepository.findAll();
    }
    
    @RequestMapping(value = "/planeta/{id}", method = RequestMethod.GET)
    public ResponseEntity<Planeta> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Planeta> planeta = _planetaRepository.findById(id);
        if(planeta.isPresent())
            return new ResponseEntity<Planeta>(planeta.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/planeta", method =  RequestMethod.POST)
    public ResponseEntity<Object> Post(@Valid @RequestBody Planeta planeta)
    {
        _planetaRepository.save(planeta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/planeta/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Planeta> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Planeta planetaEditado)
    {
        Optional<Planeta> antigoPlaneta = _planetaRepository.findById(id);
        if(antigoPlaneta.isPresent()){
            Planeta planeta = antigoPlaneta.get();
            planeta.setNome(planetaEditado.getNome());
            planeta.setClima(planetaEditado.getClima());
            planeta.setTerreno(planetaEditado.getTerreno());
            planeta.setAparicoes(planetaEditado.getAparicoes());
            _planetaRepository.save(planeta);
            return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/planeta/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Planeta> planeta = _planetaRepository.findById(id);
        if(planeta.isPresent()){
            _planetaRepository.delete(planeta.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
