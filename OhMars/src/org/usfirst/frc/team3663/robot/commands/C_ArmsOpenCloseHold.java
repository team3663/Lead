package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SSArms;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsOpenCloseHold extends Command {
	
	boolean leftSide;
	
    public C_ArmsOpenCloseHold(boolean pLeftSide) {
        leftSide = pLeftSide;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(leftSide){
    		if(!Robot.ssArms.lArmClose){
        		Robot.ssArms.armLClose();   			
    		}else{
    			Robot.ssArms.armLOpen();
    		}
    	}
    	else{
    		if(!Robot.ssArms.rArmClose){
        		Robot.ssArms.armRClose();   			
    		}else{
    			Robot.ssArms.armROpen();
    		}
    	}
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
