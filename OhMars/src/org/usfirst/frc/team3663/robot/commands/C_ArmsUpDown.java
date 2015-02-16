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
public class C_ArmsUpDown extends Command {
	double axisValue;
    public C_ArmsUpDown(){
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	axisValue = Robot.oi.logitech.getRawAxis(1);
    	if(Math.abs(axisValue) < 0.2) axisValue = 0;
    	//Robot.ssArms.armUpDownRSet(axisValue);
    	SmartDashboard.putNumber("yAxis", axisValue);
    	Robot.ssArms.armUpDownLSet(axisValue);
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
