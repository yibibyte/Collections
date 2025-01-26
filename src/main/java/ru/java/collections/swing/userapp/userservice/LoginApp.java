package ru.java.collections.swing.userapp.userservice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

// Класс для окна авторизации
class LoginApp extends JFrame {
    private JButton loginButton;
    private JButton cancelButton;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JPanel loginPanel;

    private final String DB_URL = DatabaseConfig.getDbUrl();
    private final String USERNAME = DatabaseConfig.getUsername();
    private final String PASSWORD = DatabaseConfig.getPassword();

    LoginApp() {
        initComponents();
        pack();
        setLocationRelativeTo(null); // Центрирование окна
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 20);

        // Email
        emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(emailLabel, gbc);

        emailTextField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(emailTextField, gbc);

        // Пароль
        passwordLabel = new JLabel("Пароль:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passwordField, gbc);

        // Кнопка входа
        loginButton = new JButton("Войти");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // Кнопка отмены
        cancelButton = new JButton("Отмена");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(cancelButton, gbc);

        add(loginPanel);

        // Обработчик кнопки входа
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
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

    private void authenticateUser() {
        String email = emailTextField.getText().trim();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, заполните все поля.", "Ошибка", INFORMATION_MESSAGE);
            return;
        }

        // Подключение к базе данных и проверка пользователя
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.users WHERE email = ? AND pass = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Вы успешно вошли в систему!", "Успех", INFORMATION_MESSAGE);
                showProfile(resultSet);
            } else {
                JOptionPane.showMessageDialog(this, "Неверный email или пароль.", "Ошибка", INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Произошла ошибка при подключении к базе данных.", "Ошибка", INFORMATION_MESSAGE);
        }
    }

    private void showProfile(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phoneNumber = resultSet.getString("phone_number");
        String email = resultSet.getString("email");

        JOptionPane.showMessageDialog(this, "Анкета пользователя:\n\nИмя: " + firstName +
                        "\nФамилия: " + lastName + "\nТелефон: " + phoneNumber + "\nEmail: " + email,
                "Профиль пользователя", INFORMATION_MESSAGE);
    }
}
