package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 
 */
public class C_EncoderDriveStraight extends Command {
	int inches;
	double speed;
    public C_EncoderDriveStraight(int pInches, double pSpeed) {
    	inches = pInches;
    	speed = pSpeed;
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(inches < 0){
    		speed = -speed;
    	}
    	Robot.ssDriveTrain.breakmodeDriveMotors(true);
    	Robot.ssDriveTrain.encoderDriving = true;
    	Robot.ssDriveTrain.setFinalLeft(inches);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {  
    	//Robot.ssDriveTrain.driveForwardDistance(0.2, 8);
    	//Robot.ssDriveTrain.encoderDriving = true;
    	Robot.ssDriveTrain.motorLeftSet(speed);
    	Robot.ssDriveTrain.motorRightSet(speed);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
