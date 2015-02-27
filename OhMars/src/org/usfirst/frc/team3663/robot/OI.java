package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
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
	public JoystickButton manualForkOut;
	//-------------
	public JoystickButton openDoor;
	public JoystickButton closeDoor;
	
	Command cgPickUpWithSensor;
	Command cgScoringPlatform;
	Command cgStep;
	Command cZeroElevator;
	Command cgManualRaiseElevator;
	Command cForkIn;
	Command cForkOut;
	
	Command cElevMoveToDashPos;
	Command cMotorDriveTest;
	Command cMoveAndSetZero;
	
	Command cOpenDoor;
	Command cCloseDoor;
	//-------------
	//-0-0-0-0-0-0    BOB STUFF
	public JoystickButton bobs;
	public JoystickButton roberto;
	public JoystickButton roberta;
	public JoystickButton robertus;
	public JoystickButton chanceller;
	public CG_Bobs cr;
	//0-0-0-0-0-0-
	public OI(){
		
		bobs = new JoystickButton(bobStick, 1);
		bobs.whenPressed(new CG_Bobs());
		
		roberto = new JoystickButton(bobStick, 3);
		roberto.whenPressed(new C_Roberto());
		
		roberta = new JoystickButton(bobStick, 4);
		roberta.whenPressed(new C_Roberta());
		
		robertus = new JoystickButton(bobStick, 2);
		cr = new CG_Bobs();
		robertus.whenPressed(cr);
		
		chanceller = new JoystickButton(bobStick, 8);
		chanceller.whenPressed(new C_Chanceller());
		
		driveControllerSafetyEnable = new JoystickButton(driveController, 7);
		driveControllerSafetyEnable.whenPressed(new C_DriveControllerSafetyOff());
		
		//----------------
		pickUp = new JoystickButton(buttonController, 1);
		cgPickUpWithSensor = new CG_PickUpWithSensor();
		pickUp.whenPressed(cgPickUpWithSensor);
		pickUp.whenReleased(new C_Interrupt(cgPickUpWithSensor));
		
		dropOnSP = new JoystickButton(buttonController, 3);
		cgScoringPlatform = new CG_DropOffSP();
		dropOnSP.whenPressed(cgScoringPlatform);
		dropOnSP.whenReleased(new C_Interrupt(cgScoringPlatform));
		
		dropOnStep = new JoystickButton(buttonController, 2);
		cgStep = new CG_DropOffStep();
		dropOnStep.whenPressed(cgStep);
		dropOnStep.whenReleased(new C_Interrupt(cgStep));
		
		resetToStart = new JoystickButton(buttonController, 8);
		cZeroElevator = new C_ElevMoveAndSetZero();
		resetToStart.whenPressed(cZeroElevator);
		resetToStart.whenReleased(new C_Interrupt(cZeroElevator));
		//----------------
		motorDriveTest = new JoystickButton(testStick, 1);
		cMotorDriveTest = new C_MotorDriveTest();
		motorDriveTest.whenPressed(cMotorDriveTest);
		motorDriveTest.whenReleased(new C_Interrupt(cMotorDriveTest));
		
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
		cMoveAndSetZero = new C_ElevMoveAndSetZero();
		elevMoveAndSetZero.whenPressed(cMoveAndSetZero);
		elevMoveAndSetZero.whenReleased(new C_Interrupt(cMoveAndSetZero));
		
		incrementElevEncoderTicks = new JoystickButton(testStick, 8);
		incrementElevEncoderTicks.whenPressed(new C_IncrementElevEncoderTicks());
		
//		decrementElevEncoderTicks = new JoystickButton(testStick, 10);
//		decrementElevEncoderTicks.whenPressed(new C_DecrementElevEncoderTicks());
		
		armOpenCloseToggle = new JoystickButton(driveController, 5);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenCloseHold2(true));
		
		armOpenCloseToggle = new JoystickButton(driveController, 6);
		armOpenCloseToggle.whenPressed(new C_ArmsOpenCloseHold2(false));

		openDoor = new JoystickButton(buttonController,9);
		cOpenDoor = new C_DoorOpenClose(false);
		openDoor.whenPressed(cOpenDoor);
		openDoor.whenReleased(new C_Interrupt(cOpenDoor));
		
		closeDoor = new JoystickButton(buttonController,10);
		cCloseDoor = new C_DoorOpenClose(true);
		closeDoor.whenPressed(cCloseDoor);
		closeDoor.whenReleased(new C_Interrupt(cCloseDoor));
		
	}
	public void updateStatus(){
		SmartDashboard.putNumber("driveController:LeftX", driveController.getRawAxis(0));
		SmartDashboard.putNumber("driveController:LeftY", driveController.getRawAxis(1));
		SmartDashboard.putNumber("driveController:RightY", driveController.getRawAxis(5));
		SmartDashboard.putNumber("driveController:Accel", driveController.getRawAxis(3));
		SmartDashboard.putNumber("driveController:Decel", driveController.getRawAxis(2));
		if(driveController.getRawButton(4)){
			SmartDashboard.putString("driveSafeMode", "on");
		}else{
			SmartDashboard.putString("driveSafeMode", "off");
		}
		if(driveControllerOn){
			SmartDashboard.putString("driveControl", "on");
		}else{
			SmartDashboard.putString("driveControl","off");
		}
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

