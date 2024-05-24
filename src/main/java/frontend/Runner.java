package frontend;

import javax.swing.*;
import java.awt.*;

public class Runner {
    private static final JFrame FRAME = new JFrame();
    private final ImageIcon icon;
    private JPanel panel1;
    private JLabel L1;

    public static JFrame getFrame() {
        return FRAME;
    }

    public Runner() {
        java.net.URL imgURL = getClass().getResource("/WelcomeImage.jpg");
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        } else {
            // Handle the case where the image is not found
            System.err.println("Couldn't find file: WelcomeImage.jpg");
            icon = null;
        }
        L1 = new JLabel(icon);
        FRAME.setUndecorated(true);
        FRAME.setSize(new Dimension(1000, 534));
        FRAME.setLocationRelativeTo(null);
        FRAME.add(L1);
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        Runner.FRAME.setVisible(true);

        try {
            Thread.sleep(1000);
            Login LoginObject = new Login();
            Runner.FRAME.getContentPane().removeAll();
            Runner.FRAME.add(LoginObject.getMainPanel());
            Runner.FRAME.getContentPane().revalidate();

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
