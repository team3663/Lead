package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSDoor extends Subsystem {
	public Talon hingeMotor;
	//public DigitalInput doorIsOpenSwitch;
	//public DigitalInput doorIsClosedSwitch;
	public boolean doorIsClosed;
	
	public SSDoor(){
		hingeMotor = new Talon(11);
		//doorIsOpenSwitch = new DigitalInput(8);
		//doorIsClosedSwitch = new DigitalInput(9);
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setDoorSpeed(double speed){
    	hingeMotor.set(speed);
    }
    public double getDoorSpeed(){
    	return Robot.ssDoor.hingeMotor.get();
    }
    public boolean getIsOpenSwitch(){
    	if(true/*Robot.ssDoor.doorIsOpenSwitch.get()*/){
    		doorIsClosed = false;
    		return true;
    	}else{
    		doorIsClosed = true;
    		return false;
    	}
    }
    public boolean getIsClosedSwitch(){
    	if(true/*Robot.ssDoor.doorIsClosedSwitch.get()*/){
    		doorIsClosed = true;
    		return true;
    	}else{
    		doorIsClosed = false;
    		return false;
    	}
    }
    public void updateStatus(){
    	SmartDashboard.putNumber("DoorHingeMotor", Robot.ssDoor.hingeMotor.get());
    	if(true/*Robot.ssDoor.doorIsOpenSwitch.get()*/)
    		SmartDashboard.putString("DoorLimitSwitch", "open?");
    	else
    		SmartDashboard.putString("DoorLimitSwitch", "closed?");
    }
    
}

