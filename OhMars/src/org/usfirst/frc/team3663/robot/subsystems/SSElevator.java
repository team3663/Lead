package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;


//test
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
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
	boolean setZero;
	double speed;
	double lastSpeed;
	final double absMaxSpeed = 1.0;
	final double absMinSpeed = 0.2;
	double maxSpeed;
	double delta, manualDelta;
	final double elevDelta = 0.05;
	int dir, manualDir;
//	double currTime, lastTime;
	//int lastTicks, startTicks;
	//double ticksPerSec;
	
	public int lowestPos = -15;
	public int lowStepPos = 50;
	public int onScoringPlatformPos = 275;
	public int onStepPos = 525;
	public int noTotePos = 600;
	public int highestPos = 1075;
	
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

    	lastSpeed = 0;
    	setZero = false;
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
    	if (!brakeOn && (speed == 0 || lastSpeed == 0))
    	{
	    	bikeBreak.set(DoubleSolenoid.Value.kReverse);
	    	brakeOn = true;
	    	Robot.ssDashBoard.putDashBool("brakeOn: ", brakeOn);
    	}
    }
    
    public void terminateMove()
    {
    	lastSpeed = speed = 0;
		bikeBrakeTriggerClose();
		motorsSet(speed);
    }
    
    //questions: are we there yet? are we going up? are we accelerating?
    public boolean moveToPos(int pTicks, double pMaxSpeed)
    {//find place to init speed
    	if (setZero)
    	{
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
	    	if (lastSpeed != 0)
	    	{
	    		bikeBrakeTriggerOpen();
	    	}
	    	motorsSet(lastSpeed);
    	}
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
        	speed = 0;
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
    		terminateMove();
    		setZero = true;
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
    
    public boolean getToteSwitch(){
    	if(toteSensor.get())
    		Robot.oi.driveController.setRumble(Joystick.RumbleType.kRightRumble, 500);
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