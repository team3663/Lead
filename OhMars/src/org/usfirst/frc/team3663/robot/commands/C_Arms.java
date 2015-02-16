package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SSArms;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_Arms extends Command {

    public C_Arms() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssArms);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssArms.intakeMotorLSet(Robot.oi.logitech.getRawAxis(2));
    	Robot.ssArms.intakeMotorRSet(Robot.oi.logitech.getRawAxis(3));
    	Robot.ssArms.armUpDownRSet(Robot.oi.logitech.getRawAxis(1));
    	Robot.ssArms.armUpDownLSet(Robot.oi.logitech.getRawAxis(5));
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
