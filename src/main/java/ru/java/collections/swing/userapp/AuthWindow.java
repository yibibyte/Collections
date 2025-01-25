package ru.java.collections.swing.userapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthWindow extends JFrame implements ActionListener {

    private JButton loginButton;
    private JButton registerButton;
    RegistrationApplication registrationApplicatio;

    public AuthWindow() {
        super("Авторизация / Регистрация");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Выберите действие:");
        loginButton = new JButton("Авторизация");
        registerButton = new JButton("Регистрация");

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        panel.add(label);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == loginButton) {
            // Код для открытия окна авторизации
            LoginApp loginApp = new LoginApp();
            loginApp.setVisible(true);
            //JOptionPane.showMessageDialog(this, "Открыть окно авторизации", "Авторизация", JOptionPane.INFORMATION_MESSAGE);


        } else if (source == registerButton) {
            // Код для открытия окна регистрации
            //JOptionPane.showMessageDialog(this, "Открыть окно регистрации", "Регистрация", JOptionPane.INFORMATION_MESSAGE);
            new RegistrationApplication();

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AuthWindow::new);
    }
}