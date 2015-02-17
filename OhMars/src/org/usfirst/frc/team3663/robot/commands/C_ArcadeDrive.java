package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ArcadeDrive extends Command {

    public C_ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double Speed = Robot.oi.driveController.getRawAxis(2) - Robot.oi.driveController.getRawAxis(3);
    	Robot.ssDriveTrain.arcadeDrive(Speed, Robot.oi.driveController.getRawAxis(0));
    	//Robot.ssDriveTrain.arcadeDrive(Robot.oi.driveController.getRawAxis(0), Speed);
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
