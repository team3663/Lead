package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Right90 extends Command {
	double speed;
    public C_Right90(double pSpeed) {
    	speed = pSpeed;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ssDriveTrain.setFinalLeft((int)(13*Math.PI/2));
    	Robot.ssDriveTrain.setFinalRight((int)(-13*Math.PI/2));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDriveTrain.motorLeftSet(speed);
    	Robot.ssDriveTrain.motorRightSet(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.ssDriveTrain.finalTicksL < Robot.ssDriveTrain.leftEncoder.get()){
        	return true;
    	}	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDriveTrain.motorLeftSet(0);
    	Robot.ssDriveTrain.motorRightSet(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
