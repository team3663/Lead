package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_DefaultElevatorRunning extends Command {

	double lastAxis, currAxis;
	
    public C_DefaultElevatorRunning(int pTicks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastAxis = Robot.oi.buttonController.getRawAxis(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currAxis = Robot.oi.buttonController.getRawAxis(2);
    	if (Robot.ssElevator.winchEncoder.get() < -13 || (currAxis < 0.1 && lastAxis > 0.1))
    	{
    		currAxis = 0;
    		Robot.ssElevator.stopElevator();
    	}
    	else if (currAxis >= 0.1)
    	{
    		if (lastAxis < 0.1)
    		{
    			Robot.ssElevator.prepForMove(-13);
    		}
    		Robot.ssElevator.moveToPos(-13, currAxis);
    	}
    	Robot.ssElevator.moveInAndOut(Robot.oi.buttonController.getRawAxis(3));
    	lastAxis = currAxis;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
