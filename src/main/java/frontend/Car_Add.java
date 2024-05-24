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

public class Car_Add extends JFrame {

    JButton Add_Button, Cancel_Button;
    JLabel Maker_Label, Name_Label, Type_Label, SeatingCapacity_Label, RegNo_Label, RentPerHour_Label,
            MakerValidity_Label, NameValidity_Label, RegNoValidity_Label, RentPerHourValidity_Label, OwnerIDValidity_Label;
    JTextField Maker_TextField, Name_TextField, RegNo_TextField, RentPerHour_TextField, OwnerID_TextField;
    JComboBox<String> Colour_ComboBox, Type_ComboBox, Model_ComboBox, Condition_ComboBox;
    JSpinner SeatingCapacity_Spinner;

    public Car_Add() {
        super("Add Car");
        setLayout(new FlowLayout());
        setSize(new Dimension(450, 475));
        setResizable(false);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });

        Add_Button = new JButton("Add");
        Cancel_Button = new JButton("Cancel");

        Maker_Label = new JLabel("Maker");
        Type_Label = new JLabel("Car type");
        SeatingCapacity_Label = new JLabel("Seating capacity");
        RegNo_Label = new JLabel("Reg no (ABC-0123)");
        RentPerHour_Label = new JLabel("Rent Per Hour (in PKR)");

        MakerValidity_Label = new JLabel();
        NameValidity_Label = new JLabel();
        RegNoValidity_Label = new JLabel();
        RentPerHourValidity_Label = new JLabel();

        Maker_TextField = new JTextField();
        Name_TextField = new JTextField();
        RegNo_TextField = new JTextField();
        RentPerHour_TextField = new JTextField();
        // try to initialize array from text file so that new items can be added and can be updated
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

        String[] Conditions = {"Excellent", "Good", "Average", "Bad"};
        Condition_ComboBox = new JComboBox<>(Conditions);

        SeatingCapacity_Spinner = new JSpinner();
        SeatingCapacity_Spinner.setModel(new SpinnerNumberModel(4, 1, null, 1));
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
        Add_Button.setPreferredSize(new Dimension(100, 22));
        Cancel_Button.setPreferredSize(new Dimension(100, 22));

        MakerValidity_Label.setForeground(Color.red);
//        MakerValidity_Label.setFont(new Font("Serif", 1, 10));
//        MakerValidity_Label.setText("                                                           MakerValidity_Label");
        NameValidity_Label.setForeground(Color.red);
//        NameValidity_Label.setText("                                                            NameValidity_Label");
        RegNoValidity_Label.setForeground(Color.red);
//        RegNoValidity_Label.setText("RegNoValidity_Label");
        RentPerHourValidity_Label.setForeground(Color.red);
//        RentPerHourValidity_Label.setText("RentPerHourValidity_Label");

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

        add(Add_Button);
        add(Cancel_Button);

        Add_Button.addActionListener(new ActionListener() {
                                         @Override

                                         public void actionPerformed(ActionEvent e) {

                                             String maker = Maker_TextField.getText().trim(),
                                                     name = Name_TextField.getText().trim(),
                                                     regNo = RegNo_TextField.getText().trim(),
                                                     ownerID = OwnerID_TextField.getText().trim(),
                                                     rentPerHour = RentPerHour_TextField.getText().trim();

                                             if (!name.isEmpty()) {
                                                 NameValidity_Label.setText("");
                                             } else {
                                                 name = null;
                                                 NameValidity_Label.setText("                                                            Enter Car Name !");
                                             }
                                             if (!maker.isEmpty()) {
                                                 MakerValidity_Label.setText("");
                                             } else {
                                                 maker = null;
                                                 MakerValidity_Label.setText("                                                            Enter Maker'sName !");
                                             }
                                             if (!regNo.isEmpty()) {
                                                 RegNoValidity_Label.setText("");
                                             } else {
                                                 regNo = null;
                                                 RegNoValidity_Label.setText("                                                            Enter Reg No !");
                                             }
                                             if (!rentPerHour.isEmpty()) {
                                                 try {
                                                     if (Integer.parseInt(rentPerHour) > 0) {
                                                         RentPerHourValidity_Label.setText("");
                                                     } else {
                                                         rentPerHour = null;
                                                         RentPerHourValidity_Label.setText("                                                            Rent cannot be '0' or negative !");
                                                     }
                                                 } catch (NumberFormatException ex) {

                                                     rentPerHour = null;
                                                     RentPerHourValidity_Label.setText("                                                            Invalid Rent !");
                                                 }

                                             } else {
                                                 rentPerHour = null;
                                                 RentPerHourValidity_Label.setText("                                                            Enter Rent !");
                                             }

//Car(plateNo, maker, name, type, rentSts, seats rentPerHour);
                                             try {
                                                 if (maker != null && name != null && regNo != null && rentPerHour != null) {
                                                     CarStore carStore = new CarStore().get();
                                                     Car car = carStore.getCar(regNo);
                                                         if (car == null) {
                                                             //Car(id, Maker, Name, Colour, Type, SeatingCapacity, Model, Condition, RegNo, RentPerHour, carOwner)
                                                             // id is auto

                                                             car = new Car(regNo, maker, name, Type_ComboBox.getSelectedItem() + "", CarRentSts.NON_AVAILABLE.toString(),
                                                                     Integer.parseInt(SeatingCapacity_Spinner.getValue().toString()), Long.parseLong(rentPerHour));
                                                             carStore.addCar(car);

                                                             Parent_JFrame.getMainFrame().getContentPane().removeAll();
                                                             Car_Details cd = new Car_Details();
                                                             Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                                                             Parent_JFrame.getMainFrame().getContentPane().revalidate();
                                                             JOptionPane.showMessageDialog(null, "Record Successfully Added !");
                                                             Parent_JFrame.getMainFrame().setEnabled(true);
                                                             dispose();
                                                         } else {
                                                             JOptionPane.showMessageDialog(null, "This Car Registeration no is already registered !");
                                                         }
                                                     } else {
                                                         JOptionPane.showMessageDialog(null, "Owner ID doesnot exists !");
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
