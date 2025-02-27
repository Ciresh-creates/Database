import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class displayData extends JFrame{
    private JButton updbtn; //button to update personal details
    private JButton umpbtn; //button to update monthly deposit
    private JButton newloan;
    public JTable table1;
    private JPanel panel1;
    private JButton backButtonButton;

    public displayData() {

        setContentPane(panel1);
        setMinimumSize(new Dimension(1080,720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1080,720);
        setResizable(false);
        setTitle("Data from MYSQL server.");

        updbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check.btn1=true;
                check.btn2=false;
                check.btn3=false;
                dispose();
                new check();
            }
        });
        umpbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check.btn2 = true;
                check.btn1=false;
                check.btn3=false;
                dispose();
                new check();
            }
        });
        setVisible(true);
        newloan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new homePage(null);
            }
        });
    }
}
