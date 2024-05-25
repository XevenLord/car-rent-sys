package frontend;

import org.cr.model.Car;
import org.cr.store.CarStore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Parent_JFrame {

    private static JFrame MainFrame;
    private final JMenuBar menu_Bar;
    private final JMenu File, CarMenu, CustomerMenu, HelpMenu;
    private final JMenuItem Exit, addCar, updateCar, removeCar, ViewUnbookedCars, ViewbookedCars,
            addCustomer, updateCustomer, removeCustomer, About;


    public Parent_JFrame() {
        System.out.println("Enter parent jframe");
        MainFrame = new JFrame("Car Rent System");
        MainFrame.setSize(1366, 730);
        MainFrame.setVisible(true);

        MainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                        + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                if (showConfirmDialog == 0) {
                    System.exit(0);
                }
            }
        });

        menu_Bar = new JMenuBar();

        File = new JMenu("File");
        CarMenu = new JMenu("Cars");
        CustomerMenu = new JMenu("Customer");
        HelpMenu = new JMenu("Help");

        Exit = new JMenuItem("Exit");
        addCar = new JMenuItem("Add Car");
        updateCar = new JMenuItem("Update Car");
        removeCar = new JMenuItem("Remove Car");
        ViewbookedCars = new JMenuItem("View booked Cars");
        ViewUnbookedCars = new JMenuItem("View Unbooked Cars");

        addCustomer = new JMenuItem("Add Customer");
        updateCustomer = new JMenuItem("Update Customer");
        removeCustomer = new JMenuItem("Remove  Customer");

        About = new JMenuItem("About");

        File.add(Exit);
        CarMenu.add(addCar);
        CarMenu.add(updateCar);
        CarMenu.add(removeCar);
        CarMenu.add(ViewbookedCars);
        CarMenu.add(ViewUnbookedCars);

        CustomerMenu.add(addCustomer);
        CustomerMenu.add(updateCustomer);
        CustomerMenu.add(removeCustomer);

        HelpMenu.add(About);

        menu_Bar.add(File);
        menu_Bar.add(CarMenu);
        menu_Bar.add(CustomerMenu);
        menu_Bar.add(HelpMenu);

        MainFrame.setJMenuBar(menu_Bar);

        Exit.addActionListener(new Parent_JFrame_ActionListner());
        addCar.addActionListener(new Parent_JFrame_ActionListner());
        updateCar.addActionListener(new Parent_JFrame_ActionListner());
        removeCar.addActionListener(new Parent_JFrame_ActionListner());
        ViewbookedCars.addActionListener(new Parent_JFrame_ActionListner());
        ViewUnbookedCars.addActionListener(new Parent_JFrame_ActionListner());

        addCustomer.addActionListener(new Parent_JFrame_ActionListner());
        updateCustomer.addActionListener(new Parent_JFrame_ActionListner());
        removeCustomer.addActionListener(new Parent_JFrame_ActionListner());

        About.addActionListener(new Parent_JFrame_ActionListner());
        System.out.println("Next process");
    }

    public static JFrame getMainFrame() {
        return MainFrame;
    }

    private class Parent_JFrame_ActionListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CarStore carStore = new CarStore().get();

            switch (e.getActionCommand()) {
                case "Exit": {
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                            + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if (showConfirmDialog == 0) {
                        System.exit(0);
                    }
                }
                break;
                case "Add Car": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Add ac = new Car_Add();
                    ac.setVisible(true);
                }
                break;
                case "Update Car": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Update ac = new Car_Update();
                    ac.setVisible(true);
                }
                break;
                case "Remove Car": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Remove ac = new Car_Remove();
                    ac.setVisible(true);
                }
                break;
                case "View booked Cars": {
                    ArrayList<Car> SearchBookedCars_Array = carStore.getBookedCars();
                    String result = "";
                    if (!SearchBookedCars_Array.isEmpty()) {
                        for (int i = 0; i < SearchBookedCars_Array.size(); i++) {
                            result += (i + 1) + " : " + SearchBookedCars_Array.get(i) + "\n";
                        }
                    } else {
                        result = "No Cars are Booked !";
                    }
                    JOptionPane.showMessageDialog(null, result);
                }
                break;
                case "View Unbooked Cars": {
                    ArrayList<Car> SearchUnBookedCars_Array = carStore.getUnbookedCars();
                    String result = "";
                    if (!SearchUnBookedCars_Array.isEmpty()) {
                        for (int i = 0; i < SearchUnBookedCars_Array.size(); i++) {
                            result += (i + 1) + " : " + SearchUnBookedCars_Array.get(i) + "\n";
                        }
                    } else {
                        result = "No UnBooked Cars are available !";
                    }
                    JOptionPane.showMessageDialog(null, result);
                }
                break;
                case "Add Customer": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Customer_Add aco = new Customer_Add();
                    aco.frame.setVisible(true);
                }
                break;
                case "Update Customer": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    new Customer_Update().frame.setVisible(true);
                }
                break;
                case "Remove Customer": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    new Customer_Remove().frame.setVisible(true);
                }
                break;
                case "About": {
                    JOptionPane.showMessageDialog(null, "JAVA PROGRAMMING FOR CAR RENT SYSTEM");
                }
                break;

            }
        }
    }

}
