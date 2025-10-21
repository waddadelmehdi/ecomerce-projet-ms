package ma.waddad.bilingservice.model;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    public Long id;
    public String name;
    public String email;
}
