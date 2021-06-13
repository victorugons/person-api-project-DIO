package dev.victorugons.personapi.service;

import dev.victorugons.personapi.dto.response.ResponseMessageDTO;
import dev.victorugons.personapi.entity.Person;
import dev.victorugons.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseMessageDTO createPerson(Person person) {

        Person savedPerson = personRepository.save(person);
        return ResponseMessageDTO
                .builder()
                .message("Created person with ID: " + savedPerson.getId())
                .build();
    }
}
