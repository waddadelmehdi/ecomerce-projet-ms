package ma.waddad.bilingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.waddad.bilingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private long customerId;

    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems = new ArrayList<>();
    @Transient
    private Customer customer;

}
