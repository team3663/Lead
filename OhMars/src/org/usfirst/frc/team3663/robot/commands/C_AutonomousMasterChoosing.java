package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SSArms;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_AutonomousMasterChoosing extends Command {
    public C_AutonomousMasterChoosing(){
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandGroup theOneToRun;
    	int choice = Robot.ssAutonomous.getAutonomousRoutine();
    	switch(choice){
    		case 0: 
    			theOneToRun = new CG_Autonomous3Totes();
    			theOneToRun.start();
    			break;
    		case 1:
    			break;
    		case 2:
    			break;
    		case 3:
    			break;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
