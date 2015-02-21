package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsUpDownTime extends Command {
	double axisValue;
	double pSeconds;
	boolean pDirectionIsUp;
	int speedModifier;
	double endTime;
    public C_ArmsUpDownTime(boolean directionIsUp, double seconds){
    	pSeconds = seconds;
    	pDirectionIsUp = directionIsUp;
    	if(pDirectionIsUp){
        	speedModifier = 1;
    	}else{
    		speedModifier = -1;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssArmsUpDown.armUpDownRSet(0.9*speedModifier);
    	Robot.ssArmsUpDown.armUpDownLSet(0.9*speedModifier);
    	endTime = Timer.getFPGATimestamp() + pSeconds;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() >= endTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
