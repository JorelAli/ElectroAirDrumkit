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
import com.leapmotion.leap.Tool;

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
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
//        Frame previousFrame = controller.frame(1);
//        System.out.println("Frame id: " + frame.id()
//                         + ", timestamp: " + frame.timestamp()
//                         + ", hands: " + frame.hands().count()
//                         + ", fingers: " + frame.fingers().count()
//                         + ", tools: " + frame.tools().count()
//                         + ", gestures " + frame.gestures().count());

        //Get hands
//        for(Hand hand : frame.hands()) {
//            String handType = hand.isLeft() ? "Left hand" : "Right hand";
            
//            if(!hand.isLeft()) {
//            	 if(hand.palmPosition().getX() > 100 && hand.palmPosition().getX() < 300){
//                 	if(hand.palmPosition().getY() < 100) {
//                 		Utilities.playSound("crash02");
//                 	}
//                 }
//            } else {
//            	 if(hand.palmPosition().getX() > -150 && hand.palmPosition().getX() < 0){
//                  	if(hand.palmPosition().getY() < 100) {
//                  		Utilities.playSound("bassdrum_acoustic02");
//                  	}
//                  }
//            }
           
            
            
//            System.out.println("  " + handType + ", id: " + hand.id()
//                             + ", palm position: " + hand.palmPosition());

//            // Get the hand's normal vector and direction
//            Vector normal = hand.palmNormal();
//            Vector direction = hand.direction();
//
//            // Calculate the hand's pitch, roll, and yaw angles
//            System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
//                             + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
//                             + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
//
//            // Get arm bone
//            Arm arm = hand.arm();
//            System.out.println("  Arm direction: " + arm.direction()
//                             + ", wrist position: " + arm.wristPosition()
//                             + ", elbow position: " + arm.elbowPosition());
//
//            // Get fingers
//            for (Finger finger : hand.fingers()) {
//                System.out.println("    " + finger.type() + ", id: " + finger.id()
//                                 + ", length: " + finger.length()
//                                 + "mm, width: " + finger.width() + "mm");
//
//                //Get Bones
//                for(Bone.Type boneType : Bone.Type.values()) {
//                    Bone bone = finger.bone(boneType);
//                    System.out.println("      " + bone.type()
//                                     + " bone, start: " + bone.prevJoint()
//                                     + ", end: " + bone.nextJoint()
//                                     + ", direction: " + bone.direction());
//                }
//            }
//        }

        // Get tools
        for(Tool tool : frame.tools()) {
            System.out.println("  Tool id: " + tool.id()
                             + ", position: " + tool.tipPosition()
                             + ", direction: " + tool.direction());
        }

        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);
            switch (gesture.type()) {
//                case TYPE_CIRCLE:
//                    CircleGesture circle = new CircleGesture(gesture);
//
//                    // Calculate clock direction using the angle between circle normal and pointable
//                    String clockwiseness;
//                    if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2) {
//                        // Clockwise if angle is less than 90 degrees
//                        clockwiseness = "clockwise";
//                        Utilities.playSound("crash02");
//                    } else {
//                        clockwiseness = "counterclockwise";
//                    }
//
//                    // Calculate angle swept since last frame
//                    double sweptAngle = 0;
//                    if (circle.state() != State.STATE_START) {
//                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
//                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
//                    }
//
//                    System.out.println("  Circle id: " + circle.id()
//                               + ", " + circle.state()
//                               + ", progress: " + circle.progress()
//                               + ", radius: " + circle.radius()
//                               + ", angle: " + Math.toDegrees(sweptAngle)
//                               + ", " + clockwiseness);
//                    break;
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    
//                    for(Hand hand : swipe.frame().hands()){
                    	
                    	if(swipe.state() == State.STATE_STOP) {
                    		if(swipe.direction().getY() < 0){
//                        	SwipeGesture previousUpdate = new SwipeGesture(controller.frame(1).gesture(swipe.id()));
//                        	if(previousUpdate.position().getY() > swipe.position().getY()) {
                        		if(swipe.position().getX() > 125) {
                        			Utilities.playSound((SoundType) Main.instance.farRightDrum.getSelectedItem());
                        		} else if (swipe.position().getX() < 125 && swipe.position().getX() > 0 ){
                        			Utilities.playSound((SoundType) Main.instance.rightDrum.getSelectedItem());
                        		} else if (swipe.position().getX() < -125){
                        			Utilities.playSound((SoundType) Main.instance.farLeftDrum.getSelectedItem());
                        		} else if (swipe.position().getX() > -125 && swipe.position().getX() < 0 ){
                        			Utilities.playSound((SoundType) Main.instance.leftDrum.getSelectedItem());
                        		}
                        	}
//                        }
                    	
                    	
//                    	if(swipe.position().getY() < previous && swipe.state() == State.STATE_STOP) {
//                    		if(hand.palmPosition().getX() < 0) {
//                    			previous = swipe.position().getY();
//                    			Utilities.playSound("bassdrum_acoustic02");
//                    		} else {
//                    			previous = swipe.position().getY();
//                    			Utilities.playSound("crash02");
//                    		}
//                    	}
                    }
//                    if(swipe.position().getY() < previous && swipe.state() == State.STATE_STOP) {
//                    	previous = swipe.position().getY();
//                    	Utilities.playSound("crash02");
//                    }
//                    System.out.println("  Swipe id: " + swipe.id()
//                               + ", " + swipe.state()
//                               + ", position: " + swipe.position()
//                               + ", direction: " + swipe.direction()
//                               + ", speed: " + swipe.speed());
//                    break;
//                case TYPE_SCREEN_TAP:
//                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
//                    System.out.println("  Screen Tap id: " + screenTap.id()
//                               + ", " + screenTap.state()
//                               + ", position: " + screenTap.position()
//                               + ", direction: " + screenTap.direction());
//                    break;
//                case TYPE_KEY_TAP:
//                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
//                    if(keyTap.position().getY() < previous && keyTap.state() == State.STATE_STOP) {
//                    	previous = keyTap.position().getY();
//                    	Utilities.playSound("crash02");
//                    }
//                    System.out.println("  Key Tap id: " + keyTap.id()
//                               + ", " + keyTap.state()
//                               + ", position: " + keyTap.position()
//                               + ", direction: " + keyTap.direction());
//                    break;
                default:
//                    System.out.println("Unknown gesture type.");
                    break;
            }
        }

        if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
//            System.out.println();
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
