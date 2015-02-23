
package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.commands.A_Log;
//import org.usfirst.frc.team3663.robot.subsystems.ExampleSubsystem;
//import org.usfirst.frc.team3663.robot.commands.ExampleCommand;
import org.usfirst.frc.team3663.robot.commands.C_ArcadeDrive;
import org.usfirst.frc.team3663.robot.commands.C_AutonomousChooser;
import org.usfirst.frc.team3663.robot.commands.C_EncoderDriveStraight;
import org.usfirst.frc.team3663.robot.commands.C_EncoderTurn;
import org.usfirst.frc.team3663.robot.subsystems.SSArmsIntake;
//import org.usfirst.frc.team3663.robot.subsystems.SSArmsIntake;
import org.usfirst.frc.team3663.robot.subsystems.SSArmsSolenoids;
import org.usfirst.frc.team3663.robot.subsystems.SSArmsUpDown;
import org.usfirst.frc.team3663.robot.subsystems.SSAutonomous;
import org.usfirst.frc.team3663.robot.subsystems.SSDashBoard;
//import org.usfirst.frc.team3663.robot.subsystems.SSDashBoard;
import org.usfirst.frc.team3663.robot.subsystems.SSDriveTrain;
import org.usfirst.frc.team3663.robot.subsystems.SSElevator;
import org.usfirst.frc.team3663.robot.commands.A_Log;
import org.usfirst.frc.team3663.robot.commands.C_DefaultElevatorRunning;
import org.usfirst.frc.team3663.robot.commands.CG_PickUpWithSensor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static SSArms ssArms;
	
	public static SSArmsIntake ssArmsIntake;
	public static SSArmsSolenoids ssArmsSolenoids;
	public static SSArmsUpDown ssArmsUpDown;
	
	public static SSDriveTrain ssDriveTrain;
	public static SSElevator ssElevator;
  	public static SSDashBoard ssDashBoard;
  	public static SSAutonomous ssAutonomous;
	public static OI oi;

	Command arcadeDrive;
	Command ALog;
	Command Auto;
    //CommandGroup armExecutables;
    Command defaultElevator;
    Command pickUpWithSensor;
    
    public final static double elevDelta = 0.05;
	public static double motorTestSpeed = 0;
	public static int testMotor = 0;
	public final static int encoderZeroAdjust = -92;
	static String testMotorName;
	public static boolean runCommand = true;
	public static boolean runCG = true;
	
	double updateStatusNextRefresh;
	final double UPDATESTATUSREFRESHINTERVAL = 0.25;

	
    public void robotInit() {
    	ssDashBoard = new SSDashBoard();
    	ssDriveTrain = new SSDriveTrain();
    	ssElevator = new SSElevator();
    	//ssArms = new SSArms();
    	
    	ssArmsIntake = new SSArmsIntake();
    	ssArmsSolenoids = new SSArmsSolenoids();
    	ssArmsUpDown = new SSArmsUpDown();
    	
    	ssAutonomous = new SSAutonomous();
		oi = new OI();
		//Auto = new C_EncoderTurn(0,90, true);
		Auto = new C_AutonomousChooser();
		arcadeDrive = new C_ArcadeDrive();
		ALog = new A_Log();
		ALog.start();
		//armExecutables = new CG_ArmsExecute();
		defaultElevator = new C_DefaultElevatorRunning(0);
		pickUpWithSensor = new CG_PickUpWithSensor();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	Auto.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		Robot.oi.driveController.setRumble(Joystick.RumbleType.kRightRumble, 1);
        arcadeDrive.start();
		//armExecutables.start();
		defaultElevator.start();
	//	pickUpWithSensor.start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    
    
    /*for Testing*/
	public static void encoderTicksChange(int delta)
	{/*
		encoderTicks+=delta;
		if (encoderTicks > 1150)
		{
			encoderTicks = 1150;
		}
		else if (encoderTicks < 0)
		{
			encoderTicks = 0;
		}
	//	encoderTicks = (int)(SmartDashboard.getNumber("encoderTicks: "));
		SmartDashboard.putNumber("encoderTicks: ", encoderTicks);
		*/
	}
	
	
    public static void motorSpeedChange(double delta)
    {
    	motorTestSpeed+=delta;
    	if (motorTestSpeed < -1)
    	{
    		motorTestSpeed = -1;
    	}
    	else if (motorTestSpeed > 1)
    	{
    		motorTestSpeed = 1;
    	}
    	SmartDashboard.putNumber("motorSpeed: ", motorTestSpeed);
    }
    
    public static void testMotorChange(int delta)
    {
    	testMotor+=delta;
    	switch (Robot.testMotor)
    	{
		case 0:
	    	testMotorName = "DriveTrain L1";
    		break;
		case 1:
			testMotorName = "DriveTrain L2";
			break;
		case 2:
			testMotorName = "DriveTrain R1";
			break;
		case 3:
			testMotorName = "DriveTrain R2";
			break;
		case 4:
			testMotorName = "Both Left Drive Motors";
			break;
		case 5:
			testMotorName = "Both Right Drive Motors";
			break;
		case 6:
			testMotorName = "ElevWinch 1";
			break;
		case 7:
			testMotorName = "ElevWinch 2";
			break;
		case 8:
			testMotorName = "Both ElevWinch";
			break;
		case 9:
			testMotorName = "Elevator In/Out";
			break;
		case 10:
			testMotorName = "LeftArm";
			break;
		case 11:
			testMotorName = "RightArm";
			break;
		case 12:
			testMotorName = "BothArms";
			break;
		case 13:
			testMotorName = "ElevatorEncoder";
			break;
		default:
			testMotorName = "NothingSelected";
			break;
    	}
    	SmartDashboard.putString("testMotor: ", testMotorName);
    }
    public void updateStatus()
    {
        double currentTime = Timer.getFPGATimestamp();
        if (currentTime >= updateStatusNextRefresh)
        {
            updateStatusNextRefresh += UPDATESTATUSREFRESHINTERVAL;
            if (currentTime > updateStatusNextRefresh)
                updateStatusNextRefresh = currentTime + UPDATESTATUSREFRESHINTERVAL;
            ssArmsIntake.updateStatus();
            ssArmsSolenoids.updateStatus();
            ssArmsUpDown.updateStatus();
            ssAutonomous.updateStatus();
            ssDriveTrain.updateStatus();
            ssElevator.updateStatus();
        }
    }
}