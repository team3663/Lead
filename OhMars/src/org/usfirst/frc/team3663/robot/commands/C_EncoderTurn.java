package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_EncoderTurn extends Command {
	int angle, radius;
	boolean r = false,l = false;
    public C_EncoderTurn(int pRadius, int pAngle, boolean turn) {
    	angle = pAngle;
    	radius = pRadius;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDriveTrain.breakmodeDriveMotors(true);
    	Robot.ssDriveTrain.encoderDriving = true;
    	Robot.ssDriveTrain.eDistanceArc(radius, angle, true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDriveTrain.difference();
    	Robot.ssDriveTrain.setTheSpeeds(.2);
    	if(r == false){
        	Robot.ssDriveTrain.timeRunningR++;
    		Robot.ssDriveTrain.motorRightSet(Robot.ssDriveTrain.speedR);
    	}
    	if(l == false){
        	Robot.ssDriveTrain.timeRunningL++;
    		Robot.ssDriveTrain.motorLeftSet(Robot.ssDriveTrain.speedL);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean r = false,l = false;
    	if(Robot.ssDriveTrain.checkIfLeftDone()){
    		Robot.ssDriveTrain.motorLeftSet(0);
    		l = true;
    	}
    	if(Robot.ssDriveTrain.checkIfRightDone()){
    		Robot.ssDriveTrain.motorRightSet(0);
    		r = true;
    	}
    	if(r == true && l == true){
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDriveTrain.encoderDriving = false;
    	Robot.ssDriveTrain.motorLeftSet(0);
    	Robot.ssDriveTrain.motorRightSet(0);
    	Robot.ssDriveTrain.breakmodeDriveMotors(false);
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}