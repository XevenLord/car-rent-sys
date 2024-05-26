package frontend;

import org.cr.model.user.Customer;
import org.cr.store.CustomerStore;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

public class Customer_Add {

    JButton Add_Button, Cancel_Button;
    JLabel NRIC_Label, Name_Label, Contact_Label, Email_Label, UserName_Label, Password_Label, NRICValidity_Label, contactValidity_Label, NameValidity_Label, EmailValidity_Label, UserNameValidity_Label, PasswordValidity_Label;
    JTextField NRIC_TextField, Name_TextField, Contact_TextField, Email_TextField, UserName_TextField, Password_TextField;
    JFrame frame = new JFrame();

    public Customer_Add() {
        frame.setTitle("Add Customer");
        frame.setLayout(new AbsoluteLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                frame.dispose();
            }
        });

        frame.setSize(new Dimension(450, 290));
        frame.setResizable(false);
        frame.setLocationRelativeTo(Parent_JFrame.getMainFrame());

        Add_Button = new JButton("Add");
        Cancel_Button = new JButton("Cancel");

        NRIC_Label = new JLabel("Enter NRIC (without dashes)");
        Name_Label = new JLabel("Enter Name");
        Contact_Label = new JLabel("Enter Contact");
        Email_Label = new JLabel("Enter Email");
        UserName_Label = new JLabel("Enter Username");
        Password_Label = new JLabel("Enter Password");
        NRICValidity_Label = new JLabel();
        NameValidity_Label = new JLabel();
        EmailValidity_Label = new JLabel();
        UserNameValidity_Label = new JLabel();
        PasswordValidity_Label = new JLabel();
        contactValidity_Label = new JLabel();
        NRIC_TextField = new JTextField();
        Name_TextField = new JTextField();
        Contact_TextField = new JTextField();
        Email_TextField = new JTextField();
        UserName_TextField = new JTextField();
        Password_TextField = new JTextField();

        NRIC_TextField.setPreferredSize(new Dimension(240, 22));
        Name_TextField.setPreferredSize(new Dimension(240, 22));
        Contact_TextField.setPreferredSize(new Dimension(240, 22));
        Email_TextField.setPreferredSize(new Dimension(240, 22));
        UserName_TextField.setPreferredSize(new Dimension(240, 22));
        Password_TextField.setPreferredSize(new Dimension(240, 22));

        NRIC_Label.setPreferredSize(new Dimension(175, 22));
        Name_Label.setPreferredSize(new Dimension(175, 22));
        Contact_Label.setPreferredSize(new Dimension(175, 22));
        Email_Label.setPreferredSize(new Dimension(175, 22));
        UserName_Label.setPreferredSize(new Dimension(175, 22));
        Password_Label.setPreferredSize(new Dimension(175, 22));
        NRICValidity_Label.setPreferredSize(new Dimension(240, 9));
        contactValidity_Label.setPreferredSize(new Dimension(240, 9));
        NameValidity_Label.setPreferredSize(new Dimension(240, 9));
        EmailValidity_Label.setPreferredSize(new Dimension(240, 9));
        UserNameValidity_Label.setPreferredSize(new Dimension(240, 9));
        PasswordValidity_Label.setPreferredSize(new Dimension(240, 9));

        NRICValidity_Label.setForeground(Color.red);
        contactValidity_Label.setForeground(Color.red);
        NameValidity_Label.setForeground(Color.red);
        EmailValidity_Label.setForeground(Color.red);
        UserNameValidity_Label.setForeground(Color.red);
        PasswordValidity_Label.setForeground(Color.red);

        frame.add(NRIC_Label, new AbsoluteConstraints(10, 5));
        frame.add(NRIC_TextField, new AbsoluteConstraints(195, 5));
        frame.add(NRICValidity_Label, new AbsoluteConstraints(195, 30));

        frame.add(Name_Label, new AbsoluteConstraints(10, 42));
        frame.add(Name_TextField, new AbsoluteConstraints(195, 42));
        frame.add(NameValidity_Label, new AbsoluteConstraints(195, 66));

        frame.add(Contact_Label, new AbsoluteConstraints(10, 77));
        frame.add(Contact_TextField, new AbsoluteConstraints(195, 77));
        frame.add(contactValidity_Label, new AbsoluteConstraints(195, 102));

        frame.add(Email_Label, new AbsoluteConstraints(10, 112));
        frame.add(Email_TextField, new AbsoluteConstraints(195, 112));
        frame.add(EmailValidity_Label, new AbsoluteConstraints(195, 138));

        frame.add(Password_Label, new AbsoluteConstraints(10, 148));
        frame.add(Password_TextField, new AbsoluteConstraints(195, 148));
        frame.add(PasswordValidity_Label, new AbsoluteConstraints(195, 174));

        frame.add(Add_Button, new AbsoluteConstraints(100, 225, 100, 22));
        frame.add(Cancel_Button, new AbsoluteConstraints(250, 225, 100, 22));

        Add_Button.addActionListener(new Customer_Add_ActionListener());

        Cancel_Button.addActionListener(new Customer_Add_ActionListener());
    }

    private class Customer_Add_ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerStore customerStore = new CustomerStore().get();

            switch (e.getActionCommand()) {
                case "Add": {
                    String nric = NRIC_TextField.getText().trim();
                    String name = Name_TextField.getText().trim();
                    String contact = Contact_TextField.getText().trim();
                    String email = Email_TextField.getText().trim();
                    String pw = Password_TextField.getText().trim();

                    if (nric.length() == 12 && !name.isEmpty() && !email.isEmpty() && !pw.isEmpty()) {
                        Customer customer = customerStore.getCustomerByIC(nric);
                        if (customer == null) {
                            Customer newCustomer = new Customer().builder()
                                    .id(UUID.randomUUID().toString().substring(0, 6))
                                    .email(email)
                                    .pw(pw)
                                    .name(name)
                                    .contactNo(contact)
                                    .ic(nric)
                                    .age(null)
                                    .bill(0L)
                                    .build();
                            customerStore.addCustomer(newCustomer);
                            Parent_JFrame.getMainFrame().getContentPane().removeAll();
                            Customer_Details cd = new Customer_Details();
                            Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                            Parent_JFrame.getMainFrame().getContentPane().revalidate();
                            Parent_JFrame.getMainFrame().setEnabled(true);
                            JOptionPane.showMessageDialog(null, "Customer added successfully !");
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "This NRIC is already registered !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid information/Information Not Found!");
                    }
                    break;
                }
                case "Cancel": {
                    Parent_JFrame.getMainFrame().setEnabled(true);
                    frame.dispose();
                    break;
                }
            }
        }

    }

}
