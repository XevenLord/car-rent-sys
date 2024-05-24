package org.cr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car implements Serializable {

    private String plateNo;

    private String maker;

    private String name;

    private String type;

    private String rentSts;

    private int seats;

    private Long rentPerHour;

}
