package com.javeedishaq.springsecuritydb;

import com.javeedishaq.springsecuritydb.entity.MyUser;
import com.javeedishaq.springsecuritydb.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);


    @Autowired
    private MyUserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        logger.info("Running DataLoader...");


        MyUser admin = new MyUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles("ADMIN");
        userRepository.save(admin);

        MyUser user = new MyUser();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles("USER");
        userRepository.save(user);

        logger.info("DataLoader execution finished.");
    }
}
