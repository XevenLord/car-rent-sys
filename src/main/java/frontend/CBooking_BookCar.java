package frontend;

import org.cr.enums.CarRentSts;
import org.cr.model.Booking;
import org.cr.model.Car;
import org.cr.model.user.Customer;
import org.cr.store.BookingStore;
import org.cr.store.CarStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class CBooking_BookCar extends JFrame {
    JButton Book_Button, Cancel_Button;
    JLabel CarID_Label, CarIDValidity_Label, CustomerID_Label, CustomerIDValidity_Label;
    JTextField CarID_TextField, CustomerID_TextField;

    private Car car;
    private Customer customer;

    public CBooking_BookCar(Customer customer) {
        super("Book Car");
        this.customer = customer;

        setLayout(new FlowLayout());
        setSize(new Dimension(300, 200));
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

        Book_Button = new JButton("Book");
        Cancel_Button = new JButton("Cancel");

        CarID_Label = new JLabel("Enter Car plate no to be Booked");
        CarIDValidity_Label = new JLabel();
        CarID_TextField = new JTextField();

        CarID_TextField.setPreferredSize(new Dimension(240, 22));
        CarIDValidity_Label.setPreferredSize(new Dimension(415, 9));

        Book_Button.setPreferredSize(new Dimension(100, 22));
        Cancel_Button.setPreferredSize(new Dimension(100, 22));

        CarIDValidity_Label.setForeground(Color.red);

        add(CarID_Label);
        add(CarID_TextField);
        add(CarIDValidity_Label);

        add(Book_Button);
        add(Cancel_Button);

        Book_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarStore carStore = new CarStore().get();
                BookingStore bookingStore = new BookingStore().get();

                String CarID = CarID_TextField.getText().trim();
                if (!CarID.isEmpty()) {
                    CarIDValidity_Label.setText("");
                    car = carStore.getCar(CarID);
                    if (car != null) {
                        if (CarRentSts.AVAILABLE.toString().equals(car.getRentSts())) {
                            CarIDValidity_Label.setText("");
                        } else {
                            car = null;
                            JOptionPane.showMessageDialog(null, "This car is already booked !");
                            return;
                        }
                    } else {
                        CarID = null;
                        CarIDValidity_Label.setText("                                                            Car plate no does not exist !");
                    }
                } else {
                    CarID = null;
                    CarIDValidity_Label.setText("                                                            Enter Car plate no !");
                }

                if (CarID != null) {
                    setEnabled(false);
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null,
                            "You are about to Book the Car: \n" + car.toString() + "\n against the Customer: \n"
                                    + customer.toString() + "\n Are you sure you want to continue?",
                            "Book Confirmation", JOptionPane.OK_CANCEL_OPTION);
                    if (showConfirmDialog == 0) {
                        Booking booking = new Booking(UUID.randomUUID().toString().substring(0, 6), true, car.getPlateNo(),
                                customer.getId(), customer.getName(), LocalDateTime.now(), null);
                        bookingStore.addBooking(booking);
                        // change status of rent
                        Car car = carStore.getCar(booking.getPlateNo());
                        car.setRentSts(CarRentSts.NON_AVAILABLE.toString());
                        carStore.updCar(car);

                        CParent_JFrame.getMainFrame().getContentPane().removeAll();
                        CBooking_Details cd = new CBooking_Details();
                        CParent_JFrame.getMainFrame().add(cd.getMainPanel());
                        CParent_JFrame.getMainFrame().getContentPane().revalidate();
                        JOptionPane.showMessageDialog(null, "Car Successfully Booked!");
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
