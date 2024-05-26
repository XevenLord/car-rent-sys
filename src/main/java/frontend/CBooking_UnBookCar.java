package frontend;

import org.cr.enums.CarRentSts;
import org.cr.model.Booking;
import org.cr.model.Car;
import org.cr.model.user.Customer;
import org.cr.store.BookingStore;
import org.cr.store.CarStore;
import org.cr.store.CustomerStore;
import org.cr.store.PaymentStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

public class CBooking_UnBookCar extends JFrame {
    JButton UnBook_Button, Cancel_Button;
    JLabel CarID_Label, CarIDValidity_Label;
    JTextField CarID_TextField;

    private Car car;

    public CBooking_UnBookCar(Customer customer) {
        super("UnBook Car");

        setLayout(new FlowLayout());
        setSize(new Dimension(300, 145));
        setResizable(false);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CParent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });

        UnBook_Button = new JButton("UnBook");
        Cancel_Button = new JButton("Cancel");

        CarID_Label = new JLabel("Enter Car plate no to be UnBooked");
        CarIDValidity_Label = new JLabel();
        CarID_TextField = new JTextField();

        CarID_TextField.setPreferredSize(new Dimension(240, 22));
        CarIDValidity_Label.setPreferredSize(new Dimension(415, 9));

        UnBook_Button.setPreferredSize(new Dimension(100, 22));
        Cancel_Button.setPreferredSize(new Dimension(100, 22));

        CarIDValidity_Label.setForeground(Color.red);

        add(CarID_Label);
        add(CarID_TextField);
        add(CarIDValidity_Label);

        add(UnBook_Button);
        add(Cancel_Button);

        UnBook_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingStore bookingStore = new BookingStore().get();
                PaymentStore paymentStore = new PaymentStore().get();
                CarStore carStore = new CarStore().get();
                CustomerStore customerStore = new CustomerStore().get();

                String carID = CarID_TextField.getText().trim();
                if (!carID.isEmpty()) {
                    CarIDValidity_Label.setText("");
                    car = carStore.getCar(carID);
                    if (car != null) {
                        System.out.println("Unbook car: " + car);
                        if (CarRentSts.NON_AVAILABLE.toString().equals(car.getRentSts())) {
                            CarIDValidity_Label.setText("");
                        } else {
                            car = null;
                            JOptionPane.showMessageDialog(null, "This car is not booked!");
                        }
                    } else {
                        car = null;
                        JOptionPane.showMessageDialog(null, "Car plate no does not exist!");
                    }
                } else {
                    carID = null;
                    CarIDValidity_Label.setText("                                                            Enter Car plate no!");
                }

                if (carID != null && car != null) {
                    setEnabled(false);
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to UnBook this Car\n" + car.toString()
                            + "\n Are you sure you want to continue?", "UnBook Confirmation", OK_CANCEL_OPTION);
                    if (showConfirmDialog == 0) {

                        Booking last = bookingStore.getByPlateNoWthtReturn(carID);
                        last.setEndTm(LocalDateTime.now());
                        bookingStore.updBooking(last);

                        // update car status
                        car.setRentSts(CarRentSts.AVAILABLE.toString());
                        carStore.updCar(car);

                        int bill = paymentStore.calcBill(last);

                        customer.setBill(customer.getBill() + bill);
                        customerStore.updCustomer(customer);

                        CParent_JFrame.getMainFrame().getContentPane().removeAll();
                        CBooking_Details cd = new CBooking_Details();
                        CParent_JFrame.getMainFrame().add(cd.getMainPanel());
                        CParent_JFrame.getMainFrame().getContentPane().revalidate();
                        JOptionPane.showMessageDialog(null, "Car Successfully UnBooked!");
                        CParent_JFrame.getMainFrame().setEnabled(true);
                        dispose();
                    } else {
                        setEnabled(true);
                    }
                }
            }
        });
        Cancel_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CParent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });
    }
}
