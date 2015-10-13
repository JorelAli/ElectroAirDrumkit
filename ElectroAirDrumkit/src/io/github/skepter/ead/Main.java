package io.github.skepter.ead;

import io.github.skepter.ead.Utilities.SoundType;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import com.leapmotion.leap.Controller;

public class Main {

	private JFrame frame;
	public static Main instance;
	JComboBox<SoundType> leftDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> farLeftDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> rightDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> farRightDrum = new JComboBox<SoundType>();
	public LeapVisualizerPanel panel = new LeapVisualizerPanel();

	/**
	 * Launch the application.
	 */
	
    Controller controller = new Controller();
    public SampleListener sample;
	
	public static void main(String[] args) {
		Main window = new Main();
		window.frame.setVisible(true);
	}


	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		sample = new SampleListener();
		controller.addListener(sample);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		instance = this;
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		leftDrum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		farLeftDrum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		rightDrum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		farRightDrum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		@SuppressWarnings("rawtypes")
		JComboBox[] drums = new JComboBox[] { leftDrum, rightDrum, farLeftDrum, farRightDrum };
		for (JComboBox<SoundType> drum : drums) {
			for (SoundType type : SoundType.values()) {
				drum.addItem(type);
			}
		}
		

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(farLeftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(leftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(146)
							.addComponent(rightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(farRightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(173)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(leftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(farLeftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(farRightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}

class LeapVisualizerPanel extends JComponent{								//component for drawing stuff
	private static final long serialVersionUID = 1337L;
	int fingerX, fingerY;													//x and y coords for circle

	public LeapVisualizerPanel() {
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, 800, 520);

		Point2D center = new Point2D.Float(20,20);
		float radius = 40;
		float[] distribution = {0.0f, 1.0f};
		float[] hsbcolor = Color.RGBtoHSB(255, 220, 178, null);
		float[] hsbcolor2 = Color.RGBtoHSB(231, 158, 109, null);
		Color[] colors = {Color.getHSBColor(hsbcolor[0], hsbcolor[1], hsbcolor[2]),Color.getHSBColor(hsbcolor2[0], hsbcolor2[1], hsbcolor2[2])};
		RadialGradientPaint gradient = new RadialGradientPaint(center,radius,distribution,colors);

		if(!(fingerX==0 && fingerY==0)){
			g2d.setPaint(gradient);
			g2d.fillOval(fingerX, fingerY, 40, 40);
		}
	}

	public int getFingerX() {
		return fingerX;
	}
	public void setFingerX(int fingerX) {
		this.fingerX = fingerX;
	}
	public int getFingerY() {
		return fingerY;
	}
	public void setFingerY(int fingerY) {
		this.fingerY = fingerY;
	}
}