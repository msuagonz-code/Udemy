package org.sam.curso.springboot.jpa.springboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.sam.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
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
		// list();
		// findOne();
		// create();
		// update();
		// delete();
		//delete2();
		// personalizedQueries();
		// personalizedQueries2();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperAndLowerCase();
		// personalizedQueriesBetween();
		// queriesFunctionAggregation();
		// subQueries();
		whereIn();
	}

	@Transactional(readOnly = true)
	public void whereIn(){

		System.out.println("=============== Consulta getPersonsById ===============");
		List<Long> ids = Arrays.asList(1L, 2L, 5L);
		List<Person> listado = repository.getPersonsById(ids);
		listado.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void subQueries(){

		System.out.println("=============== Consulta getShorterName ===============");
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Nombre: "+ name +", length: "+ length);
		});

		System.out.println("=============== Consulta getLastRegistration ===============");
		Optional<Person> optionalPerson = repository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);

	}
	
	@Transactional(readOnly = true)
	public void queriesFunctionAggregation(){

		System.out.println("=============== Consulta getTotalPerson ===============");
		Long count = repository.getTotalPerson();
		System.out.println("Count: "+ count);
		
		System.out.println("=============== Consulta getMinId ===============");
		Long min = repository.getMinId();
		System.out.println("MinID: "+ min);

		System.out.println("=============== Consulta getMaxId ===============");
		Long max = repository.getMaxId();
		System.out.println("MaxId: "+ max);

		System.out.println("=============== Consulta getPersonNameLength ===============");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Nombre: "+ name +", length: "+ length);
		});

		System.out.println("=============== Consulta getMinLengthName ===============");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println("minLengthName: "+ minLengthName);

		System.out.println("=============== Consulta getMaxLengthName ===============");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println("maxLengthName: "+ maxLengthName);

		System.out.println("=============== Consulta getResumeAggregationFunction min(), max(), sum(), avg(length(p.name)), count()===============");
		Object[] resumeReg = (Object[]) repository.getResumeAggregationFunction();
		System.out.println("min=" + resumeReg[0] + ", max="+ resumeReg[1] + ", sum="+ resumeReg[2] + ", avg="+ resumeReg[3] + ", count="+ resumeReg[4]);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween(){
		System.out.println("=============== Consulta findAllBetweenId ===============");
		List<Person> persons = repository.findAllBetweenId(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta findAllBetweenName ===============");
		persons = repository.findAllBetweenName("J", "Q");
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta findByIdBetweenOrederByIdDesc ===============");
		persons = repository.findByIdBetweenOrderByIdDesc(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta findByNameBetween ===============");
		persons = repository.findByNameBetween("J", "Q");
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta getAllOrdered ===============");
		persons = repository.getAllOrdered();
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta findAllByOrderByNameDesc ===============");
		persons = repository.findAllByOrderByNameDesc();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase(){
	
		System.out.println("=============== Consulta findAllNames ===============");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);
	
		System.out.println("=============== Consulta findAllFullNameConcatUpper ===============");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);
	
		System.out.println("=============== Consulta findAllFullNameConcatLower ===============");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
	
		System.out.println("=============== Consulta obtenerPersonDataListCase ===============");
		List<Object[]> regs = repository.obtenerPersonDataListCase();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct(){
		System.out.println("=============== Consulta findAllNames ===============");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);
		
		System.out.println("=============== Consulta findAllNamesDistinct ===============");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("=============== Consulta findAllProgrammingLanguageDistinct ===============");
		List<String> languages = repository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("=============== Consulta findAllProgrammingLanguageDistinctCount ===============");
		Long count = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println("total de lenguajes de programación unicos: "+ count);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2(){
		
		System.out.println("=============== Consulta findAllMixPerson ===============");
		
		List<Object[]> personRegs = repository.findAllMixPerson();
		personRegs.forEach(reg -> {
			System.out.println("ProgrammingLanguage" + reg[1] + ", person= "+ reg[0]);
		});

		System.out.println("=============== Consulta findAllObjectPersonPersonalized ===============");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("=============== Consulta findAllPersonDto ===============");
		List<PersonDto> personDto = repository.findAllPersonDto();
		personDto.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id para el nombre: ");
		Long id = scanner.nextLong();
		scanner.close();

		String name = repository.getNameById(id);
		System.out.println("el nombre es " + name);

		String fullname = repository.getFullNameById(id);
		System.out.println("el nombre completo " + fullname);
	}
	
	@Transactional
	public void delete2(){
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id a eliminar: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		optionalPerson.ifPresentOrElse(repository::delete,
		 	() -> System.out.println("Lo sentimos no existe la persona con ese ID"));

		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void delete(){
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id a eliminar: ");
		Long id = scanner.nextLong();
		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void update(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id de la persona: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresent(person -> {
			//Antes del Update
			System.out.println(person);

			System.out.println("Ingrese el lenguaje del programacion: ");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);
			Person personDB = repository.save(person);

			//Después del Update
			System.out.println(personDB);

		});

		scanner.close();
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
