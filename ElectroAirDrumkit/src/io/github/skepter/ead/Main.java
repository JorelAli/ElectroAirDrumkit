package io.github.skepter.ead;

import io.github.skepter.ead.Utilities.SoundType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.leapmotion.leap.Controller;

public class Main {

	private JFrame frame;
	public static Main instance;
	JComboBox<SoundType> leftDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> farLeftDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> rightDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> farRightDrum = new JComboBox<SoundType>();

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
		frame.setBounds(100, 100, 491, 429);
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
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addGap(27).addComponent(farLeftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(60)
						.addComponent(leftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
						.addComponent(rightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(65)
						.addComponent(farRightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(45)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(173)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(leftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(rightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(farLeftDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(farRightDrum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addContainerGap(197, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
