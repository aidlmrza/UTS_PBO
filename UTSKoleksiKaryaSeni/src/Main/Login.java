package Main;

import GUI.KaryaSeniGUI;
import User.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    private JTextField userIdField;
    private JPasswordField passwordField;

    public Login(JFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 

        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField(15); 
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15); 
        JButton loginButton = new JButton("Login");

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userIdLabel, gbc);
        
        gbc.gridx = 1;
        add(userIdField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        
        gbc.gridx = 1;
        add(passwordField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; 
        add(loginButton, gbc);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = userIdField.getText();
                String password = new String(passwordField.getPassword());

                User user = new User("Amir", "amir@gmail.com", "aidlmrza", "amiraja");

                if (user.getUserID().equals(userID) && user.getUserPW().equals(password)) {
                    
                    KaryaSeniGUI gui = new KaryaSeniGUI(user);
                    gui.setVisible(true);
                    frame.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Login gagal");
                }
            }
        });
    }
}
