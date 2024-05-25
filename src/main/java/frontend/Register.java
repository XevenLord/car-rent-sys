package frontend;

import org.cr.model.user.Customer;
import org.cr.store.CustomerStore;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import java.util.regex.Pattern;

public class Register {

    private final JPanel MiniPanel, MainPanel;
    private final JButton Register_Button, Cancel_Button;
    private final JLabel PW_Label, UN_Label, Name_Label, ContactNo_Label, IC_Label, info_Label;
    private final JTextField UN_TextField, Name_TextField, ContactNo_TextField, IC_TextField;
    private final JPasswordField Password_Field;

    public Register() {
        MiniPanel = new JPanel();
        MainPanel = new JPanel();

        Register_Button = new JButton("Register");
        Cancel_Button = new JButton("Back Login");

        PW_Label = new JLabel("Password");
        UN_Label = new JLabel("Email");
        Name_Label = new JLabel("Name");
        ContactNo_Label = new JLabel("Contact No");
        IC_Label = new JLabel("IC");
        info_Label = new JLabel("Please Enter your Registration Details");

        UN_TextField = new JTextField();
        Name_TextField = new JTextField();
        ContactNo_TextField = new JTextField();
        IC_TextField = new JTextField();
        Password_Field = new JPasswordField();

        MiniPanel.setBackground(Color.BLUE);
        MiniPanel.setForeground(Color.WHITE);
        MiniPanel.setLayout(new FlowLayout());
        MiniPanel.setPreferredSize(new Dimension(450, 400));

        MainPanel.setMinimumSize(new Dimension(1366, 730));
        MainPanel.setLayout(new AbsoluteLayout());

        Register_Button.setPreferredSize(new Dimension(200, 20));
        Cancel_Button.setPreferredSize(new Dimension(140, 20));

        info_Label.setFont(new Font("Consolas", Font.BOLD, 18));
        info_Label.setForeground(Color.WHITE);

        UN_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        UN_Label.setForeground(Color.WHITE);
        UN_Label.setPreferredSize(new Dimension(200, 20));

        PW_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        PW_Label.setForeground(Color.WHITE);
        PW_Label.setPreferredSize(new Dimension(200, 20));

        Name_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        Name_Label.setForeground(Color.WHITE);
        Name_Label.setPreferredSize(new Dimension(200, 20));

        ContactNo_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        ContactNo_Label.setForeground(Color.WHITE);
        ContactNo_Label.setPreferredSize(new Dimension(200, 20));

        IC_Label.setFont(new Font("Consolas", Font.PLAIN, 18));
        IC_Label.setForeground(Color.WHITE);
        IC_Label.setPreferredSize(new Dimension(200, 20));

        UN_TextField.setPreferredSize(new Dimension(200, 20));
        Password_Field.setPreferredSize(new Dimension(200, 20));
        Name_TextField.setPreferredSize(new Dimension(200, 20));
        ContactNo_TextField.setPreferredSize(new Dimension(200, 20));
        IC_TextField.setPreferredSize(new Dimension(200, 20));

        MiniPanel.add(info_Label);
        MiniPanel.add(Name_Label);
        MiniPanel.add(Name_TextField);
        MiniPanel.add(UN_Label);
        MiniPanel.add(UN_TextField);
        MiniPanel.add(PW_Label);
        MiniPanel.add(Password_Field);
        MiniPanel.add(ContactNo_Label);
        MiniPanel.add(ContactNo_TextField);
        MiniPanel.add(IC_Label);
        MiniPanel.add(IC_TextField);
        MiniPanel.add(Register_Button);
        MiniPanel.add(Cancel_Button);

        MainPanel.add(MiniPanel, new AbsoluteConstraints(50, 150, 400, 400));

        Register_Button.addActionListener(new RegisterActionListener());
        Cancel_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToLogin();
            }
        });
    }

    /**
     * @return the MainPanel
     */
    public JPanel getMainPanel() {
        return MainPanel;
    }

    private void navigateToLogin() {
        Runner.getFrame().dispose();
        Runner.getFrame().setContentPane(new Login().getMainPanel());
        Runner.getFrame().setVisible(true);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pat.matcher(email).matches();
    }

    private boolean isValidContactNo(String contactNo) {
        return contactNo.matches("^01\\d{8,9}$");
    }

    private boolean isValidIC(String ic) {
        return ic.matches("^\\d{12}$");
    }

    private class RegisterActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerStore customerStore = new CustomerStore().get();
            String name = Name_TextField.getText().trim();
            String email = UN_TextField.getText().trim();
            String password = String.valueOf(Password_Field.getPassword()).trim();
            String contactNo = ContactNo_TextField.getText().trim();
            String ic = IC_TextField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || contactNo.isEmpty() || ic.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValidContactNo(contactNo)) {
                JOptionPane.showMessageDialog(null, "Invalid contact number format! Must begin with '01' and be 10 or 11 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValidIC(ic)) {
                JOptionPane.showMessageDialog(null, "Invalid IC format! Must be 12 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (customerStore.getByEmail(email) == null) {
                Customer newCustomer = new Customer().builder()
                        .id(UUID.randomUUID().toString().substring(0,6))
                        .name(name)
                        .email(email)
                        .pw(password)
                        .contactNo(contactNo)
                        .ic(ic)
                        .build();
                customerStore.addCustomer(newCustomer);
                JOptionPane.showMessageDialog(null, "Registration successful! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                navigateToLogin();
            } else {
                JOptionPane.showMessageDialog(null, "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
