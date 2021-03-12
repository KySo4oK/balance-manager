package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.Month;
import java.time.Year;
import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode
public class MonthEntry implements Map.Entry<Year, Month>, Comparable<MonthEntry> {
    private final Year year;
    private Month month;

    @Override
    public Year getKey() {
        return year;
    }

    @Override
    public Month getValue() {
        return month;
    }

    @Override
    public Month setValue(Month value) {
        this.month = value;
        return value;
    }

    @Override
    public int compareTo(MonthEntry o) {
        int compareYears = year.compareTo(o.year);
        if (compareYears == 0) {
            return month.compareTo(o.month);
        }
        return compareYears;
    }
}
