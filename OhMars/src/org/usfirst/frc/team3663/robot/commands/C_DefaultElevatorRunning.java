package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_DefaultElevatorRunning extends Command {

	double lastAxis2, currAxis2, lastAxis3, currAxis3;
	
    public C_DefaultElevatorRunning(int pTicks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastAxis2 = Robot.oi.buttonController.getRawAxis(2);
    	lastAxis3 = Robot.oi.buttonController.getRawAxis(3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currAxis2 = Robot.oi.buttonController.getRawAxis(2);
    	currAxis3 = Robot.oi.buttonController.getRawAxis(3);
    	if (Robot.ssElevator.winchEncoder.get() < -13 || (currAxis2 < 0.1 && lastAxis2 > 0.1))
    	{
    		currAxis2 = 0;
    		Robot.ssElevator.stopElevator();
    	}
    	else if (currAxis2 >= 0.1)
    	{
    		if (lastAxis2 < 0.1)
    		{
    			Robot.ssElevator.prepForMove(-13);
    		}
    		Robot.ssElevator.moveToPos(-13, currAxis2);
    	}
    	if (currAxis3 > 0.1)
    	{
        	Robot.ssElevator.moveInAndOut(Robot.oi.buttonController.getRawAxis(3));
    	}
    	else if (currAxis3 < 0.1 && lastAxis3 > 0.1)
    	{
    		Robot.ssElevator.moveInAndOut(0);
    	}
    	lastAxis2 = currAxis2;
    	lastAxis3 = currAxis3;
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
