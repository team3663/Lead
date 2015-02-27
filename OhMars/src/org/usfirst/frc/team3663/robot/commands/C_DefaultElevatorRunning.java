package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_DefaultElevatorRunning extends Command {

	double lastAxis2, currAxis2, lastAxis3, currAxis3, lastAxis, currAxis;
	//not using axis 3
	boolean button5Pressed;
	int destination;
	
    public C_DefaultElevatorRunning(int pTicks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastAxis2 = 0;
    }

    protected void execute() {
    	button5Pressed = Robot.oi.buttonController.getRawButton(5);
    	currAxis2 = -Robot.oi.buttonController.getRawAxis(2);
    	if (button5Pressed)
    	{
    		currAxis = 1.0;
    	}
    	else if (currAxis2 > 0.1)
    	{
    		currAxis = currAxis2;
    	}
    	else
    	{
    		currAxis = 0;
    	}
    	//currAxis = Robot.oi.buttonController.getRawAxis(3)-Robot.oi.buttonController.getRawAxis(2);
    	Robot.ssElevator.manualMoveElevator(currAxis);
    	lastAxis2 = currAxis2;
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
