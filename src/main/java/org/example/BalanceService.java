package org.example;

import org.example.entity.Account;

import java.time.Month;
import java.util.Map;

public interface BalanceService {
    Map<Month, Integer> getNegativeDaysInMonths(Account account);
}
