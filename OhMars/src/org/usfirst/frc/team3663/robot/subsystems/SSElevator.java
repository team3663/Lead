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
	public Talon elevInAndOut;
	DoubleSolenoid bikeBreak;
	public Encoder winchEncoder;
	public DigitalInput elevLimitSwitch, toteSensor;
	
	public int i = 0;
	
	public boolean brakeOn;
	boolean startZeroState;
	double speed;
	final double maxSpeedP = 1.0;
	double maxSpeed;
	double delta, manualDelta;
	int dir, manualDir;
	double currTime, lastTime;
	int lastTicks, startTicks;
	double ticksPerSec;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new C_DefaultElevatorRunning(0));
    }
    
    public SSElevator(){
    	toteSensor = new DigitalInput(7);
    	bikeBreak = new DoubleSolenoid(2,3);
    	elevInAndOut = new Talon(4);
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
    public void moveInAndOut(double speed){
    	elevInAndOut.set(speed);
    	Robot.ssDashBoard.putDashNumber("testing window motor counter: ", i++);
    	Robot.ssDashBoard.putDashNumber("ElevatorInAndOut SpeedInput: ", speed);
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
    	bikeBrakeTriggerOpen();
    	if (encoderVal < ticks)
    	{
    		dir = 1;
    	}
    	else
		{
    		dir = -1;
		}
		delta = dir*Robot.elevDelta;
		SmartDashboard.putNumber("ticksEntered: ", ticks);
		speed = 0;
    }
    
    public boolean moveToPos(int ticks, double pMaxSpeed)
    {
    	if (pMaxSpeed > maxSpeedP)
    	{
    		pMaxSpeed = maxSpeedP;
    	}
    	maxSpeed = dir*pMaxSpeed;
    	int encoderVal = winchEncoder.get();
    	if (dir == 1)
    	{
    		if (encoderVal > ticks)
    		{
    			return true;
    		}
    		else if (encoderVal > ticks-15)
    		{
    			maxSpeed = 0.4;
    			delta = -delta;
    		}
    	}
    	else
    	{
    		int diff = encoderVal - ticks;
    		if (diff <= 0)
    		{
    			return true;
    		}
    		else if (diff < 60)
    		{
    			maxSpeed = -0.5;
    			delta = -delta;
    		}
    	}
    	speed+=delta;
    	if (Math.abs(speed) > Math.abs(maxSpeed))
    	{
    		speed = maxSpeed;
    	}
    	motorsSet(speed);
    	return false;
    }
    
    public void moveAndSetZeroInit()
    {
		bikeBrakeTriggerOpen();
    	startZeroState = elevLimitSwitch.get();
    	if (startZeroState)
    	{
    		dir = -1;
    	}
    	else
    	{
    		dir = 1;
    	}
		delta = dir*Robot.elevDelta;
		maxSpeed = dir*maxSpeedP;
    	speed = 0;
    }
    public boolean moveAndSetZero()
    {
		if (elevLimitSwitch.get() != startZeroState)
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
		bikeBrakeTriggerClose();
		motorsSet(0);
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
    public void updateStatus(){
    	SmartDashboard.putNumber("ElevEncoder", Robot.ssElevator.winchEncoder.get());
    	SmartDashboard.putNumber("ElevMotor1", Robot.ssElevator.elevMotor1.get());
    	SmartDashboard.putNumber("ElevMotor2", Robot.ssElevator.elevMotor2.get());
    	if(Robot.ssElevator.toteSensor.get()){
    		SmartDashboard.putString("ElevToteSensor", "toteIn");
    	}else{
    		SmartDashboard.putString("ElevToteSensor", "noTote");
    	}
    	if(Robot.ssElevator.brakeOn){
    		SmartDashboard.putString("ElevBrake", "on");
    	}else{
    		SmartDashboard.putString("ElevBrake", "off");
    	}
    }
}