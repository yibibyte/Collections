package ru.java.collections.swing;

import javax.swing.*;

public class RegistrationApp extends JDialog{
    private JButton resistraionButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel RegistrationPanel;
    RegistrationApp(){
        setTitle("Registration");
        setContentPane(RegistrationPanel);
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new RegistrationApp();

    }
}
