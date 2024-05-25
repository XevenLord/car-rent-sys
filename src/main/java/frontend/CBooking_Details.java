package frontend;

import org.cr.model.Booking;
import org.cr.model.Car;
import org.cr.model.user.Customer;
import org.cr.store.BookingStore;
import org.cr.store.CarStore;
import org.cr.store.CustomerStore;
import org.cr.util.UserSession;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class CBooking_Details {
    private static DefaultTableModel tablemodel; // static so that it can be accessed in add GUI class to update the Jtable when a new record is added
    private static JButton SearchCustomerID_Button, SearchCarRegNo_Button, BackButton, LogoutButton, BookCar_Button, UnbookCar_Button;
    private static JTextField CustomerID_TextField, CarRegNo_TextField;
    private static JScrollPane jScrollPane1;
    private static JTable jTable1;
    private JPanel MainPanel;

    public CBooking_Details() {
        MainPanel = new JPanel();
        CParent_JFrame.getMainFrame().setTitle("Booking Details - Rent-A-Car Management System");
        MainPanel.setLayout(new AbsoluteLayout());
        MainPanel.setMinimumSize(new Dimension(1366, 730));

        SearchCustomerID_Button = new JButton("Search by Customer ID");
        SearchCarRegNo_Button = new JButton("Search by Car RegNo");
        BackButton = new JButton("Back");
        LogoutButton = new JButton("Logout");
        BookCar_Button = new JButton("Book");
        UnbookCar_Button = new JButton("Unbook");

        CustomerID_TextField = new JTextField();
        CarRegNo_TextField = new JTextField();

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        String[] columns = {"Sr#", "ID", "Customer ID+Name", "Car Name", "Rent Time", "Return Time"};
        tablemodel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1 = new JTable(getTablemodel());
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(jTable1);
        jTable1.setFillsViewportHeight(true);

        BookingStore bookingStore = new BookingStore().get();
        ArrayList<Booking> bookingObjects = bookingStore.getAll();
        for (int i = 0; i < bookingObjects.size(); i++) {
            String ID = bookingObjects.get(i).getId();
            String customerIDName = bookingObjects.get(i).getCustomerId() + ": " + bookingObjects.get(i).getCustomerNm();
            String carPlateNo = bookingObjects.get(i).getPlateNo();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a dd-MM-yyyy");
            Date rentTimeDate = Date.from(bookingObjects.get(i).getStTm().atZone(ZoneId.systemDefault()).toInstant());
            String rentTime = dateFormat.format(rentTimeDate);

            LocalDateTime returnTime_ = bookingObjects.get(i).getEndTm();
            String returnTime;
            if (returnTime_ != null) {
                Date returnTimeDate = Date.from(returnTime_.atZone(ZoneId.systemDefault()).toInstant());
                returnTime = dateFormat.format(returnTimeDate);
            } else {
                returnTime = "Not returned yet!";
            }

            String[] oneRecord = {((i + 1) + ""), "" + ID, customerIDName, carPlateNo, rentTime, returnTime};
            tablemodel.addRow(oneRecord);
        }

        // Center aligning the text in all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.length; i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Adjusting the size of each column
        jTable1.getColumnModel().getColumn(0).setMinWidth(80);
        jTable1.getColumnModel().getColumn(1).setMinWidth(80);
        jTable1.getColumnModel().getColumn(2).setMinWidth(400);
        jTable1.getColumnModel().getColumn(3).setMinWidth(300);
        jTable1.getColumnModel().getColumn(4).setMinWidth(230);
        jTable1.getColumnModel().getColumn(5).setMinWidth(235);

        jTable1.getTableHeader().setReorderingAllowed(false);

        MainPanel.add(jScrollPane1, new AbsoluteConstraints(10, 60, 1330, 550));
        MainPanel.add(BackButton, new AbsoluteConstraints(1106, 625, 100, 22));
        MainPanel.add(LogoutButton, new AbsoluteConstraints(1236, 625, 100, 22));
        MainPanel.add(BookCar_Button, new AbsoluteConstraints(10, 625, 130, 22));
        MainPanel.add(UnbookCar_Button, new AbsoluteConstraints(160, 625, 130, 22));
        MainPanel.add(SearchCarRegNo_Button, new AbsoluteConstraints(10, 15, 160, 22));
        MainPanel.add(CarRegNo_TextField, new AbsoluteConstraints(185, 15, 240, 22));
        MainPanel.add(SearchCustomerID_Button, new AbsoluteConstraints(440, 15, 180, 22));
        MainPanel.add(CustomerID_TextField, new AbsoluteConstraints(635, 15, 240, 22));

        SearchCustomerID_Button.addActionListener(new CBooking_Details_ActionListener());
        SearchCarRegNo_Button.addActionListener(new CBooking_Details_ActionListener());
        BackButton.addActionListener(new CBooking_Details_ActionListener());
        LogoutButton.addActionListener(new CBooking_Details_ActionListener());
        BookCar_Button.addActionListener(new CBooking_Details_ActionListener());
        UnbookCar_Button.addActionListener(new CBooking_Details_ActionListener());
    }

    public static DefaultTableModel getTablemodel() {
        return tablemodel;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    private class CBooking_Details_ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BookingStore bookingStore = new BookingStore().get();
            CustomerStore customerStore = new CustomerStore().get();
            CarStore carStore = new CarStore().get();

            switch (e.getActionCommand()) {

                case "Back": {
                    CParent_JFrame.getMainFrame().setTitle("Rent-A-Car Management System");
                    CMainMenu mm = new CMainMenu();
                    CParent_JFrame.getMainFrame().getContentPane().removeAll();
                    CParent_JFrame.getMainFrame().add(mm.getMainPanel());
                    CParent_JFrame.getMainFrame().getContentPane().revalidate();
                }
                break;
                case "Logout": {
                    CParent_JFrame.getMainFrame().dispose();
                    Runner r = new Runner();
                    JFrame frame = r.getFrame();
                    Login login = new Login();
                    JPanel panel = login.getMainPanel();
                    frame.add(panel);
                    frame.setVisible(true);
                }
                break;
                case "Book": {
                    Customer currentCustomer = UserSession.getInstance().getLoggedInCustomer();
                    if (!carStore.getUnbookedCars().isEmpty()) {
                        // Open a booking dialog with customer information
                        CBooking_BookCar bookCarDialog = new CBooking_BookCar(currentCustomer);
                        bookCarDialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No UnBooked Cars are available!");
                    }
                }
                break;
                case "Unbook": {
                    Customer currentCustomer = UserSession.getInstance().getLoggedInCustomer();
                    ArrayList<Booking> customerBookings = bookingStore.getByCustomerId(currentCustomer.getId());
                    if (!customerBookings.isEmpty()) {
                        // Open an unbooking dialog with customer information
                        CBooking_UnBookCar unBookCarDialog = new CBooking_UnBookCar(currentCustomer);
                        unBookCarDialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No booked cars found for this customer!");
                    }
                }
                break;
                case "Search by Customer ID": {
                    String customerID = CustomerID_TextField.getText().trim();
                    if (!customerID.isEmpty()) {
                        Customer customer = customerStore.getCustomer(customerID);
                        if (customer != null) {
                            ArrayList<Booking> bookings = bookingStore.getByCustomerId(customerID);
                            if (!bookings.isEmpty()) {
                                JOptionPane.showMessageDialog(null, bookings.toString());
                            } else {
                                JOptionPane.showMessageDialog(null, "This Customer has not booked any cars yet!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Customer ID not found!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter Customer ID first!");
                    }
                    CustomerID_TextField.setText("");
                }
                break;
                case "Search by Car RegNo": {
                    String carRegNo = CarRegNo_TextField.getText().trim();
                    if (!carRegNo.isEmpty()) {
                        Car car = carStore.getCar(carRegNo);
                        if (car != null) {
                            ArrayList<Booking> bookings = bookingStore.getByPlateNo(carRegNo);
                            if (!bookings.isEmpty()) {
                                JOptionPane.showMessageDialog(null, bookings.toString());
                            } else {
                                JOptionPane.showMessageDialog(null, "This Car is not booked yet!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Registration no. not found!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter Car Registration No first!");
                    }
                    CarRegNo_TextField.setText("");
                }
                break;
            }
        }
    }
}
