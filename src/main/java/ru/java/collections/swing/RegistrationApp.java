package ru.java.collections.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class RegistrationApp extends JDialog{
    private JButton resistraionButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel RegistrationPanel;
    RegistrationApp(){
        setTitle("Регистрация пользователя");
        setContentPane(RegistrationPanel);
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RegistrationPanel, "Вы успешно отменили регистрацию пользователя");
            }
        });
        resistraionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RegistrationPanel, "Вы успешно зарегистрировались");
            }
        });
    }

    public static void main(String[] args) {
        new RegistrationApp();

    }
}
