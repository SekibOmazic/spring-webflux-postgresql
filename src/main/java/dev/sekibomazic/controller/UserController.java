package dev.sekibomazic.controller;


import dev.sekibomazic.model.User;
import dev.sekibomazic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<User> all() {
        return userService.getUsers();
    }

    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<User> getFlux(){
        return userService.getUsers()
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable int id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/guests/{id}")
    public Mono<ResponseEntity<User>> getGuestUser(@PathVariable int id) {
        return userService.getGuestUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody Mono<User> userMono) {
        return userMono.flatMap(userService::saveUser);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable int id, @RequestBody Mono<User> userMono) {
        return userMono.flatMap(user -> userService.updateUser(id, user))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

}