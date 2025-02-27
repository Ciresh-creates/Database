import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homePage extends JDialog {
    private JPanel homePanel;
    private JButton rbtn;
    private JButton lbtn;
    private JButton exitButton;
    private JButton savingsutton;
    private JButton summaryButton;
    private JButton buttonOK;

    public homePage(JFrame parent) {
        super(parent);
        setContentPane(homePanel);
        setMinimumSize(new Dimension(600,474));
        setSize(600,474);
        setLocationRelativeTo(parent);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        rbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //calling constructor to register new user
                new regForm(null);
            }
        });
        lbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                //constructor to display data of all customer
                new display();
            }
        });
        savingsutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new savings();
            }
        });
        summaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }
}
