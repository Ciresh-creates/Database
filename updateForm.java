import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.*;

public class updateForm extends check{
    private JPanel update;
    private JButton backButton;
    private JButton saveButton;
    private JTextField lntf;
    private JTextField adtf;
    private JTextField pntf;
    private JTextField agtf;
    private JTextField fntf;
    private JTable table;


    public updateForm() {
        showExistingData();
        this.setVisible(true);
        this.setSize(850,454);
        this.setContentPane(update);
        this.setDefaultCloseOperation(3);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new display();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInfo();
                dispose();
                new display();
            }
        });
    }

    public void getInfo() {
        String first_name = fntf.getText();
        String last_name = lntf.getText();
        String Address = adtf.getText();
        String phone_number = pntf.getText();
        String age = agtf.getText();

        if (first_name.isEmpty() || last_name.isEmpty() || Address.isEmpty() || phone_number.isEmpty() || age.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all the details above!!", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = updateSelected(first_name, last_name, Address, phone_number, age, u_id);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Update successful :-)", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Update failed!!!!", "Try Again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;

    private User updateSelected(String first_name, String last_name, String Address, String phone_number, String age, String u_id) {

        user = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative", "root", "Appletomypie99");
            Statement stn = conn.createStatement();
            String qryUp = "UPDATE customer_data SET first_name = ?, last_name = ?, address = ?, phone_number = ?, age = ? WHERE customer_id=?";


            PreparedStatement preparedStatement = conn.prepareStatement(qryUp);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, Address);
            preparedStatement.setString(4, phone_number);
            preparedStatement.setString(5, age);
            preparedStatement.setString(6, String.valueOf(chkid));

            int addedRows = preparedStatement.executeUpdate();

            if (addedRows>0) {
                user = new User();
                user.first_name = first_name;
                user.last_name = last_name;
                user.Address = Address;
                user.phone_number = phone_number;
                user.age = age;
            }

            conn.close();
            stn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void showExistingData(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Appletomypie99");
            Statement stn = conn.createStatement();
            String qry="SELECT *FROM customer_data";
            ResultSet rs= stn.executeQuery(qry);
            ResultSetMetaData rsmd = rs.getMetaData();

            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);

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
}
