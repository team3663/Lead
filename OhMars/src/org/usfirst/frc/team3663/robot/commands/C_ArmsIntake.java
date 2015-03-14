package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_ArmsIntake extends Command {
	int pov;
	boolean isPovActive = false;
    public C_ArmsIntake(){
    	requires(Robot.ssArmsIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssArmIntake", "C_ArmsIntake initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * In doing it this way, there is an inherent risk of catastrophic failure
    	 * and not being able to operate the arm wheels if need be. Say, a fleck of
    	 * something is stuck to the light sensor and then the arms are useless
    	 */
    	if(Robot.ssElevator.getToteSwitch())
    	{
    		//For unknown reasons, ^this does not work. It does not make the motors turn off
    		//when the switch is tripped, nor does it prevent the motors from turning off.
    		pov = Robot.oi.buttonController.getPOV();
    		if(pov > -1)
    		{
    			isPovActive = true;
    		}
    		else
    		{
    			isPovActive = false;
    		}
      		if(Robot.oi.driveController.getRawButton(1))
    			Robot.ssArmsIntake.intakeMotorsSet(0.0);
    		else if(Robot.oi.driveController.getRawButton(2))
    			Robot.ssArmsIntake.intakeMotorsSet(-0.9);
    		else if(Robot.oi.driveController.getRawButton(3))
    			Robot.ssArmsIntake.intakeMotorsSet(0.9);
    		else if(isPovActive){
	    		if(pov == 270){
	    			Robot.ssArmsIntake.intakeMotorRSet(-0.3);
	    			Robot.ssArmsIntake.intakeMotorLSet(0.3);
	    		}
	    		else if(pov == 90){
	    			Robot.ssArmsIntake.intakeMotorLSet(-0.3);
    				Robot.ssArmsIntake.intakeMotorRSet(0.3);
    			}
    			else if(pov == 180){
    				Robot.ssArmsIntake.intakeMotorsSet(0.0);
	    		}
    		}
    		/*else if(!Robot.ssArmsIntake.armsAreSameDirection()){
				Robot.ssArmsIntake.intakeMotorsSet(0.0);
    		}*/
		}else{
    		Robot.ssArmsIntake.intakeMotorsSet(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("ssArmIntake", "C_ArmsIntake end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssArmIntake", "C_ArmsIntake interrupted");
    }
}
