package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Transaction {
    private final int amount;
    private final LocalDate date;
}
