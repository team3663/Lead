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
public class C_ArmsIntakeToggle extends Command {
	boolean pOn;
    public C_ArmsIntakeToggle(boolean on){
    	pOn = on;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	if(Robot.ssElevator.getToteSwitch()){
	    	if(pOn){
	    		Robot.ssArms.intakeMotorsSet(1.0);
	    	}else{
	    		Robot.ssArms.intakeMotorsSet(0.0);
	    	}
//    	}
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
