import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class transactionDetails {

    transactionDetails(){
        try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coorporative","root","Appletomypie99");
        String qry = "";
        Statement stn = conn.createStatement();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
