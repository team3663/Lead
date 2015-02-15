package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_Log extends Command {

    public A_Log() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDashBoard);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDashBoard.SendToDashInt("RightDriveEncoder", Robot.ssDriveTrain.rightEncoder.get());
    	Robot.ssDashBoard.SendToDashInt("LeftDriveEncoder", Robot.ssDriveTrain.leftEncoder.get());
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