/*
 * File: LandscapeDisplay.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Brendan Doyle and Steven Parrott
 * Date: Dec. 8, 2015
 * Assignment: Lab and Project 9
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

/**
 * Displays a Landscape graphically using Swing.  The Landscape
 * can be displayed at any scale factor.
 * @author bseastwo
 */
 
 
/*
 * File provided by CS231 Professors
 * Edited by Tony Karalekas
 */ 


public class LandscapeDisplay extends JFrame
{
		protected Landscape scape;
		private LandscapePanel canvas;
		private int scale;
		
		//field added for ext.2
		private boolean shot;
		
	
		/**
		 * Initializes a display window for a Landscape.
		 * @param scape	the Landscape to display
		 * @param scale	controls the relative size of the display
		 */
		public LandscapeDisplay(Landscape scape, int scale)
		{
				// setup the window
				super("Landscape Display");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//initialize shot as false
				this.shot = false;
				this.scape = scape;
				this.scale = scale;

				// create a panel in which to display the Landscape
				this.canvas = new LandscapePanel(
																				 (int) this.scape.getWidth() * this.scale,
																				 (int) this.scape.getHeight() * this.scale);
		
				// add the panel to the window, layout, and display
				this.add(this.canvas, BorderLayout.CENTER);
				this.pack();
				this.setVisible(true);
		}
		
		//Extension 2!!
		//toggles the boolean of shot between true and false
    	public void toggleShooter(){
    		//toggle the shot boolean
    		this.shot = !this.shot;
    	}
	
		/**
		 * Saves an image of the display contents to a file.  The supplied
		 * filename should have an extension supported by javax.imageio, e.g.
		 * "png" or "jpg".
		 *
		 * @param filename	the name of the file to save
		 */
		public void saveImage(String filename)
		{
				// get the file extension from the filename
				String ext = filename.substring(
																				filename.lastIndexOf('.') + 1, filename.length());
		
				// create an image buffer to save this component
				Component tosave = this.getRootPane();
				BufferedImage image = new BufferedImage(
																								tosave.getWidth(), 
																								tosave.getHeight(), 
																								BufferedImage.TYPE_INT_RGB);
		
				// paint the component to the image buffer
				Graphics g = image.createGraphics();
				tosave.paint(g);
				g.dispose();
		
				// save the image
				try
						{
								ImageIO.write(image, ext, new File(filename));
						}
				catch (IOException ioe)
						{
								System.out.println(ioe.getMessage());
						}
		}
	
		/**
		 * This inner class provides the panel on which Landscape elements
		 * are drawn.
		 */
		private class LandscapePanel extends JPanel
		{
				/**
				 * Creates the panel.
				 * @param width		the width of the panel in pixels
				 * @param height		the height of the panel in pixels
				 */
				public LandscapePanel(int width, int height)
				{
						super();
						this.setPreferredSize(new Dimension(width, height));
						this.setBackground(Color.white);
				}
		
				/**
				 * Method overridden from JComponent that is responsible for
				 * drawing components on the screen.  The supplied Graphics
				 * object is used to draw.
				 * 
				 * @param g		the Graphics object used for drawing
				 */
				public void paintComponent(Graphics g, boolean shot){
				//added a shot parameter
						//Extension 1!!
						//Created a background for my simulation!
						super.paintComponent(g);
						Image img1 = Toolkit.getDefaultToolkit().getImage("Background.png");
						g.drawImage(img1, 0, 0, null);
						
						//EXTENSION 2
						//if shot is true, then show crosshairs in the top right of scape
						if( shot == true){
							Image img2 = Toolkit.getDefaultToolkit().getImage("Gun.png");
        					g.drawImage(img2, 0, 0, null);
						}
			
			
						// draw all the agents
						List<Cell> agents = scape.getAgents();	
						for (Cell agent: agents)
								{
										agent.draw(g, 0, 0, scale);
								}
				}
		}

		public void update() {
				Graphics g = canvas.getGraphics();
										//note the shot parameter
				canvas.paintComponent( g, this.shot );
		}

	
		public static void main(String[] args) throws InterruptedException
		{
				Landscape scape = new Landscape(225, 400);
				Random gen = new Random();
				int sim = 0;

				LandscapeDisplay display = new LandscapeDisplay(scape, 2);

				for(int i=0;i<100;i++) {
						scape.advance();
						display.update();
						Thread.sleep( 250 );
				}
		}
}
