package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 ****THIS CODE HAS BEEN REMOVED FROM robot BECAUSE IT CRASHES ssAUTONOMOUS
 */
public class SSDoor extends Subsystem {
	public Talon hingeMotor;
	public DigitalInput doorIsOpenSwitch;
	public DigitalInput doorIsClosedSwitch;
	
	//These have been removed because I thought they were causing problems.
	//However, after their removal, the problem persisted, leading to the complete
	//removal of the whole subsystem, as stated above.
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
    	if(Robot.ssDoor.doorIsOpenSwitch.get()){
    		doorIsClosed = false;
    		return true;
    	}else{
    		doorIsClosed = true;
    		return false;
    	}
    }
    public boolean getIsClosedSwitch(){
    	if(Robot.ssDoor.doorIsClosedSwitch.get()){
    		doorIsClosed = true;
    		return true;
    	}else{
    		doorIsClosed = false;
    		return false;
    	}
    }
    public void updateStatus(){
    	SmartDashboard.putNumber("DoorHingeMotor", Robot.ssDoor.hingeMotor.get());
    	if(Robot.ssDoor.doorIsOpenSwitch.get())
    		SmartDashboard.putString("DoorLimitSwitch", "open?");
    	else
    		SmartDashboard.putString("DoorLimitSwitch", "closed?");
    }
    
}

