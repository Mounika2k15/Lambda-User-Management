package com.dash.aws.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.dash.aws.lambda.domain.User;
import com.dash.aws.lambda.respository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringbootAwsLambdaApplication {

    @Autowired
    private UserDao userDao;

    @Bean
    public Supplier<List<User>> users() {
        return () -> userDao.findAll();
    }

    @Bean
    public Function<APIGatewayProxyRequestEvent, List<User>> findUserByName() {
        return (requestEvent) -> userDao.findAll()
                .stream()
                .filter(order -> order.getName().equals(requestEvent.getQueryStringParameters().get("name")))
                .collect(Collectors.toList());
    }

    @Bean
    public Function<APIGatewayProxyRequestEvent, List<User>> deleteUserById() {
        return (requestEvent) -> check(requestEvent);
    }

    public List<User> check(APIGatewayProxyRequestEvent input) {
        if(input != null){
            String value = input.getQueryStringParameters().get("id");
            userDao.deleteById(value);
        }
        return userDao.findAll();
    }

    @Bean
    public Function<APIGatewayProxyRequestEvent, List<User>> updateUser() {
        return (requestEvent) -> checkUpdate(requestEvent);
    }

    public List<User> checkUpdate(APIGatewayProxyRequestEvent input) {
        String value = input.getQueryStringParameters().get("name");
        String id=input.getQueryStringParameters().get("id");
        if(value != null && id != null){
            userDao.updateUsers(value,id);
        }
       return userDao.findAll();
    }

    @Bean
    public Function<APIGatewayProxyRequestEvent, List<User>> createUser() {
        return (requestEvent) -> createUpdate(requestEvent);
    }

    public List<User> createUpdate(APIGatewayProxyRequestEvent input) {
        String value = input.getQueryStringParameters().get("name");
        String id = input.getQueryStringParameters().get("id");
        if(value!=null){
            User o= new User(id,value);
            userDao.save(o);
        }
        return userDao.findAll();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsLambdaApplication.class, args);
    }

}
