package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSDoor extends Subsystem {
	public CANTalon hingeMotor;
	public DigitalInput doorIsOpenSwitch;
	public DigitalInput doorIsClosedSwitch;
	boolean doorIsOpen;
	public SSDoor(){
		hingeMotor = new CANTalon(11);
		doorIsOpenSwitch = new DigitalInput(8);
		doorIsClosedSwitch = new DigitalInput(9);
		doorIsOpen = false;
		enableBrakeMode(true);
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
    public boolean openDoor(){
    	boolean isOpen = Robot.ssDoor.doorIsOpenSwitch.get();
    	if(isOpen){
    		setDoorSpeed(0.0);
    		doorIsOpen = true;
    	}
    	else
    		setDoorSpeed(-0.5);
		return isOpen;
		}
    public boolean closeDoor(){
    	boolean isClosed = Robot.ssDoor.doorIsClosedSwitch.get();
    	if(isClosed){
    		setDoorSpeed(0.0);
    		doorIsOpen = false;
    	}
    	else
    		setDoorSpeed(0.5);
		return isClosed;
    }
    public void enableBrakeMode(boolean pBrake){
    	hingeMotor.enableBrakeMode(pBrake);
    }
    public void stopDoor(){
    	setDoorSpeed(0.0);
    }
    public void updateStatus(){
    	SmartDashboard.putNumber("DoorHingeMotor", Robot.ssDoor.hingeMotor.get());
    	if(doorIsOpen)
    		SmartDashboard.putString("Door", "Open");
    	else
    		SmartDashboard.putString("Door", "Closed");
    }
    
}
