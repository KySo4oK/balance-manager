package org.example.service;

import org.example.entity.Account;
import org.example.entity.MonthEntry;
import org.example.entity.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceServiceTest {
    private BalanceService testedInstance = new BalanceServiceImpl();

    @Test
    public void shouldReturnTwoWhenTransactionInOneDay() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(-102, LocalDate.now()));
        transactions.add(new Transaction(-102, LocalDate.now()));
        Account account = new Account(100, transactions);
        Map<MonthEntry, Integer> negativeDaysInMonths = testedInstance.getNegativeDaysInMonths(account);
        Assert.assertTrue(negativeDaysInMonths.containsValue(1));
    }

    @Test
    public void shouldReturnTwoWhenTransactionInAnotherYear() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(-102, LocalDate.now()));
        transactions.add(new Transaction(-102, LocalDate.now().minusYears(1)));
        Account account = new Account(100, transactions);
        Map<MonthEntry, Integer> negativeDaysInMonths = testedInstance.getNegativeDaysInMonths(account);
        Assert.assertEquals(2, negativeDaysInMonths.keySet().size());
    }

    @Test
    public void shouldReturnTwoWhenSecondDayIsPrevious() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(-102, LocalDate.now()));
        transactions.add(new Transaction(-102, LocalDate.now().minusDays(1)));
        Account account = new Account(100, transactions);
        Map<MonthEntry, Integer> negativeDaysInMonths = testedInstance.getNegativeDaysInMonths(account);
        Assert.assertFalse(negativeDaysInMonths.containsValue(2));
    }

    @Test
    public void shouldNotAddAmountsWhenMonthsNotEquals() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(102, LocalDate.now()));
        transactions.add(new Transaction(-102, LocalDate.now().minusMonths(1)));
        Account account = new Account(100, transactions);
        Map<MonthEntry, Integer> negativeDaysInMonths = testedInstance.getNegativeDaysInMonths(account);
        Assert.assertTrue(negativeDaysInMonths.containsValue(1));
    }
}