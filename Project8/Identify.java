/*
 * File: Identify.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Steven Parrott
 * Date: Nov. 22, 2015
 * Assignment: Project 8
 */


//imports
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


//identify class
//got help from CP with JFrame implementation
public class Identify extends JFrame implements ActionListener{
    private JLabel messageDisp;
    private JButton newGame;
    private JButton aChoice;
    private JButton bChoice;
    private boolean choiceMode;
    private BirdID session;
    private String object;
    private String instruction;

    //constructor
    public Identify(){
        
        //set up session with all the treeNodes
        //each BTNode has an answer choice like "smaller" or "large"
        //and each choice then points to a subsequent new BTNode
        session = new BirdID(new BTNode("Was the bird larger or smaller than a crow?", "Larger", "Smaller", 
        							/*largerCrow*/new BTNode("Was the bird larger or smaller than a hawk?", "Larger", "Smaller",
        										/*larger*/new BTNode("Where did you see this bird?", "Water", "Land",
        												/*water*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        														/*bb*/new BTNode("Cormorant, Canada Goose, American Black Duck, Loon or Blue Heron", "", "", null, null),
        														/*wc*/new BTNode("Common Merganser, Long-Tailed Duck, Common Eider, or Mallard", "","", null, null)
        														),
        												/*land*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        														/*bb*/new BTNode("Red Tailed Hawk, Northern Pintail, Turkey, Vulture, or Raven", "", "", null, null),
        														/*wc*/new BTNode("Herring Gull, Ring-Billed Gull, Pileated Woodpecker, Bald Eagle", "","", null, null)
        														)
        													),
        										/*smaller*/new BTNode("Where did you see this bird?", "Water", "Land",
        												/*water*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        														/*bb*/new BTNode("Bufflehead, Ring-Neck Duck, Common Goldeneye, or Hooded Merganser", "", "", null, null),
        														/*wc*/new BTNode("Lesser Scaup, Common Eider, Long-Tailed Duck, or Common Merganser", "", "", null, null)
        														),
        												/*land*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        														/*bb*/new BTNode("Sharp-Shinned Hawk, Rock Pigeon, Green-Winged Teal", "", "", null, null),
        														/*wc*/new BTNode("Blue Jay, Cardinal, Robin, or Ruffed Grouse", "", "", null, null)
        														)
        													)
        											),
        									
        							/*smallerCrow*/new BTNode("Was the bird larger or smaller than a robin?", "Larger", "Smaller",
        										/*larger*/new BTNode("Where did you see this bird?", "Water", "Land",
        												/*water*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        														/*bb*/new BTNode("Hooded Merganser, Bufflehead, Lesser Scaup", "", "", null, null),
        														/*wc*/new BTNode("Belted Kingfisher or Bufflehead", "","", null, null)
        														),
        												/*land*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        														/*bb*/new BTNode("Mourning Dove, European Starling, American Robin", "", "", null, null),
        														/*wc*/new BTNode("Cardinal, Hairy Woodpecker, Downy Woodpecker, or a Robin", "","", null, null)
        														)
        													),
        										/*smaller*/new BTNode("Where did you see this bird?", "Water", "Land",
        												/*water*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        														/*bb*/new BTNode("Belted Kingfisher", "", "", null, null),
        														/*wc*/new BTNode("Belted Kingfisher", "", "", null, null)
        														),
        												/*land*/new BTNode("What color was the bird?", "Black/Brown", "White/Colorful",
        															new BTNode("Black-capped Chickadee, Song Sparrow, House Sparrow, American Tree Sparrow, Dark-Eyed Junco, Common Grackle", "", "", null, null),
        															new BTNode("American Goldfinch, Tufted Titmouse, Red-breasted Nuthatch, White-throated Sparrow, House Finch, White Throated Sparrow", "", "", null, null)
        														)
        													)
        											)
        										));
          
        
        //this sets up the window for the ID session
        this.setTitle("Ornithology Labs: Bird Identification");
        this.setPreferredSize(new Dimension(600,300));
        this.setLocation(500, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        //this adds the Jbutton onto the JFrame
        //this button starts the bird ID
        this.newGame = new JButton("Start Bird ID:");
        this.newGame.setBounds(225, 200, 150, 50);
        this.newGame.addActionListener(this);
        this.add(newGame);
        //this adds another Jbutton onto the JFrame
        //this gets the aChoice option button
        this.aChoice = new JButton(session.getCurrentA());
        this.aChoice.setBounds(125, 200, 150, 50); 
        this.aChoice.addActionListener(this);
        this.add(aChoice);
        //this adds another Jbutton onto the JFrame
        //this gets the bChoice option button
        this.bChoice = new JButton(session.getCurrentB());
        this.bChoice.setBounds(325, 200, 150, 50);
        this.bChoice.addActionListener(this);
        this.add(bChoice);
        
        
        //extension 
        //found JFrame image capabilities on StackOverflow
        //decorated the JFrame with an assortment of bird pictures
        JLabel imageLabel = new JLabel( new ImageIcon("robin.png")); 
        add(imageLabel);
        imageLabel.setBounds(-150, -50, 400, 400);
		imageLabel.setVisible(true);
		
		JLabel imageLabel1 = new JLabel( new ImageIcon("mallard.png")); 
        add(imageLabel1);
        imageLabel1.setBounds(-25, -50, 400, 400);
		imageLabel1.setVisible(true);
		
		JLabel imageLabel2 = new JLabel( new ImageIcon("bluejay.png")); 
        add(imageLabel2);
        imageLabel2.setBounds(105, -50, 400, 400);
		imageLabel2.setVisible(true);
		
		JLabel imageLabel3 = new JLabel( new ImageIcon("woodpecker.png")); 
        add(imageLabel3);
        imageLabel3.setBounds(225, -50, 400, 400);
		imageLabel3.setVisible(true);

		JLabel imageLabel4 = new JLabel( new ImageIcon("hawk.png")); 
        add(imageLabel4);
        imageLabel4.setBounds(350, -50, 400, 400);
		imageLabel4.setVisible(true);


        
        //received more help from CP here
        //adding and configuring the Text display
        this.messageDisp = new JLabel("");
        this.messageDisp.setBounds(100, 40, 400, 70);
        this.messageDisp.setHorizontalAlignment(SwingConstants.CENTER);
        this.messageDisp.setHorizontalTextPosition(SwingConstants.CENTER);
        this.messageDisp.setFont(new Font("Arial", Font.BOLD, 15));
        this.messageDisp.setVisible(true);
        this.add(messageDisp);
        //management stuff suggested by CP
        toggleChoiceMode(false);
        show();
        pack();
    }
    
	//toggles which buttons are shown
	public void toggleChoiceMode(boolean choose){
    	this.aChoice.setVisible(choose);
        this.bChoice.setVisible(choose);
        this.newGame.setVisible(!choose);
    }
    
    //this main code creates the session
    public static void main(String args[]){
        new Identify();    
    }
    
    /**
     * This code in actionPerformed is kinda of like an updateState for each button press
     * received help and advice from CP
     * manages what to do with each button press possibility
     */
    public void actionPerformed(ActionEvent e) {
        //if a new game
        if(e.getSource().equals(this.newGame)){
            //show the choice buttons
            toggleChoiceMode(true);
            //restart the ID session
			session.restartID();
			this.messageDisp.setText(session.getCurrent());
			this.aChoice.setText(session.getCurrentA());
			this.bChoice.setText(session.getCurrentB());
			this.revalidate();  
        }
        
        if(e.getSource().equals(this.aChoice)){
			//if the user chooses choice A    
			this.messageDisp.setText(session.getA());
			//navigate the tree!
			this.aChoice.setText(session.getCurrentA());
			this.bChoice.setText(session.getCurrentB());
			
			//if you reach the end of the tree or the leaf
			//turn off the buttons
			if(session.isLeaf()){
				toggleChoiceMode(false);
			}
			this.revalidate();  
        }
        
        if(e.getSource().equals(this.bChoice)){
			//if the user chooses choice B
			this.messageDisp.setText(session.getB());
			//navigate the tree!
			this.aChoice.setText(session.getCurrentA());
			this.bChoice.setText(session.getCurrentB());
			
			//if you reach the end of the tree or the leaf
			//turn off the buttons
			if(session.isLeaf()){
				toggleChoiceMode(false);
			}
			this.revalidate();  
        }
    }
}