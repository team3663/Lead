package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_ArmsOpenCloseToggle extends Command {
    public C_ArmsOpenCloseToggle() {
        requires(Robot.ssArms);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Right is the same as the left on purpose
    	//to make them the same
    	Robot.ssArms.armLClose(!Robot.ssArms.lArmClose);
    	Robot.ssArms.armRClose(Robot.ssArms.lArmClose);
    	//Update the states
    	Robot.ssArms.lArmClose = !Robot.ssArms.lArmClose;
    	Robot.ssArms.rArmClose = Robot.ssArms.lArmClose;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(Robot.ssArms.lArmClose);
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
