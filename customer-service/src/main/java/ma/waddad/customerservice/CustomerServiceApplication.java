package ma.waddad.customerservice;

import ma.waddad.customerservice.config.CustomerConfigParams;
import ma.waddad.customerservice.entities.Customer;
import ma.waddad.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(CustomerConfigParams.class)
@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                            .name("User1").email("User1@gmail.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .name("User2").email("User2@gmail.com")
                    .build());

            customerRepository.save(Customer.builder()
                    .name("User3").email("User3@gmail.com")
                    .build());

            customerRepository.findAll().forEach(c ->{
                System.out.println("========================");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("========================");
            });
        };
    }

}
