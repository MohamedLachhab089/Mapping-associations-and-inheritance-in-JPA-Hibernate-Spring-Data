package ma.emsi.jpa;

import ma.emsi.jpa.entities.Role;
import ma.emsi.jpa.entities.User;
import ma.emsi.jpa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User user = new User();
            user.setUsername("Salma");
            user.setPassword("12345");
            userService.addUser(user);

            User user2 = new User();
            user2.setUsername("Mehdi");
            user2.setPassword("54321");
            userService.addUser(user2);

            Stream.of("Student", "Admin", "User").forEach(r -> {
                Role role = new Role();
                role.setRoleName(r);
                userService.addRole(role);
            });

            userService.addRoleToUser("Salma", "Student");
            userService.addRoleToUser("Mehdi", "Admin");

            try {
                User user1 = userService.authenticate("Salma", "12345");
                System.out.println(user1.getUserId());
                System.out.println("Roles : " + user1.getRoles());
            } catch (Exception e) {
                System.out.println("Error 404: " + e);
            }

        };
    }

}
