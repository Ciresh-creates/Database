import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class regForm extends JDialog{
    private JLabel fName;
    private JTextField fnTF;
    private JLabel aFeild;
    private JTextField aTF;
    private JLabel lFeild;
    private JTextField lnTF;
    private JTextField pnTH;
    private JLabel agLabel;
    private JTextField agTF;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel regPanel;
    private JLabel phlabel;
    private JTextField lotf;

    public void registerUser(){
        String first_name = fnTF.getText();
        String last_name = lnTF.getText();
        String Address = aTF.getText();
        String phone_number = pnTH.getText();
        String age = agTF.getText();
        String loan = lotf.getText();

        float interestPercent = 12;
        String interest = String.valueOf(interestPercent);

        float monthlydeposit=Float.parseFloat(loan)*((interestPercent/100)/12);

        String monthlyDeposit = String.valueOf(monthlydeposit);
        String paid_amount = String.valueOf(0);
        String remaining_amount = loan;



        if(first_name.isEmpty()||last_name.isEmpty()||Address.isEmpty()||phone_number.isEmpty()||age.isEmpty()||loan.isEmpty()){
            JOptionPane.showMessageDialog(this,"Fill all the details above!!","Try Again",JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUsers(first_name,last_name,Address,phone_number,age,loan,interest,monthlyDeposit,paid_amount,remaining_amount);
        if(user!=null){
            JOptionPane.showMessageDialog(this,"Registration successful :-)","Registration Successful",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,"Registration failed!!!!","Try Again",JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user; // creating object of class User
    private User addUsers(String first_name, String last_name, String Address, String phone_number, String age, String loan, String interest, String monthly_deposit,String paid_amount, String remaining_amount){
        User user = null;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Appletomypie99");
            System.out.println("Connection established successfully");
            String sql = "INSERT INTO customer_data (first_name,last_name,address,phone_number,age,loan,interest,interest_to_pay,total_paid_amount,remaining_amount)"+"VAlUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,first_name);
            preparedStatement.setString(2,last_name);
            preparedStatement.setString(3,Address);
            preparedStatement.setString(4,phone_number);
            preparedStatement.setString(5,age);
            preparedStatement.setString(6,loan);
            preparedStatement.setString(7,interest);
            preparedStatement.setString(8,monthly_deposit);
            preparedStatement.setString(9,paid_amount);
            preparedStatement.setString(10,remaining_amount);



            int addedRows=preparedStatement.executeUpdate();

            if(addedRows>0){
                user = new User();
                user.first_name=first_name;
                user.last_name=last_name;
                user.Address=Address;
                user.phone_number=phone_number;
                user.age=age;
                user.loan = loan;
                user.interest=interest;
                user.monthly_deposit=monthly_deposit;
                user.total_paid_amount=paid_amount;
                user.remaining_amount=remaining_amount;

            }

            conn.close();
        }catch(Exception e){
            Logger.getLogger(regForm.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;
    }

    public regForm(JFrame parent){
        super(parent);
        setTitle("Create new Account");
        setContentPane(regPanel);
        setMinimumSize(new Dimension(600,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
