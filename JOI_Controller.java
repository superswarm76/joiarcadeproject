
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JOI_Controller {
	JOI_Model m;
	JOI_View v;
	
	public JOI_Controller(){
		m = new JOI_Model(this);
		v = new JOI_View(this);
		v.addCustomKeyListener(new MyKeyListener());
		
		restart();
	}

	public void restart() {
		m.startOver();
		v.redraw(m.getCombinedView());
		
	}

	public char[][] getArr() {
		return m.getCombinedView();
	}

	public int numRows() {
		return m.numRows;
	}

	public int numCols() {
		return m.numCols;
	}
	
	private class MyKeyListener extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			if (e.getKeyChar() == 'w') {
				m.moveUp();
				v.redraw(m.getCombinedView());
			}
			if (e.getKeyChar() == 'a') {
				m.moveLeft();
				v.redraw(m.getCombinedView());
			}
			if (e.getKeyChar() == 's') {
				m.moveDown();
				v.redraw(m.getCombinedView());
			}
			if (e.getKeyChar() == 'd') {
				m.moveRight();
				v.redraw(m.getCombinedView());
			}
			m.printBoard(m.getCombinedView());

		}
	}
}