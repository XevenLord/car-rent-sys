package frontend;

import org.cr.model.Booking;
import org.cr.model.user.Customer;
import org.cr.store.BookingStore;
import org.cr.store.CustomerStore;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Customer_Remove {

    JButton Remove_Button, Cancel_Button;
    JLabel ID_Label, IDValidity_Label;
    JTextField ID_TextField;
    JFrame frame = new JFrame();

    public Customer_Remove() {
        frame.setTitle("Remove Customer");
        frame.setLayout(new AbsoluteLayout());
        frame.setSize(new Dimension(450, 290));
        frame.setResizable(false);
        frame.setLocationRelativeTo(Parent_JFrame.getMainFrame());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                frame.dispose();
            }
        });

        Remove_Button = new JButton("Remove");
        Cancel_Button = new JButton("Cancel");

        ID_Label = new JLabel("Enter ID (without dashes)");
        IDValidity_Label = new JLabel();
        ID_TextField = new JTextField();

        ID_TextField.setPreferredSize(new Dimension(240, 22));

        ID_Label.setPreferredSize(new Dimension(175, 22));
        IDValidity_Label.setPreferredSize(new Dimension(240, 9));
        IDValidity_Label.setForeground(Color.red);
        frame.add(ID_Label, new AbsoluteConstraints(10, 5));
        frame.add(ID_TextField, new AbsoluteConstraints(195, 5));
//        IDValidity_Label.setText("Invalid ID !");
        frame.add(IDValidity_Label, new AbsoluteConstraints(195, 30));
        frame.add(Remove_Button, new AbsoluteConstraints(100, 225, 100, 22));
        frame.add(Cancel_Button, new AbsoluteConstraints(250, 225, 100, 22));

        Remove_Button.addActionListener(new Customer_Remove_ActionListener());

        Cancel_Button.addActionListener(new Customer_Remove_ActionListener());
    }

    private class Customer_Remove_ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerStore customerStore = new CustomerStore().get();
            BookingStore bookingStore = new BookingStore().get();

            switch (e.getActionCommand()) {
                case "Remove": {
                    String id = ID_TextField.getText().trim();

                    Customer customer = customerStore.getCustomer(id);
                    if (customer != null) {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(frame, "You are about to remove the following Customer.\n"
                                + customer.toString() + " \nAll the data including Booked Cars and Balance for this Customer will also be deleted  !"
                                + "\n Are you sure you want to continue ??", "Remove Customer", JOptionPane.OK_CANCEL_OPTION);
                        if (showConfirmDialog == 0) {
                            // Deleting all the booking records of customer
                            ArrayList<Booking> bookings = bookingStore.getAll();
                            for (int i = 0; i < bookings.size(); i++) {
                                if (customer.getId() == bookings.get(i).getCustomerId()) {
                                    bookingStore.removeBooking(bookings.get(i).getId());
                                }
                            }
                            // ** Delete all cars for this Customer **
                            customerStore.removeCustomer(customer.getId());

                            System.out.println("Customer deleted !");
                            Parent_JFrame.getMainFrame().getContentPane().removeAll();
                            Customer_Details cd = new Customer_Details();
                            Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                            Parent_JFrame.getMainFrame().getContentPane().revalidate();
                            JOptionPane.showMessageDialog(null, "Record successfully Removed !");
                            Parent_JFrame.getMainFrame().setEnabled(true);
                            frame.dispose();
                        } else {

                            frame.setEnabled(true);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "This ID does not exists !");
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
