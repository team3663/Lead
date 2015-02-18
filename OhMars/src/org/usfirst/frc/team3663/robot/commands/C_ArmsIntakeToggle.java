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
	double pLeftSpeed;
	double pRightSpeed;
    public C_ArmsIntakeToggle(double leftSpeed, double rightSpeed){
    	pLeftSpeed = leftSpeed;
    	pRightSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//this doesn't work because it's not constantly called. Hence, isFinished = true;
		if(Robot.ssElevator.getToteSwitch()){
			Robot.ssArms.intakeMotorLSet(pLeftSpeed);
			Robot.ssArms.intakeMotorRSet(pRightSpeed);
		}else{
			Robot.ssArms.intakeMotorsSet(0.0);
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.ssElevator.getToteSwitch()){
    		Robot.ssArms.intakeMotorsSet(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.ssArms.lArmIntakeSpeed + Robot.ssArms.rArmIntakeSpeed) <= 0; 
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
