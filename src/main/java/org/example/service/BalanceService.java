package org.example.service;

import org.example.entity.Account;
import org.example.entity.MonthEntry;

import java.util.Map;

public interface BalanceService {
    Map<MonthEntry, Integer> getNegativeDaysInMonths(Account account);
}
