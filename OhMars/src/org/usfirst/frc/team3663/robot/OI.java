package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team3663.robot.commands.CG_ArmsExecute;
import org.usfirst.frc.team3663.robot.commands.C_ArmsIntakeToggle;
import org.usfirst.frc.team3663.robot.commands.C_ArmsOpenClose;
import org.usfirst.frc.team3663.robot.commands.C_EncoderDriveStrait;
import org.usfirst.frc.team3663.robot.commands.C_Test;
import org.usfirst.frc.team3663.robot.commands.C_TestSensors;
import org.usfirst.frc.team3663.robot.commands.C_MotorDriveTest;
import org.usfirst.frc.team3663.robot.commands.C_MotorDriveTestInterrupt;
import org.usfirst.frc.team3663.robot.commands.C_IncrementMotorSpeed;
import org.usfirst.frc.team3663.robot.commands.C_DecrementMotorSpeed;
import org.usfirst.frc.team3663.robot.commands.C_IncrementTestMotor;
import org.usfirst.frc.team3663.robot.commands.C_DecrementTestMotor;
import org.usfirst.frc.team3663.robot.commands.C_ReverseMotorSpeed;
import org.usfirst.frc.team3663.robot.commands.C_ToggleBrake;
import org.usfirst.frc.team3663.robot.commands.C_ElevMoveToPos;
import org.usfirst.frc.team3663.robot.commands.C_ElevMoveAndSetZero;
import org.usfirst.frc.team3663.robot.commands.C_IncrementElevEncoderTicks;
import org.usfirst.frc.team3663.robot.commands.C_DecrementElevEncoderTicks;

public class OI {
	
	public Joystick driveStick = new Joystick(0);
	public Joystick logitech = new Joystick(1);
	public Joystick buttonStick = new Joystick(2);
	public Joystick driveControler = new Joystick(3);
	
	public JoystickButton testSensors;
	public JoystickButton motorDriveTest;
	public JoystickButton incrementSpeed;
	public JoystickButton decrementSpeed;
	public JoystickButton incrementTestMotor;
	public JoystickButton decrementTestMotor;
	public JoystickButton reverseMotorSpeed;
	public JoystickButton toggleBrake;
	public JoystickButton elevMoveToPos;
	public JoystickButton elevMoveToPos0;
	public JoystickButton elevMoveToPos1;
	public JoystickButton elevMoveToPos2;
	public JoystickButton elevMoveAndSetZero;
	public JoystickButton incrementElevEncoderTicks;
	public JoystickButton decrementElevEncoderTicks;
	public JoystickButton testArmSolenoids;
	public JoystickButton motorDriveTestInterrupt;
	public JoystickButton testEncoderDrive;
	public JoystickButton armOpenCloseToggle;
	public JoystickButton armIntakeToggle;
	public JoystickButton armExecutables;
	
	public OI(){
		elevMoveToPos = new JoystickButton(driveStick, 7);
		elevMoveToPos.whenPressed(new C_ElevMoveToPos(-50));
		elevMoveToPos.whenReleased(new C_MotorDriveTestInterrupt());
		
		motorDriveTest = new JoystickButton(driveStick, 1);
		motorDriveTest.whenPressed(new C_MotorDriveTest());
		motorDriveTest.whenReleased(new C_MotorDriveTestInterrupt());
		
		incrementSpeed = new JoystickButton(driveStick, 5);
		incrementSpeed.whenPressed(new C_IncrementMotorSpeed());
		
		decrementSpeed = new JoystickButton(driveStick, 3);
		decrementSpeed.whenPressed(new C_DecrementMotorSpeed());
		
		incrementTestMotor = new JoystickButton(driveStick, 6);
		incrementTestMotor.whenPressed(new C_IncrementTestMotor());
		
		decrementTestMotor = new JoystickButton(driveStick, 4);
		decrementTestMotor.whenPressed(new C_DecrementTestMotor());
		
		reverseMotorSpeed = new JoystickButton(driveStick, 2);
		reverseMotorSpeed.whenPressed(new C_ReverseMotorSpeed());
		
		toggleBrake = new JoystickButton(driveStick, 12);
		toggleBrake.whenPressed(new C_ToggleBrake());
		
		elevMoveAndSetZero = new JoystickButton(driveStick, 11);
		elevMoveAndSetZero.whenPressed(new C_ElevMoveAndSetZero());
		elevMoveAndSetZero.whenReleased(new C_MotorDriveTestInterrupt());
		
		incrementElevEncoderTicks = new JoystickButton(driveStick, 8);
		incrementElevEncoderTicks.whenPressed(new C_IncrementElevEncoderTicks());
		
//		decrementElevEncoderTicks = new JoystickButton(driveStick, 10);
//		decrementElevEncoderTicks.whenPressed(new C_DecrementElevEncoderTicks());
		elevMoveToPos0 = new JoystickButton(driveStick, 10);
		elevMoveToPos0.whenPressed(new C_ElevMoveToPos(-49));
		elevMoveToPos0.whenReleased(new C_MotorDriveTestInterrupt());
		
		testEncoderDrive = new JoystickButton(buttonStick, 1);
		testEncoderDrive.whenPressed(new C_EncoderDriveStrait());

		armOpenCloseToggle = new JoystickButton(logitech, 5);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenClose(true));
		
		armOpenCloseToggle = new JoystickButton(logitech, 6);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenClose(false));
		
		armOpenCloseToggle = new JoystickButton(logitech, 5);
		armOpenCloseToggle.whenReleased(new C_ArmsOpenClose(true));
		
		armOpenCloseToggle = new JoystickButton(logitech, 6);
		armOpenCloseToggle.whenReleased(new C_ArmsOpenClose(false));
		
		armIntakeToggle = new JoystickButton(logitech, 4);
		armIntakeToggle.whenPressed(new C_ArmsIntakeToggle());
	}
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
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

