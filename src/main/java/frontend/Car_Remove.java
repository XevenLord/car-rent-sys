package frontend;

import org.cr.model.Car;
import org.cr.store.CarStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Car_Remove extends JFrame {
    JButton Remove_Button, Cancel_Button;
    JLabel CarID_Label, CarIDValidity_Label;
    JTextField CarID_TextField;

    private Car car;

    public Car_Remove() {
        super("Remove Car");
        setLayout(new FlowLayout());
        setSize(new Dimension(300, 140));
        setResizable(false);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });

        Remove_Button = new JButton("Remove");
        Cancel_Button = new JButton("Cancel");

        CarID_Label = new JLabel("Enter Car plate no to be removed");
        CarIDValidity_Label = new JLabel();
        CarID_TextField = new JTextField();

        CarID_TextField.setPreferredSize(new Dimension(240, 22));
//        CarID_Label.setPreferredSize(new Dimension(175, 22));
        CarIDValidity_Label.setPreferredSize(new Dimension(415, 9));
        Remove_Button.setPreferredSize(new Dimension(100, 22));
        Cancel_Button.setPreferredSize(new Dimension(100, 22));

        CarIDValidity_Label.setForeground(Color.red);

        add(CarID_Label);
        add(CarID_TextField);
        add(CarIDValidity_Label);

        add(Remove_Button);
        add(Cancel_Button);

        Remove_Button.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                CarStore carStore = new CarStore().get();

                                                String carID = CarID_TextField.getText().trim();
                                                if (!carID.isEmpty()) {
                                                        CarIDValidity_Label.setText("");
                                                        Car car = carStore.getCar(carID);
                                                        if (car != null) {
                                                            int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to remove this car \n "
                                                                            + car.toString() + "\n Are you sure you want to continue ??", "Confirmation",
                                                                    JOptionPane.OK_CANCEL_OPTION);
                                                            if (showConfirmDialog == 0) {
                                                                carStore.removeCar(car.getPlateNo());
                                                                Parent_JFrame.getMainFrame().getContentPane().removeAll();
                                                                Car_Details cd = new Car_Details();
                                                                Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                                                                Parent_JFrame.getMainFrame().getContentPane().revalidate();

                                                                Parent_JFrame.getMainFrame().setEnabled(true);
                                                                dispose();
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Car plate no not found !");
                                                        }

                                                } else {
                                                    carID = null;
                                                    CarIDValidity_Label.setText("                                                            Enter Car plate no !");
                                                }
                                            }
                                        }
        );
        Cancel_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });
    }
}
