import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class testUI extends JFrame
{
    public testUI()
    {
        setLayout(null);


    
      try
      {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
      }
      catch(Exception exception)
      {
        System.out.println("NF");
      }

        JComboBox jb = new JComboBox <>();
        jb.addItem("Dhaka");
        jb.addItem("Chi");

        jb.setBounds(200,200,50,50);
        add(jb);
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new testUI();
    }
}