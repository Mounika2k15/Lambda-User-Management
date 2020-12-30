package com.dash.aws.lambda.Util;

import com.dash.aws.lambda.domain.User;
import com.dash.aws.lambda.respository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer  implements CommandLineRunner {

    @Autowired
    private final UserDao userDao;

    public User o= new User("1","Dash");
    public User o1= new User("2","Energy");
    public User o2= new User("3","testing");
    public User o3= new User("4","lambda");

    public Initializer(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception {
//        orderDao.save(o);
//        orderDao.save(o1);
//        orderDao.save(o2);
//        orderDao.save(o3);
    }
}
