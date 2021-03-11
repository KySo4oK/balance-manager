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
            int amountSum = monthListMap.get(month).stream()
                    .map(Transaction::getAmount)
                    .mapToInt(Integer::intValue)
                    .sum();
            balance += amountSum;
            result.put(month, balance);
        }
        return result;
    }
}
