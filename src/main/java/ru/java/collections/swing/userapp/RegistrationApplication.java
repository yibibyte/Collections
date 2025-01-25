package ru.java.collections.swing.userapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

//    // Константа для хранения URL базы данных
//    private final String DB_URL = "jdbc:postgresql://localhost:5432/db_users";
//    // Имя пользователя БД
//    private final String USERNAME = "postgres";
//    // Пароль пользователя БД
//    private final String PASSWORD = "postgres";

public class RegistrationApplication extends JFrame {
    private JButton registrationButton;
    private JButton cancelButton;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JPanel registrationPanel;

    private final String DB_URL = DatabaseConfig.getDbUrl();
    private final String USERNAME = DatabaseConfig.getUsername();
    private final String PASSWORD = DatabaseConfig.getPassword();

    RegistrationApplication() {
        initComponents();
        pack();
        setLocationRelativeTo(null); // Центрирование окна
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 20);

        // Имя
        firstNameLabel = new JLabel("Имя:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        registrationPanel.add(firstNameLabel, gbc);

        firstNameTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        registrationPanel.add(firstNameTextField, gbc);

        // Фамилия
        lastNameLabel = new JLabel("Фамилия:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        registrationPanel.add(lastNameLabel, gbc);

        lastNameTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        registrationPanel.add(lastNameTextField, gbc);

        // Телефон
        phoneNumberLabel = new JLabel("Телефон:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        registrationPanel.add(phoneNumberLabel, gbc);

        phoneNumberTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        registrationPanel.add(phoneNumberTextField, gbc);

        // Email
        emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        registrationPanel.add(emailLabel, gbc);

        emailTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        registrationPanel.add(emailTextField, gbc);

        // Пароль
        passwordLabel = new JLabel("Пароль:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        registrationPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        registrationPanel.add(passwordField, gbc);

        // Кнопка регистрации
        registrationButton = new JButton("Зарегистрироваться");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        registrationPanel.add(registrationButton, gbc);

        // Кнопка отмены
        cancelButton = new JButton("Отмена");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        registrationPanel.add(cancelButton, gbc);

        add(registrationPanel);

        // Обработчик кнопки регистрации
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        // Обработчик кнопки отмены
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
        LoginApp loginApp = new LoginApp();
        loginApp.setVisible(true);
    }
}

