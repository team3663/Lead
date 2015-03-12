package org.usfirst.frc.team3663.robot.commands;

import java.util.Random;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_EncoderDriveSpeed extends Command {

	double inchesPerSecLeft;
	double inchesPerSecRight;
	int leftRightBoth;
	
    public C_EncoderDriveSpeed(double pInchesPerSec,int pLeftRightBoth) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDriveTrain);
        inchesPerSecLeft = pInchesPerSec;
        leftRightBoth = pLeftRightBoth;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Random rnd = new Random();
    	inchesPerSecLeft = 20+rnd.nextInt(75);
    	
    	if (rnd.nextInt(2) == 1)
    		inchesPerSecLeft*=-1;

    	inchesPerSecRight = 20+rnd.nextInt(75);
    	
    	if (rnd.nextInt(2) == 1)
    		inchesPerSecRight*=-1;
}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (leftRightBoth){
    	case 0:
    		Robot.ssDriveTrain.encoderDriveVelocityLeft(inchesPerSecLeft);
    		break;
    	case 1:
    		Robot.ssDriveTrain.encoderDriveVelocityRight(inchesPerSecRight);
    		break;
    	case 2:
    		Robot.ssDriveTrain.encoderDriveVelocityLeft(inchesPerSecLeft);
    		Robot.ssDriveTrain.encoderDriveVelocityRight(inchesPerSecRight);
    		break;
    	}
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
