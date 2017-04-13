import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class userUI extends JFrame  implements ActionListener
{

    private loginUI lui;
    private mysqlConn connection;

   //
    private JPanel menuPanel = new JPanel(null);
    private JPanel displayPanel = new JPanel(null);
    private JLabel menu      = new JLabel("Menu");
    private JLabel picIcon    = new JLabel();
    private JButton btnProfile  = new JButton("Profile");
    private JButton btnCoach    = new JButton("Coach");
    private JButton btnTicket    = new JButton("Ticket");
    private JButton btnLogout    = new JButton("Logout");

    //For profile panel;

    private JButton btnEdit = new JButton("Edit");
    private JButton btnUpdate = new JButton("Update");

    private JLabel nameLabel = new JLabel("Name : ");
    private JLabel mnoLabel = new JLabel("Mobile no : ");
    private JLabel userNameLabel = new JLabel("Username : ");
    private JLabel passwordLabel = new JLabel("Password :");
    public JTextField nameField = new JTextField();
    
    public JTextField mNoField = new JTextField();
    public JTextField usernameField = new JTextField();
    public JTextField passwordField = new JTextField();
    
    public JTextField pTelements[] = {nameField,mNoField,usernameField,passwordField};
    private JLabel  pLelements [] = {nameLabel,mnoLabel,userNameLabel,passwordLabel};
    private JButton btnListMenu[] = {btnProfile,btnCoach,btnTicket,btnLogout};
    Font pLabelFont = new Font("sansserif",Font.BOLD,17);

   
     public userUI()
    {
      connection = new mysqlConn();

         try
      {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
      }
      catch(Exception exception)
      {
        System.out.println("NF");
      }

      setLayout(null);
    }

    public userUI(loginUI aui)
    {
      connection = new mysqlConn();      
      this.lui = aui;
      setLayout(null);

      
    }
    public void buildnAddProfile()
    {

        //face icon Label
        displayPanel.setBounds(165, 0, 685, 560);
        
        
        
        picIcon.setIcon(new ImageIcon("rsc/facesmall.png"));
        picIcon.setBounds(295, 50, 155, 150);
        displayPanel.add(picIcon);

        //addiing edit and update button
        btnEdit.setBounds(260, 460, 85, 35);
        btnEdit.addActionListener(this);
        displayPanel.add(btnEdit);

        btnUpdate.setBounds(355, 460, 90, 35);
        btnUpdate.addActionListener(this);
        displayPanel.add(btnUpdate);

        nameLabel.setBounds(90, 205 , 105, 25 );
        mnoLabel.setBounds(90, 270 , 105, 25);
        userNameLabel.setBounds(90, 330 , 105, 25);
        passwordLabel.setBounds(90, 395 , 105, 25);


        nameField.setBounds(225, 205 ,300, 25);
        mNoField.setBounds(225, 270 ,300, 25);
        usernameField.setBounds(225, 330 ,300, 25);
        passwordField.setBounds(225, 395 ,300, 27);

        //a lil shortcut

       for(int i = 0 ; i<pTelements.length; i++)
       {
         pTelements[i].setEditable(false);
         pLelements[i].setFont(pLabelFont);
       }
       for(int i = 0 ; i<btnListMenu.length; i++)
       {
         btnListMenu[i].addActionListener(this);
       }
       

        displayPanel.add(nameLabel);
        displayPanel.add(nameField);
        displayPanel.add(mnoLabel);
        displayPanel.add(mNoField);
        displayPanel.add(userNameLabel);
        displayPanel.add(usernameField);
        displayPanel.add(passwordLabel);
        displayPanel.add(passwordField);


        add(displayPanel);
    }

    public void buildMenuBar()
    {
         
         
         //Setting locations of Menu Panel

        menuPanel.setBounds(15, 50, 145, 475);
        setBackground(new Color(100,100,100));
        displayPanel.setBounds(170, 0, 690, 570);
        //displayPanel.setBackground(new Color(160,205,50));
        //menu.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        menuPanel.add(btnProfile);
        menuPanel.add(btnCoach);
        menuPanel.add(btnTicket);
        menuPanel.add(btnLogout);
        //Settings of JButton
      
      
         btnProfile.setBounds(25, 25, 90, 45);
        btnCoach.setBounds (25, 95, 90, 45);
        btnTicket.setBounds(25, 160, 90, 45);
        btnLogout.setBounds(30, 410, 90, 45);

        add(menuPanel);
    }

    public void run(String uname)
    {   
        buildMenuBar();
        buildnAddProfile();

        connection.setProfileInfo(this,uname);

        setSize(875,605);
       // SwingUtilities.updateComponentTreeUI(getRootPane());
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      
      if(e.getSource() == btnLogout )
      {
        setVisible(false);
        lui.setVisible(true);
      
      }
      else if(e.getSource() == btnEdit)
      {
        for(int i=0;i<pTelements.length;i++)
        {
          pTelements[i].setEditable(true);
        }
      }
      else if(e.getSource() == btnUpdate )
      {
        connection.updateProfile(this,usernameField.getText());
      }
    
    }
  /* public static void main(String[] args)
    {
        userUI ui = new userUI();
        ui.run();
    }*/
}