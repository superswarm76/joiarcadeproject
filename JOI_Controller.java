import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JOI_Controller {
	JOI_Model m;
	JOI_View v;
	
	public JOI_Controller(){
		m = new JOI_Model(this);
		v = new JOI_View(this);
		v.addCustomKeyListener(new MyKeyListener());
	}

	public void restart() {
		// TODO Auto-generated method stub
		
	}

	public char[][] getArr() {
		// TODO Auto-generated method stub
		return null;
	}

	public int numRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numCols() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private class MyKeyListener extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			if (e.getKeyChar() == 'a') {
				AINextMove();
			}

		}
	}
}
