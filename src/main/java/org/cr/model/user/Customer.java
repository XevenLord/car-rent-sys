package org.cr.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class Customer extends BaseUser {

    private Long bill;

    public Customer(String id, String email, String pw, String name, String age, String ic, String contactNo, Long bill) {
        super(id, email, pw, name, age, ic, contactNo);
        this.bill = bill;
    }

    public Customer(Long bill) {
        this.bill = bill;
    }

    public Customer(BaseUserBuilder<?, ?> b, Long bill) {
        super(b);
        this.bill = bill;
    }
}
