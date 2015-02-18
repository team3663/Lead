package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team3663.robot.commands.*;

public class OI {
	public boolean driveControllerOn = false;
	//public boolean buttonControllerOn = false;
	public JoystickButton driveControllerSafetyEnable;
	//public Joystick buttonControllerSafetyEnable;
	
	public Joystick driveController = new Joystick(0);
	public Joystick buttonController = new Joystick(1);
	public Joystick logitech = new Joystick(2);
	public Joystick buttonStick = new Joystick(3);
	public Joystick testStick = new Joystick(5);
	public Joystick bobStick = new Joystick(4);
	
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
	public JoystickButton armIntakeToggleOn;
	public JoystickButton armIntakeToggleOff;
	public JoystickButton armIntakeToggleReverse;
	public JoystickButton armExecutables;
	public JoystickButton armIntakeReverse;
	//-------------
	public JoystickButton pickUp;
	public JoystickButton dropOnSP;
	public JoystickButton dropOnStep;
	public JoystickButton resetToStart;
	public JoystickButton manualRaiseElevator;
	public JoystickButton manualForkIn;
	//-------------
	
	public JoystickButton bobs;
	public JoystickButton roberto;
	public JoystickButton roberta;
	
	public OI(){
		
		bobs = new JoystickButton(bobStick, 1);
		bobs.whenPressed(new CG_Bobs());
		
		roberto = new JoystickButton(bobStick, 3);
		roberto.whenPressed(new C_Roberto());
		
		roberta = new JoystickButton(bobStick, 4);
		roberta.whenPressed(new C_Roberta());
		
		driveControllerSafetyEnable = new JoystickButton(driveController, 7);
		driveControllerSafetyEnable.whenPressed(new C_DriveControllerSafetyOff());
		
		//----------------
		pickUp = new JoystickButton(buttonController, 1);
		pickUp.whenPressed(new CG_PickUpWithSensor());
		pickUp.whenReleased(new C_MotorDriveTestInterrupt());
		
		dropOnSP = new JoystickButton(buttonController, 3);
		dropOnSP.whenPressed(new CG_DropOffSP());
		dropOnSP.whenReleased(new C_MotorDriveTestInterrupt());
		
		dropOnStep = new JoystickButton(buttonController, 2);
		dropOnStep.whenPressed(new CG_DropOffStep());
		dropOnStep.whenReleased(new C_MotorDriveTestInterrupt());
		
		resetToStart = new JoystickButton(buttonController, 8);
		resetToStart.whenPressed(new CG_RestartToStartPos());
		resetToStart.whenReleased(new C_MotorDriveTestInterrupt());
		
		manualRaiseElevator = new JoystickButton(buttonController, 5);
		manualRaiseElevator.whenPressed(new CG_ManualRaiseElevator());
		manualRaiseElevator.whenReleased(new C_MotorDriveTestInterrupt());
		
		manualForkIn = new JoystickButton(buttonController, 6);
		manualForkIn.whenPressed(new CG_ForkIn());
		manualForkIn.whenReleased(new C_MotorDriveTestInterrupt());
		
		//----------------
		elevMoveToPos = new JoystickButton(testStick, 7);
		elevMoveToPos.whenPressed(new C_ElevMoveToPos(-50));
		elevMoveToPos.whenReleased(new C_MotorDriveTestInterrupt());
		
		motorDriveTest = new JoystickButton(testStick, 1);
		motorDriveTest.whenPressed(new C_MotorDriveTest());
		motorDriveTest.whenReleased(new C_MotorDriveTestInterrupt());
		
		incrementSpeed = new JoystickButton(testStick, 5);
		incrementSpeed.whenPressed(new C_IncrementMotorSpeed());
		
		decrementSpeed = new JoystickButton(testStick, 3);
		decrementSpeed.whenPressed(new C_DecrementMotorSpeed());
		
		incrementTestMotor = new JoystickButton(testStick, 6);
		incrementTestMotor.whenPressed(new C_IncrementTestMotor());
		
		decrementTestMotor = new JoystickButton(testStick, 4);
		decrementTestMotor.whenPressed(new C_DecrementTestMotor());
		
		reverseMotorSpeed = new JoystickButton(testStick, 2);
		reverseMotorSpeed.whenPressed(new C_ReverseMotorSpeed());
		
		toggleBrake = new JoystickButton(testStick, 12);
		toggleBrake.whenPressed(new C_ToggleBrake());
		
		elevMoveAndSetZero = new JoystickButton(testStick, 11);
		elevMoveAndSetZero.whenPressed(new C_ElevMoveAndSetZero());
		elevMoveAndSetZero.whenReleased(new C_MotorDriveTestInterrupt());
		
		incrementElevEncoderTicks = new JoystickButton(testStick, 8);
		incrementElevEncoderTicks.whenPressed(new C_IncrementElevEncoderTicks());
		
//		decrementElevEncoderTicks = new JoystickButton(testStick, 10);
//		decrementElevEncoderTicks.whenPressed(new C_DecrementElevEncoderTicks());
		
		elevMoveToPos0 = new JoystickButton(testStick, 10);
		elevMoveToPos0.whenPressed(new C_ElevMoveToPos(-49));
		elevMoveToPos0.whenReleased(new C_MotorDriveTestInterrupt());
		
		testEncoderDrive = new JoystickButton(buttonStick, 1);
		testEncoderDrive.whenPressed(new C_EncoderDriveStrait(0));
		
		armOpenCloseToggle = new JoystickButton(driveController, 5);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenCloseHold(true));
		//armOpenCloseToggle.whenReleased(new C_ArmsOpenClose(true));
		
		armOpenCloseToggle = new JoystickButton(driveController, 6);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenCloseHold(false));
		//armOpenCloseToggle.whenReleased(new C_ArmsOpenClose(false));
		
		armOpenCloseToggle = new JoystickButton(driveController, 5);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenCloseTogether(true));
		
		armOpenCloseToggle = new JoystickButton(driveController, 6);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenCloseTogether(false));
		
		armIntakeToggleOn = new JoystickButton(driveController, 3);
		armIntakeToggleOn.whenPressed(new C_ArmsIntakeToggle(1.0,1.0));
		
		armIntakeToggleOff = new JoystickButton(driveController, 1);
		armIntakeToggleOff.whenPressed(new C_ArmsIntakeToggle(0.0,0.0));
		
		armIntakeToggleReverse = new JoystickButton(driveController, 2);
		armIntakeToggleReverse.whenPressed(new C_ArmsIntakeToggle(-1.0,-1.0));
		
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

