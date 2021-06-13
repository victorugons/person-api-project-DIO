package dev.victorugons.personapi.repositories;

import dev.victorugons.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}