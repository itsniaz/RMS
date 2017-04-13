import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class mysqlConn
{

 //static final String DB_URL = "jdbc:mysql://johnny.heliohost.org/trialnly_rms";
	static final String DB_URL = "jdbc:mysql://alpha.hostmydreams.net/trialnly_rms";
 
              static final String USER = "trialnly";
             //static final String PASS = "Trialnly_1234";
			 static final String PASS = "Wuff_1234";

        Connection conn;
        Statement stmt;
        String sql;
        ResultSet rs;

       public mysqlConn()
       {
           try
           {
              Class.forName("com.mysql.jdbc.Driver"); 
          //  System.out.println("Driver Loaded");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
           // System.out.println("Succesful"); 
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Can't connect to the internet");
        }
       }
    public boolean validate (String tempUsername, String tempPass) 
    {
        ;
        if(tempUsername.equals(""))
        {
            return false;
        }
        try
        {
            sql = "SELECT password FROM user where username = "  + "'"+ tempUsername+ "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);


           String result="";

            while(rs.next()){
                
                result = rs.getString("password");
             }
             

            // System.out.println(tempPass);
            // System.out.println(result);
      
            if(result.equals(tempPass))
            {
                return true;
            }
            

            return false;

    
        
        }
        catch(Exception e)
        {
           return false;
        }

    }

    public boolean signUp(String name,String mNo,String userName,String password )
    {
         try{
            name = "'" + name + "'";
            mNo = "'" + mNo + "'";
            userName = "'" + userName + "'";
            password = "'" + password + "'";
            
            sql = "insert into user values("+name+","+mNo+","+userName+","+password+")";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

             return true;
         }

         catch (Exception e)
         {
             if (e instanceof SQLIntegrityConstraintViolationException) 
             {
                JOptionPane.showMessageDialog(null, "Username already taken !", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
                
             } 
             else
            {
      
            }
            return false;
         }    
    }

    public void setProfileInfo(userUI aUserUI,String uname)
    {
         sql = "select * from user where username = '"+uname+"'";
         String username = "";
         String password = "";
         String mobileNo = "" ;
         String name = "";
         try
         {
             stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        while(rs.next()){
                
              name = rs.getString("name");
              mobileNo = rs.getString("mobileNo");
              username = rs.getString("username");
              password = rs.getString("password");
             }


            aUserUI.nameField.setText(name);
            aUserUI.mNoField.setText(mobileNo);
            aUserUI.usernameField.setText(username);
            aUserUI.passwordField.setText(password);
         }
        catch(Exception e)
        {
            //System.out.println(e);
        }

    }

    public void updateProfile(userUI aUserUI,String previousUsername)
    {
        previousUsername = "'"+previousUsername+"'";
        String name = "'"+aUserUI.nameField.getText()+"'";
        String mNo  = "'"+aUserUI.mNoField.getText()+"'";
        String username = "'"+aUserUI.usernameField.getText()+"'";
        String password = "'"+aUserUI.passwordField.getText()+"'";
        sql = "update user set name = "+name+", mobileNo = "+mNo+", username = "+username+", password = "+password+" where username = "+previousUsername;
        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Profile Updated","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Couldn't update profile","Failed !",JOptionPane.ERROR_MESSAGE);            
            //System.out.println(e);
        }
    }

}