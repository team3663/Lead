package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *THERE IS NOT A GYRO ON THE ROBOT ON THE ROBOT SO THIS DOES NOT WORK
 */
public class C_TurnWithGyro extends Command {
	public int angle;
	public double speed;
    public C_TurnWithGyro(double pSpeed, int pAngle) {
    	speed = pSpeed;
    	angle = pAngle;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDriveTrain.setFinalGyro(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(angle < 0){
    		Robot.ssDriveTrain.motorRightSet(-speed);
    		Robot.ssDriveTrain.motorLeftSet(speed);
    	}
    	else if(angle > 0){
    		Robot.ssDriveTrain.motorRightSet(speed);
    		Robot.ssDriveTrain.motorLeftSet(-speed);
    	}
    	else{
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.ssDriveTrain.CheckGyro()){
    		Robot.ssDriveTrain.motorRightSet(0);
    		Robot.ssDriveTrain.motorLeftSet(0);
    		return true;
    	}
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
