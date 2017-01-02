/*
 * File: ElevatorSimulation.java
 * Edited By: Anthony Karalekas
 * Help:
 * Date: Nov. 13, 2015
 * Assignment: Lab 7 and Project 7
 */


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Simulates the operation of a bank of elevators.  This simulation was 
 * inspired by waiting for an elevator at the Marriott Marquis Times Square 
 * elevator bank, <a href="http://flic.kr/p/rvHG7" target="_blank">seen here</a>.
 * <p>
 * The Javadoc for this project was generated with the following
 * command:
 * <pre><code>
 * $ javadoc -d html -private -link http://download.oracle.com/javase/6/docs/api/ *.java
 * </code></pre>
 * 
 * @author bseastwo
 * Edited by Tony Karalekas
 */
 
  /*
  * File provided by CS231 Professors
  * Edited by Tony Karalekas
  */
 
 
public class ElevatorSimulation
{
	private ElevatorBank bank;
	private Landscape landscape;
	private LandscapeDisplay display;
	private JLabel textMessage;
	
	// controls whether the simulation is playing, paused, or exiting
	private enum PlayState { PLAY, PAUSE, STOP }
	private PlayState state;
		
	// simulation control
	private int iteration;
	// the number of milliseconds to pause between iterations.
	private int pause;
	
	/**
	 * Initializes an elevator simulation.  Creates an elevator bank and
	 * populates a landscape with the elevators.
	 * 
	 * @param lifts the number of elevators in the simulation
	 * @param floors the number of floors in the simulation
	 * @param capacity the passenger capacity of each elevator
	 */
	public ElevatorSimulation(int lifts, int floors, int capacity)
	{
		this.pause = 1000;
		
		// create the elevator bank
		this.bank = new ElevatorBank(lifts, floors, capacity);
		Dispatch.instance().setBank(this.bank);
		
		// create the landscape
		this.landscape = new Landscape(bank.getFloorCount() + 4, bank.getElevatorCount() * 4 + 4);
		Dispatch.instance().setLandscape(this.landscape);
		
		// create the display
		if (this.display != null)
			this.display.dispose();
		this.display = new LandscapeDisplay(landscape, 16);

		this.state = PlayState.PLAY;
		
		// add the elevators to the landscape
		for (int i = 0; i < this.bank.getElevatorCount(); i++)
		{
			this.bank.getElevator(i).setPosition(4 * i + 2, 2);
			this.landscape.addAgent(this.bank.getElevator(i));
		}
		
		this.setupUI();
	}
	
	/**
	 * Sets up the UI controls for the elevator simulation.
	 */
	private void setupUI()
	{
		// add elements to the UI
		this.textMessage = new JLabel("Your text here.");
		JButton pause = new JButton("Pause");
		JButton quit = new JButton("Quit");
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(this.textMessage);
		panel.add(pause);
		panel.add(quit);
		
		this.display.add(panel, BorderLayout.SOUTH);
		this.display.pack();
		
		// listen for keystrokes
		Control control = new Control();
		pause.addActionListener(control);
		quit.addActionListener(control);
		this.display.addKeyListener(control);
		this.display.setFocusable(true);
		this.display.requestFocus();
	}
	
	/**
	 * Implements one iteration (time step) of the elevator simulation.
	 */
	public void iterate()
	{
		this.iteration++;

		if (this.state == PlayState.PLAY)
		{
			// dispatch new passengers
			Dispatch.instance().updateState();
			
			// update the landscape, display
			this.landscape.advance();
			this.display.repaint();
		
			// print simulation information as well
			System.out.println("\n\n******");
			System.out.println(this.bank);
			for (int i = 0; i < this.bank.getElevatorCount(); i++)
				System.out.printf("%3d    %s\n", i, this.bank.getElevator(i));
			System.out.println(Dispatch.instance().formatStatistics());
			
			this.textMessage.setText(Dispatch.instance().formatStatistics());
		}

		// pause for refresh
		try
		{
			Thread.sleep(this.pause);
		}
		catch (InterruptedException ie)
		{
			// do threads get insomnia?
			ie.printStackTrace();
		}
	}
	
		/**
	 * Provides simple keyboard control to the simulation by implementing
	 * the KeyListener interface.
	 */
	private class Control extends KeyAdapter implements ActionListener
	{
		/**
		 * Controls the simulation in response to key presses.
		 */
		public void keyTyped(KeyEvent e)
		{
			if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PLAY)
			{
				state = PlayState.PAUSE;
				System.out.println("*** Simulation paused ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PAUSE)
			{
				state = PlayState.PLAY;
				System.out.println("*** Simulation resumed ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("q"))
			{
				state = PlayState.STOP;
				System.out.println("*** Simulation ended ***");
			}
		}

		public void actionPerformed(ActionEvent event)
		{
			System.out.println(event.getActionCommand());
			
			if (event.getActionCommand().equalsIgnoreCase("Pause") &&
				state == PlayState.PLAY)
			{
				state = PlayState.PAUSE;
				((JButton) event.getSource()).setText("Play");
			}
			else if (event.getActionCommand().equalsIgnoreCase("Play") &&
				state == PlayState.PAUSE)
			{
				state = PlayState.PLAY;
				((JButton) event.getSource()).setText("Pause");
			}
			else if (event.getActionCommand().equalsIgnoreCase("Quit"))
			{
				state = PlayState.STOP;
			}
		}	
	}

	/**
	 * Provides simple keyboard control to the simulation by implementing
	 * the KeyListener interface.
	 */
		/*
	private class Control extends KeyAdapter
	{
		public void keyTyped(KeyEvent e)
		{
			if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PLAY)
			{
				state = PlayState.PAUSE;
				System.out.println("*** Simulation paused ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("p") && 
				state == PlayState.PAUSE)
			{
				state = PlayState.PLAY;
				System.out.println("*** Simulation resumed ***");
			}
			else if (("" + e.getKeyChar()).equalsIgnoreCase("q"))
			{
				state = PlayState.STOP;
				System.out.println("*** Simulation ended ***");
			}
		}	
	}
*/

	/**
	 * Runs an elevator simulation.
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		// parse command line parameters
		String fileIn = null;
		String fileOut = null;
		for (int i = 0; i < args.length; i++)
		{
			// -h prints help information
			if (args[i].equalsIgnoreCase("-h"))
			{
				System.out.println("Usage:\n\tjava ElevatorSimulation {-o fileOut} {-i fileIn} {-h}\n" +
					"\t    -h          prints this help message\n" +
					"\t    -o fileOut  writes spawned passenger information to fileOut\n" +
					"\t    -i fileIn   reads spawned passenger information from fileIn\n" +
					"\t    fileIn and fileOut should not be the same file\n");
				System.exit(0);
			}

			// -o specifies an output file
			else if (args[i].equalsIgnoreCase("-o") && args.length > i+1)
				fileOut = args[i+1];

			// -i specifies an input file
			else if (args[i].equalsIgnoreCase("-i") && args.length > i+1)
				fileIn = args[i+1];
		}
		
		// initialize the simulation
		ElevatorSimulation sim = new ElevatorSimulation(8, 25, 8);
		sim.pause = 60;

		// configure the dispatcher
		Dispatch.instance().setSpawnTries(8);
		Dispatch.instance().setGroundProbability(0.10);
		Dispatch.instance().setOtherProbability(0.08);

		if (fileIn != null)
			Dispatch.instance().readFromFile(fileIn);
		if (fileOut != null)
			Dispatch.instance().writeToFile(fileOut);

		System.out.println("Init => " + sim);
		
		// run simulation until terminated
		while (sim.state != PlayState.STOP)
		{
			sim.iterate();
		}
		
		// clean up and close the application
		Dispatch.instance().closeFiles();
		sim.display.dispose();
	}



}
