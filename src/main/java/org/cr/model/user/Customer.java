package org.cr.model.user;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class Customer extends BaseUser {

    private Long bill;

}
