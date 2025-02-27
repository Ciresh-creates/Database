import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class updateEntry extends check{
    private JPanel dataEntry;
    private JTextField textField1;
    private JButton cancelButton;
    private JTextField textField2;
    private JButton saveButton;
    private JTable table1;

    float  remaining_amt, tot_paid_amt, interest, interest_to_pay;public void showExistingData(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Appletomypie99");
            Statement stn = conn.createStatement();
            String qry="SELECT *FROM customer_data";
            ResultSet rs= stn.executeQuery(qry);
            ResultSetMetaData rsmd = rs.getMetaData();

            DefaultTableModel model = new DefaultTableModel();
            table1.setModel(model);

            int cols = rsmd.getColumnCount();
            String[] colName= new String[cols];

            for(int i=0; i<cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
                model.setColumnIdentifiers(colName);
            }


            int check=0;

            String user_id, first_name, last_name, Address, phone_number, age, loan, interest, monthly_deposit, paid_amount, remaining_amount;
            while(rs.next()){
                if(check==chkid-1){
                    user_id=rs.getString(1);
                    first_name=rs.getString(2);
                    last_name=rs.getString(3);
                    Address=rs.getString(4);
                    phone_number =rs.getString(5);
                    age=rs.getString(6);
                    loan=rs.getString(7);
                    interest= rs.getString(8);
                    monthly_deposit=rs.getString(9);
                    paid_amount = rs.getString(10);
                    remaining_amount = rs.getString(11);

                    String row[]={user_id,first_name,last_name,Address,phone_number,age,loan,interest,monthly_deposit,paid_amount,remaining_amount};
                    model.addRow(row);
                }
                check++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getDataFromDB(){

        //creating connection with database
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Appletomypie99");
            Statement stn = conn.createStatement();
            String qry = "SELECT remaining_amount, total_paid_amount, interest FROM customer_data";
            ResultSet rs = stn.executeQuery(qry);

            int chk = 0;

            while(rs.next()){
                if(chk==(check.chkid-1)){
                    remaining_amt=rs.getFloat("remaining_amount");
                    tot_paid_amt=rs.getFloat("total_paid_amount");
                    interest=rs.getFloat("interest");
                    interest_to_pay=rs.getFloat("interest_to_pay");
                }
                chk++;
            }
            conn.close();
            stn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void calc(){

        String monthlyDeposit = textField1.getText();
        String interestDeposit = textField2.getText();

        remaining_amt=remaining_amt-Integer.parseInt(monthlyDeposit);
        tot_paid_amt=tot_paid_amt+Integer.parseInt(monthlyDeposit);
        interest_to_pay=((remaining_amt*interest/100)/12);

        String remainingAmt = String.valueOf(remaining_amt);
        String totpayamt = String.valueOf(tot_paid_amt);
        String interestPay = String.valueOf(interest_to_pay);

        updateData(interestPay, totpayamt, remainingAmt, u_id);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Update successful :-)", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Update failed!!!!", "Try Again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;

    private User updateData(String interest_to_pay,String total_paid_amount,String remaining_amount,String u_id){
        user=null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Appletomypie99");
            Statement stn = conn.createStatement();
            String qry ="UPDATE customer_data SET interest_to_pay=?, total_paid_amount=?, remaining_amount=? WHERE customer_id=?";

            PreparedStatement preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setString(1,interest_to_pay);
            preparedStatement.setString(2,total_paid_amount);
            preparedStatement.setString(3,remaining_amount);
            preparedStatement.setString(4, String.valueOf(check.chkid));

            int addedRow=preparedStatement.executeUpdate();

            if(addedRow>0){
                user = new User();
                user.monthly_deposit=interest_to_pay;
                user.total_paid_amount=total_paid_amount;
                user.remaining_amount=remaining_amount;
            }
            conn.close();
            stn.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public updateEntry(){
        showExistingData();
        this.setVisible(true);
        this.setSize(850,454);
        this.setContentPane(dataEntry);
        this.setDefaultCloseOperation(3);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new display();
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDataFromDB();
                calc();
            }
        });

        this.setVisible(true);
    }
}
