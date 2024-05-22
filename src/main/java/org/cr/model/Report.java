package org.cr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private String id;

    private List<Booking> bookingLs;

    private List<Payment> paymentLs;

    private String crtBy;

    private LocalDateTime crtTm;

}
