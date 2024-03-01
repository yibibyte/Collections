package ru.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 22));

        // Сортировка по имени с использованием Comparator
        // Comparator.comparing используется для определения сравнения объектов по их имени
        Collections.sort(people, Comparator.comparing(Person::getName));

        System.out.println("Список людей по имени:");
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
