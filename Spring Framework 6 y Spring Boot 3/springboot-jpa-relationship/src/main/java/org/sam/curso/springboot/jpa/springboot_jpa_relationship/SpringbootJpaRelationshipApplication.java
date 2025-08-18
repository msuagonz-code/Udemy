package org.sam.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.sam.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import org.sam.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import org.sam.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import org.sam.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import org.sam.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import org.sam.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import org.sam.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// manyToOne();
		// manyToOneFindByIdClient();
		// oneToMany();
		// oneToManyFindById();
		// removeAddress();
		// removeAddressFindById();
		// oneToManyInvoiceBidireccional();
		// oneToManyInvoiceBidireccionalFindById();
		// removeInvoiceBidireccionalFindById();
		// removeInvoiceBidireccional();
		// oneToOne();
		// oneToOneFindById();
		// oneToOneBidireccional();
		oneToOneBidireccionalFindById();

	}

	@Transactional	
	private void oneToOneBidireccionalFindById(){
		
		Optional<Client> clientOptional = clientRepository.findOne(1L);

		clientOptional.ifPresent(client ->{
			ClientDetails clientDetails = new ClientDetails(true, 5000);
					
			client.setClientDetails(clientDetails);
			
			clientRepository.save(client);
			
			System.out.println(client);
		});
	}

	@Transactional	
	private void oneToOneBidireccional(){
		
		Client client = new Client("Erba", "Pura");
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		
		client.setClientDetails(clientDetails);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional	
	private void oneToOneFindById(){
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
			
			System.out.println(client);
		});
	}

	@Transactional	
	private void oneToOne(){
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		client = clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeInvoiceBidireccional(){
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 8000L);
	
		client.addInvoice(invoice1).addInvoice(invoice2);
		Client newClient = clientRepository.save(client);
		System.out.println(newClient);

		Optional<Client> optionalClientBD = clientRepository.findOne(3L);
		optionalClientBD.ifPresent(clientdb -> {

			Optional<Invoice> invOptional = invoiceRepository.findById(2L);
			invOptional.ifPresent(invoice -> {
				clientdb.removeInvoice(invoice);
				Client newClient2 = clientRepository.save(clientdb);
				System.out.println(newClient2);
			});

		});

	}

	@Transactional
	public void removeInvoiceBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);
	
			client.addInvoice(invoice1).addInvoice(invoice2);

			Client newClient = clientRepository.save(client);
	
			System.out.println(newClient);
		});

		Optional<Client> optionalClientBD = clientRepository.findOne(1L);
		optionalClientBD.ifPresent(client -> {
			
			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				Client newClient = clientRepository.save(client);
				System.out.println(newClient);
			});

		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById(){
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);
	
			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);
	
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional(){
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById(){

		Optional<Client> optionalClient = clientRepository.findById(2L);

		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address1);
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress(){
		
		Client client = new Client("Fran", "Moras");
		
		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		Optional<Client> optionalCliente = clientRepository.findById(3L);
		
		optionalCliente.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});

	}

	@Transactional
	public void oneToManyFindById(){

		Optional<Client> optionalClient = clientRepository.findById(2L);

		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToMany(){
		
		Client client = new Client("Fran", "Moras");
		
		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
	}

	@Transactional
	public void manyToOne(){
		
		Client client = new Client("John", "Doe");
		clientRepository.save(client);
		
		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		
		Invoice invoiceDB = invoiceRepository.save(invoice);
		
		System.out.println(invoiceDB);
	}
	
	@Transactional
	public void manyToOneFindByIdClient(){
		
		Optional<Client> optionalClient = clientRepository.findById(1L);

		if(optionalClient.isPresent()){
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
	
			Invoice invoiceDB = invoiceRepository.save(invoice);
	
			System.out.println(invoiceDB);
		}

	}


}
