package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsIntake extends Command {
	double leftSpeed, rightSpeed;
    public C_ArmsIntake(){
    	requires(Robot.ssArmsIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssArmIntake", "C_ArmsIntake initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * In doing it this way, there is an inherent risk of catastrophic failure
    	 * and not being able to operate the arm wheels if need be. Say, a fleck of
    	 * something is stuck to the light sensor and then the arms are useless
    	 */
    	if(Robot.ssElevator.getToteSwitch()){
    		if(Robot.oi.driveController.getRawButton(1))
    			Robot.ssArmsIntake.intakeMotorsSet(0.0);
    		if(Robot.oi.driveController.getRawButton(2))
    			Robot.ssArmsIntake.intakeMotorsSet(-1.0);
    		if(Robot.oi.driveController.getRawButton(3))
    			Robot.ssArmsIntake.intakeMotorsSet(1.0);

    		leftSpeed = -Robot.ssArmsIntake.intakeMotorL.get();
    		rightSpeed = -Robot.ssArmsIntake.intakeMotorR.get();
    		
    		//code below: if POV left, reverse current left motor speed. Vice versa for POV right
    		if(Robot.oi.buttonController.getPOV() == 90)
    			Robot.ssArmsIntake.intakeMotorLSet(-leftSpeed);
    		else if(Robot.oi.driveController.getPOV() == 270)
    			Robot.ssArmsIntake.intakeMotorRSet(-rightSpeed);
    	}else{
    		Robot.ssArmsIntake.intakeMotorsSet(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssArmIntake", "C_ArmsIntake end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssArmIntake", "C_ArmsIntake interrupted");
    }
}
