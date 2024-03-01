package ru.java.collections;

import java.util.Iterator;

// Простой класс, реализующий Iterable
public class Main {
    public static void main(String[] args) {
        String[] array = {"One", "Two", "Three", "Four"};

        // Используем наш класс MyIterable
        MyIterable myIterable = new MyIterable(array);

        // Итерация с использованием цикла foreach
        for (String element : myIterable) {
            System.out.println(element);
        }
    }
}
