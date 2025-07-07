import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Arrays;

public class display extends displayData{

    public display(){
    try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Admin");
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
        }

        model.setColumnIdentifiers(colName);


        String user_id, first_name, last_name, Address, phone_number, age, loan, interest, monthly_deposit,paid_amount,remaining_amount;
        while(rs.next()){
            user_id=rs.getString(1);
            first_name=rs.getString(2);
            last_name=rs.getString(3);
            Address=rs.getString(4);
            phone_number =rs.getString(5);
            age=rs.getString(6);
            loan=rs.getString(7);
            interest=rs.getString(8);
            monthly_deposit = rs.getString(9);
            paid_amount = rs.getString(10);
            remaining_amount = rs.getString(11);

            String row[]={user_id,first_name,last_name,Address,phone_number,age,loan,interest,monthly_deposit,paid_amount,remaining_amount};
            model.addRow(row);
        }

    }catch(Exception e){
        e.printStackTrace();
    }
}
}
