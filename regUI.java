import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * regUI
 */
public class regUI extends JFrame implements ActionListener{

    private JLabel lRegTitle  = new JLabel("Registration");
    private JLabel lUsername  = new JLabel("Username :");
    private JLabel lPassword  = new JLabel("Password :");
    private JLabel lMobileNo  = new JLabel("Mobile no :");
    private JLabel lName      = new JLabel("Name :");
    private JTextField tName = new JTextField();
    private JTextField tUname = new JTextField();
    private JPasswordField tPassword = new JPasswordField();
    private JTextField   tMobileNo = new JTextField();
    private JButton     btnSubmit = new JButton("Submit");
    private JButton     btnBack   = new JButton();
    private JTextField allFields[] = {tName,tUname,tMobileNo};
    Font lFont = new Font("sansserif", Font.PLAIN, 14);
    Font tFont = new Font("Andalus", Font.PLAIN, 26);
    public loginUI test;
    public mysqlConn con = new mysqlConn();
    
 /* public regUI () {
        
       try
      {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
      }
      catch(Exception exception)
      {
        System.out.println("NF");
      }
      

} */

    public regUI(loginUI test)
    {
      this.test = test;
    }

    public regUI()
    {

    }

    public void buildregUI()
    {
        setLayout(null);
        

        //Setting Font

        lRegTitle.setFont(tFont);
        lName.setFont(lFont);
        lMobileNo.setFont(lFont);
        lUsername.setFont(lFont);
        lPassword.setFont(lFont);

        //Setting the locations of Labels
        lRegTitle.setBounds(320, 15, 160, 50);
        lName.setBounds(100, 105, 50, 20);
        lMobileNo.setBounds(100, 170, 100, 25);
        lUsername.setBounds(100, 250, 100, 16);
        lPassword.setBounds(100, 320, 80, 16);
       

        

        //Setting the locations of text and passwordfield

        tName.setBounds(240, 100, 290, 30);
        tMobileNo.setBounds(240, 170, 290, 30);
        tUname.setBounds(240, 245, 290, 30);
        tPassword.setBounds(240, 315, 290, 30);
        
        btnSubmit.setBounds(295, 390, 170, 33);

        btnBack.setBounds(20,15, 60, 50);

        Icon back = new ImageIcon(getClass().getResource("rsc/back.png"));
       btnBack.setBorder(BorderFactory.createEmptyBorder());

        btnBack.setIcon(back);
      //  btnBack.setBorder(null);
      btnBack.setContentAreaFilled(false);
    btnBack.addActionListener(this);
    btnSubmit.addActionListener(this);
        add(btnBack);
        add(lRegTitle);
        add(lUsername);
        add(lPassword);
        add(lMobileNo);
        add(lName);

        add(tName);
        add(tMobileNo);
        add(tUname);
        add(tPassword);
       
        add(btnSubmit);



        SwingUtilities.updateComponentTreeUI(getRootPane());
         //setAlwaysOnTop(true);
        setSize(690,500);
// setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  @Override
    public void actionPerformed(ActionEvent event)
    {
     if(event.getSource() == btnBack)
        {
         //System.out.println(setVisible(false))
      
        
          setVisible(false);         
          test.setVisible(true);
          // System.out.println(test);
        }
        else if(event.getSource() == btnSubmit)
        {
          String pass = new String(tPassword.getPassword());
          if(tName.getText() .equals("") || tMobileNo.getText().equals("") || tUname.getText().equals("") || pass == "")
          {
            JOptionPane.showMessageDialog(null, "<html>Registrtion failed !<br> One or more fields are empty !<html>","Error",JOptionPane.ERROR_MESSAGE);
          }
          else 
          {
            if(con.signUp(tName.getText(), tMobileNo.getText(), tUname.getText(), pass))
            {
              JOptionPane.showMessageDialog(null, "Registration Successful");
              test.setVisible(true);
              setVisible(false);

              for(int i = 0 ; i<allFields.length;i++)
              {
                allFields[i].setText("");
              }
              tPassword.setText("");
            }
            else
            {
              JOptionPane.showMessageDialog(null, "Registrtion failed !","Error",JOptionPane.ERROR_MESSAGE);
            }
          }

          
       }
    }
   /*public static void main(String[] args) {
        regUI uid = new regUI();
        uid.buildregUI();
        uid.setVisible(true);
       
   }*/
}