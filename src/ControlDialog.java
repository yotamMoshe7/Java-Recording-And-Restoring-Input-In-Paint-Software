import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;


public class ControlDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SPEED = 3;
	private JButton okButton;
	private JSlider speed;
	private JComboBox<String> thickness;
	private JRadioButton[] radioButtonsArray = new JRadioButton[4];;
	private String[] colorsArray = { "Black", "Red", "Blue", "Green" };
	private int[] firstCharArray = { 66, 82, 76, 71 };
	private Color newColor;
	private int newSpeed;
	private int newThickness;
	private DrawPanel drawPanel;

	public ControlDialog(DrawPanel drawPanel) {
		//	super(owner, "Control Frame", ModalityType.APPLICATION_MODAL);
		setModalityType(DEFAULT_MODALITY_TYPE);
		this.drawPanel = drawPanel;
		drawPanel.stopRestore(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(350, 350);
		setResizable(false);
		setLocationRelativeTo(drawPanel); // Center the frame
		setAlwaysOnTop(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
		initColorsPanel();
		initThicknessPanel();
		initSpeedPanel();
		initOkPanel();
		setVisible(true);
	}

	public Color getColor() {
		return this.newColor;
	}

	public int getThickness() {
		return this.newThickness;
	}

	public int getNewSpeed() {
		return this.newSpeed;
	}

	private void initOkPanel() {
		// create JButton ok panel
		JPanel okPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		getRootPane().setDefaultButton(okButton);
		okPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		okPanel.add(okButton);
		add(okPanel);
	}

	private void initSpeedPanel() {
		// create speed scale panel
		JPanel downPanel = new JPanel();

		// create speed slider box
		speed = new JSlider();
		speed.setValue(DEFAULT_SPEED);
		speed.setMaximum(3);
		speed.setMinimum(1);
		speed.setMajorTickSpacing(1);
		speed.setPaintLabels(true);
		speed.setPaintTicks(true);
		speed.setBorder(new TitledBorder("Speed"));
		downPanel.add(speed);
		add(downPanel);
	}

	private void initThicknessPanel() {
		// create thickness panel
		JPanel middlePanel = new JPanel();
		String[] thicknessArr = { "Default thickness", "Heavy thickness", "Eazy thickness" };

		// create combo box
		thickness = new JComboBox<>(thicknessArr);
		thickness.setSelectedIndex(0);
		thickness.setToolTipText("Choose the thickness");
		middlePanel.add(thickness);
		add(middlePanel);
	}

	private void initColorsPanel() {

		// create colors panel
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new TitledBorder("Color"));
		ButtonGroup group = new ButtonGroup();
		// create JRadio Button
		for (int i = 0; i < 4; i++) {
			JRadioButton newColor = new JRadioButton(colorsArray[i]);
			newColor.setActionCommand(colorsArray[i]);
			newColor.setMnemonic(firstCharArray[i]);
			radioButtonsArray[i] = newColor;
			group.add(newColor);
			newColor.setSelected(i == 0);
			topPanel.add(newColor);
		}

		add(topPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get speed value
		newSpeed = speed.getValue();

		// get thickness value
		if (thickness.getSelectedIndex() == 0) {
			newThickness = 10;
		} else if (thickness.getSelectedIndex() == 1) {
			newThickness = 20;
		} else {
			newThickness = 5;
		}


		drawPanel.stopRestore(false);

		// get color
		if (radioButtonsArray[0].isSelected()) {
			newColor = Color.BLACK;
		}
		if (radioButtonsArray[1].isSelected()) {
			newColor = Color.RED;
		}
		if (radioButtonsArray[2].isSelected()) {
			newColor = Color.BLUE;
		}
		if (radioButtonsArray[3].isSelected()) {
			newColor = Color.GREEN;
		}
		dispose();
	}

}
