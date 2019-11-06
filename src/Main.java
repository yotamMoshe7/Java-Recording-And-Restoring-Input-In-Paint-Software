import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JApplet;

// To restore the painting, click on CTR and Space

@SuppressWarnings("serial")
public class Main extends JApplet implements KeyListener{
	private DrawPanel drawPanel;

	public Main(){
		this.drawPanel = new DrawPanel();
		addKeyListener(this);
		setSize(700, 700);
		add(this.drawPanel);
		setFocusable(true);
		setVisible(true); 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE   && e.isControlDown()){
			ControlDialog controlDialog = new ControlDialog(this.drawPanel);
			this.drawPanel.restoreDraw(controlDialog.getColor(), controlDialog.getThickness(),
					controlDialog.getNewSpeed());
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
