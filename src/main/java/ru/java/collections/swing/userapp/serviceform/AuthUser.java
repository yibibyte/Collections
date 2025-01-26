package ru.java.collections.swing.userapp.serviceform;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class AuthUser extends JDialog {
    private JPanel authPane;
    private JButton loginButton;
    private JButton buttonCancel;
    private JTextField loginEmailField;
    private JPasswordField passwordField;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    private final String DB_URL = DatabaseConfig.getDbUrl();
    private final String USERNAME = DatabaseConfig.getUsername();
    private final String PASSWORD = DatabaseConfig.getPassword();

    public AuthUser() {

//        super(MainUserWindows,"Диалоговое окно", true); // true - модальное окно
        setSize(300, 200);
//        setLocationRelativeTo(parent);

//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //getRootPane().setDefaultButton(loginButton);

        setTitle("Авторизация");
        setContentPane(authPane);
        setSize(375, 150);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        setModal(true);
//        setVisible(true);


        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLogin();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        authPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onLogin() {
        authenticateUser();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void authenticateUser() {
        String email = loginEmailField.getText().trim();
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

//    public static void main(String[] args) {
//        AuthUser authUser = new AuthUser();
//        authUser.pack();
//        authUser.setVisible(true);
//        System.exit(0);
//    }
}
