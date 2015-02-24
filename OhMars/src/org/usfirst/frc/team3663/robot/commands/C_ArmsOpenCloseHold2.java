package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsOpenCloseHold2 extends Command {
	
	boolean leftSide;
	
    public C_ArmsOpenCloseHold2(boolean pLeftSide) {
        leftSide = pLeftSide;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(leftSide){
    		if(!Robot.ssArmsSolenoids.armLIsClosed)
    		{
        		Robot.ssArmsSolenoids.armLClose();
    		}
    		else
    		{
    			Robot.ssArmsSolenoids.armLOpen();
    		}
    	}
    	else
    	{
    		if(!Robot.ssArmsSolenoids.armRIsClosed)
    		{
        		Robot.ssArmsSolenoids.armRClose();
    		}
    		else
    		{
    			Robot.ssArmsSolenoids.armROpen();
    		}
    	}
    	SmartDashboard.putString("ssArmsSolenoid", "C_ArmsOpenCloseHold2 initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssArmsSolenoid", "C_ArmsOpenCloseHold2 end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssArmsSolenoid", "C_ArmsOpenCloseHold2 interrupted");
    }
}
