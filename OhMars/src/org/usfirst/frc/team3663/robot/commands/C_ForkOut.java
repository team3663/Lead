package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ForkOut extends Command {

	double endTime;
	double currTime;
	double time;
	double speed;
	boolean goOut;
	boolean finished;
	
    public C_ForkOut(boolean pGoOut, double timeToRun) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssFork);
        goOut = pGoOut;
        time = timeToRun;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.runCommand = true;
    	endTime = Timer.getFPGATimestamp()+time;
    	SmartDashboard.putString("ssElevator", "C_ForkOut initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currTime = Timer.getFPGATimestamp();
    	if (currTime < endTime)
    	{
    		Robot.ssFork.moveOut(goOut);
    		finished = false;
    	}
    	else
    	{
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssFork.set(0);
    	SmartDashboard.putString("ssFork", "C_ForkOut end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    	SmartDashboard.putString("ssFork", "C_ForkOut interrupted");
    }
}
