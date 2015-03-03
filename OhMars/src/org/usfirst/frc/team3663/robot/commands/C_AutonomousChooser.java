package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_AutonomousChooser extends Command {
    public C_AutonomousChooser(){
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandGroup theOneToRun_CG;
    	Command theOneToRun_C;
    	int choice = Robot.ssAutonomous.getAutonomousRoutine();
    	switch(choice){
    		case 0: 
    	//		theOneToRun_CG = new CG_Autonomous3Totes();
    		//	theOneToRun_CG.start();
    			break;
    		case 1:
    			theOneToRun_CG = new CG_Auto1();
    			theOneToRun_CG.start();
    			break;
    		case 2:
    			theOneToRun_C = new C_Right90(.4);
    			theOneToRun_C.start();
    			break;
    		case 3:
    			//do nothing!!!!!!!!!!!!!!
    			break;
    	}
    	SmartDashboard.putString("ssAutonomous", "C_AutonomousChooser initialize");
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
    	SmartDashboard.putString("ssAutonomous", "C_AutonomousChooser end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("ssAutonomous", "C_AutonomousChooser interrupted");
	}
}
