import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

import java.util.Vector;
import net.proteanit.sql.DbUtils;

public class userUI extends JFrame  implements ActionListener,ItemListener
{

    private loginUI lui;
    private mysqlConn connection;

   //
   String uid;

    private JPanel menuPanel = new JPanel(null);
    private JPanel profilePanel = new JPanel(null);
    private JPanel coachPanel = new JPanel(null);
    private JPanel ticketPanel = new JPanel(null);
   


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
    public JLabel lFrom = new JLabel("From");
    public JLabel lTo = new JLabel("To");
    public JLabel lTime;
    public JLabel lSeatType = new JLabel("Seat Type");
    public JLabel lTrainName;

    public JComboBox cbFrom;
    public JComboBox cbTo;
    public JComboBox cbTime;
    public JComboBox cbSeatType;

    public JTable fareTable;

    public String[] locations = {"Dhaka","Chittagong","Sylhet","Rajshahi","Khulna"}; 

    //For ticketPanel
    public JComboBox cbPerson;
    public Object[] personNo = {1,2,3,4};
    public JTextField fjourneyDate = new JTextField("dd/mm/yy");
    public JTextField ftxnID = new JTextField();
    public JLabel ldate = new JLabel("Date");
    public JLabel lPerson = new JLabel("Person");
    public JLabel ltxnID = new JLabel("Txn ID");
    public JButton btnGetCost = new JButton("Get Cost");
    public JButton btnSubmit = new JButton("Submit");

    public JComboBox cbTFrom;
    public JComboBox cbTTo;
    public JComboBox cbTSeatType;

   
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
        lTime = new JLabel("Time");
        lTrainName = new JLabel();
       // lSeatType = new JLabel("Seat");

        //Combo Boxes
        cbFrom.setBounds(235, 60, 100, 26);
        cbTo.setBounds(410, 60, 100, 26);
        cbTime.setBounds(235, 105, 170, 26);

        cbFrom.addItemListener(this);
         cbTo.addItemListener(this);
        cbTime.addItemListener(this);

       
       // cbSeatType.setBounds(235,150,70,26);

        //Labels
        lFrom.setBounds(150, 60, 35, 20);
        lTo.setBounds(375, 65, 20, 16);
        lTime.setBounds(150, 110, 50, 16);
        lTrainName.setBounds(310, 185, 300, 50);
        lTrainName.setFont(new Font("sanserif",Font.BOLD,20));
      //  lSeatType.setBounds(150, 155, 35, 16);
        


        //JTable
       String[] columnNames = {"Seat Type","Fare(Tk)"};
        Object[][] data = new Object[][] {
            {"AC(Berth)",500},
            {"AC(Seat)",400},
            {"Shobhon",300}
    
        };


       
       fareTable = new JTable();
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

    public void buildTicketPanel()
    {
      ticketPanel.setSize(660,570);

      String[] typeOfSeats = {"AC","Shulobh","Shobhon"};
      cbTFrom = new JComboBox<>(locations);
      cbTTo = new JComboBox<>(locations);
      cbTSeatType = new JComboBox<>(typeOfSeats);
      cbPerson = new JComboBox<>(personNo);


      lFrom = new JLabel("From");
      lTo   = new JLabel("To");
      lSeatType = new JLabel("Seat Type");

      cbTFrom.setBounds(150,90,125,25);
      cbTTo.setBounds(360,90,125,25);
      lFrom.setBounds(85, 90, 35, 26);
      lTo.setBounds(315,90, 30, 35);

      cbTSeatType.setBounds(150, 150, 135, 30);
      lSeatType.setBounds(85,155,55,20);

      cbPerson.setBounds(150, 200, 60, 25);
      lPerson.setBounds(85, 205, 50, 16);
      

      ldate.setBounds(85,255,35,15);
      fjourneyDate.setBounds(150, 250, 165, 25);

      ltxnID.setBounds(85, 310, 40, 20);
      ftxnID.setBounds(150,305,165,28);

      btnGetCost.setBounds(150, 365, 80, 35);
      btnSubmit.setBounds(240,365,75,35);

      

      ticketPanel.add(cbTFrom);
      ticketPanel.add(cbTTo);
      ticketPanel.add(lFrom);
      ticketPanel.add(lTo);
      ticketPanel.add(cbTSeatType);
      ticketPanel.add(lSeatType);
      ticketPanel.add(cbPerson);
      ticketPanel.add(lPerson);
      ticketPanel.add(ldate);
      ticketPanel.add(fjourneyDate);
      ticketPanel.add(ftxnID);
      ticketPanel.add(ltxnID);
      ticketPanel.add(btnSubmit);
      ticketPanel.add(btnGetCost);
    }

    public void addTicketPanel()
    {
      displayPanel.removeAll();
      displayPanel.revalidate();
      displayPanel.repaint();
      
      displayPanel.add(ticketPanel);
      add(displayPanel);
      SwingUtilities.updateComponentTreeUI(displayPanel);
    }
    public void builder()
    {   
        buildMenuBar();
        buildProfilePanel();
        buildCoachPanel();
        buildTicketPanel();

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
      else if(e.getSource() == btnTicket)
      {
        addTicketPanel();
      }
    
    }

  @Override
  public void itemStateChanged(ItemEvent evt) {
    JComboBox cb = (JComboBox) evt.getSource();

    Object item = evt.getItem();

     String sFrom = "'"+cbFrom.getSelectedItem()+"'";
      String sTo = "'"+cbTo.getSelectedItem()+"'";

    if(cb == cbTime)
    {
      String tTime = "'"+cbTime.getSelectedItem()+"'";
      String sql = "Select * from dc";
     System.out.println(sql);
      ResultSet  rs = connection.getResult(sql);

      if(cbTime.getSelectedItem() == null)
      {
        lTrainName.setText("");
      }

      fareTable.setModel(DbUtils.resultSetToTableModel(rs));
      sql = "select T_name from T_Schedule where STime = "+tTime;
      rs = connection.getResult(sql);
      System.out.println(sql);
      try
      {
        while(rs.next())
        {
        lTrainName.setText(rs.getString("T_Name"));
      }
      }
      catch(Exception e)
      {
        System.out.println(e);
      }

    }
    else if(evt.getStateChange() == ItemEvent.SELECTED) {

      cbTime.removeAllItems();

       
       String sql = "SELECT STime FROM `T_schedule` WHERE `LFrom` = +"+sFrom+ "AND" +" `LTo` = "+sTo;
       try{
        ResultSet rs = connection.getResult(sql);
       
        while(rs.next())
        {
          cbTime.addItem(rs.getString("STime"));
        }

       }
       catch(Exception exception)
       {
        JOptionPane.showMessageDialog(null, exception);
       }

    }
  else if (evt.getStateChange() == ItemEvent.DESELECTED) {
      // Item is no longer selected
    }
  }


  /* public static void main(String[] args)
    {
        userUI ui = new userUI();
        ui.run();
    }*/
}