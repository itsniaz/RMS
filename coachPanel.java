import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class coachPanel extends JPanel
{
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
  

    public void buildCoachPanel()
    {
       
    }
    public coachPanel()
    {
        setLayout(null);
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

       
       add(js);
        add(cbFrom);
        add(cbTo);
        add(cbTime);
        add(cbSeatType);

        add(lTo);
        add(lFrom);
        add(lTime);
        add(lTrainName);
       // add(lSeatType);

        setSize(695,565);
        
    }
}