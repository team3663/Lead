package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ElevMoveAndSetZero extends Command {

	boolean finished;
	
    public C_ElevMoveAndSetZero() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finished = Robot.ssElevator.moveAndSetZero();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (finished)
    	{
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ssElevator.motorsSet(0);
    	Robot.ssElevator.bikeBrakeTriggerClose();
    }
}
