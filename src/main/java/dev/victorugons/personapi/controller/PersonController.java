package dev.victorugons.personapi.controller;

import dev.victorugons.personapi.dto.request.PersonDTO;
import dev.victorugons.personapi.dto.response.ResponseMessageDTO;
import dev.victorugons.personapi.entity.Person;
import dev.victorugons.personapi.exception.PersonNotFoundException;
import dev.victorugons.personapi.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessageDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {

        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
