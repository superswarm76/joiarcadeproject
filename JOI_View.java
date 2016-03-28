import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;



public class JOI_View implements ActionListener{
	JOI_Controller c;
	JFrame window;
	MyDrawingPanel drawingPanel;
	private int dim = 20;
	BufferedImage pic[][];
	BufferedImage GROUND,PLAYER,UNWALKABLE;
	JLabel score;
	Timer timer;
	int time = 0;
	
	public JOI_View(JOI_Controller c){
		this.c = c;
		
		window = new JFrame();
		window.setTitle("Name here");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(450, 600);

		JMenuBar menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);

		JMenu Game = new JMenu("Game");
		menuBar.add(Game);

		JMenuItem newGame = new JMenuItem("New Game");
		Game.add(newGame);
		newGame.addActionListener(e -> {
			c.restart();
		});


		JMenuItem exit = new JMenuItem("Exit");
		Game.add(exit);
		exit.addActionListener(e -> {
				System.exit(0);
		});

		JPanel main = new JPanel();
		main.setBounds(new Rectangle(20, 20, 600, 400));

		drawingPanel = new MyDrawingPanel(c.getArr());
		drawingPanel.setPreferredSize(new Dimension(400, 400));
		drawingPanel.setMaximumSize(new Dimension(400, 400));
		drawingPanel.setMinimumSize(new Dimension(400, 400));
		drawingPanel.setBounds(new Rectangle(20, 20, 400, 400));
		drawingPanel.setBounds(20, 20, 400, 400);
		drawingPanel.setBorder(BorderFactory.createEtchedBorder());
		window.getContentPane().setLayout(new BorderLayout(0, 0));
		main.add(drawingPanel);
		main.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(100, 70));
		panel_1.setBounds(new Rectangle(125, 400, 200, 50));
		panel_1.setBackground(UIManager.getColor("Button.background"));
		panel_1.setBorder(new TitledBorder(null, "Time ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		main.add(panel_1);

		JLabel timePassed = new JLabel(Integer.toString(time));
		panel_1.add(timePassed);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(100, 70));
		panel_2.setBounds(new Rectangle(20, 400, 200, 50));
		panel_2.setBackground(UIManager.getColor("Button.background"));
		panel_2.setBorder(new TitledBorder(null, "Score", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(20, 500, 400, 70);
		main.add(panel_2);

		score = new JLabel(timePassed.getText());
		panel_2.add(score);
		

		window.getContentPane().add(main);
		window.setVisible(true);
		
		timer = new Timer(1000,this);
		timer.start();
		timer.addActionListener(e -> {
			time++;
			timePassed.setText(Integer.toString(time));
		});
	}
	
	public void addCustomKeyListener(KeyListener m) {
		window.addKeyListener(m);
	}
	
	
	private class MyDrawingPanel extends JPanel {
		static final long serialVersionUID = 1234567890L;

		int numRows = c.numRows();
		int numCols = c.numCols();

		

		MyDrawingPanel(char[][] arr) {
			pic = new BufferedImage[numRows][numCols];
			try {
				UNWALKABLE = ImageIO.read(new File("tree.gif"));;
				GROUND = ImageIO.read(new File("ground.gif"));
				PLAYER = ImageIO.read(new File("player.gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			// set the pics based on the controller.getArr()
			char[][] arr = c.getArr();
			for (int row = 0; row < numRows; row++) {
				for (int col = 0; col < numCols; col++) {

					char test = arr[row][col];
					pic[row][col] = getValueOf(test);

				}
			}

			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					int x = j * dim;
					int y = i * dim;
					g.drawImage(pic[i][j], x, y, this.getWidth() / numRows, this.getHeight() / numCols, null);
				}
			}

		}

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	public void redraw(char[][] c) {
		for(int i = 0; i < c.length; i++){
			for(int j = 0; j < c[0].length; j++){
				setPic(i,j,c[i][j]);
			}
		}
		drawingPanel.paintComponent(drawingPanel.getGraphics());
		
	}

	private void setPic(int row, int col, char state) {
		pic[row][col] = getValueOf(state);
	}

	private BufferedImage getValueOf(char state) {
		if(state == '*'){
			return PLAYER;
		}
		if(state == '-'){
			return GROUND;
		}
		if(state == 'x'){
			return UNWALKABLE;
		}
		return null;
	}
	
}