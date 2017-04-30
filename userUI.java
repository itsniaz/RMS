import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class userUI extends JFrame  implements ActionListener
{

    private loginUI lui;
    private mysqlConn connection;

   //
   String uid;

    private JPanel menuPanel = new JPanel(null);
    private JPanel profilePanel = new JPanel(null);
    private JPanel coachPanel = new JPanel(null);



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

    //For coachPanel
    public JLabel lFrom;
    public JLabel lTo;
    public JLabel lTime;
    public JLabel lSeatType;
    public JLabel lTrainName;

    public JComboBox cbFrom;
    public JComboBox cbTo;
    public JComboBox cbTime;
    public JComboBox cbSeatType;

    public String[] locations = {"Dhaka","Chittagong","Noakhali","Kishorgonj","Sylhet"}; 

   
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

    public userUI(loginUI aui,String uid)
    {
      connection = new mysqlConn();      
      this.lui = aui;
      setLayout(null);

      this.uid = uid;


        setSize(950,605);
        setLocationRelativeTo(null);
        builder();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //displayPanel.setBounds(170, 0, 690, 570);
  
    }
    public void buildProfilePanel()
    {
        
        profilePanel.setSize(685, 560);
  
        //face icon 
        picIcon.setIcon(new ImageIcon("rsc/facesmall.png"));
        picIcon.setBounds(295, 50, 155, 150);
        profilePanel.add(picIcon);

        //addiing edit and update button
        btnEdit.setBounds(260, 460, 85, 35);
        btnEdit.addActionListener(this);
        profilePanel.add(btnEdit);

        btnUpdate.setBounds(355, 460, 90, 35);
        btnUpdate.addActionListener(this);
        profilePanel.add(btnUpdate);

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
     

        profilePanel.add(nameLabel);
        profilePanel.add(nameField);
        profilePanel.add(mnoLabel);
        profilePanel.add(mNoField);
        profilePanel.add(userNameLabel);
        profilePanel.add(usernameField);
        profilePanel.add(passwordLabel);
        profilePanel.add(passwordField);

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

        connection.setProfileInfo(this,uid);

          for(int i = 0 ; i<btnListMenu.length; i++)
       {
         btnListMenu[i].addActionListener(this);
       }
       
        add(menuPanel);
    }


     public void buildCoachPanel()
    {
       

        cbFrom = new JComboBox<>(locations);
        cbTo = new JComboBox<>(locations);
        cbTime = new JComboBox<>();
        cbSeatType = new JComboBox<>();
        lTo = new JLabel("To");
        lFrom = new JLabel("From");
        lTime = new JLabel("Time");
        lTrainName = new JLabel("Train Name");
       // lSeatType = new JLabel("Seat");

        //Combo Boxes
        cbFrom.setBounds(235, 60, 100, 26);
        cbTo.setBounds(410, 60, 100, 26);
        cbTime.setBounds(235, 105, 170, 26);
       
       // cbSeatType.setBounds(235,150,70,26);

        //Labels
        lFrom.setBounds(150, 60, 35, 20);
        lTo.setBounds(375, 65, 20, 16);
        lTime.setBounds(150, 110, 50, 16);
        lTrainName.setBounds(335, 185, 75, 31);
      //  lSeatType.setBounds(150, 155, 35, 16);
        


        //JTable
       String[] columnNames = {"Seat Type","Fare(Tk)"};
        Object[][] data = new Object[][] {
            {"AC(Berth)",500},
            {"AC(Seat)",400},
            {"Shobhon",300}
    
        };


       
       JTable fareTable = new JTable(data,columnNames);
       JScrollPane js = new JScrollPane(fareTable);
        js.setBounds(80, 230, 585, 310);

       
        coachPanel.add(js);
       coachPanel.add(cbFrom);
        coachPanel.add(cbTo);
        coachPanel.add(cbTime);
        coachPanel.add(cbSeatType);

        coachPanel.add(lTo);
        coachPanel.add(lFrom);
        coachPanel.add(lTime);
        coachPanel.add(lTrainName);
       // add(lSeatType);

        coachPanel.setSize(695,565);
        
    }
    public void addCoachPanel()
    {
      displayPanel.removeAll();
      displayPanel.revalidate();
      displayPanel.repaint();
  
      displayPanel.add(coachPanel);
      add(displayPanel);
      SwingUtilities.updateComponentTreeUI(displayPanel);
    }
    public void builder()
    {   
        buildMenuBar();
        buildProfilePanel();
        buildCoachPanel();

    }

    //Panel Controlling

    public void addProfilePanel()
    {
      
      displayPanel.removeAll();
      displayPanel.revalidate();
      displayPanel.repaint();
      
      displayPanel.add(profilePanel);
      add(displayPanel);
      SwingUtilities.updateComponentTreeUI(displayPanel);
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
      else if(e.getSource() == btnProfile)
      {
          addProfilePanel();
      }
      else if(e.getSource() == btnCoach)
      {
          addCoachPanel();
      }
    
    }
  /* public static void main(String[] args)
    {
        userUI ui = new userUI();
        ui.run();
    }*/
}