package com.sam.springboot.reactor.app;

import com.sam.springboot.reactor.app.models.Comments;
import com.sam.springboot.reactor.app.models.User;
import com.sam.springboot.reactor.app.models.UserComments;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static reactor.core.publisher.Flux.fromIterable;

@SpringBootApplication
public class SpringbootReactorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringbootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//this.subscribeAndOnNext();
		//mapAndFilter();
		//mapAndFilter2Integer();
		//mapAndFilter3User();
		//mapAndFilter4UserInmutable();
		//userfromIterable();
		//userFlatmap();
		//userFlatmapToString();
		//collectList();
		//userCommentsFlatMap();
		//userCommentsZipWith();
		//userCommentsZipWith2();
		//zipRange();
		//fluxInterval();
		//fluxDelayElements();
		//intervalInfinite();
		//intervalFromCreate();
		//backPressure();
		backPressure2();
	}

	private void backPressure2() {
		Flux.range(1,10)
				.log()
				.limitRate(5)
				.subscribe(value -> log.info(value.toString()));
	}

	private void backPressure() {
		Flux.range(1,10)
				.log()
				.subscribe(new Subscriber<Integer>() {

					private Subscription subscription;
					private final int limit = 5;
					private int consumed = 0;
					@Override
					public void onSubscribe(Subscription subscription) {
						this.subscription = subscription;
						this.subscription.request(limit);
					}

					@Override
					public void onNext(Integer element) {
						log.info(element.toString());
						consumed++;
						if(consumed == limit){
							consumed = 0;
							this.subscription.request(limit);
						}
					}

					@Override
					public void onError(Throwable t) {

					}

					@Override
					public void onComplete() {
						log.info("Hemos finalizado el flujo!");
					}
				});
	}

	private void intervalFromCreate() {
		Flux.create( emmiter -> {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				private Integer counter = 0;
				@Override
				public void run() {
					emmiter.next(++counter);
					/*
					if(counter.equals(10)){
						timer.cancel();
						emmiter.complete();
					}*/
					if(counter.equals(10)){
						timer.cancel();
						emmiter.error(new InterruptedException("Error!, se rompio el proceso en 10!"));
					}
				}
			}, 1000, 1);
		})
				.doOnTerminate(() -> log.info("Hemos terminado!"))
				.retry(1)
				.subscribe(next -> log.info(next.toString())
						, error -> log.info(error.getMessage())
						, () -> log.info("Hemos Completado correctamente!"));
	}

	private void intervalInfinite() throws InterruptedException {
		CountDownLatch counter = new CountDownLatch(1);
		Flux.interval(Duration.ofSeconds(1))
				.doOnTerminate(counter::countDown)
				.flatMap(value -> {
					if(value == 5){
						return Flux.error(new InterruptedException("solo hasta 5!"));
					}
					return Flux.just(value);
				})
				.map(i -> "hola ".concat(i.toString()))
				.retry(2)
				.subscribe(log::info, error -> log.error(error.getMessage()));
		counter.await();
	}

	private void fluxDelayElements() throws InterruptedException {
		CountDownLatch counter = new CountDownLatch(1);
		Flux<Integer> range = Flux.range(1,12)
				.delayElements(Duration.ofSeconds(2))
				.doOnNext(value -> log.info(value.toString()))
				.doOnTerminate(counter::countDown);
		range.subscribe();

		counter.await();
		//TimeUnit.SECONDS.sleep(26);
		//Thread.sleep(26000L);
	}

	private void fluxInterval() {
		Flux<Integer> range = Flux.range(1,12);
		Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));

		range
				.zipWith(delay, (first, second) -> first)
				.doOnNext(value -> log.info(value.toString()))
				.blockLast();
	}

	private void zipRange() {
		Flux<Integer> range = Flux.range(0,5);
		Flux<Integer> numbers = Flux.just(1,2,3,4,5,6,7,9);

		numbers
				.map(integer -> integer * 2)
				.zipWith(range, (first, second) -> String.format("Primer flux: %d, Segundo flux: %d", first, second))
				.subscribe(log::info);


	}

	private void userCommentsZipWith2(){
		Mono<User> userMono = Mono.fromCallable(this::createUser);
		Mono<Comments> commentsMono = Mono.fromCallable(() -> {
			Comments comments = new Comments();
			comments.addComments("Hola pepe que tal!");
			comments.addComments("Mañana voy a la playa");
			comments.addComments("Estoy en la seccion de spring con reactor!");
			return  comments;
		});

		Mono<UserComments> userCommentsMono = userMono
				.zipWith(commentsMono)
				.map(tuple -> new UserComments(tuple.getT1(), tuple.getT2()));
		userCommentsMono.subscribe(System.out::println);
	}

	private void userCommentsZipWith(){
		Mono<User> userMono = Mono.fromCallable(this::createUser);
		Mono<Comments> commentsMono = Mono.fromCallable(() -> {
			Comments comments = new Comments();
			comments.addComments("Hola pepe que tal!");
			comments.addComments("Mañana voy a la playa");
			comments.addComments("Estoy en la seccion de spring con reactor!");
			return  comments;
		});

		/*
		userMono.zipWith(commentsMono, (user, comments) ->{
			return new UserComments(user, comments);
		});
		 */
		Mono<UserComments> userCommentsMono = userMono.zipWith(commentsMono, UserComments::new);
		userCommentsMono.subscribe(System.out::println);
	}

	private void  userCommentsFlatMap(){
		//Mono<User> userMono = Mono.just(new User("John", "Doe"));
		Mono<User> userMono = Mono.fromCallable(this::createUser);
		Mono<Comments> commentsMono = Mono.fromCallable(() -> {
			Comments comments = new Comments();
			comments.addComments("Hola pepe que tal!");
			comments.addComments("Mañana voy a la playa");
			comments.addComments("Estoy en la seccion de spring con reactor!");
			return  comments;
		});

		Mono<UserComments> userCommentsMono = userMono.flatMap(user ->{
			//return commentsMono.map(comments -> new UserComments(user, comments));
			//return commentsMono.flatMap(comments -> Mono.just(new UserComments(user, comments)));
			return commentsMono.flatMap(comments -> Mono.fromCallable(() -> new UserComments(user, comments)));
		});

		userCommentsMono.subscribe(userComments -> log.info(userComments.toString()));
	}

	private User createUser(){
		return new User("John", "Doe");
	}

	private void collectList (){
		List<User> userList = Arrays.asList(
		new User("Andres"," Guzman"),
		new User("Diego ","Fulano"),
		new User("Maria ","Ana"),
		new User("Pedro ","Mengano"),
		new User("Juan P","erengano"),
		new User("Bruce ","Sultano"),
		new User("Bruce ","Doe"));

		Mono<List<String>> names = Flux.fromIterable(userList)
				.flatMap(user -> Mono.just(user.getName().concat(" ").concat(user.getLastname()) ))
				.collectList();

		names.subscribe(list -> list.forEach(System.out::println));
	}

	private void userFlatmapToString(){
		List<User> userList = new ArrayList<>();
		userList.add(new User("Andres"," Guzman"));
		userList.add(new User("Diego ","Fulano"));
		userList.add(new User("Maria ","Ana"));
		userList.add(new User("Pedro ","Mengano"));
		userList.add(new User("Juan P","erengano"));
		userList.add(new User("Bruce ","Sultano"));
		userList.add(new User("Bruce ","Doe"));

		Flux.fromIterable(userList)
				.flatMap(user -> Mono.just(user.getName().concat(" ").concat(user.getLastname()) ))
				.flatMap(user -> {
					if(user.toLowerCase().contains("bruce")){
						return Mono.just(user);
					}
					return Mono.empty();
				})
				.map(String::toUpperCase)
				.subscribe(log::info);
	}

	private void userFlatmap(){
		List<String> userList = new ArrayList<>();
		userList.add("Andres Guzman");
		userList.add("Diego Fulano");
		userList.add("Maria Ana");
		userList.add("Pedro Mengano");
		userList.add("Juan Perengano");
		userList.add("Bruce Sultano");
		userList.add("Bruce Doe");

		Flux<String> names = Flux.fromIterable(userList);

		Flux<User> users = names.map(name-> {
					String n = name.split(" ")[0];
					String ln = name.split(" ")[1];
					return new User(n, ln);
				})
				.flatMap(user -> {
					if(user.getName().equalsIgnoreCase("bruce")){
						return Mono.just(user);
					}
					return Mono.empty();
				})
				.map(user -> {
					user.setLastname(user.getLastname().toUpperCase());
					user.setCreatedAt(LocalDateTime.now());
					return user;
				});

		users.subscribe(user -> log.info(user.toString()));
	}

	private void userfromIterable() {
		List<String> userList = new ArrayList<>();
		userList.add("Andres Guzman");
		userList.add("Diego Fulano");
		userList.add("Maria Ana");
		userList.add("Pedro Mengano");
		userList.add("Juan Perengano");
		userList.add("Bruce Sultano");
		userList.add("Bruce Doe");

		Flux<String> names = Flux.fromIterable(userList);

		Flux<User> users = names.map(name-> {
					String n = name.split(" ")[0];
					String ln = name.split(" ")[1];
					return new User(n, ln);
				})
				.filter(user -> user.getName().length() == 5)
				.doOnNext(System.out::println)
				.filter(user -> user.getName().equalsIgnoreCase("bruce"))
				.doOnNext(user -> {
					if(user.getName().isEmpty()){
						throw new RuntimeException("Los nombres no pueden ser vacios!");
					}
					System.out.println(user.getName().length());
				})
				.map(user -> {
					user.setLastname(user.getLastname().toUpperCase());
					user.setCreatedAt(LocalDateTime.now());
					return user;
				});

		users.subscribe(user -> log.info(user.toString())
				, error -> log.error(error.getMessage())
				, () -> log.info("El flujo Users ha finalizado correctamente del observable\r\n"));

	}

	private void mapAndFilter4UserInmutable() {
		Flux<String> names = Flux.just("Andres Guzman",
						"Diego Fulano",
						"Maria Ana",
						"Pedro Mengano",
						"Juan Perengano",
						"Bruce Sultano",
						"Bruce Doe");

		Flux<User> users = names.map(name-> {
					String n = name.split(" ")[0];
					String ln = name.split(" ")[1];
					return new User(n, ln);
				})
				.filter(user -> user.getName().length() == 5)
				.doOnNext(System.out::println)
				.filter(user -> user.getName().equalsIgnoreCase("bruce"))
				.doOnNext(user -> {
					if(user.getName().isEmpty()){
						throw new RuntimeException("Los nombres no pueden ser vacios!");
					}
					System.out.println(user.getName().length());
				})
				.map(user -> {
					user.setLastname(user.getLastname().toUpperCase());
					user.setCreatedAt(LocalDateTime.now());
					return user;
				});

		users.subscribe(user -> log.info(user.toString())
				, error -> log.error(error.getMessage())
				, () -> log.info("El flujo Users ha finalizado correctamente del observable\r\n"));

		names.subscribe(user -> log.info(user.toString())
				, error -> log.error(error.getMessage())
				, () -> log.info("El flujo Names ha finalizado correctamente del observable\r\n"));

	}

	private void mapAndFilter3User() {
		Flux<User> names = Flux.just("Andres Guzman", "Diego Fulano", "Maria Ana", "Pedro Mengano", "Juan Perengano", "Bruce Sultano", "Bruce Doe")
				.map(name-> {
					String n = name.split(" ")[0];
					String ln = name.split(" ")[1];
					return new User(n, ln);
				})
				.filter(user -> user.getName().length() == 5)
				.doOnNext(System.out::println)
				.filter(user -> user.getName().equalsIgnoreCase("bruce"))
				.doOnNext(user -> {
					if(user.getName().isEmpty()){
						throw new RuntimeException("Los nombres no pueden ser vacios!");
					}
					System.out.println(user.getName().length());
				})
				.map(user -> {
					user.setLastname(user.getLastname().toUpperCase());
					user.setCreatedAt(LocalDateTime.now());
					return user;
				});

		names.subscribe(user -> log.info(user.toString())
				, error -> log.error(error.getMessage())
				, () -> log.info("El flujo ha finalizado correctamente del observable"));
	}

	private void mapAndFilter2Integer() {
		Flux<Integer> names = Flux.just("Andres", "Diego", "Maria", "Pedro", "Juan", "", "Bruce")
				.map(String::length)
				.doOnNext(length -> {
					if(length == 0){
						throw new RuntimeException("Los nombres no pueden ser vacios!");
					}
					System.out.println(length);
				});

		names.subscribe(value -> log.info(value.toString())
				, error -> log.error(error.getMessage())
				, () -> log.info("El flujo ha finalizado correctamente del observable"));
	}

	private void mapAndFilter() {
		Flux<String> names = Flux.just("Andres", "Diego", "Maria", "Pedro", "Juan", "Bruce")
				.map(String::toUpperCase)
				.doOnNext(name -> {
					if(name.isBlank()){
						throw new RuntimeException("Los nombres no pueden ser vacios!");
					}
					System.out.println(name.toLowerCase());
				});

		names.subscribe(log::info
				, error -> log.error(error.getMessage())
				, () -> log.info("El flujo ha finalizado correctamente del observable"));
	}

	public void subscribeAndOnNext(){
		// Para probar el error
		//Flux<String> names = Flux.just("Andres", "Diego", " ", "Pedro", "Juan", "Bruce")
		Flux<String> names = Flux.just("Andres", "Diego", "Pedro", "Juan", "Bruce")
				.doOnNext(System.out::println)
				.doOnNext(name -> System.out.println(name.toUpperCase()))
				.doOnNext(name -> {
					if(name.isBlank()){
						throw new RuntimeException("Los nombres no pueden ser vacios!");
					}
					System.out.println(name.toLowerCase());
				});

		/*
			names.subscribe(new Subscriber<String>() {
				@Override
				public void onSubscribe(Subscription s) {
					s.request(2);
				}

				@Override
				public void onNext(String s) {
					log.info(s);
				}

				@Override
				public void onError(Throwable error) {
					log.error(error.getMessage());
				}

				@Override
				public void onComplete() {
					log.info("El flujo ha finalizado correctamente del observable");
				}
			});
		*/

		names.subscribe(log::info
				, error -> log.error(error.getMessage())
				, () -> log.info("El flujo ha finalizado correctamente del observable"));
	}
}
