package ma.waddad.inventoryservice;

import ma.waddad.inventoryservice.entites.Product;
import ma.waddad.inventoryservice.repositores.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
          productRepository.save(Product.builder()
                  .id(UUID.randomUUID().toString())
                  .name("Product 1")
                  .price(100.00)
                  .quantity(11)
                  .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Product 2")
                    .price(140.00)
                    .quantity(14)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Product 3")
                    .price(170.00)
                    .quantity(14)
                    .build());

            productRepository.findAll().forEach(p->{
                System.out.println(p.toString());
            });

        };


    }

}
