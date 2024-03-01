package ru.java.collections;

import java.time.DayOfWeek;
import java.util.*;

/**
 * В этом примере HashMap используется для отслеживания количества различных слов,
 * и выводится информация о каждом слове и его количестве.
 */
public class MapExample {
    public static void main(String[] args) {
        // Неупорядоченная реализация Map.
        // Основана на хеш-таблице.
        // Разрешает null в качестве ключа и значения.
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Упорядоченная реализация Map, сохраняющая порядок вставки.
        // Добавляет возможность итерации по элементам в порядке вставки.
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Реализация Map, основанная на красно-черном дереве.
        // Сортирует элементы по их ключам в естественном порядке или с использованием компаратора.
        Map<String, Integer> treeMap = new TreeMap<>();

        // Старая синхронизированная версия HashMap.
        // Разрешает null в качестве ключа и значения.
        // Редко используется из-за синхронизации, что может вызывать проблемы производительности
        Map<String, Integer> hashTable = new Hashtable<>();

        // Реализация Map для использования в качестве хранилища, когда ключи - перечисления.
        // Эффективна и компактна для перечислений
        Map<DayOfWeek, String> enumMap = new EnumMap<>(DayOfWeek.class);



        // Добавление записей в Map
        wordCountMap.put("apple", 3);
        wordCountMap.put("banana", 2);
        wordCountMap.put("orange", 5);

        // Получение значения по ключу
        System.out.println("Количество яблок: " + wordCountMap.get("apple"));

        // Перебор записей в Map
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println("Слово: " + entry.getKey() + ", Количество: " + entry.getValue());
        }
    }
}
