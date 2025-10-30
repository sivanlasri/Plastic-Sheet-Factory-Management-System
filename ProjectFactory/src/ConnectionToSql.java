import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class ConnectionToSql {
    public static void main(String[] args) {
        //panel for the username and the password
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Login Screen");
        frame.setSize(350,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        panel.setBackground(new Color(70, 162, 49, 255));
        //username label and text field
        JLabel userlabel = new JLabel("Username: ");
        userlabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
        userlabel.setBounds(10,20,100,25);
        panel.add(userlabel);
        JTextField username = new JTextField();
        username.setBounds(100,20,165,25);
        username.setVisible(true);
        panel.add(username);
        //password label and text field
        JLabel userlabel1 = new JLabel("Password: ");
        userlabel1.setFont(new Font("MV Boli", Font.PLAIN, 15));
        userlabel1.setBounds(10,50,80,25);
        panel.add(userlabel1);
        JPasswordField password = new JPasswordField();
        password.setBounds(100,50,165,25);
        password.setVisible(true);
        panel.add(password);
        //login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("MV Boli", Font.PLAIN, 15));
        loginButton.setBackground(new Color(168, 250, 100, 255));
        loginButton.setBounds(10,80,80,25);
        loginButton.addActionListener(e -> {
                    String enteredUsername = username.getText();
                    String enteredPassword = new String(password.getPassword());
                    try {
                        //creating connection to sql
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectfactory", enteredUsername, enteredPassword); //root for the connection to mysql
                        if(connection!=null) {
                            Statement statement = connection.createStatement();
                            //if connection has succeeded
                            JOptionPane.showMessageDialog(null, "The Connection succeed!");
                            new SqlQueries(connection, statement);
                            frame.dispose();
                            new GUI();
                        }
                    } catch (Exception ignored) {
                        //if it doesn't work a failed message will appear
                        JOptionPane.showMessageDialog(null, "The Connection has not succeed, please try again!");
                    }

        });
        panel.add(loginButton);
        frame.add(panel);
        frame.setVisible(true);
        GUI.centerFrameOnScreen(frame);
    }
}
