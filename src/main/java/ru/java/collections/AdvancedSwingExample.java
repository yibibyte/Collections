package ru.java.collections;

import javax.swing.*; // Импортируем Swing-компоненты
import java.awt.*; // Для работы с компоновкой и другими графическими элементами
import java.awt.event.*; // Для обработки событий
import java.util.*; // Для работы с коллекциями

/**
 * Пояснение к коду:
 *
 *     Основные компоненты:
 *         JFrame: Главное окно приложения.
 *         JPanel: Панели для компоновки и группировки компонентов.
 *         JTextField: Текстовое поле для ввода текста.
 *         JList: Список с несколькими элементами для выбора.
 *         JRadioButton и ButtonGroup: Группа радиокнопок для выбора одной опции.
 *         JCheckBox: Чекбокс для подтверждения действия.
 *         JButton: Кнопка для выполнения действия.
 *         JToolBar: Панель инструментов с кнопками.
 *
 *     Компоновка:
 *         Мы используем BorderLayout для основного окна, чтобы разместить панель инструментов (NORTH), центральные компоненты (CENTER) и нижнюю панель (SOUTH).
 *         Для центральной панели используется GridLayout(2, 2), чтобы разместить компоненты в сетке 2x2 (метка, текстовое поле, список и панель с радиокнопками).
 *
 *     Обработчики событий:
 *         Обработчик для кнопки "Подтвердить" проверяет, был ли выбран чекбокс. Если да, то показывается окно с сообщением, которое выводит введенный текст, выбранный элемент из списка и выбранную опцию из радиокнопок. Если чекбокс не выбран, появляется предупреждение.
 *         В примере использована библиотека JOptionPane для отображения сообщений.
 *
 *     Дополнительные элементы:
 *         Список (JList): позволяет пользователю выбрать элемент из предустановленного списка.
 *         Радиокнопки (JRadioButton): используются для выбора одной из двух опций.
 *         Чекбокс (JCheckBox): добавляет дополнительную валидацию перед выполнением действия.
 */

public class AdvancedSwingExample {

    public static void main(String[] args) {
        // Гарантируем, что интерфейс будет создаваться в правильном потоке
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); // Создаем и отображаем графический интерфейс
            }
        });
    }

    private static void createAndShowGUI() {
        // Создаем основное окно приложения (JFrame)
        JFrame frame = new JFrame("Продвинутый пример на Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрытие программы при закрытии окна
        frame.setSize(600, 400); // Устанавливаем размеры окна

        // Создаем панель для добавления компонентов
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Используем BorderLayout для панелей

        // Панель инструментов (для кнопок)
        JToolBar toolBar = new JToolBar();
        JButton btnOpen = new JButton("Открыть");
        JButton btnSave = new JButton("Сохранить");
        toolBar.add(btnOpen); // Добавляем кнопку "Открыть"
        toolBar.add(btnSave); // Добавляем кнопку "Сохранить"
        frame.add(toolBar, BorderLayout.NORTH); // Размещаем панель инструментов сверху

        // Центр окна: добавляем текстовое поле, список и радиокнопки
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2)); // Располагаем компоненты в сетке 2x2

        // Создаем метку (Label)
        JLabel label = new JLabel("Введите текст: ");
        centerPanel.add(label);

        // Создаем текстовое поле (TextField)
        JTextField textField = new JTextField();
        centerPanel.add(textField);

        // Создаем список (JList) с элементами
        String[] listData = {"Элемент 1", "Элемент 2", "Элемент 3", "Элемент 4"};
        JList<String> list = new JList<>(listData);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Одновременный выбор только одного элемента
        centerPanel.add(new JScrollPane(list)); // Используем JScrollPane для прокрутки списка

        // Создаем панель с радиокнопками (Radio Buttons)
        JPanel radioPanel = new JPanel();
        JRadioButton option1 = new JRadioButton("Опция 1");
        JRadioButton option2 = new JRadioButton("Опция 2");
        ButtonGroup group = new ButtonGroup(); // Группа радиокнопок
        group.add(option1);
        group.add(option2);
        radioPanel.add(option1);
        radioPanel.add(option2);
        centerPanel.add(radioPanel);

        // Добавляем центральную панель в окно
        frame.add(centerPanel, BorderLayout.CENTER);

        // Нижняя панель с чекбоксом и кнопкой
        JPanel bottomPanel = new JPanel();
        JCheckBox checkBox = new JCheckBox("Подтвердить действие");
        JButton submitButton = new JButton("Подтвердить");

        // Обработчик для кнопки "Подтвердить"
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    String inputText = textField.getText();
                    String selectedOption = option1.isSelected() ? "Опция 1" : option2.isSelected() ? "Опция 2" : "Нет выбора";
                    String selectedItem = list.getSelectedValue();
                    JOptionPane.showMessageDialog(frame,
                            "Текст: " + inputText + "\nВыбранный элемент: " + selectedItem +
                                    "\nВыбранная опция: " + selectedOption,
                            "Подтверждение",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Пожалуйста, подтвердите действие с помощью чекбокса!",
                            "Ошибка", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Добавляем компоненты на нижнюю панель
        bottomPanel.add(checkBox);
        bottomPanel.add(submitButton);
        frame.add(bottomPanel, BorderLayout.SOUTH); // Размещаем снизу

        // Отображаем окно
        frame.setVisible(true);
    }
}
