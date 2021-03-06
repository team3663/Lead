package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ToggleBrake extends Command {

    public C_ToggleBrake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.ssElevator.brakeOn)
    	{
    		Robot.ssElevator.bikeBrakeTriggerOpen();
    		Robot.ssElevator.enableBrakeMotors(false);
    	}
    	else
    	{
    		Robot.ssElevator.bikeBrakeTriggerClose();
    		//Robot.ssElevator.enableBrakeMotors(true);
    	}
    	SmartDashboard.putString("ssElevator","C_ToggleBrake initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssElevator","C_ToggleBrake end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssElevator","C_ToggleBrake interrupted");
    }
}
