package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsUpDown2 extends Command {
	double axisValue;
    public C_ArmsUpDown2(){
    	requires(Robot.ssArmsUpDown);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssArmsUpDown", "C_ArmsUpDown2 initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	axisValue = -Robot.oi.driveController.getRawAxis(5);
    	if(Math.abs(axisValue) < 0.2){
    		Robot.ssArmsUpDown.armUpDownRSet(0);
    		Robot.ssArmsUpDown.armUpDownLSet(0);
    		axisValue = 0;
    	}
    	
    	Robot.ssArmsUpDown.armUpDownRSet(axisValue);
    	if(!Robot.oi.driveController.getRawButton(8)){
    		Robot.ssArmsUpDown.armUpDownLSet(axisValue);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssArmsUpDown", "C_ArmsUpDown2 end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssArmsUpDown", "C_ArmsUpDown2 interrupted");
    }
}
