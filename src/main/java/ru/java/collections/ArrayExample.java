package ru.java.collections;

// Простой класс, реализующий Iterable
public class ArrayExample {
    //static final Integer CONSTAINER_SIZE = 10;
    public static void main(String[] args) {

        Integer[] arrayInt = {1, 2, 3, 4};
        String[] arrayString = {"One", "Two", "Three", "Four"};

        // Используем наш класс MyIterable
        MyIterable myIterable = new MyIterable(arrayString);

        // Итерация с использованием цикла foreach
        for (String element : myIterable) {
            System.out.println(element);
        }
    }
}
