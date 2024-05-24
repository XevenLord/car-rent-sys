package frontend;

import org.cr.model.user.Admin;
import org.cr.store.AdminStore;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private final JPanel MiniPanel, MainPanel;
    private final JButton Close_Button, Login_Button;
    private final JLabel PW_Label, UN_Label, Image_jLabel, info_Label;
    private final JTextField UN_TextField;
    private final JPasswordField Password_Field;

    public Login() {

        MiniPanel = new JPanel();
        MainPanel = new JPanel();

        Close_Button = new JButton("Close");
        Login_Button = new JButton("Login");

        PW_Label = new JLabel("Password");
        UN_Label = new JLabel("Email");
        info_Label = new JLabel("Please Enter your Login Details");
        Image_jLabel = new JLabel();

        UN_TextField = new JTextField();
        Password_Field = new JPasswordField();

        MiniPanel.setBackground(Color.BLUE);
        MiniPanel.setForeground(Color.WHITE);
        MiniPanel.setLayout(new FlowLayout());

        MainPanel.setMinimumSize(new Dimension(1366, 730));
        MainPanel.setLayout(new AbsoluteLayout());

        Login_Button.setPreferredSize(new Dimension(80, 20));
        Close_Button.setPreferredSize(new Dimension(80, 20));

        info_Label.setFont(new Font("Consolas", Font.BOLD, 18)); // Consolas, Bold , 18pt
        info_Label.setForeground(Color.WHITE);

        UN_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        UN_Label.setForeground(Color.WHITE);
        UN_Label.setPreferredSize(new Dimension(100, 20));

        PW_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        PW_Label.setForeground(Color.WHITE);
        PW_Label.setPreferredSize(new Dimension(100, 20));

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
        MiniPanel.add(UN_Label);
        MiniPanel.add(UN_TextField);
        MiniPanel.add(PW_Label);
        MiniPanel.add(Password_Field);
        MiniPanel.add(Login_Button);
        MiniPanel.add(Close_Button);

        MainPanel.add(MiniPanel, new AbsoluteConstraints(50, 150, 350, 125));
        MainPanel.add(Image_jLabel, new AbsoluteConstraints(0, 0));

        Login_Button.addActionListener(new LoginActionListener());
        Close_Button.addActionListener(new LoginActionListener());
    }

    /**
     * @return the MainPanel
     */
    public JPanel getMainPanel() {
        return MainPanel;
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminStore adminStore = new AdminStore().get();
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
                    Admin admin = adminStore.getByEmail(UN_TextField.getText().trim());
                    if (admin != null
                            && String.valueOf(Password_Field.getPassword()).equals(admin.getPw())) {
                        UN_TextField.setText("");
                        Password_Field.setText("");
                        Runner.getFrame().dispose();
                        Parent_JFrame frame = new Parent_JFrame();
                        MainMenu menu = new MainMenu();
                        JFrame mainFrame = Parent_JFrame.getMainFrame();
                        mainFrame.add(menu.getMainPanel());
                        mainFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid UserName/Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        }
    }
}