package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsOpenCloseIndividual extends Command {
	boolean open;
	boolean isLeft;
    public C_ArmsOpenCloseIndividual(boolean pOpen, boolean pIsLeft) {
    	requires(Robot.ssArmsSolenoids);
        open= pOpen;
    	isLeft = pIsLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(isLeft){
	    	if(open){
				Robot.ssArmsSolenoids.armLOpen();
	    	}
	    	else{
				Robot.ssArmsSolenoids.armLClose();
	    	}
    	}else{
    		if(open){
    			Robot.ssArmsSolenoids.armROpen();
    		}else{
    			Robot.ssArmsSolenoids.armRClose();
    		}
    	}
    	SmartDashboard.putString("ssArmsSolenoid", "C_ArmsOpenCloseIndividual initialize");
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
    	SmartDashboard.putString("ssArmsSolenoid", "C_ArmsOpenCloseIndividual end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssArmsSolenoid", "C_ArmsOpenCloseIndividual interrupted");
    }
}
