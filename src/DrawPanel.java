import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
class DrawPanel extends JPanel implements MouseMotionListener, ActionListener{
	private ArrayList<Point> pointArray;
	private Color restoreColor;
	private int restoreThickness;
	private int restoreSpeed = 3; // default speed
	private boolean restoreNow = false;
	private boolean controlDialogOn = false;
	private int restoreUntil = 0;

	public DrawPanel(){
		addMouseMotionListener(this);
		pointArray = new ArrayList<Point>();
	}

	public void mouseDragged(MouseEvent e) {
		// Get the new location and repaint the screen
		Point point = new Point(e.getX(), e.getY());
		pointArray.add(point);
		repaint();
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		// draw
		if(restoreNow == false){
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("ARIEL", 3, 25));
			graphics.drawString("RECORDING...", 1, 40);
			graphics.setColor(Color.BLACK);
			for(int i=0; i < pointArray.size(); i++){
				graphics.fillOval(pointArray.get(i).getMouseX(),
						pointArray.get(i).getMouseY(), 10, 10);
			}	
		}
		//restore
		else {
			graphics.setColor(Color.BLACK);
			graphics.setFont(new Font("ARIEL", 3, 25));
			graphics.drawString("RESTORING...", 1, 40);
			graphics.setColor(this.restoreColor);
			for(int i=0; i < restoreUntil && i < pointArray.size(); i++){
				graphics.fillOval(pointArray.get(i).getMouseX(),
						pointArray.get(i).getMouseY(), this.restoreThickness, this.restoreThickness);	
			}
		}
	}

	public void restoreDraw(Color newColor, int newThickness, int newSpeed){
		this.restoreColor = newColor;
		this.restoreSpeed = newSpeed;
		this.restoreThickness = newThickness;
		this.restoreUntil = 0;

		//create timer with correct speed
		Timer timer = new Timer((int) (70 * ((double)1 / restoreSpeed)), this);
		timer.start();
		this.restoreNow = true;
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		//not in use
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// repaint until i
		if(e.getSource().getClass() == Timer.class){
			repaint();
			// advance restoreUntil
			restoreUntil++;
			// when done --> stop timer
			if(restoreUntil == pointArray.size()){
				((Timer)e.getSource()).stop();
				this.restoreNow = false;
				this.pointArray.clear();
				repaint();
			}
			if(controlDialogOn == true){
				((Timer)e.getSource()).stop();
				this.controlDialogOn = false;
			}
		}
	}

	public void stopRestore(boolean enable){
		this.controlDialogOn = enable;
	}



}
