package dev.sekibomazic.controller.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RoutingHandler {

    private static final String apiPrefix = "/api/v1/functional/users";

    @Bean
    public RouterFunction<ServerResponse> functionalRoutes(UserHandler userHandler) {
        return RouterFunctions.route()
                .GET(apiPrefix+"/{id}", accept(MediaType.APPLICATION_JSON), userHandler::getUserById)
                .GET(apiPrefix, accept(MediaType.APPLICATION_JSON), userHandler::getUsers)
                .POST(apiPrefix, accept(MediaType.APPLICATION_JSON), userHandler::create)
                .PUT(apiPrefix+"/{id}", accept(MediaType.APPLICATION_JSON), userHandler::updateUserById)
                .DELETE(apiPrefix+"/{id}", accept(MediaType.APPLICATION_JSON), userHandler::deleteUserById)
                .build();

    }
}
