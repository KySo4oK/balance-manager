package org.example.service;

import org.example.entity.Account;
import org.example.entity.MonthEntry;
import org.example.entity.Transaction;

import java.time.Year;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BalanceServiceImpl implements BalanceService {
    @Override
    public Map<MonthEntry, Integer> getNegativeDaysInMonths(Account account) {
        int balance = account.getStartBalance();
        Map<MonthEntry, List<Transaction>> monthEntryTransactionsMap = getMonthEntryMap(account);
        Map<MonthEntry, Integer> result = new HashMap<>();
        for (MonthEntry month : monthEntryTransactionsMap.keySet().stream().sorted().collect(Collectors.toList())) {
            List<Transaction> transactionsForMonth = monthEntryTransactionsMap.get(month);
            Map<Integer, List<Transaction>> monthTransactionMap =
                    transactionsForMonth.stream()
                            .collect(Collectors.groupingBy(this::getTransactionDayOfMonth));
            int negativeDays = 0;
            for (Map.Entry<Integer, List<Transaction>> entry : monthTransactionMap.entrySet()) {
                int amountSum = monthTransactionMap.get(entry.getKey()).stream()
                        .map(Transaction::getAmount)
                        .mapToInt(Integer::intValue)
                        .sum();
                balance += amountSum;
                if (balance < 0) {
                    negativeDays++;
                }
            }
            result.put(month, negativeDays);
        }
        return result;
    }

    private int getTransactionDayOfMonth(Transaction transaction) {
        return transaction.getDate().getDayOfMonth();
    }

    private Map<MonthEntry, List<Transaction>> getMonthEntryMap(Account account) {
        List<Transaction> transactions = account.getTransactions();
        transactions.sort(Comparator.comparing(Transaction::getDate));
        return transactions.stream()
                .collect(Collectors.groupingBy(this::getMonthEntryFromTransaction));
    }

    private MonthEntry getMonthEntryFromTransaction(Transaction transaction) {
        return new MonthEntry(Year.of(transaction.getDate().getYear()),
                transaction.getDate().getMonth());
    }
}
