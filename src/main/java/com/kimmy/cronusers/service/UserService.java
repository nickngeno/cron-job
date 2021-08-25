package com.kimmy.cronusers.service;

import com.kimmy.cronusers.entity.OldUser;
import com.kimmy.cronusers.entity.User;
import com.kimmy.cronusers.repository.OldUserRepository;
import com.kimmy.cronusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OldUserRepository oldUserRepository;

    @Scheduled(cron = " 0 */5 * * * *")
    private void adduser() {
        User newUser = new User();
        newUser.setName("User" + UUID.randomUUID());
        System.out.println("Adding a user ------");
        userRepository.save(newUser).subscribe(System.out::println);
    }

    @Scheduled(cron = "0/30 * * * * *")
    private void getUsers() {
        System.out.println("--------- Fetching users ---------------");
        Mono<List<User>> usersList = userRepository.findAll().filter(user -> user.getId() > 200).collectList();
//        System.out.println(usersList.subscribe(System.out::println));
        usersList.subscribe(users -> System.out.println("No of users fetched : " + users.size()));
        usersList.subscribe(users -> {
            OldUser oldUser = new OldUser();
            users.forEach(u -> {
                oldUserRepository.findByUserId(u.getId()).defaultIfEmpty(0).doOnSuccess(exists -> {
                    if (exists == 0) {
                        oldUser.setUserId(u.getId());
                        oldUserRepository.save(oldUser).subscribe();
                    }
                });

            });
        });
        Mono<List<OldUser>> oldUsers = oldUserRepository.findAll().collectList();
        System.out.println("------------- fetching old users --------------");
        System.out.println(oldUsers.subscribe(System.out::println));
    }
}
