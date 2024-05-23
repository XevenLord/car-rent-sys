package org.cr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking implements Serializable {

    private String id;

    private boolean status;

    private String plateNo;

    private String customerId;

    private String customerNm;

    LocalDateTime stTm;

    LocalDateTime endTm;

}
