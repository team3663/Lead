package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsIntakeSet extends Command {
	double pLeftSpeed;
	double pRightSpeed;
    public C_ArmsIntakeSet(double leftSpeed, double rightSpeed){
    	pLeftSpeed = leftSpeed;
    	pRightSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.ssArmsIntake.intakeMotorLSet(pLeftSpeed);
		Robot.ssArmsIntake.intakeMotorRSet(pRightSpeed);
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