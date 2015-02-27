package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_Hard90 extends Command {
	double speed;
	boolean turnLeft;
    public C_Hard90(double pSpeed, boolean pTurnLeft) {
    	speed = pSpeed;
    	turnLeft = pTurnLeft;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDriveTrain.breakmodeDriveMotors(true);
    	Robot.ssDriveTrain.encoderDriving = true;
    	if(turnLeft){
        	Robot.ssDriveTrain.setFinalLeft((int)(26*Math.PI/2));//26 is the length between wheels
    	}
    	else{
    		Robot.ssDriveTrain.setFinalRight((int)(26*Math.PI/2));
    	}
    	SmartDashboard.putString("ssDriveTrain","C_Hard90 initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(turnLeft){
        	Robot.ssDriveTrain.motorLeftSet(speed);
    	}
    	else{
        	Robot.ssDriveTrain.motorRightSet(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ssDriveTrain.driveForwardDistance(speed);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDriveTrain.encoderDriving = false;
    	Robot.ssDriveTrain.motorLeftSet(0);
    	Robot.ssDriveTrain.motorRightSet(0);
    	SmartDashboard.putString("ssDriveTrain","C_Hard90 end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssDriveTrain","C_Hard90 interrupted");
    }
}
