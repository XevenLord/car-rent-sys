package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Payment;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStore {

    private HashMap<String, Payment> map;

}
