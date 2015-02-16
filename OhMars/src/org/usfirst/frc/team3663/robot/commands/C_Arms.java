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
public class C_Arms extends Command {
	
	boolean intakeR,intakeL = false;
	boolean canChangeStateB1 = true;
	boolean intakeOn = false;
	double intakeSpeed = 0.0;
	
    public C_Arms() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssArms);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.ssArms.intakeMotorLSet(Robot.oi.logitech.getRawAxis(2));
    	//Robot.ssArms.intakeMotorRSet(Robot.oi.logitech.getRawAxis(3));
    	if(Robot.oi.logitech.getRawButton(1)){
    		if(canChangeStateB1){
    			if(!intakeOn){
    				intakeSpeed = 0.7;
    				intakeOn = true;
    				canChangeStateB1 = false;
    			}else{
    				intakeSpeed = 0.0;
    				intakeOn = false;
    				canChangeStateB1 = false;
    			}
    		}
    	}else{
    		canChangeStateB1 = true;
    	}
    	
    	if(Robot.oi.logitech.getRawButton(3))
    		intakeSpeed = -intakeSpeed;

		SmartDashboard.putNumber("intakeSpeed", intakeSpeed);
		
		Robot.ssArms.intakeMotorsSet(intakeSpeed);
    	
    	Robot.ssArms.armUpDownRSet(Robot.oi.logitech.getRawAxis(1));
    	Robot.ssArms.armUpDownLSet(Robot.oi.logitech.getRawAxis(1));
    	Robot.ssArms.armLClose(Robot.oi.logitech.getRawButton(5));
    	Robot.ssArms.armRClose(Robot.oi.logitech.getRawButton(6));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
