package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class C_EncoderDrivingPosition extends Command {

	double maxInchesPerSec;
	double inchesToMove;
	double finalLocationLeft;
	double finalLocationRight;
	int leftRightBoth;
	boolean stopAtEnd;
	boolean finishedLeft;
	boolean finishedRight;
	
    public C_EncoderDrivingPosition(double pMaxInchesPerSec, double pInchesToMove, boolean pStopAtEnd, int pLeftRightBoth) {
        requires(Robot.ssDriveTrain);
        
        maxInchesPerSec = pMaxInchesPerSec;
        inchesToMove = pInchesToMove;
        stopAtEnd = pStopAtEnd;
        leftRightBoth = pLeftRightBoth;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	finalLocationLeft = Robot.ssDriveTrain.getLeftPositionInInches() + inchesToMove;
    	finalLocationRight = Robot.ssDriveTrain.getRightPositionInInches() + inchesToMove;
    	finishedLeft = true;
    	finishedRight = true;
    	switch (leftRightBoth){
    	case 0:
        	finishedLeft = false;
        	break;
    	case 1:
    		finishedRight = false;
        	break;
    	case 2:
			finishedLeft = false;
			finishedRight = false;
    		break;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	switch (leftRightBoth){
    	case 0:
        	finishedLeft = Robot.ssDriveTrain.encoderDrivePositionLeft(maxInchesPerSec, finalLocationLeft, stopAtEnd);
        	break;
    	case 1:
    		finishedRight = Robot.ssDriveTrain.encoderDrivePositionRight(maxInchesPerSec, finalLocationRight, stopAtEnd);
        	break;
    	case 2:
    		if (!finishedLeft)
    			finishedLeft = Robot.ssDriveTrain.encoderDrivePositionLeft(maxInchesPerSec, finalLocationLeft, stopAtEnd);
    		if (!finishedRight)
    			finishedRight = Robot.ssDriveTrain.encoderDrivePositionRight(maxInchesPerSec, finalLocationRight, stopAtEnd);
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finishedLeft && finishedRight;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
