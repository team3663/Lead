package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_EncoderDrivingCurved extends Command {

	double arcInRadians;
	double inchesToArcCenter;
	double inchesLeft;
	double inchesRight;
	double maxInchesPerSec;
	boolean finishedLeft;
	boolean finishedRight;
	boolean stopAtEnd;
	double leftInchesPerSec;
	double rightInchesPerSec;
	double finalLocationLeft;
	double finalLocationRight;

	
    public C_EncoderDrivingCurved(double pArcInRadians, double pInchesToArcCenter, double pMaxInchesPerSec, boolean pStopAtEnd) {
        requires(Robot.ssDriveTrain);
    	arcInRadians = pArcInRadians;
    	inchesToArcCenter = pInchesToArcCenter;
    	maxInchesPerSec = pMaxInchesPerSec;
    	stopAtEnd = pStopAtEnd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	inchesLeft = Robot.ssDriveTrain.ticksToTravelLeft(inchesToArcCenter, arcInRadians)/Robot.ssDriveTrain.ticksPerInchLeft;
    	inchesRight = Robot.ssDriveTrain.ticksToTravelRight(inchesToArcCenter, arcInRadians)/Robot.ssDriveTrain.ticksPerInchRight;
    	
    	finalLocationLeft = Robot.ssDriveTrain.getLeftPositionInInches() + inchesLeft;
    	finalLocationRight = Robot.ssDriveTrain.getRightPositionInInches() + inchesRight;

    	double absTicksLeft = Math.abs(inchesLeft);
    	double absTicksRight = Math.abs(inchesRight);
    	
    	leftInchesPerSec = maxInchesPerSec;
    	rightInchesPerSec = maxInchesPerSec;
    	
    	if (absTicksLeft < absTicksRight){
    		leftInchesPerSec *= absTicksLeft/absTicksRight; 
    	} else if (absTicksLeft > absTicksRight){
    		rightInchesPerSec *= absTicksRight/absTicksLeft;
    	}
    	
		finishedLeft = false;
		finishedRight = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (!finishedLeft)
			finishedLeft = Robot.ssDriveTrain.encoderDrivePositionLeft(leftInchesPerSec, finalLocationLeft, stopAtEnd);
		if (!finishedRight)
			finishedRight = Robot.ssDriveTrain.encoderDrivePositionRight(rightInchesPerSec, finalLocationRight, stopAtEnd);
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
