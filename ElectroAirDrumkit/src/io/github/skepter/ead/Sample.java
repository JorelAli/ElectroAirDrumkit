package io.github.skepter.ead;

/******************************************************************************\
 * Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
 * Leap Motion proprietary and confidential. Not for distribution.              *
 * Use subject to the terms of the Leap Motion SDK Agreement available at       *
 * https://developer.leapmotion.com/sdk_agreement, or another agreement         *
 * between Leap Motion and you, your company or other organization.             *
 \******************************************************************************/

import io.github.skepter.ead.Utilities.SoundType;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.SwipeGesture;

class SampleListener extends Listener {

	public void onInit(Controller controller) {
		System.out.println("Initialized");

	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}

	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
	}

	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	public long map(long x, long in_min, long in_max, long out_min, long out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public void onFrame(Controller controller) {
		// Get the most recent frame and report some basic information
		Frame frame = controller.frame();

		Main.instance.panel.repaint();
		Main.instance.panel.setFingerX((int) map((long) frame.fingers().get(0).tipPosition().getX(), -300, 300, 0, 800));
		Main.instance.panel.setFingerY(520 - (int) map((long) frame.fingers().get(0).tipPosition().getY(), 20, 250, 0, 520));

		GestureList gestures = frame.gestures();
		for (int i = 0; i < gestures.count(); i++) {
			Gesture gesture = gestures.get(i);
			switch (gesture.type()) {
				case TYPE_SWIPE:
					SwipeGesture swipe = new SwipeGesture(gesture);

					// for(Hand hand : swipe.frame().hands()){

					if (swipe.state() == State.STATE_STOP) {
						if (swipe.direction().getY() < 0) {
							// SwipeGesture previousUpdate = new
							// SwipeGesture(controller.frame(1).gesture(swipe.id()));
							// if(previousUpdate.position().getY() >
							// swipe.position().getY()) {
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
					// System.out.println("Unknown gesture type.");
					break;
			}
		}

		if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
			// System.out.println();
		}
	}
}

class Sample {

	public Sample() {
		// Create a sample listener and controller
		SampleListener listener = new SampleListener();
		Controller controller = new Controller();

		// Have the sample listener receive events from the controller
		controller.addListener(listener);

		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);
	}
}
