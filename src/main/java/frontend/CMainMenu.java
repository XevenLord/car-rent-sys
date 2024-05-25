package frontend;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CMainMenu implements ActionListener {

    private static JLabel Image_Label;
    private static JButton BookingButton, LogoutButton;
    private JPanel MainPanel;
    private final ImageIcon icon;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public CMainMenu() {
        System.out.println("Entering CMainMenu");
        MainPanel = new JPanel();

        MainPanel.setLayout(new AbsoluteLayout());
        MainPanel.setMinimumSize(new Dimension(1366, 730));

        BookingButton = new JButton("Booking Details");
        LogoutButton = new JButton("Logout");

        Image_Label = new JLabel();

        LogoutButton.setFont(new Font("Tahoma", 1, 14));
        BookingButton.setFont(new Font("Tahoma", 1, 14));

        java.net.URL imgURL = getClass().getResource("/MainMenuImage.jpeg");
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        } else {
            // Handle the case where the image is not found
            System.err.println("Couldn't find file: WelcomeImage.jpg");
            icon = null;
        }

        System.out.println("Pass through this");

        Image_Label.setIcon(icon);

        BookingButton.setBackground(new Color(240,240,240));
        LogoutButton.setBackground(new Color(240,240,240));

        MainPanel.add(LogoutButton, new AbsoluteConstraints(1166, 80, 100, 25));
        MainPanel.add(BookingButton, new AbsoluteConstraints(70, 80, 200, 99));
        MainPanel.add(Image_Label, new AbsoluteConstraints(0, 0, 1370, 710));

        BookingButton.addActionListener(this);
        LogoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
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
            case "Booking Details": {
                CParent_JFrame.getMainFrame().getContentPane().removeAll();
                CBooking_Details bd = new CBooking_Details();
                CParent_JFrame.getMainFrame().add(bd.getMainPanel());
                CParent_JFrame.getMainFrame().getContentPane().revalidate();
            }
            break;
        }
    }
}
