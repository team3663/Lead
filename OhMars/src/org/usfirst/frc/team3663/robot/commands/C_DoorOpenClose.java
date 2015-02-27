package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_DoorOpenClose extends Command {
	boolean close;
	boolean finished;
    public C_DoorOpenClose(boolean pClose){
    	//requires(Robot.ssDoor);
    	close = pClose;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssDoor", "C_DoorOpen initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(close)
    		finished = Robot.ssDoor.closeDoor();
    	else
    		finished = Robot.ssDoor.openDoor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssDoor", "C_DoorOpen end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssDoor", "C_DoorOpen interrupted");
    }
}
