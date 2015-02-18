package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Hard90 extends Command {
	double speed;
	boolean turnLeft;
    public C_Hard90(double pSpeed, boolean turnLeft) {
    	speed = pSpeed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDriveTrain.breakmodeDriveMotors(true);
    	Robot.ssDriveTrain.encoderDriving = true;
    	if(turnLeft){
        	Robot.ssDriveTrain.setFinalLeft((int)(26*Math.PI/2));
    	}
    	else{
    		Robot.ssDriveTrain.setFinalRight((int)(26*Math.PI/2));
    	}
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
    	Robot.ssDriveTrain.motorLeftSet(0);
    	Robot.ssDriveTrain.motorRightSet(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
