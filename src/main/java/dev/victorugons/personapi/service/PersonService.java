package dev.victorugons.personapi.service;

import dev.victorugons.personapi.dto.request.PersonDTO;
import dev.victorugons.personapi.dto.response.ResponseMessageDTO;
import dev.victorugons.personapi.entity.Person;
import dev.victorugons.personapi.exception.PersonNotFoundException;
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
        return createResponseMessage(savedPerson.getId(), "Created person with ID: ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public ResponseMessageDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createResponseMessage(updatedPerson.getId(), "Updated person with ID: ");
    }

    private ResponseMessageDTO createResponseMessage(Long id, String message) {
        return ResponseMessageDTO
                .builder()
                .message(message + id)
                .build();
    }
}
