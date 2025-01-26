package ru.java.collections.swing.userapp.service.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUserWindows extends JFrame implements ActionListener{
    private JButton loginButton;
    private JButton registerButton;
    private JPanel MainPanel;

    public MainUserWindows() {
            setTitle("Главное окно пользователя");
            setContentPane(MainPanel);
            setSize(375, 150);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthUser authUser = new AuthUser();
                authUser.setVisible(true);

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationUser registrationUser = new RegistrationUser();
                registrationUser.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RegistrationUser registrationUser = new RegistrationUser();
        registrationUser.setVisible(true);
    }
    public static void main(String[] args) {
             new MainUserWindows();
    }
}
