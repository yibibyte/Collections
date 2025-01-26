package ru.java.collections.swing.userapp.serviceform;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class RegistrationUser extends JDialog{
    private JButton registerButton;
    private JButton cancelButton;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailTextLabel;
    private JLabel passwordLabel;
    private JPanel registrationPanel;

    private final String DB_URL = DatabaseConfig.getDbUrl();
    private final String USERNAME = DatabaseConfig.getUsername();
    private final String PASSWORD = DatabaseConfig.getPassword();


    public RegistrationUser() {

        setSize(600, 250);
        setTitle("Регистрация пользователя");
        setContentPane(registrationPanel);
//        pack();
        setLocationRelativeTo(null); // Центрирование окна
        setResizable(false);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void registerUser() {
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String phoneNumber = phoneNumberTextField.getText().trim();
        String email = emailTextField.getText().trim();

        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() ||
                email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, заполните все поля.", "Ошибка", INFORMATION_MESSAGE);
            return;
        }

        // Подключение к базе данных и вставка нового пользователя
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.users (first_name, last_name, phone_number, pass, email) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setString(4, password);
            statement.setString(5, email);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Пользователь успешно зарегистрирован!", "Успех", INFORMATION_MESSAGE);
                dispose();
                openLoginWindow();
            } else {
                JOptionPane.showMessageDialog(this, "Не удалось зарегистрировать пользователя.", "Ошибка", INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Произошла ошибка при подключении к базе данных.", "Ошибка", INFORMATION_MESSAGE);
        }
    }

    private void openLoginWindow() {
        AuthUser authUser = new AuthUser();
        authUser.pack();
        authUser.setVisible(true);
//        System.exit(0);
    }
}
