package ru.java.collections;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionApp {
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;
    private JTextField inputField;
    private List<String> collection;

    public CollectionApp() {
        collection = new ArrayList<>();
        listModel = new DefaultListModel<>();
        frame = new JFrame("Swing Collection App");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Список для отображения элементов
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Панель с кнопками и полем ввода
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        inputField = new JTextField(20);
        panel.add(inputField);

        JButton addButton = new JButton("Добавить");
        JButton removeButton = new JButton("Удалить");

        panel.add(addButton);
        panel.add(removeButton);

        frame.add(panel, BorderLayout.SOUTH);

        // Обработчик добавления элемента
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                if (!input.isEmpty()) {
                    collection.add(input);
                    listModel.addElement(input);
                    inputField.setText("");
                }
            }
        });

        // Обработчик удаления элемента
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = itemList.getSelectedValue();
                if (selectedItem != null) {
                    collection.remove(selectedItem);
                    listModel.removeElement(selectedItem);
                }
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CollectionApp app = new CollectionApp();
                app.show();
            }
        });
    }
}
