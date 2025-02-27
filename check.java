import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class check extends JFrame{
    public JTextField uid;
    private JButton cancelButton;
    private JButton searchButton;
    private JPanel search;



    public static boolean btn1;
    public static boolean btn2;
    public static boolean btn3;


    public String u_id=uid.getText();
    public int getid(){
        return Integer.parseInt(uid.getText());
    }
    static int chkid;
    int a =0;
    int b=1;

    public int count =0;

    public void checkId(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Appletomypie99");
            Statement stn = conn.createStatement();
            String qry = "SELECT * FROM customer_data";
            ResultSet rs = stn.executeQuery(qry);
            while(rs.next()){

                b++;
            }

            while(count<b){
                if(getid()==count){
                    a=count;
                    chkid=count;
                }
                count++;
            }

            if(a>0){
                if(btn1==true){
                    new updateForm();
                }
                else if(btn2==true){
                    new updateEntry();
                }
            }
            else{
                JOptionPane.showMessageDialog(this,"User not found!!!", "Try Again.",JOptionPane.ERROR_MESSAGE);new check();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public check() {
        setSize(400,200);
        setResizable(false);
        setContentPane(search);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                checkId();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new display();
                dispose();
            }
        });
        setVisible(true);
    }
}
