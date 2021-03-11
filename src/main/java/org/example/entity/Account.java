package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Account {
    private final int startBalance;
    private List<Transaction> transactions;
}
