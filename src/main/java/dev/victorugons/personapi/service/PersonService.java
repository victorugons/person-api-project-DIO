package dev.victorugons.personapi.service;

import dev.victorugons.personapi.dto.request.PersonDTO;
import dev.victorugons.personapi.dto.response.ResponseMessageDTO;
import dev.victorugons.personapi.entity.Person;
import dev.victorugons.personapi.mapper.PersonMapper;
import dev.victorugons.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseMessageDTO createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return ResponseMessageDTO
                .builder()
                .message("Created person with ID: " + savedPerson.getId())
                .build();
    }
}
