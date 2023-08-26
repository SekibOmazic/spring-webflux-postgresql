package dev.sekibomazic;

import dev.sekibomazic.model.Address;
import dev.sekibomazic.model.User;
import dev.sekibomazic.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ReactiveRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRestApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserService userService) {
        return (args) -> {
            // save a couple of users
            var users = Flux.just(
                    new User("Sekib", ThreadLocalRandom.current().nextInt(1, 100), new Address("Munich")),
                    new User("Doug", ThreadLocalRandom.current().nextInt(1, 100), new Address("Richmond")),
                    new User("Kate", ThreadLocalRandom.current().nextInt(1, 100), new Address("Baltimore"))
            );
            users.flatMap(userService::saveUser)
                    .subscribe(user -> System.out.println("Saved user: " + user));
        };
    }


}
