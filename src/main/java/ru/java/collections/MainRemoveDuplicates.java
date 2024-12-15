package ru.java.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainRemoveDuplicates {
    public static void main(String[] args) {
        List<String> input = List.of("apple", "banana", "apple", "orange", "banana");
        List<String> result = removeDuplicates(input);
        System.out.println(result);

        // Ожидаемый результат: [apple, banana, orange]
    }

    private static List<String> removeDuplicates(List<String> input) {
        Set<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (String s : input) {
            if (!set.contains(s)) {
                set.add(s);
                result.add(s);
            }
        }
        return result;
    }
}

