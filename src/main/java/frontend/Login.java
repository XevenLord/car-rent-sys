package frontend;

import org.cr.model.user.Admin;
import org.cr.model.user.Customer;
import org.cr.store.AdminStore;
import org.cr.store.CustomerStore;
import org.cr.util.UserSession;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private final JPanel MiniPanel, MainPanel;
    private final JButton Close_Button, Login_Button, Register_Button;
    private final JLabel PW_Label, UN_Label, Image_jLabel, info_Label;
    private final JTextField UN_TextField;
    private final JPasswordField Password_Field;
    private final JRadioButton adminRadioButton, customerRadioButton;
    private final ButtonGroup userTypeGroup;

    public Login() {

        MiniPanel = new JPanel();
        MainPanel = new JPanel();

        Close_Button = new JButton("Close");
        Login_Button = new JButton("Login");
        Register_Button = new JButton("Register");

        adminRadioButton = new JRadioButton("Admin");
        customerRadioButton = new JRadioButton("Customer");
        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(adminRadioButton);
        userTypeGroup.add(customerRadioButton);
        adminRadioButton.setSelected(true);

        PW_Label = new JLabel("Password");
        UN_Label = new JLabel("Email");
        info_Label = new JLabel("Please Enter your Login Details");
        Image_jLabel = new JLabel();

        UN_TextField = new JTextField();
        Password_Field = new JPasswordField();


        MiniPanel.setBackground(Color.BLACK);
        MiniPanel.setForeground(Color.WHITE);
        MiniPanel.setLayout(new FlowLayout());
        MiniPanel.setPreferredSize(new Dimension(350, 400));

        MainPanel.setMinimumSize(new Dimension(1366, 730));
        MainPanel.setLayout(new AbsoluteLayout());

        Login_Button.setPreferredSize(new Dimension(150, 20));
        Close_Button.setPreferredSize(new Dimension(80, 20));
        Register_Button.setPreferredSize(new Dimension(150, 20));

        info_Label.setFont(new Font("Consolas", Font.BOLD, 18)); // Consolas, Bold , 18pt
        info_Label.setForeground(Color.WHITE);

        UN_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        UN_Label.setForeground(Color.WHITE);
        UN_Label.setPreferredSize(new Dimension(200, 20));

        PW_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        PW_Label.setForeground(Color.WHITE);
        PW_Label.setPreferredSize(new Dimension(200, 20));

        Image_jLabel.setMinimumSize(new Dimension(1366, 730));
        java.net.URL imgURL = getClass().getResource("/LoginImage.jpg");
        if (imgURL != null) {
            Image_jLabel.setIcon(new ImageIcon(imgURL));
        } else {
            System.err.println("Couldn't find file: LoginImage.jpg");
        }

        UN_TextField.setPreferredSize(new Dimension(200, 20));
        Password_Field.setPreferredSize(new Dimension(200, 20));

        MiniPanel.add(info_Label);
        MiniPanel.add(adminRadioButton);
        MiniPanel.add(customerRadioButton);
        MiniPanel.add(UN_Label);
        MiniPanel.add(UN_TextField);
        MiniPanel.add(PW_Label);
        MiniPanel.add(Password_Field);
        MiniPanel.add(Login_Button);
        MiniPanel.add(Close_Button);
        MiniPanel.add(Register_Button);

        MainPanel.add(MiniPanel, new AbsoluteConstraints(50, 150, 350, 240));
        MainPanel.add(Image_jLabel, new AbsoluteConstraints(0, 0));

        Login_Button.addActionListener(new LoginActionListener());
        Close_Button.addActionListener(new LoginActionListener());
        Register_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToRegister();
            }
        });
    }

    /**
     * @return the MainPanel
     */
    public JPanel getMainPanel() {
        return MainPanel;
    }

    private void navigateToRegister() {
        Runner.getFrame().dispose();
        Runner.getFrame().setContentPane(new Register().getMainPanel());
        Runner.getFrame().setVisible(true);
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Close": {
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                            + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if (showConfirmDialog == 0) {
                        System.exit(0);
                    }
                    break;
                }
                case "Login": {
                    if (adminRadioButton.isSelected()) {
                        AdminStore adminStore = new AdminStore().get();
                        Admin admin = adminStore.getByEmail(UN_TextField.getText().trim());
                        if (admin != null && String.valueOf(Password_Field.getPassword()).equals(admin.getPw())) {
                            UN_TextField.setText("");
                            Password_Field.setText("");
                            Runner.getFrame().dispose();
                            Parent_JFrame frame = new Parent_JFrame();
                            MainMenu menu = new MainMenu();
                            JFrame mainFrame = Parent_JFrame.getMainFrame();
                            mainFrame.add(menu.getMainPanel());
                            mainFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Email/Password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (customerRadioButton.isSelected()) {
                        CustomerStore customerStore = new CustomerStore().get();
                        Customer customer = customerStore.getByEmail(UN_TextField.getText().trim());
                        if (customer != null && String.valueOf(Password_Field.getPassword()).equals(customer.getPw())) {
                            // Store the logged-in customer in the UserSession
                            UserSession.getInstance().setLoggedInCustomer(customer);

                            UN_TextField.setText("");
                            Password_Field.setText("");
                            Runner.getFrame().dispose();
                            CParent_JFrame frame = new CParent_JFrame();
                            CMainMenu menu = new CMainMenu();
                            JFrame mainFrame = CParent_JFrame.getMainFrame();
                            mainFrame.add(menu.getMainPanel());
                            mainFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Email/Password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
            }
        }
    }
}