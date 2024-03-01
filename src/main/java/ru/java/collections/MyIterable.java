package ru.java.collections;

import java.util.Iterator;

class MyIterable implements Iterable<String> {
    private String[] elements;

    public MyIterable(String[] elements) {
        this.elements = elements;
    }

    // Реализация интерфейса Iterator
    private class MyIterator implements Iterator<String> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < elements.length;
        }

        @Override
        public String next() {
            return elements[index++];
        }
    }

    // Реализация метода iterator() интерфейса Iterable
    @Override
    public Iterator<String> iterator() {
        return new MyIterator();
    }
}
