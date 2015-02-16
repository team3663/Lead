package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
//test
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class SSElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon elevMotor1, elevMotor2;
	Talon elevInAndOut;
	DoubleSolenoid bikeBreak;
	public Encoder winchEncoder;
	public DigitalInput elevLimitSwitch, toteSensor;
	
	public boolean brakeOn;
	
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public SSElevator(){
    	toteSensor = new DigitalInput(7);
    	bikeBreak = new DoubleSolenoid(2,3);
    	elevInAndOut = new Talon(4);
    	elevMotor1 = new CANTalon(15);
    	elevMotor2 = new CANTalon(25);
    	winchEncoder = new Encoder(1,2);
    	elevLimitSwitch = new DigitalInput(0);
    }
    
    public void motorsSet(double speed)
    {
    	motor1Set(speed);
    	motor2Set(speed);
    }
    public void motor1Set(double speed){
    	elevMotor1.set(speed);
    }
    public void motor2Set(double speed){
    	elevMotor2.set(speed);
    }
    public void moveInAndOut(double speed){
    	elevInAndOut.set(speed);
    }
    public void bikeBrakeTriggerOpen()
    {
    	bikeBreak.set(DoubleSolenoid.Value.kForward);
    	brakeOn = false;
    	SmartDashboard.putBoolean("brakeOn: ", brakeOn);
    }
    public void bikeBrakeTriggerClose(){
    	bikeBreak.set(DoubleSolenoid.Value.kReverse);
    	brakeOn = true;
    }
    
    public boolean moveToPos(int ticks)
    {
    	bikeBrakeTriggerOpen();
    	SmartDashboard.putBoolean("elevLimitSwtich: ", elevLimitSwitch.get());
    	if (winchEncoder.get() < ticks)
    	{
    		if (winchEncoder.get() > ticks-50)
    		{
    			motorsSet(0.3);
    		}
    		motorsSet(1.0);
    	}
    	else if (winchEncoder.get() > ticks && elevLimitSwitch.get())
    	{
    		if (winchEncoder.get() < ticks+50)
    		{
    			motorsSet(-0.2);
    		}
    		motorsSet(-0.4);
    	}
    	else if (winchEncoder.get() > ticks-15 || winchEncoder.get() < ticks+15 || !elevLimitSwitch.get())
    	{
    		bikeBrakeTriggerClose();
    		return true;
    	}
    	return false;
    }
    
    public boolean moveAndSetZero()
    {
    	if (elevLimitSwitch.get())
    	{
    		bikeBrakeTriggerOpen();
    		motorsSet(-0.2);
    	}
    	SmartDashboard.putBoolean("elevLimitSwitch: ", elevLimitSwitch.get());
    	if (!elevLimitSwitch.get())
    	{
    		motorsSet(0);
    		bikeBrakeTriggerClose();
    		winchEncoder.reset();
    		return true;
    	}
    	return false;
    }
    
    public void logValues()
    {
    	SmartDashboard.putNumber("winchEncoder: ", Robot.ssElevator.winchEncoder.get());
    	SmartDashboard.putBoolean("elevLimitSwitch: ", Robot.ssElevator.elevLimitSwitch.get());
    }
    public boolean getToteSwitch(){
    	return toteSensor.get();
    }
    
}