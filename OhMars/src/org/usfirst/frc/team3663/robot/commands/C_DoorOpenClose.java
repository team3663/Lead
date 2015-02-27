package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.OI;

/**
 *
 */
public class C_DoorOpenClose extends Command {
	public boolean close;
    public C_DoorOpenClose(boolean pClose){
    	//requires(Robot.ssDoor);
    	close = pClose;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ssDoor", "C_DoorOpen initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(close){
    		Robot.ssDoor.setDoorSpeed(1.0);//check direction
    	}else{
    		Robot.ssDoor.setDoorSpeed(-1.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.ssDoor.doorIsClosed && !close){ //If the door is closed and you are trying to open it
    		return Robot.ssDoor.getIsOpenSwitch();//end when the open switch is hit
    	}else if(!Robot.ssDoor.doorIsClosed && close){//if the door is not closed and trying to close it
    		return Robot.ssDoor.getIsClosedSwitch();//end when the close switch is hit
    	}else{
    		return true;//If you are trying to open an open door, or close a closed one, do nothing
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDoor.setDoorSpeed(0.0);
    	SmartDashboard.putString("ssDoor", "C_DoorOpen end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    	SmartDashboard.putString("ssDoor", "C_DoorOpen interrupted");
    }
}
