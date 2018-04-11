package com.engagetech.expensify.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by abdulhakim on 4/11/18.
 */
@Data
public class ExpenseDto {

    private double amount;
    private String reason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;

}
