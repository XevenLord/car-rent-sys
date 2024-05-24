package frontend;

import org.cr.enums.CarRentSts;
import org.cr.model.Car;
import org.cr.store.CarStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class Car_Update extends JFrame {
    JButton Update_Button, Cancel_Button;
    JLabel CarID_Label, CarIDValidity_Label;
    JTextField CarID_TextField;

    private Car car;

    public Car_Update() {
        super("Update Car");
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

        Update_Button = new JButton("Update");
        Cancel_Button = new JButton("Cancel");

        CarID_Label = new JLabel("Enter Car ID to be updated");
        CarIDValidity_Label = new JLabel();
        CarID_TextField = new JTextField();

        CarID_TextField.setPreferredSize(new Dimension(240, 22));
//        CarID_Label.setPreferredSize(new Dimension(175, 22));
        CarIDValidity_Label.setPreferredSize(new Dimension(415, 9));
        Update_Button.setPreferredSize(new Dimension(100, 22));
        Cancel_Button.setPreferredSize(new Dimension(100, 22));

        CarIDValidity_Label.setForeground(Color.red);

        add(CarID_Label);
        add(CarID_TextField);
        add(CarIDValidity_Label);

        add(Update_Button);
        add(Cancel_Button);

        Update_Button.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                CarStore carStore = new CarStore().get();

                                                String carID = CarID_TextField.getText().trim();

                                                if (!carID.isEmpty()) {
                                                    try {
                                                        if (Integer.parseInt(carID) > 0) {
                                                            CarIDValidity_Label.setText("");
                                                        } else {
                                                            carID = null;
                                                            CarIDValidity_Label.setText("                                                            ID cannot be '0' or negative !");
                                                        }
                                                    } catch (NumberFormatException ex) {
                                                        carID = null;
                                                        CarIDValidity_Label.setText("                                                            Invalid ID !");
                                                    }
                                                } else {
                                                    carID = null;
                                                    CarIDValidity_Label.setText("                                                            Enter Car ID !");
                                                }

                                                if (carID != null) {
                                                    car = carStore.getCar(carID);
                                                    if (car != null) {
                                                        Car_UpdateInner cui = new Car_UpdateInner();
                                                        cui.setVisible(true);
                                                        dispose();

                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Car ID not found !");
                                                    }
                                                } else {
                                                    CarIDValidity_Label.setText("                                                            Enter Car ID !");
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

    private class Car_UpdateInner extends JFrame {

        JButton Update_Button, Cancel_Button;
        JLabel Maker_Label, Name_Label, Color_Label, Type_Label, SeatingCapacity_Label, Model_Label, Condition_Label, RegNo_Label, RentPerHour_Label,
                OwnerID_Label,
                MakerValidity_Label, NameValidity_Label, RegNoValidity_Label, RentPerHourValidity_Label, OwnerIDValidity_Label;
        JTextField Maker_TextField, Name_TextField, RegNo_TextField, RentPerHour_TextField, OwnerID_TextField;
        JComboBox<String> Colour_ComboBox, Type_ComboBox, Model_ComboBox, Condition_ComboBox;
        JSpinner SeatingCapacity_Spinner;

        public Car_UpdateInner() {
            super("Update Car");
            setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            setSize(new Dimension(450, 475));
            setResizable(false);
            setLocationRelativeTo(this);

            Update_Button = new JButton("Update");
            Cancel_Button = new JButton("Cancel");

            Maker_Label = new JLabel("Enter Maker");
            Name_Label = new JLabel("Enter Name");
            Color_Label = new JLabel("Enter Color");
            Model_Label = new JLabel("Enter Model");
            Type_Label = new JLabel("Enter Car type");
            SeatingCapacity_Label = new JLabel("Enter Seating capacity");
            RegNo_Label = new JLabel("Enter Reg no (ABC-0123)");
            OwnerID_Label = new JLabel("Enter Owner ID");
            RentPerHour_Label = new JLabel("Enter Rent Per Hour (in PKR)");
            Condition_Label = new JLabel("Condition");

            MakerValidity_Label = new JLabel();
            NameValidity_Label = new JLabel();
            RegNoValidity_Label = new JLabel();
            OwnerIDValidity_Label = new JLabel();
            RentPerHourValidity_Label = new JLabel();

            Maker_TextField = new JTextField();
            Name_TextField = new JTextField();
            RegNo_TextField = new JTextField();
            OwnerID_TextField = new JTextField();
            RentPerHour_TextField = new JTextField();

            String[] Colours = {"White", "Red", "Silver", "Blue", "Black"};
            // try to initialize array from text file so that new items can be added and can be updated
            Colour_ComboBox = new JComboBox<>(Colours);
            String[] Types = {"Familycar", "Comercial", "Microcar", "Compact car", "Mid-size car", "Supercar", "Convertible", "Sports cars"};
            Type_ComboBox = new JComboBox<>(Types);

            // Creating an array containing Years from Today's Year till 1950
            int TodaysYear = new Date().getYear() + 1900;
            int noOfYears = TodaysYear - 1949;
            String[] Years = new String[noOfYears];
            for (int i = 0; i < noOfYears; i++) {
                Years[i] = TodaysYear - i + "";
            }
            Model_ComboBox = new JComboBox<>(Years);


            SeatingCapacity_Spinner = new JSpinner();
            SeatingCapacity_Spinner.setModel(new javax.swing.SpinnerNumberModel(4, 1, null, 1));
            SeatingCapacity_Spinner.setFocusable(false);

            Maker_TextField.setPreferredSize(new Dimension(240, 22));
            Name_TextField.setPreferredSize(new Dimension(240, 22));
            RegNo_TextField.setPreferredSize(new Dimension(240, 22));
            RentPerHour_TextField.setPreferredSize(new Dimension(240, 22));

            Maker_Label.setPreferredSize(new Dimension(175, 22));
            Name_Label.setPreferredSize(new Dimension(175, 22));
            RegNo_Label.setPreferredSize(new Dimension(175, 22));
            RentPerHour_Label.setPreferredSize(new Dimension(175, 22));

            MakerValidity_Label.setPreferredSize(new Dimension(415, 9));
            NameValidity_Label.setPreferredSize(new Dimension(415, 9));
            RegNoValidity_Label.setPreferredSize(new Dimension(415, 9));
            RentPerHourValidity_Label.setPreferredSize(new Dimension(415, 9));

            SeatingCapacity_Spinner.setPreferredSize(new Dimension(50, 22));
            Update_Button.setPreferredSize(new Dimension(100, 22));
            Cancel_Button.setPreferredSize(new Dimension(100, 22));

            MakerValidity_Label.setForeground(Color.red);
            NameValidity_Label.setForeground(Color.red);
            RegNoValidity_Label.setForeground(Color.red);
            RentPerHourValidity_Label.setForeground(Color.red);

            Maker_TextField.setText(car.getMaker());
            Name_TextField.setText(car.getName());
            RegNo_TextField.setText(car.getPlateNo());
            RentPerHour_TextField.setText(car.getRentPerHour() + "");
            Type_ComboBox.setSelectedItem(car.getType());
            SeatingCapacity_Spinner.setValue(car.getSeats());

//            car
            add(Maker_Label);
            add(Maker_TextField);
            add(MakerValidity_Label);

            add(Name_Label);
            add(Name_TextField);
            add(NameValidity_Label);

            add(RegNo_Label);
            add(RegNo_TextField);
            add(RegNoValidity_Label);

            add(RentPerHour_Label);
            add(RentPerHour_TextField);
            add(RentPerHourValidity_Label);

            add(Type_Label);
            add(Type_ComboBox);
            add(SeatingCapacity_Label);
            add(SeatingCapacity_Spinner);

            add(Update_Button);
            add(Cancel_Button);

            Update_Button.addActionListener(new ActionListener() {
                                                @Override

                                                public void actionPerformed(ActionEvent e) {
                                                    CarStore carStore = new CarStore().get();
                                                    String Maker = Maker_TextField.getText().trim(),
                                                            Name = Name_TextField.getText().trim(),
                                                            RegNo = RegNo_TextField.getText().trim(),
                                                            OwnerID = OwnerID_TextField.getText().trim(),
                                                            RentPerHour = RentPerHour_TextField.getText().trim();

                                                    if (!Name.isEmpty()) {
                                                        NameValidity_Label.setText("");
                                                    } else {
                                                        Name = null;
                                                        NameValidity_Label.setText("                                                            Enter Car Name !");
                                                    }
                                                    if (!Maker.isEmpty()) {
                                                        MakerValidity_Label.setText("");
                                                    } else {
                                                        Maker = null;
                                                        MakerValidity_Label.setText("                                                            Enter Maker'sName !");
                                                    }
                                                    if (!RegNo.isEmpty()) {
                                                        RegNoValidity_Label.setText("");
                                                        Car car2 = carStore.getCar(RegNo);
                                                        // checking if the newly entered regNo is already registered or not
                                                        if ((car2 != null) & (!RegNo.equalsIgnoreCase(car.getPlateNo()))) {
                                                            RegNo = null;
                                                            JOptionPane.showMessageDialog(null, "This Car Registeration no is already registered !");
                                                        }
                                                    } else {
                                                        RegNo = null;
                                                        RegNoValidity_Label.setText("                                                            Enter Reg No !");
                                                    }
                                                    if (!RentPerHour.isEmpty()) {
                                                        try {
                                                            if (Integer.parseInt(RentPerHour) > 0) {
                                                                RentPerHourValidity_Label.setText("");
                                                            } else {
                                                                RentPerHour = null;
                                                                RentPerHourValidity_Label.setText("                                                            Rent cannot be '0' or negative !");
                                                            }
                                                        } catch (NumberFormatException ex) {
                                                            RentPerHour = null;
                                                            RentPerHourValidity_Label.setText("                                                            Invalid Rent !");
                                                        }
                                                    } else {
                                                        RentPerHour = null;
                                                        RentPerHourValidity_Label.setText("                                                            Enter Rent !");
                                                    }
                                                    try {
                                                        if (Maker != null && Name != null && RegNo != null && OwnerID != null && RentPerHour != null) {
//new Car(ID, Maker, Name, Colour, Type, seatingCapacity, model, Condition, RegNo, RentPerHour, carOwner)
                                                            car = new Car(car.getPlateNo(), Maker, Name,
                                                                    Type_ComboBox.getSelectedItem() + "", CarRentSts.NON_AVAILABLE.toString(), Integer.parseInt(SeatingCapacity_Spinner.getValue() + "")
                                                                    , (long) Integer.parseInt(RentPerHour));
                                                            carStore.updCar(car);

                                                            Parent_JFrame.getMainFrame().getContentPane().removeAll();
                                                            Car_Details cd = new Car_Details();
                                                            Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                                                            Parent_JFrame.getMainFrame().getContentPane().revalidate();

                                                            JOptionPane.showMessageDialog(null, "Record Successfully Updated !");

                                                            Parent_JFrame.getMainFrame().setEnabled(true);
                                                            dispose();

                                                        }
                                                    } catch (HeadlessException | NumberFormatException ex) {
                                                        System.out.println(ex);
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
}
