package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CParent_JFrame {

    private static JFrame MainFrame;
    private final JMenuBar menu_Bar;
    private final JMenu File, HelpMenu;
    private final JMenuItem Exit, ViewBookings, About;

    public CParent_JFrame() {
        System.out.println("Enter customer parent jframe");
        MainFrame = new JFrame("Customer Dashboard");
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
        HelpMenu = new JMenu("Help");

        Exit = new JMenuItem("Exit");
        ViewBookings = new JMenuItem("View Bookings");

        About = new JMenuItem("About");

        File.add(Exit);
        File.add(ViewBookings);

        HelpMenu.add(About);

        menu_Bar.add(File);
        menu_Bar.add(HelpMenu);

        MainFrame.setJMenuBar(menu_Bar);

        Exit.addActionListener(new CParent_JFrame_ActionListner());
        ViewBookings.addActionListener(new CParent_JFrame_ActionListner());

        About.addActionListener(new CParent_JFrame_ActionListner());
        System.out.println("Next process");
    }

    public static JFrame getMainFrame() {
        return MainFrame;
    }

    private class CParent_JFrame_ActionListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Exit": {
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                            + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if (showConfirmDialog == 0) {
                        System.exit(0);
                    }
                }
                break;
                case "View Bookings": {
                    CParent_JFrame.getMainFrame().getContentPane().removeAll();
                    CBooking_Details bd = new CBooking_Details();
                    CParent_JFrame.getMainFrame().add(bd.getMainPanel());
                    CParent_JFrame.getMainFrame().getContentPane().revalidate();
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
