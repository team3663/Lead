package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_DefaultElevatorRunning extends Command {

	double lastAxis2, currAxis2, lastAxis3, currAxis3, lastAxis, currAxis;
	int currTicks;
	int destination;
	
    public C_DefaultElevatorRunning(int pTicks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastAxis2 = 0;
    	lastAxis3 = 0;
    	lastAxis = 0;
    }

    protected void execute() {
    	currTicks = Robot.ssElevator.winchEncoder.get();
    	currAxis2 = Robot.oi.buttonController.getRawAxis(2);
    	currAxis3 = Robot.oi.buttonController.getRawAxis(3);
    	
    	currAxis = currAxis3-currAxis2;
    	
    	if (currAxis == 0 && lastAxis == 0)
    	{
    		currAxis = 0;
    		Robot.ssElevator.stopElevator();
    	}
    	else if (currAxis >= 0.1)
    	{
    		destination = 1075;
    	}
    	else if (currAxis <= -0.1)
    	{
    		destination = -13;
    	}
    	Robot.ssElevator.moveToPos(destination, currAxis);
    	lastAxis2 = currAxis2;
    	lastAxis3 = currAxis3;
    	lastAxis = currAxis;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssElevator", "C_DefaultElevatorRunning end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssElevator", "C_DefaultElevatorRunning interrupted");
    }
}
