import javax.swing.*;



import javafx.scene.image.Image;
import javafx.scene.layout.Background;

import java.awt.*;
import java.awt.event.*;



public class loginUI extends JFrame implements ActionListener
{
   private mysqlConn con = new mysqlConn();
   private regUI uir = new regUI(this);
   private userUI userPanel ;

   private JLabel faceicon = new JLabel();
    //Buttons

    private JButton btnLogin;
    private  JButton btnRegister;
    
    //Username
    private JLabel lusername = new JLabel("Username");
    private JTextField username = new JTextField(10);

    //Password
    private JLabel lpassword = new JLabel("Password");
    public static JPasswordField password = new JPasswordField(10);

    //Header
    private JLabel rms = new JLabel("<html>Railway<br>Management<br>System</html>");
    
    //Font for username and password
    Font upFont = new Font("sansserif", Font.BOLD, 17); 

    //Seperator for username and password

   JSeparator sp1 = new JSeparator();
   JSeparator sp2 = new JSeparator();

   //Color 

   Color p1c = new Color(100, 100, 100);
   Color btnColor = new Color(0, 132, 255);


    //Panel for Username and password;

    JPanel up = new JPanel(null);

    JPanel title = new JPanel(null);


    //actionLister logics

    @Override 
    public void actionPerformed(ActionEvent ev)
    {
      if(ev.getSource()== btnLogin)
      {
        String pass = new String(password.getPassword());
        String uid = username.getText();
     
        if(pass.equals("") || uid.equals("") )
        {
          JOptionPane.showMessageDialog(null, "Username/Password field empty !", "Login failed", JOptionPane.ERROR_MESSAGE);
        }
    
        else if(con.validate(uid, pass))
        {
          
            userPanel = new userUI(this);
            userPanel.run(username.getText());
            this.setVisible(false);

        }
        else
        {
          JOptionPane.showMessageDialog(null, "Username/Password Incorrect");
        }
      }
      else if(ev.getSource() == btnRegister)
      {

         setVisible(false);
         uir.buildregUI();
         uir.setVisible(true);
      }
    }
  
    public void run()
    {
      
      try
      {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
      }
      catch(Exception exception)
      {
        System.out.println("NF");
      }
      setLayout(null);

      up.setBounds(385,0,360,470);

      //JLabel preferences for the username
      lusername.setForeground(new Color(255,255,255));   
      lusername.setFont(upFont);
      lusername.setBounds(25,195,90,15);

      //JTextField preferences for username

      username.setBorder(null);
      username.setForeground(Color.WHITE);
      username.setBounds(135, 185, 165, 20);
      username.setBackground(p1c);

      //JPasswordField preferences for password
      password.setBorder(null);
      password.setBackground(p1c);
      password.setForeground(Color.WHITE);
      password.setBounds(135,235,165,20);

      //JLabel preferences for password
      lpassword.setForeground(new Color(255,255,255));   
      lpassword.setFont(upFont);
      lpassword.setBounds(25,245,85,15);

      faceicon.setBounds(138, 50, 120 ,120);
      faceicon.setIcon(new ImageIcon("rsc/facesmall.png"));
      up.add(faceicon);


      //Seperators

      sp1.setBounds(125,205,180,1);
      sp1.setForeground(new Color(255,255,255));

      sp2.setBounds(125,255,180,1);
      sp2.setForeground(new Color(255,255,255));


      //login JButton
      btnLogin = new JButton("Login");
      btnRegister = new JButton("Register");

      btnLogin.addActionListener(this);
      btnRegister.addActionListener(this);


      btnLogin.setFont(new Font("sansserif",Font.ITALIC,18));
      btnLogin.setBounds(140,285,130,30);

      //the Text in between two JButton
      JLabel newuser = new JLabel("New user ?");
      newuser.setForeground(Color.WHITE);
      newuser.setFont(new Font("sansserif",Font.ITALIC,13));
      newuser.setBounds(175,340,75,25);
      btnRegister.setFont(new Font("sansserif",Font.ITALIC,18));
      btnRegister.setBounds(140,380,130,30);


      rms.setBounds(30, 195, 300, 220);
      rms.setFont(new Font("Georgia", Font.BOLD, 35));

      title.setBounds(0,0,385,470);

      //
     
     


      //login panel preferences
      up.setBackground(new Color(100, 100, 100));
      up.add(lusername);
      up.add(lpassword);
      up.add(sp1);
      up.add(sp2);
      up.add(username);
      up.add(password);
      up.add(btnLogin);
      up.add(btnRegister);
      up.add(newuser);

      title.add(rms);


      add(up);
      add(title);
      setSize(750,498);
      setResizable(false);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //setVisible(true);

      
    }
}