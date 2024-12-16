package ru.java.collections.swing;

import javax.swing.*;

public class SwingApp extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JPanel mainPanel;

    public SwingApp() {

        // Инициализация компонентов
        mainPanel = new JPanel();
        textField1 = new JTextField(15);
        textField2 = new JTextField(15);
        button1 = new JButton("Перенести и очистить");

        // Добавление компонентов на панель
        mainPanel.add(textField1);
        mainPanel.add(textField2);
        mainPanel.add(button1);

        // Обработчик нажатия кнопки для переноса текста
        button1.addActionListener(e -> {
            String text = textField1.getText(); // Получаем текст из первого поля
            textField2.setText(text);           // Устанавливаем текст во второе поле
            textField1.setText("");             // Очищаем первое поле
        });

        this.setContentPane(mainPanel); // Здесь устанавливаем mainPanel как contentPane
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingApp::new);
    }
}


//private void createUIComponents() {
//    textField1 = new JTextField(15); // Инициализация текстового поля
//    textField2 = new JTextField(15);
//    button1 = new JButton("Перенести и очистить");
//
//    // Добавление обработчиков событий
//    button1.addActionListener(e -> {
//        String text = textField1.getText();
//        textField2.setText(text);
//        textField1.setText("");
//    });
//}