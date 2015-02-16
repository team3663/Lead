package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ElevMoveToPos extends Command {

	int ticks, origTicks;
	boolean finished;
	
    public C_ElevMoveToPos(int Ticks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
        origTicks = ticks = Ticks;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.runCommand = true;
    	if (origTicks == -10)
    	{
    		ticks = (int)(SmartDashboard.getNumber("encoderTicks: "));
    	}
    	Robot.ssElevator.prepForMove(ticks);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finished = Robot.ssElevator.moveToPos(ticks);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (finished || !Robot.runCommand)
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
