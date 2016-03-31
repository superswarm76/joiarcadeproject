import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
public class JOI_Controller {
	JOI_Model m;
	JOI_View v;

	public JOI_Controller(){
		m = new JOI_Model(this);
		v = new JOI_View(this);
		v.addCustomKeyListener(new MyKeyListener());
		
		restart();
	}

	public void timePassed(int time){
		m.timeElapsed(time);
		v.redraw(m.getCombinedView());
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
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				m.moveUp();
				v.redraw(m.getCombinedView());
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				m.moveLeft();
				v.redraw(m.getCombinedView());
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN){
				m.moveDown();
				v.redraw(m.getCombinedView());
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				m.moveRight();
				v.redraw(m.getCombinedView());
			}

		}
		
	}

	public void increaseScore(int i) {
		v.updateScore(i);
		
	}
}
