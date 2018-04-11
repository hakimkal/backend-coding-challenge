package com.engagetech.expensify.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created  on 4/11/18.
 * Expense entity for persistence
 */
@Entity
@Table(name = "expenses")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue
    private long id;

    private double amount;
    private String reason;
    private double vat;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;


    @PrePersist
    private void beforeSave() {
        /*

        Calculate 20%  of amount as UK VAT
        formula: amount - (amount * (100 +20 )/100)

        amount -  (1.2 * amount)

         */
        this.vat = (this.amount - ( this.amount / 1.2));

    }


}
