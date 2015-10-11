package io.github.skepter.ead;

import io.github.skepter.ead.Utilities.SoundType;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.SwipeGesture;

public class Main extends Listener {

	private JFrame frame;
	public static Main instance;
	JComboBox<SoundType> leftDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> farLeftDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> rightDrum = new JComboBox<SoundType>();
	JComboBox<SoundType> farRightDrum = new JComboBox<SoundType>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new Sample();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Leap motion stuff
	 */

	public void onConnect(Controller controller) {
		System.out.println("#Connected#");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}
	
	public void onExit(Controller controller) {
        System.out.println("Exited");
    }

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		GestureList gestures = frame.gestures();
		for (int i = 0; i < gestures.count(); i++) {
			Gesture gesture = gestures.get(i);
			switch (gesture.type()) {
				case TYPE_SWIPE:
					SwipeGesture swipe = new SwipeGesture(gesture);
					if (swipe.state() == State.STATE_STOP) {
						if (swipe.direction().getY() < 0) {
							if (swipe.position().getX() > 125) {
								Utilities.playSound((SoundType) Main.instance.farRightDrum.getSelectedItem());
							} else if (swipe.position().getX() < 125 && swipe.position().getX() > 0) {
								Utilities.playSound((SoundType) Main.instance.rightDrum.getSelectedItem());
							} else if (swipe.position().getX() < -125) {
								Utilities.playSound((SoundType) Main.instance.farLeftDrum.getSelectedItem());
							} else if (swipe.position().getX() > -125 && swipe.position().getX() < 0) {
								Utilities.playSound((SoundType) Main.instance.leftDrum.getSelectedItem());
							}
						}
					}
				default:
					break;
			}
		}
	}

	// ///////////////////////

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
        Controller controller = new Controller();
        controller.addListener(this);
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
