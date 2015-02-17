package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	Robot.ssDashBoard.putDashInt("elevWinchEncoder: ", Robot.ssElevator.winchEncoder.get());
    	//**Driving
    	Robot.ssDashBoard.putDashInt("Left Drive Encoder", Robot.ssDriveTrain.leftEncoder.get());
    	Robot.ssDashBoard.putDashInt("Right Drive Encoder", Robot.ssDriveTrain.rightEncoder.get());
    	Robot.ssDashBoard.putDashInt("Final Encoder Ticks Left", Robot.ssDriveTrain.finalTicksL);
    	Robot.ssDashBoard.putDashInt("Final Encoder Ticks Right", Robot.ssDriveTrain.finalTicksR);
    	Robot.ssDashBoard.putDashBool("Encoder Running", Robot.ssDriveTrain.encoderDriving);
    	Robot.ssDashBoard.putDashDouble("Right Speed", Robot.ssDriveTrain.speedR);
    	Robot.ssDashBoard.putDashDouble("Left Speed", Robot.ssDriveTrain.speedL);
    	Robot.ssDashBoard.putDashInt("Time Running Right", Robot.ssDriveTrain.timeRunningR);
    	Robot.ssDashBoard.putDashInt("Time Running Left", Robot.ssDriveTrain.timeRunningL);
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
