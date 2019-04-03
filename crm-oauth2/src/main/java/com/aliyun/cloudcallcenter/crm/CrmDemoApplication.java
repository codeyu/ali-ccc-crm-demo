package com.aliyun.cloudcallcenter.crm;

import com.aliyun.cloudcallcenter.crm.model.Customer;
import com.aliyun.cloudcallcenter.crm.model.User;
import com.aliyun.cloudcallcenter.crm.repository.CustomerRepository;
import com.aliyun.cloudcallcenter.crm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author edward
 * @date 2017/11/12
 */
@SpringBootApplication
public class CrmDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrmDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialize(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository,
                                        CustomerRepository customerRepository) {
        return (args) -> {
            if (userRepository.findAll().size() == 0) {
                userRepository.save(new User("edward", bCryptPasswordEncoder.encode("1234")));
                userRepository.save(new User("sissi", bCryptPasswordEncoder.encode("5678")));
                customerRepository.save(new Customer("Mr Lu", "135012345678"));
                customerRepository.save(new Customer("Miss Lu", "135012345679"));
            }
        };
    }
}
