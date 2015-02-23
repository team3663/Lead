package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
/**
 *
 */
public class C_WaitForToteSensor extends Command {

    public C_WaitForToteSensor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssArms","C_WaitForToteSensor initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ssElevator.getToteSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssArms","C_WaitForToteSensor end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssArms","C_WaitForToteSensor interrupted");
    }
}
