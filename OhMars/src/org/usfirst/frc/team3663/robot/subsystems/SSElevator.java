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

	final double elevDelta = 0.25;//0.05;
	final double absMaxSpeed = 1.0;
	final double absMinSpeed = 0.2;
	
	boolean startZeroState;
	boolean elevZeroed;
	double speed;
	double lastSpeed;
	double minSpeedAdjust = 0;
	double maxSpeed;
	double manualDelta;
	double delta = elevDelta;
	int dir, manualDir;
	int lastTicks = 0;
	int counter = 0;
	int terminateCounter = 0;

	public boolean brakeOn;
	public final int lowestPos = -5;
	public final int lowStepPos = 50;
	public final int onScoringPlatformPos = 220;//220 is new good position moving from 275
	public final int onStepPos = 525;
	public final int readyForBinPos = 218;
	public final int puttingStackPos = 485;
	public final int noTotePos = 600;
	public final int nextToteReadyPos = 1040;
	public final int highestPos = 1040;//1075;1165;
	
	//Stuff to print
	public int setTickPos;
	public double maxSpeedSetPos;
	public String message = "";
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
    	
    	elevMotor1.enableBrakeMode(true);//false);
    	elevMotor2.enableBrakeMode(true);//false);
    	
    	bikeBrakeTriggerClose();

    	lastSpeed = 0;
    	elevZeroed = false;
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
    	}
    }
    public void bikeBrakeTriggerClose(){
    	if (!brakeOn && (speed == 0 || lastSpeed == 0))
    	{
	    	bikeBreak.set(DoubleSolenoid.Value.kReverse);
	    	brakeOn = true;
    	}
    }
    
    public void terminateMove()
    {
    	lastSpeed = speed = 0;
		bikeBrakeTriggerClose();
		motorsSet(speed);
    }
    
    public boolean manualMoveElevator(double pSpeed)
    {
    	int currTicks = winchEncoder.get();
    	double newSpeed = lastSpeed;
    	double desiredSpeed = pSpeed;
    	if ((Math.abs(desiredSpeed) < 0.2) || ((pSpeed<0) && (currTicks < lowestPos+60)) || ((pSpeed>0) && (currTicks > highestPos-60)))
		{
    		desiredSpeed = 0;
		}
    	SmartDashboard.putNumber("Elev desiredSpeed: ", desiredSpeed);
    	if (lastSpeed > desiredSpeed)
    	{
    		newSpeed-=delta;
    	}
    	else if (lastSpeed < desiredSpeed)
    	{
    		newSpeed+=delta;
    	}
    	boolean stop = false;
    	if (desiredSpeed == 0 && (Math.abs(newSpeed) < 0.2))
    	{
    		stop = true;
        	message = "slowed down";
    	}
    	if ((currTicks > highestPos) || (currTicks < lowestPos))//dir == 1 or dir == -1 with these
    	{
    		stop = true;
        	message = "reached limit";
    	}
    	if (!elevZeroed)
    	{
    		stop = true;
    		message = "not zeroed";
    	}
    	//if near end or if manual is stopping, then terminate
    	if (stop)
    	{
        	SmartDashboard.putNumber("Elev lastSpeed", lastSpeed);
			terminateMove();
			terminateCounter++;
			return true;
    	}
    	message = "not stopped";
    	if (newSpeed > 1)
    	{
    		newSpeed = 1;
    	}
    	if (newSpeed < -1)
    	{
    		newSpeed = -1;
    	}
    	if (newSpeed != 0)
    	{
    		bikeBrakeTriggerOpen();
    	}
    	motorsSet(newSpeed);
    	SmartDashboard.putNumber("Elev lastSpeed", lastSpeed);
    	lastSpeed = newSpeed;
    	return false;
    }
    
    public boolean moveToSetPos(int pTicks, double pMaxSpeed)
    {
    	setTickPos = pTicks;
    	maxSpeedSetPos = pMaxSpeed;
    	maxSpeed = Math.abs(pMaxSpeed);
    	int acceleration = 1;//default to accelerating
    	int dir = 1;//default to going up
    	int currTicks = winchEncoder.get();
    	int tickDelta = Math.abs(pTicks-currTicks);//distance to goal
    	if (tickDelta < 5 || !elevZeroed)
    	{
    		terminateMove();
    		minSpeedAdjust = 0;
    		terminateCounter++;
    		return true;
    	}
    	if (currTicks > pTicks)
    	{
    		dir = -1;
    	}
    	if (tickDelta < 75)
    	{
    		acceleration = -1;
    	}
    	speed = Math.abs(lastSpeed) + acceleration*delta;
    	if (speed > maxSpeed)
    	{
    		speed = maxSpeed;
    	}
    	if (speed < absMinSpeed + minSpeedAdjust)
    	{
			//if stalled then increase minSpeed
			if (lastTicks == currTicks)
			{
				minSpeedAdjust+=delta;
			}
    		speed = absMinSpeed + minSpeedAdjust;
    	}
		lastSpeed = dir*speed;
    	if (lastSpeed != 0)
    	{
    		bikeBrakeTriggerOpen();
    	}
    	motorsSet(lastSpeed);
    	lastTicks = currTicks;
    	return false;
    }
    
    public void moveAndSetZeroInit()
    {
		bikeBrakeTriggerOpen();
    	speed = -absMinSpeed;
    	maxSpeed = .5;
    }
    public boolean moveDownToZero()
    {
    	if (!elevLimitSwitch.get())
    	{
    		terminateMove();
        	speed = absMinSpeed + 0.2;
    		return false;
    	}
    	speed-=elevDelta;
    	if (speed < -maxSpeed)
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
    		elevZeroed = true;
    		return true;
		}
		if (speed != 0)
		{
			bikeBrakeTriggerOpen();
		}
		motorsSet(speed);
    	return false;
    }
    
    public boolean getToteSwitch(){
    	return toteSensor.get();
    }
    
    public void enableBrakeMotors(boolean enable)
    {
    	if (enable)
    	{
	    	elevMotor1.enableBrakeMode(true);
	    	elevMotor2.enableBrakeMode(true);
    	}
    	else
    	{
    		//elevMotor1.enableBrakeMode(false);
    		//elevMotor2.enableBrakeMode(false);
    	}
    }
    
    double ticksPerSec = 0;
    double lastTime = 0;
    
    
    public void updateTicksPerSec()
    {
    	int currTicks = winchEncoder.get();
    	double currTime = Timer.getFPGATimestamp();
    	ticksPerSec = (currTicks-lastTicks)/(currTime-lastTime);
    	lastTime = currTime;
    	lastTicks = currTicks;
    }
    
    public void updateStatus(){
    	SmartDashboard.putString("Elevator stopped:", message);
    	SmartDashboard.putNumber("ElevSetTickPos", setTickPos);
    	SmartDashboard.putNumber("ElevMaxSpeedSet", maxSpeedSetPos);
    	SmartDashboard.putNumber("ElevTerminateCounter", terminateCounter);
    	updateTicksPerSec();
    	if (elevMotor1.get() == 1)
    	{
	    	if (Math.abs(ticksPerSec) >= 30)
	    	{
	    		SmartDashboard.putString("ElevTicksPerSec", "Elevator running on speed" + ticksPerSec);
	    	}
	    	else if (ticksPerSec == 0)
	    	{
	    		SmartDashboard.putString("ElevTicksPerSec", "Elevator running okay");
	    	}
	    	else
	    	{
	    		SmartDashboard.putString("ElevTicksPerSec", "Elevator running TOO SLOW!!!" + ticksPerSec);
	    	}
    	}
    	SmartDashboard.putNumber("ElevEncoder", winchEncoder.get());
    	SmartDashboard.putNumber("ElevMotor1", elevMotor1.get());
    	SmartDashboard.putNumber("ElevMotor2", elevMotor2.get());
    	SmartDashboard.putNumber("ElevMotor1Draw", elevMotor1.getOutputCurrent());
    	SmartDashboard.putNumber("ElevMotor2Draw", elevMotor2.getOutputCurrent());
    	if(Robot.ssElevator.toteSensor.get()){
    		SmartDashboard.putString("ElevToteSensor", "noTote");
    	}else{
    		SmartDashboard.putString("ElevToteSensor", "toteIn");
    	}
    	if(Robot.ssElevator.brakeOn){
    		SmartDashboard.putString("ElevBrake", "on");
    	}else{
    		SmartDashboard.putString("ElevBrake", "off");
    	}
    }
}