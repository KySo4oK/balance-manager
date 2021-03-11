package org.example;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceServiceTest {
    private BalanceService testedInstance = new BalanceServiceImpl();

    @Test
    public void testGetNegativeDaysInMonths() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(-102, LocalDate.now()));
        transactions.add(new Transaction(-102, LocalDate.now()));
        Account account = new Account(100, transactions);
        Map<Month, Integer> negativeDaysInMonths = testedInstance.getNegativeDaysInMonths(account);
        Assert.assertTrue(negativeDaysInMonths.containsValue(-104));
    }
}