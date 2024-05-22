package org.cr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private String id;

    private boolean status;

    private String plateNo;

    private String customerId;

    private String customerNm;

    LocalDateTime stTm;

    LocalDateTime endTm;

}
