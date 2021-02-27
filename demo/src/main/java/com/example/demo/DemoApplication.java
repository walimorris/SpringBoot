package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Entity
class Coffee {

	@Id
	private String id;
	private String name;

	public Coffee() { }

	public Coffee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Coffee(String name) {
		this(UUID.randomUUID().toString(), name);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}

@Entity
class Phone {

	@Id
	private String id;
	private String brand;
	private String color;

	public Phone() { }

	public Phone(String id, String brand, String color) {
		this.id = id;
		this.brand = brand;
		this.color = color;
	}
	public Phone(String brand, String color) {
		this(UUID.randomUUID().toString(), brand, color);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getId() {
		return this.id;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getColor() {
		return this.color;
	}
}

@RestController
@RequestMapping("/phone")
class RestControllerPhoneApi {
	private final DemoPhoneRepository demoPhoneRepository;

	@Autowired
	public RestControllerPhoneApi(DemoPhoneRepository demoPhoneRepository) {
		this.demoPhoneRepository = demoPhoneRepository;
		this.demoPhoneRepository.saveAll(List.of(
				new Phone("Iphone", "Marble Blue"),
				new Phone("123456789", "Samsung", "Green"),
				new Phone("Nokia", "Ruby Red")
		));
	}

	@GetMapping
	Iterable<Phone> getPhone() {
		return demoPhoneRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Phone> getPhoneById(@PathVariable String id) {
		return demoPhoneRepository.findById(id);
	}

	@PostMapping
	ResponseEntity<Phone> postPhone(@RequestBody Phone phone) {
		return new ResponseEntity<>(demoPhoneRepository.save(phone), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	void deletePhone(@PathVariable String id) {
		demoPhoneRepository.deleteById(id);
	}
}

@RestController
@RequestMapping("/coffee")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
class RestControllerDemoApi {
	private final DemoCoffeeRepository demoCoffeeRepository;

	@Autowired
	public RestControllerDemoApi(DemoCoffeeRepository demoCoffeeRepository) {
		this.demoCoffeeRepository = demoCoffeeRepository;
		this.demoCoffeeRepository.saveAll(List.of(
				new Coffee("Pike Place"),
				new Coffee("Arabica"),
				new Coffee("Premium Blonde"),
				new Coffee("Veronda")
		));
	}

	@GetMapping
	Iterable<Coffee> getCoffee() {
		return demoCoffeeRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		return demoCoffeeRepository.findById(id);
	}

	@PostMapping
	ResponseEntity<Coffee> postCoffee(@RequestBody Coffee coffee) {
		return new ResponseEntity<>(demoCoffeeRepository.save(coffee), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
		return (!demoCoffeeRepository.existsById(id)) ?
				new ResponseEntity<>(demoCoffeeRepository.save(coffee), HttpStatus.CREATED) :
				new ResponseEntity<>(demoCoffeeRepository.save(coffee), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
		demoCoffeeRepository.deleteById(id);
	}
}