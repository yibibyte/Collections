package ru.java.collections;

import java.util.*;

/**
 *  "This is Class CollectionExample"
 */
public class CollectionExample {
    public static void main(String[] args) {

        int[][] numbers = new int[][]{};

        // Создание списка
        List<String> list = new ArrayList<>();
        List<String> list2 = new LinkedList<>();


        list.add("Java");
        list.add("Java");
        list.add("Java");
        list.add("Java");
        list.add("Python");
        list.add("C++");

        // Создание множества
        Set<Integer> setAgePerson = new HashSet<>();
        setAgePerson.add(1);
        setAgePerson.add(1);
        setAgePerson.add(1);
        setAgePerson.add(2);
        setAgePerson.add(4);
        setAgePerson.add(2);
        setAgePerson.add(3);
        setAgePerson.add(3);
        System.out.println(setAgePerson);

        // Создание отображения
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("One", 2);
        map.put("Three", 3);

        // Итерация по списку
//        for (String item : list) {
//            System.out.println(item);
//        }

//        // Итерация по множеству
//        for (int num : setAgePerson) {
//            System.out.println(num);
//        }

/*        // Итерация по отображению
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/
    }
}
