package ru.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 22));

        Collections.sort(people);

        System.out.println("Список людей по возрасту:");
        for (Person person : people) {
            System.out.println(person);
        }
    }
}