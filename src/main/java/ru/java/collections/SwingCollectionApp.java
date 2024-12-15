package ru.java.collections;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SwingCollectionApp {

    // Главные компоненты приложения
    private JFrame frame; // Основное окно приложения
    private DefaultListModel<String> listModel; // Модель для списка (отображает элементы)
    private JList<String> itemList; // Список для отображения данных
    private JTextField inputField; // Поле ввода для добавления новых элементов
    private List<String> collection; // Коллекция, с которой будет работать приложение

    // Конструктор для настройки интерфейса
    public SwingCollectionApp() {
        collection = new ArrayList<>(); // Инициализация коллекции как ArrayList
        listModel = new DefaultListModel<>(); // Инициализация модели для списка

        // Создание главного окна
        frame = new JFrame("Application Application Swing ");
        frame.setSize(500, 300); // Устанавливаем размер окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрытие приложения при закрытии окна
        frame.setLayout(new BorderLayout()); // Используем BorderLayout для размещения компонентов

        // Создание списка для отображения коллекции
        itemList = new JList<>(listModel); // JList использует listModel для отображения элементов
        JScrollPane scrollPane = new JScrollPane(itemList); // Оборачиваем список в JScrollPane для прокрутки
        frame.add(scrollPane, BorderLayout.CENTER); // Размещаем на центральной части окна

        // Создание панели для ввода данных и кнопок
        JPanel panel = new JPanel(); // Панель для размещения ввода и кнопок
        panel.setLayout(new FlowLayout()); // Расположение компонентов по горизонтали

        inputField = new JTextField(20); // Поле для ввода данных с размером 20 символов
        panel.add(inputField); // Добавляем поле ввода на панель

        JButton addButton = new JButton("Добавить"); // Кнопка для добавления элементов
        JButton removeButton = new JButton("Удалить"); // Кнопка для удаления элементов
        panel.add(addButton); // Добавляем кнопку "Добавить"
        panel.add(removeButton); // Добавляем кнопку "Удалить"

        frame.add(panel, BorderLayout.SOUTH); // Размещаем панель с кнопками и полем ввода на нижней части окна

        // Обработчик события для кнопки "Добавить"
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim(); // Получаем текст из поля ввода и убираем лишние пробелы
                if (!input.isEmpty()) { // Проверяем, чтобы текст не был пустым
                    collection.add(input); // Добавляем элемент в коллекцию
                    listModel.addElement(input); // Добавляем элемент в модель списка (для отображения)
                    inputField.setText(""); // Очищаем поле ввода
                }
            }
        });

        // Обработчик события для кнопки "Удалить"
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Получаем выбранный элемент из списка
                String selectedItem = itemList.getSelectedValue();
                if (selectedItem != null) { // Если элемент выбран
                    collection.remove(selectedItem); // Удаляем элемент из коллекции
                    listModel.removeElement(selectedItem); // Удаляем элемент из модели списка (для обновления UI)
                }
            }
        });
    }

    // Метод для отображения окна
    public void show() {
        frame.setVisible(true); // Показываем окно
    }

    // Точка входа для запуска приложения
    public static void main(String[] args) {
        // Запускаем приложение в потоке обработки событий Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SwingCollectionApp app = new SwingCollectionApp(); // Создаем экземпляр приложения
                app.show(); // Показываем окно
            }
        });
    }
}
