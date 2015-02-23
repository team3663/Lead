package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class CW_PickUp extends Command {
	
	Command move1, move2;
	boolean finished;

    public CW_PickUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.runCommand = true;
    	C_ElevMoveToPos movePos1 = new C_ElevMoveToPos(-1);
    	move1 = movePos1;
    	C_ElevMoveToPos movePos2 = new C_ElevMoveToPos(-25);
    	move2 = movePos2;
    	System.out.println("hello, testing initializing stuff to see on rioLog :)");
    	SmartDashboard.putString("ssElevator","CW_PickUp initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.ssElevator.toteSensor.get())
    	{
    		move1.start();
    		if (!move1.isRunning())
    		{
    			move2.start();
    		}
    	}
    	if (!move2.isRunning())
    	{
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (finished || Robot.runCommand)
        {
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssElevator","CW_PickUp end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssElevator","CW_PickUp interrupted");
    }
}
