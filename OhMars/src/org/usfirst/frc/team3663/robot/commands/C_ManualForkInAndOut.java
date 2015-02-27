package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ManualForkInAndOut extends Command {

	boolean goingOut;
	double axis3;
	boolean button6Pressed;
	
    public C_ManualForkInAndOut() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssFork);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	button6Pressed = Robot.oi.buttonController.getRawButton(6);
    	axis3 = Robot.oi.buttonController.getRawAxis(3);
    	if (axis3 > 0.2)
    	{
    		Robot.ssFork.moveOut(false);
    	}
    	else if (button6Pressed)
    	{
    		Robot.ssFork.moveOut(true);	
    	}
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
