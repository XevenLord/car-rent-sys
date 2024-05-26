package frontend;

import org.cr.model.Car;
import org.cr.store.CarStore;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Customer_Car_Details {

    private static DefaultTableModel tablemodel;
    private static JButton SearchName_Button, SearchRegNo_Button, BackButton, LogoutButton;
    private static JTextField SearchName_TextField, SearchRegNo_TextField;
    private static JScrollPane jScrollPane1;
    private static JTable jTable1;
    private JPanel MainPanel;

    private CarStore carStore = new CarStore().get();

    public static DefaultTableModel getTablemodel() {
        return tablemodel;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public Customer_Car_Details() {
        MainPanel = new JPanel();
        CParent_JFrame.getMainFrame().setTitle("Car Details - Rent-A-Car Management System");
        MainPanel.setLayout(new AbsoluteLayout());
        MainPanel.setMinimumSize(new Dimension(1366, 730));

        SearchRegNo_Button = new JButton("Search Plate No");
        SearchRegNo_TextField = new JTextField();

        SearchName_Button = new JButton("Search Name");
        SearchName_TextField = new JTextField();

        BackButton = new JButton("Back");
        LogoutButton = new JButton("Logout");

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        String[] columns = {"Sr#", "ID", "Maker", "Name", "Type", "Seats", "Plate No.", "Rent/hour", "Rent Status"};
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

        CarStore carStore = new CarStore().get();
        ArrayList<Car> Car_objects = carStore.getAll();
        for (int i = 0; i < Car_objects.size(); i++) {
            Car car = Car_objects.get(i);
            String rentSts = car.getRentSts().equals("1") ? "Available" : "Non-available";
            String[] one_s_Record = {
                    (i + 1) + "", car.getPlateNo(), car.getMaker(), car.getName(),
                    car.getType(), car.getSeats() + "", car.getPlateNo(), car.getRentPerHour() + "", rentSts
            };
            tablemodel.addRow(one_s_Record);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            jTable1.getColumnModel().getColumn(i).setMinWidth(90);
        }

        jTable1.getTableHeader().setReorderingAllowed(false);

        MainPanel.add(SearchRegNo_Button, new AbsoluteConstraints(10, 15, 130, 22));
        MainPanel.add(SearchRegNo_TextField, new AbsoluteConstraints(145, 15, 240, 22));
        MainPanel.add(SearchName_Button, new AbsoluteConstraints(390, 15, 130, 22));
        MainPanel.add(SearchName_TextField, new AbsoluteConstraints(525, 15, 240, 22));
        MainPanel.add(jScrollPane1, new AbsoluteConstraints(10, 60, 1330, 550));
        MainPanel.add(BackButton, new AbsoluteConstraints(1160, 625, 100, 22));
        MainPanel.add(LogoutButton, new AbsoluteConstraints(1270, 625, 100, 22));

        SearchName_Button.addActionListener(e -> performSearchByName());
        SearchRegNo_Button.addActionListener(e -> performSearchByRegNo());
        BackButton.addActionListener(e -> navigateBack());
        LogoutButton.addActionListener(e -> performLogout());
    }

    private void performSearchByName() {
        String name = SearchName_TextField.getText().trim();
        if (!name.isEmpty()) {
            ArrayList<Car> cars = carStore.getByName(name);
            if (cars != null && !cars.isEmpty()) {
                JOptionPane.showMessageDialog(null, cars.toString());
                SearchName_TextField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Required Car not found");
                SearchName_TextField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter Car Name first !");
        }
    }

    private void performSearchByRegNo() {
        String plateNo = SearchRegNo_TextField.getText().trim();
        if (!plateNo.isEmpty()) {
            Car car = carStore.getCar(plateNo);
            if (car != null) {
                JOptionPane.showMessageDialog(null, car.toString());
                SearchRegNo_TextField.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Required Car not found");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter Car Plate no first !");
        }
    }

    private void navigateBack() {
        CParent_JFrame.getMainFrame().setTitle("Rent-A-Car Management System [REBORN]");
        CMainMenu mm = new CMainMenu();
        CParent_JFrame.getMainFrame().getContentPane().removeAll();
        CParent_JFrame.getMainFrame().add(mm.getMainPanel());
        CParent_JFrame.getMainFrame().getContentPane().revalidate();
    }

    private void performLogout() {
        CParent_JFrame.getMainFrame().dispose();
        Runner r = new Runner();
        JFrame frame = r.getFrame();
        Login login = new Login();
        JPanel panel = login.getMainPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
