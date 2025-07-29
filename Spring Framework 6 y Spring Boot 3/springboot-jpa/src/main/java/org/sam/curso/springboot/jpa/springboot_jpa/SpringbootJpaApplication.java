package org.sam.curso.springboot.jpa.springboot_jpa;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.sam.curso.springboot.jpa.springboot_jpa.entities.Person;
import org.sam.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//list();
		//findOne();
		create();
	}

	@Transactional
	public void create(){
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese Nombre: ");
		String name = scanner.next();
		System.out.println("Ingrese Apellido: ");
		String lastname = scanner.next();
		System.out.println("Ingrese lenguaje de programacion: ");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne(){
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);
		// if(optionalPerson.isPresent()){
		// 	person = optionalPerson.get();
		// }
		// System.out.println(person);
		// repository.findById(1L).ifPresent(System.out::println);
		// repository.findOne(1L).ifPresent(System.out::println);
		repository.findByNameContaining("hn").ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Python", "Pepe");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Andres");

		persons.stream().forEach(person -> System.out.println(person));
		
		List<Object[]> personsValues = repository.obtenerPersonData(); 
		personsValues.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));
	}

}
