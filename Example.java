// Import the SQL Server JDBC Driver classes 
import java.sql.*;

class Example 
{  
       public static void main(String args[]) 
       {  
       try  
       { 
            // Load the SQLServerDriver class, build the 
            // connection string, and get a connection 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            String connectionUrl = "jdbc:sqlserver://ServerName\\sqlexpress;" + 
                                    "database=DBName;" + 
                                    "user=UserName;" + 
                                    "password=Password"; 
            Connection con = DriverManager.getConnection(connectionUrl); 
            System.out.println("Connected.");

            // Create and execute an SQL statement that returns some data.  
            String SQL = "SELECT CustomerID, ContactName FROM Customers";  
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.  
            while (rs.next())  
            {  
               System.out.println(rs.getString(1) + " " + rs.getString(2));  
            }

       }  
       catch(Exception e)  
       { 
            System.out.println(e.getMessage()); 
            System.exit(0);  
       } 
    } 
}