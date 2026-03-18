// package com.smartpower.power_monitor.config;

// import com.smartpower.power_monitor.model.User;
// import com.smartpower.power_monitor.repository.UserRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// public class DataInitializer {

//     @Bean
//     CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {

//         return args -> {

//             if (userRepository.findByUsername("admin") == null) {

//                 User admin = new User();

//                 admin.setUsername("admin");
//                 admin.setPassword(passwordEncoder.encode("admin123"));
//                 admin.setRole("ADMIN");

//                 userRepository.save(admin);

//                 System.out.println("Admin user created!");
//             }

//         };
//     }
// }


package com.smartpower.power_monitor.config;

import com.smartpower.power_monitor.model.User;
import com.smartpower.power_monitor.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

@Bean
CommandLineRunner initAdmin(UserRepository userRepository,
                            PasswordEncoder passwordEncoder){

return args -> {

if(userRepository.findByUsername("admin")==null){

User admin=new User();

admin.setFirstName("Main");
admin.setLastName("Admin");

admin.setUsername("admin");

admin.setEmail("admin@power.com");   // IMPORTANT

admin.setPassword(passwordEncoder.encode("admin123"));

admin.setPhone("9999999999");

admin.setRole("ADMIN");

userRepository.save(admin);

System.out.println("Default admin created");

}

};

}

}