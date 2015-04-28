package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_MoveDownElevAndArms extends Command {

	
	private double endTimeWindowOfOppertuninty;
	private boolean buttonWasPressed = false;
    public C_MoveDownElevAndArms() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	endTimeWindowOfOppertuninty = Timer.getFPGATimestamp() + 5;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.driveController.getRawButton(5)){
    		buttonWasPressed = true;
    		//this is pretty much a command in a command couldnt think of how to properly do this at the time
    		comand c = new comand();
    		c.WhileRun();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(endTimeWindowOfOppertuninty < Timer.getFPGATimestamp() && !buttonWasPressed && Robot.ssElevator.safeToRun()){
        	return false;
        }
        else{
        	return true;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssElevator.motorsSet(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
    
    
    protected void interrupted() {
    	end();
    }
}
    
class comand{    
    
	public void WhileRun(){
		WtInit();
		while(WtIsFinished()){
			WtExecute();
		}
	}
	
	
	private double timeForRunning;
    private void WtInit(){
    	timeForRunning = Timer.getFPGATimestamp() + .5;
    }
    private void WtExecute(){
    	Robot.ssElevator.motorsSet(-.125);
    }
    private boolean WtIsFinished(){
    	return timeForRunning > Timer.getFPGATimestamp();
    }
}
