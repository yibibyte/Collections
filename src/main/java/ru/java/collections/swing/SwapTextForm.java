package ru.java.collections.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwapTextForm extends JFrame{
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel textPanel;

    public SwapTextForm() {
                setTitle("Меняем текст");
        setContentPane(textPanel);
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String text = textField1.getText(); // Получаем текст из первого поля
            textField2.setText(text);           // Устанавливаем текст во второе поле
            textField1.setText("");             // Очищаем первое поле
                // Задание
                // Необходимо решение как осуществить процесс обратный и чтобы кнопка всегда пергоняла наш текст не только в одну сторону?
            }
        });
    }
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(SwapTextForm::new);
        new SwapTextForm();
    }
}
