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

	final double elevDelta = 0.05;
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
	public final int lowestPos = -15;
	public final int lowStepPos = 50;
	public final int onScoringPlatformPos = 275;
	public final int onStepPos = 525;
	public final int readyForBinPos = 218;
	public final int noTotePos = 600;
	public final int nextToteReadyPos = 1075;
	public final int highestPos = 1075;//1165;
	
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
    
    public boolean manualMoveElevator(double pSpeed)
    {
    	int currTicks = winchEncoder.get();
    	double newSpeed = lastSpeed;
    	int dir = 1;
    	if (pSpeed < 0)
    	{
    		dir = -1;
    	}
    	double desiredSpeed = Math.abs(pSpeed);
    	if (desiredSpeed < 0.2)
    	{
    		desiredSpeed = 0;
    	}
    	Robot.ssDashBoard.putDashNumber("Elevator: pSpeed: ", pSpeed);
    	if ((dir == -1 && currTicks < lowestPos+30) || (dir == 1 && currTicks > highestPos-25))
		{
    		newSpeed-=delta;
		}
    	else if (lastSpeed > desiredSpeed)
    	{
    		newSpeed-=delta;
    	}
    	else if (lastSpeed < desiredSpeed)
    	{
    		newSpeed+=delta;
    	}
    //	int currTicks = winchEncoder.get();
    	boolean stop = false;
    	if (desiredSpeed == 0 && (newSpeed < 0.2 && lastSpeed >=0.2))
    	{
    		stop = true;
        	Robot.ssDashBoard.putDashString("Elevator: stopped: ", "slowed down");
    	}
    	if ((dir == 1 && currTicks > highestPos) || (dir == -1 && currTicks < lowestPos))
    	{
    		stop = true;
        	Robot.ssDashBoard.putDashString("Elevator: stopped: ", "reached limit");
    	}
    	if (!elevZeroed)
    	{
    		stop = true;
        	Robot.ssDashBoard.putDashString("Elevator: stopped: ", "not zeroed");
    	}
    	//if near end or if manual is stopping, then terminate
    	if (stop)
    	{
				terminateMove();
				Robot.ssDashBoard.putDashNumber("Elevator: terminate: ", terminateCounter++);
				return true;
    	}
    	Robot.ssDashBoard.putDashString("Elevator: stopped: ", "not stopped");
    	if (newSpeed != 0)
    	{
    		bikeBrakeTriggerOpen();
    	}
		Robot.ssDashBoard.putDashNumber("Elevator: speed: ", newSpeed);
    	Robot.ssDashBoard.putDashNumber("Elevator: counter", counter++);
		Robot.ssDashBoard.putDashNumber("encoderTicks: ", currTicks);
		
    	motorsSet(dir*newSpeed);
    	lastSpeed = newSpeed;
    	return false;
    }
    
    public boolean moveToSetPos(int pTicks, double pMaxSpeed)
    {
    	Robot.ssDashBoard.putDashNumber("Elevator: pTicks(SetPos): ", pTicks);
    	Robot.ssDashBoard.putDashNumber("Elevator: pMaxSpeed(SetPos): ", pMaxSpeed);
    	maxSpeed = Math.abs(pMaxSpeed);
    	int acceleration = 1;//default to accelerating
    	int dir = 1;//default to going up
    	int currTicks = winchEncoder.get();
    	int tickDelta = Math.abs(pTicks-currTicks);//distance to goal
    	if (tickDelta < 5 || !elevZeroed)
    	{
    		terminateMove();
    		minSpeedAdjust = 0;
    		Robot.ssDashBoard.putDashNumber("Elevator: terminate: ", terminateCounter++);
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
    	maxSpeed = 1.0;
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
		Robot.ssDashBoard.putDashBool("Elevator: Zeroed: ", elevZeroed);
		Robot.ssDashBoard.putDashNumber("encoderTicks: ", winchEncoder.get());
		if (elevLimitSwitch.get())
		{
    		winchEncoder.reset();
    		terminateMove();
    		elevZeroed = true;
    		return true;
		}
		Robot.ssDashBoard.putDashNumber("Elevator: setZero speed:", speed+((double)counter++/10000.0));
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