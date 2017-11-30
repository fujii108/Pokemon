//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Demo {
 
 //Note: Typically the main method will be in a
 //separate class. As this is a simple one class
 //example it's all in the one class.
 public static void main(String[] args) {
 
 new Demo();
 }

 public Demo()
 {
 JFrame guiFrame = new JFrame();
 
 //make sure the program exits when the frame closes
 guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 guiFrame.setTitle("Pokemon");
 guiFrame.setSize(600,600);
 
 //This will center the JFrame in the middle of the screen
 guiFrame.setLocationRelativeTo(null);
 
 //Options for the JComboBox 
 String[] rarePoke = {"squirtle", "Pikachu", "bulbasaur"
 ,"Mew", "Mewtwo", "Charizard", "Ivasaur", "NeoQueen", "Ditto"};
 
 //Options for the JList
 String[] medRare = {"Butterfree", "Spearow", "Nidoran", "Clefairy"
 , "Nidoking", "zubat", "ninetales", "jigglypuff", "vulpex"
 , "Scyther", "Golbat", "Zubat", "oddish", "gloom"
 , "golduck"};
 
 //The first JPanel contains a JLabel and JCombobox
 final JPanel comboPanel = new JPanel();
 JLabel comboLbl = new JLabel("Rare Pokemon:");
 JComboBox rare = new JComboBox(rarePoke);
 
 comboPanel.add(comboLbl);
 comboPanel.add(rare);
 
 //Create the second JPanel. Add a JLabel and JList and
 //make use the JPanel is not visible.
 final JPanel listPanel = new JPanel();
 listPanel.setVisible(false);
 JLabel listLbl = new JLabel("Sorta Rare Pokemon:");
 JList sorta = new JList(medRare);
 sorta.setLayoutOrientation(JList.HORIZONTAL_WRAP);
 
 listPanel.add(listLbl);
 listPanel.add(sorta);
 
 JButton sortaORrare = new JButton( "Rare or Sorta Rare");

 sortaORrare.addActionListener(new ActionListener()
 {
 @Override
 public void actionPerformed(ActionEvent event)
 {
 //When the fruit of veg button is pressed
 //the setVisible value of the listPanel and
 //comboPanel is switched from true to 
 //value or vice versa.
 listPanel.setVisible(!listPanel.isVisible());
 comboPanel.setVisible(!comboPanel.isVisible());

 }
 });
 
 //The JFrame uses the BorderLayout layout manager.
 //Put the two JPanels and JButton in different areas.
 guiFrame.add(comboPanel, BorderLayout.NORTH);
 guiFrame.add(listPanel, BorderLayout.CENTER);
 guiFrame.add(sortaORrare,BorderLayout.SOUTH);
 
 //make sure the JFrame is visible
 guiFrame.setVisible(true);
 }
 
}