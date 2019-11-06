import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements KeyListener{
	private DrawPanel drawPanel;
	private static final long serialVersionUID = 1L;

	public MainFrame(){
		this.drawPanel = new DrawPanel();
		addKeyListener(this);
		this.drawPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(this.drawPanel);
		setGeneralProporties();
	}

	public void setGeneralProporties(){
		setTitle("HW3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setLocationRelativeTo(null); // Center the frame
		setResizable(false);
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub 

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



}
