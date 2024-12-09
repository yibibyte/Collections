package ru.java.collections;

import java.util.*;

public class CollectionExample {
    public static void main(String[] args) {

        int[][] numbers = new int[][]{};

        // Создание списка
        List<String> list = new ArrayList<>();
        List<String> list2 = new LinkedList<>();


        list.add("Java");
        list.add("Python");
        list.add("C++");

        // Создание множества
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(4);
        set.add(2);
        set.add(3);
        set.add(3);
        System.out.println(set);

        // Создание отображения
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("One", 2);
        map.put("Three", 3);

        // Итерация по списку
        for (String item : list) {
            System.out.println(item);
        }

//        // Итерация по множеству
//        for (int num : set) {
//            System.out.println(num);
//        }

/*        // Итерация по отображению
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/
    }
}
