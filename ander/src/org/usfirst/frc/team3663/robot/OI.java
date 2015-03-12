package org.usfirst.frc.team3663.robot;

import org.usfirst.frc.team3663.robot.commands.C_EncoderDrivingCurved;
import org.usfirst.frc.team3663.robot.commands.C_EncoderDrivingPosition;
import org.usfirst.frc.team3663.robot.commands.C_EncoderDriveSpeed;
import org.usfirst.frc.team3663.robot.commands.C_ResetEncoderDrivingState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
	public Joystick testStick = new Joystick(0);

    Button button2 = new JoystickButton(testStick, 2);
    Button button3 = new JoystickButton(testStick, 3);
    Button button4 = new JoystickButton(testStick, 4);
    Button button5 = new JoystickButton(testStick, 5);
    Button button6 = new JoystickButton(testStick, 6);
    Button button7 = new JoystickButton(testStick, 7);
    Button button9 = new JoystickButton(testStick, 9);
    Button button11 = new JoystickButton(testStick, 11);
    Button button12 = new JoystickButton(testStick, 12);
    
    public OI(){
        button2.whenPressed(new C_ResetEncoderDrivingState());

        button3.whenPressed(new C_EncoderDrivingCurved(2*Math.PI, 0, 20, true));
        button4.whenPressed(new C_EncoderDrivingCurved(-2*Math.PI, 0, 20, true));
        button5.whenPressed(new C_EncoderDrivingCurved(Math.PI, 10, 20, true));
        button6.whenPressed(new C_EncoderDrivingCurved(Math.PI, -20, 20, true));

        
        button7.whileHeld(new C_EncoderDriveSpeed(50,0)); 
    	button9.whileHeld(new C_EncoderDriveSpeed(50,1)); 
    	button11.whenPressed(new C_EncoderDrivingPosition(50, 168, true,2));
    	button12.whenPressed(new C_EncoderDrivingPosition(50, -168, true,2));
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    }
}

