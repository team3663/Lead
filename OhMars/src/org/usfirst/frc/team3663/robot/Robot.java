
package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.commands.*;
import org.usfirst.frc.team3663.robot.subsystems.*;

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
	public static SSDoor ssDoor;
	public static SSDriveTrain ssDriveTrain;
	public static SSElevator ssElevator;
	public static SSFork ssFork;
  	public static SSAutonomous ssAutonomous;
	public static OI oi;

	Command arcadeDrive;
	Command ALog;
	Command Auto;
    //CommandGroup armExecutables;
    Command defaultElevator;
    Command pickUpWithSensor;
    
	public static double motorTestSpeed = 0;
	public static int testMotor = 0;
	public final static int encoderZeroAdjust = -92;
	static String testMotorName;
	public static boolean runCommand = true;
	public static boolean runCG = true;
	
	double updateStatusNextRefresh;
	final double UPDATESTATUSREFRESHINTERVAL = 0.25;

	
    public void robotInit() {
    	ssDriveTrain = new SSDriveTrain();
    	ssElevator = new SSElevator();
    	ssFork = new SSFork();
    	ssDoor = new SSDoor();
    	ssArmsIntake = new SSArmsIntake();
    	ssArmsSolenoids = new SSArmsSolenoids();
    	ssArmsUpDown = new SSArmsUpDown();
    	
    	ssAutonomous = new SSAutonomous();
		oi = new OI();
		//Auto = new C_EncoderTurn(0,90, true);
		Auto = new C_AutonomousChooser();
		arcadeDrive = new C_ArcadeDrive();
		//armExecutables = new CG_ArmsExecute();
		defaultElevator = new C_DefaultElevatorRunning(0);
		pickUpWithSensor = new CG_PickUpWithSensor();
		SmartDashboard.putString("RobotState", "robotInit");
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
		SmartDashboard.putString("RobotState", "AutoInit");
    	Auto.start();
    }

    /**
     * This function is called periodically duri
     * ng autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
    }

    public void teleopInit() {
		SmartDashboard.putString("RobotState", "TeleopInit");
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
		SmartDashboard.putString("RobotState", "Disabled");
    	ssArmsIntake.intakeMotorsSet(0.0);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
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
    public void updateRobotState(){
    	String mode;
        if (DriverStation.getInstance().isBrownedOut())
          mode = "BrownOut";
        else if (DriverStation.getInstance().isAutonomous())
          mode = "Autonomous";
        else if (DriverStation.getInstance().isOperatorControl())
          mode = "Teleop";
        else if (DriverStation.getInstance().isTest())
          mode = "Test";
        else
          mode = "Unknown";

        SmartDashboard.putString("Mode:",mode);
    }
    public void updatePosition(){
    	String position = "blue";
    	if(DriverStation.getInstance().getAlliance() == Alliance.Red)
    		position = "red";
    	SmartDashboard.putString("DS_Position:",position+DriverStation.getInstance().getLocation());
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
            ssFork.updateStatus();
            ssDoor.updateStatus();
            oi.updateStatus();
            
            updatePosition();
            updateRobotState();
            SmartDashboard.putNumber("MatchTime", DriverStation.getInstance().getMatchTime());
            SmartDashboard.putBoolean("FMS_Attached: ",DriverStation.getInstance().isFMSAttached());
        }
    }
}