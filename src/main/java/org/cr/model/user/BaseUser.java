package org.cr.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUser {

    private String id;

    private String email;

    private String pw;

    private String name;

    private String age;

    private String ic;

    private String contactNo;

}
