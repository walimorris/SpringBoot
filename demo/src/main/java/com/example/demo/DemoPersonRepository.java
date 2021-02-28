package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface DemoPersonRepository extends CrudRepository<Person, String> { }
