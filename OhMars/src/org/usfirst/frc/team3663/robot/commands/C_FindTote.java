package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_FindTote extends Command {

    public C_FindTote() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssElevator", "C_FindTote initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDashBoard.putDashBool("toteSensor: ",Robot.ssElevator.getToteSwitch());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (!Robot.ssElevator.getToteSwitch() || !Robot.runCommand)
    	{
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssElevator", "C_FindTote end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssElevator", "C_FindTote interrupted");
    }
}
