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
import org.usfirst.frc.team3663.robot.commands.C_DefaultElevatorRunning;

/**
 *
 */
public class SSElevator extends Subsystem {
    
	CANTalon elevMotor1, elevMotor2;
	DoubleSolenoid bikeBreak;
	public Encoder winchEncoder;
	public DigitalInput elevLimitSwitch, toteSensor;
	
	public boolean brakeOn;
	boolean startZeroState;
	double speed;
	double lastSpeed = 0;
	final double absMaxSpeed = 1.0;
	final double absMinSpeed = 0.2;
	double maxSpeed;
	double delta, manualDelta;
	final double elevDelta = 0.05;
	int dir, manualDir;
//	double currTime, lastTime;
	//int lastTicks, startTicks;
	//double ticksPerSec;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new C_DefaultElevatorRunning(0));
    }
    
    public SSElevator()
    {
    	toteSensor = new DigitalInput(7);
    	bikeBreak = new DoubleSolenoid(2,3);
    	elevMotor1 = new CANTalon(15);
    	elevMotor2 = new CANTalon(25);
    	winchEncoder = new Encoder(1,2);
    	elevLimitSwitch = new DigitalInput(0);
    	
    	elevMotor1.enableBrakeMode(false);
    	elevMotor2.enableBrakeMode(false);
    	
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
    
    public void bikeBrakeTriggerOpen()
    {
    	if (brakeOn)
    	{
	    	bikeBreak.set(DoubleSolenoid.Value.kForward);
	    	brakeOn = false;
	    	Robot.ssDashBoard.putDashBool("brakeOn: ", brakeOn);
    	}
    }
    public void bikeBrakeTriggerClose(){
    	if (!brakeOn)
    	{
	    	bikeBreak.set(DoubleSolenoid.Value.kReverse);
	    	brakeOn = true;
	    	Robot.ssDashBoard.putDashBool("brakeOn: ", brakeOn);
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
    	bikeBrakeTriggerOpen();
    	if (encoderVal < ticks)
    	{
    		dir = 1;
    	}
    	else
		{
    		dir = -1;
		}
		delta = dir*elevDelta;
		SmartDashboard.putNumber("ticksEntered: ", ticks);
		speed = lastSpeed = 0;
    }
    
    //questions: are we there yet? are we going up? are we accelerating?
    public boolean moveToPos(int pTicks, double pMaxSpeed)
    {//find place to init speed
    	Robot.ssDashBoard.putDashString("testing moveToPos Start: ", "beginning moveToPos");
    	int acceleration = 1;
    	int dir = 1;
    	int currTicks = winchEncoder.get();
    	int tickDelta = Math.abs(currTicks-pTicks);
		if (tickDelta < 5 || (pMaxSpeed < absMinSpeed && lastSpeed <= absMinSpeed))
		{
			terminateMove();
			lastSpeed = 0;
			return true;
		}
    	if (currTicks > pTicks)
    	{
    		dir = -1;
    	}
		if (tickDelta < 50)
		{
			acceleration = -1;
		}
		speed = Math.abs(lastSpeed) + acceleration*delta;
		if (speed >  maxSpeed)
		{
			speed = maxSpeed;
		}
		if (speed > absMaxSpeed)
		{
			speed = absMaxSpeed;
		}
		if (speed < absMinSpeed)
		{
			speed = absMinSpeed;
		}
		lastSpeed = dir*speed;
    	motorsSet(lastSpeed);
    	return false;
    }
    
    public void moveAndSetZeroInit()
    {
		bikeBrakeTriggerOpen();
		delta = 0.05;
    	speed = 0;
    	maxSpeed = 1.0;
    }
    public boolean moveDownToZero()
    {
    	if (!elevLimitSwitch.get())
    	{
    		terminateMove();
        	maxSpeed = 0.2;
    		return false;
    	}
    	speed-=delta;
    	if (Math.abs(speed) > Math.abs(maxSpeed))
    	{
    		speed = -maxSpeed;
    	}
    	motorsSet(speed);
    	return true;//if using limitswitch.get, then no !
    }
    public boolean moveAndSetZero()
    {
		if (elevLimitSwitch.get())
		{
    		winchEncoder.reset();
    		stopElevator();
    		return true;
		}
		speed+=delta;
		if (Math.abs(speed) > Math.abs(maxSpeed))
		{
			speed = maxSpeed;
		}
		motorsSet(speed);
    	return false;
    }
    
    public void stopElevator()
    {
		terminateMove();
    }
    
    public boolean getToteSwitch(){
    	return toteSensor.get();
    }
    
    public void enableBrakeMotors(boolean enable)
    {
    	if (enable)
    	{
	    	//elevMotor1.enableBrakeMode(true);
	    	//elevMotor2.enableBrakeMode(true);
    	}
    	else
    	{
    		elevMotor1.enableBrakeMode(false);
    		elevMotor2.enableBrakeMode(false);
    	}
    }
}