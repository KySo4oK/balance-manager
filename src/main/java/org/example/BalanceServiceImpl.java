package org.example;

import org.example.entity.Account;
import org.example.entity.Transaction;

import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BalanceServiceImpl implements BalanceService {
    @Override
    public Map<Month, Integer> getNegativeDaysInMonths(Account account) {
        int balance = account.getStartBalance();
        List<Transaction> transactions = account.getTransactions();
        transactions.sort(Comparator.comparing(Transaction::getDate));
        Map<Month, List<Transaction>> monthListMap = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getDate().getMonth()));
        Map<Month, Integer> result = new HashMap<>();
        for (Month month : monthListMap.keySet().stream().sorted().collect(Collectors.toList())) {
            List<Transaction> transactionsForMonth = monthListMap.get(month);
            Map<Integer, List<Transaction>> collect = transactionsForMonth.stream().collect(Collectors.groupingBy(transaction -> transaction.getDate().getDayOfMonth()));
            int negativeDays = 0;
            for (Integer dayOfMonth : collect.keySet()) {
                int amountSum = collect.get(dayOfMonth).stream()
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
}
