package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ElevMoveAndSetZero extends Command {

	boolean finished;
	boolean aboveZero;
	
    public C_ElevMoveAndSetZero() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssElevator.moveAndSetZeroInit();
    	aboveZero = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (aboveZero)
    	{
    		aboveZero = Robot.ssElevator.moveDownToZero();
    	}
    	else
    	{
    		finished = Robot.ssElevator.moveAndSetZero();
    	}
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
    	Robot.ssElevator.terminateMove();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
