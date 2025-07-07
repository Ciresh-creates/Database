import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class savings extends JFrame{
    private JTable table1;
    private JPanel panel1;
    private JButton backButton;
    String[] options = {"Baishak", "Jestha", "Ashad", "Shrawan", "Bhadra", "Aswin", "Kartik",
            "Mangsir", "Poush", "Magh", "Falgun", "Chaitra"};
    JComboBox month = new JComboBox<String>(options);


    public void display(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Admin");
            Statement stn = conn.createStatement();
            String qry = "SELECT *FROM customer_data";
            ResultSet rs= stn.executeQuery(qry);
            ResultSetMetaData rsmd = rs.getMetaData();

            DefaultTableModel model = new DefaultTableModel();
            table1.setModel(model);

            int col =rsmd.getColumnCount();
            String[] colName= new String[col];

            int i=0;

            while(i<col){
                colName[i] = rsmd.getColumnName(i+1);
                i++;
            }

            model.setColumnIdentifiers(colName);
            model.addColumn("Month");
            TableColumn column = table1.getColumnModel().getColumn(col);
            HeaderRenderer headerRenderer = new HeaderRenderer(month);
            column.setHeaderRenderer(headerRenderer);


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

    public savings()
        {setContentPane(panel1);
        setMinimumSize(new Dimension(1080,720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1080,720);
        setResizable(false);
        setTitle("Data from MYSQL server.");
        setVisible(true);
        display();

        month.addActionListener(e->{
            String selected = (String) month.getSelectedItem();
            System.out.println(selected);

        });
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new homePage(null);
                }
            });
        }
}
