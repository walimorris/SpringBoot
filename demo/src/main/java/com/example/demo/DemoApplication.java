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
class Car {

	@Id
	private String id;
	private String make;
	private String model;
	private double price;

	public Car() {
	}

	public Car(String id, String make, String model, double price) {
		this.id = id;
		this.model = model;
		this.make = make;
		this.price = price;
	}

	public Car(String model, String make, double id) {
		this(UUID.randomUUID().toString(), model, make, id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

@RestController
@RequestMapping("/car")
class RestControllerCarApi {
	private final DemoCarRepository demoCarRepository;

	@Autowired
	public RestControllerCarApi(DemoCarRepository demoCarRepository) {
		this.demoCarRepository = demoCarRepository;
		this.demoCarRepository.saveAll(List.of(
				new Car("Audi", "s4", 25000),
				new Car("BMW", "M5", 30000),
				new Car("Subaru", "Forester",35000),
				new Car("Camaro", "Chevrolet", 15000)
		));
	}

	@GetMapping
	Iterable<Car> getCar() {
		return demoCarRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Car> getCarById(@PathVariable String id) {
		return demoCarRepository.findById(id);
	}

	@PostMapping
	ResponseEntity<Car> postCar(@RequestBody Car car) {
		return new ResponseEntity<>(demoCarRepository.save(car), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	void deleteById(@PathVariable String id) {
		demoCarRepository.deleteById(id);
	}
}

@Entity
class Person {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	private int age;

	public Person() { }

	public Person(String id, String firstname, String lastname, int age) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}

	public Person(String firstname, String lastname, int age) {
		this(UUID.randomUUID().toString(), firstname, lastname, age);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return this.id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public int getAge() {
		return this.age;
	}
}

@RestController
@RequestMapping("/person")
class RestControllerPersonApi {
	private final DemoPersonRepository demoPersonRespository;

	@Autowired
	public RestControllerPersonApi(DemoPersonRepository demoPersonRespository) {
		this.demoPersonRespository = demoPersonRespository;
		this.demoPersonRespository.saveAll(List.of(
				new Person("Wali", "Morris", 30),
				new Person("Conner", "McGregor", 32),
				new Person("Neil", "Tyson", 62),
				new Person("Linus", "Torvalds", 51)
		));
	}

	@GetMapping
	Iterable<Person> getPeople() {
		return demoPersonRespository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Person> getPerson(@PathVariable String id) {
		return demoPersonRespository.findById(id);
	}

	@PostMapping()
	ResponseEntity<Person> postPerson(@RequestBody Person person) {
		return new ResponseEntity<>(demoPersonRespository.save(person), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteByPerson(@PathVariable String id) {
		demoPersonRespository.deleteById(id);
	}

	@PutMapping("/{id}")
	ResponseEntity<Person> putById(@PathVariable String id, @RequestBody Person person) {
		return demoPersonRespository.findById(id).isPresent() ?
				new ResponseEntity<>(demoPersonRespository.save(person), HttpStatus.CREATED) :
				new ResponseEntity<>(demoPersonRespository.save(person), HttpStatus.OK);
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
