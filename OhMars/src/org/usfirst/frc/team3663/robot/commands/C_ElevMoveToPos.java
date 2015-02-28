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
	
    public C_ElevMoveToPos(int pTicks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
        origTicks = pTicks;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssElevator", "C_ElevMoveToPose initialize");
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finished = Robot.ssElevator.moveToSetPos(origTicks, 1.0);
    	Robot.ssDashBoard.putDashBool("Elevator: Button2Pressed: ", Robot.oi.buttonController.getRawButton(2));
    	Robot.ssDashBoard.putDashBool("Elevator: Button3Pressed: ", Robot.oi.buttonController.getRawButton(3));
    	Robot.ssDashBoard.putDashBool("Elevator: Button1Pressed: ", Robot.oi.buttonController.getRawButton(1));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssElevator", "C_ElevMoveToPose end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssElevator", "C_ElevMoveToPose interrupted");
    }
}
