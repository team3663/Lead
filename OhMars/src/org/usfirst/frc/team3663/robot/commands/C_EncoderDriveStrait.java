package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 
 */
public class C_EncoderDriveStrait extends Command {

    public C_EncoderDriveStrait() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDriveTrain.encoderDriving = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {  
    	//Robot.ssDriveTrain.driveForwardDistance(0.2, 8);
    	//Robot.ssDriveTrain.encoderDriving = true;
    	Robot.ssDriveTrain.driveForwardDistance();
    	Robot.ssDriveTrain.motorLeftSet(0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.ssDriveTrain.encoderDriving == true){
    		return false;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDriveTrain.zeroMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
