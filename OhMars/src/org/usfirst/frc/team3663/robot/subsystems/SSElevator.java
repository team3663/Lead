package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

//test
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
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
	int dir;
	double currTime, lastTime;
	int lastTicks;
	double ticksPerSec;
	
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
    	
    	elevMotor1.enableBrakeMode(true);
    	elevMotor2.enableBrakeMode(true);
    	
    	bikeBrakeTriggerClose();
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
    	if (brakeOn)
    	{
	    	bikeBreak.set(DoubleSolenoid.Value.kForward);
	    	brakeOn = false;
	    	SmartDashboard.putBoolean("brakeOn: ", brakeOn);
    	}
    }
    public void bikeBrakeTriggerClose(){
    	if (!brakeOn)
    	{
	    	bikeBreak.set(DoubleSolenoid.Value.kReverse);
	    	brakeOn = true;
	    	SmartDashboard.putBoolean("brakeOn: ", brakeOn);
    	}
    }
    
    public void terminateMove()
    {
		bikeBrakeTriggerClose();
		motorsSet(0);
    }
    
    public void prepForMove(int ticks)
    {
    	int encoderVal = winchEncoder.get();
    	currTime = lastTime = Timer.getFPGATimestamp();
    	lastTicks = encoderVal;
    	bikeBrakeTriggerOpen();
    	if (encoderVal < ticks)
    	{
    		dir = 1;
    	}
    	else
		{
    		dir = -1;
		}
		SmartDashboard.putNumber("ticksEntered: ", ticks);
    }
    
    public boolean moveToPos(int ticks)
    {
    	double speed;
    	int encoderVal = winchEncoder.get();
    	currTime = Timer.getFPGATimestamp();
    	ticksPerSec = Math.abs((encoderVal-lastTicks)/(currTime-lastTime));
    	SmartDashboard.putNumber("ticksPerSecElevator: ", ticksPerSec);
    	if (dir == 1)
    	{
    		speed = 1.0;
    		if (encoderVal > ticks)//(encoderVal > ticks-ticksPerSec)//(encoderVal > ticks)
    		{
    			return true;
    		}
    		else if (encoderVal > ticks-15)
    		{
    			speed = 0.3;
    		}
    	}
    	else
    	{
    		int diff = encoderVal - ticks;
    		speed = -1.0;
    		if (diff <= 0)//ticksPerSec)
    		{
    			return true;
    		}
    		else if (diff < 30)
    		{
    			speed = -0.2;
    		}
    	}
    	motorsSet(speed);
    	lastTime = currTime;
    	lastTicks = encoderVal;
    	return false;
    }
    
    public boolean moveAndSetZero()
    {
    	if (elevLimitSwitch.get())
    	{
    		bikeBrakeTriggerOpen();
    		motorsSet(-1.0);
    	}
    	if (!elevLimitSwitch.get())
    	{
    		winchEncoder.reset();
    		stopElevator();
    		return true;
    	}
    	return false;
    }
    
    public void stopElevator()
    {
		bikeBrakeTriggerClose();
		motorsSet(0);
    }
    
    public boolean getToteSwitch(){
    	return toteSensor.get();
    }
    
}