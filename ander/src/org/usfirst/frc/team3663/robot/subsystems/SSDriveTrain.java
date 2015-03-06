package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ArcadeDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SSDriveTrain extends Subsystem {
    
	CANTalon driveMotorL1, driveMotorL2, driveMotorR1, driveMotorR2;
	public Encoder leftEncoder, rightEncoder;
	RobotDrive chassis;
	ControlTracker ctl;
	ControlTracker ctr;
	
	public final double ticksPerInchLeft = 138.3; //137.5;
	public final double ticksPerInchRight = 132.7;//133.3;
	final double acceleration = .003;
	final double inchesBetweenWheels = 20;
	final double wheelDiaInInches = 7.5;
	final double ticksPerWheelRevLeft = ticksPerInchLeft*Math.PI*wheelDiaInInches; 
	final double ticksPerWheelRevRight = ticksPerInchRight*Math.PI*wheelDiaInInches; 
	
	public double ticksToTravelLeft(double inchesToArcCenter, double arcInRadians){
		return (-10-inchesToArcCenter)*arcInRadians/Math.PI/wheelDiaInInches*ticksPerWheelRevLeft;
	}
	
	public double ticksToTravelRight(double inchesToArcCenter, double arcInRadians){
		return (10-inchesToArcCenter)*arcInRadians/Math.PI/wheelDiaInInches*ticksPerWheelRevRight;
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new C_ArcadeDrive());
    }
    
    public SSDriveTrain()
    {
		driveMotorL1 = new CANTalon(10);
		driveMotorL2 = new CANTalon(12);
		
		driveMotorR1 = new CANTalon(11);
		driveMotorR2 = new CANTalon(13);
		
		chassis = new RobotDrive(driveMotorL1, driveMotorL2, driveMotorR1, driveMotorR2);
		
		leftEncoder = new Encoder(6,7);
		rightEncoder = new Encoder(8,9);

		ctl = new ControlTracker(leftEncoder,"Left_", driveMotorL1, true, ticksPerInchLeft); 
		ctr = new ControlTracker(rightEncoder,"Right_", driveMotorR1, true, ticksPerInchRight); 
		
		brakemodeDriveMotors(true);
	}
    
    public int getLeftEncoderTicks(){
    	return ctl.encoder.getRaw();
    }
    
    public double getLeftPositionInInches(){
    	return ctl.encoder.getRaw()/(double)ctl.ticksPerInch;
    }
    
    public double getRightPositionInInches(){
    	return ctr.encoder.getRaw()/(double)ctr.ticksPerInch;
    }
    
    public int getRightEncoderTicks(){
    	return ctr.encoder.getRaw();
    }
    
    void updateTrackers(ControlTracker ct){
		ct.currentTickCount = ct.encoder.getRaw();
		ct.currentTimestamp = Timer.getFPGATimestamp();
		//ct.lastMotorSetValue = ct.motor.get();
		ct.currentTicksPerSec = (ct.currentTickCount-ct.lastTickCount)/(ct.currentTimestamp-ct.lastTimestamp);
		
		ct.lastTimestamp = ct.currentTimestamp;
		ct.lastTickCount = ct.currentTickCount;
	}
    
    public void arcadeDrive(){
    	chassis.arcadeDrive(Robot.oi.testStick);
    	updateTrackers(ctl);
    	updateTrackers(ctr);
    }
    
    public boolean encoderDrivePositionLeft(double pMaxInchesPerSec, double pFinalInchesToTravel, boolean pStopAtEnd){
    	return encoderDrivePosition(pMaxInchesPerSec, pFinalInchesToTravel, pStopAtEnd, ctl);
    }

    public boolean encoderDrivePositionRight(double pMaxInchesPerSec, double pFinalInchesToTravel, boolean pStopAtEnd){
    	return encoderDrivePosition(pMaxInchesPerSec, pFinalInchesToTravel, pStopAtEnd, ctr);
    }

    boolean encoderDrivePosition(double pMaxInchesPerSec, double pFinalInchesToTravel, boolean pStopAtEnd, ControlTracker ct){
    	updateTrackers(ct);
    	
    	if (pFinalInchesToTravel != ct.finalInchesToTravel){
    		ct.finalInchesToTravel = pFinalInchesToTravel;
    		ct.currentFinalTickCount = pFinalInchesToTravel*ct.ticksPerInch;
    		ct.direction = 1;
    		if (ct.currentFinalTickCount < ct.currentTickCount)
    			ct.direction= -1;
    	}

    	ct.ticksToGo = ct.currentFinalTickCount - ct.currentTickCount;
    	
    	ct.maxTicksPerSec = pMaxInchesPerSec*ct.ticksPerInch;
    	
    	if (pStopAtEnd){
    		ct.ticksToStop = 1.5*Math.abs((ct.lastMotorSetValue/(acceleration*50))*(ct.currentTicksPerSec/2));
    		if (Math.abs(ct.ticksToGo) <= ct.ticksToStop)
    			ct.maxTicksPerSec = 0;
    	}
    	
    	encoderDriveVelocityInternal(ct.maxTicksPerSec*ct.direction, ct);
	    
    	if (ct.direction == 1 && ct.ticksToGo <= 0)
    		return true;
    	else if (ct.direction == -1 && ct.ticksToGo > 0)
    		return true;
    	
    	return false;
    }
    
    int l,r,rc;
    public void encoderDriveVelocityLeft(double pDesiredInchesPerSec){
    	encoderDriveVelocity(pDesiredInchesPerSec, ctl);
    }
    
    public void encoderDriveVelocityRight(double pDesiredInchesPerSec){
    	encoderDriveVelocity(pDesiredInchesPerSec, ctr);
    }
    
    void encoderDriveVelocity(double pDesiredInchesPerSec, ControlTracker ct){

    	updateTrackers(ct);
    	if (pDesiredInchesPerSec != ct.desiredInchesPerSec){
    		ct.desiredInchesPerSec = pDesiredInchesPerSec;
    		for (int i = 0;i<ct.averageBucketCount;i++)
    			ct.runningAverage[i] = ct.currentTicksPerSec;
    		ct.averageTicksPerSec = ct.currentTicksPerSec;
    	}
    	ct.desiredTicksPerSec=pDesiredInchesPerSec*ct.ticksPerInch;
	    	
    	encoderDriveVelocityInternal(ct.desiredTicksPerSec, ct);
    }
    
    void encoderDriveVelocityInternal(double pDesiredTicksPerSec, ControlTracker ct){
    	ct.index++;
    	ct.index %= ct.averageBucketCount;
    	ct.averageTicksPerSec += (ct.currentTicksPerSec - ct.runningAverage[ct.index])/(double)ct.averageBucketCount;
    	ct.runningAverage[ct.index] = ct.currentTicksPerSec;
    	
    	ct.deltaTicksPerSec = ct.averageTicksPerSec - pDesiredTicksPerSec;
    	double newMotorSetValue = ct.lastMotorSetValue*ct.motorFlip;
    	
    	double adjustedAcceleration = acceleration;
    	if (pDesiredTicksPerSec != 0)
    		adjustedAcceleration = Math.abs(acceleration*ct.deltaTicksPerSec/pDesiredTicksPerSec);
    	
    	if (ct.deltaTicksPerSec<0)
    		newMotorSetValue += adjustedAcceleration;
    	else
    		newMotorSetValue -= adjustedAcceleration;
    	
    	if (newMotorSetValue > 1)
    		newMotorSetValue = 1;
    	else if (newMotorSetValue < -1)
    		newMotorSetValue = -1;
    	
    	newMotorSetValue *= ct.motorFlip;
    	
		ct.lastMotorSetValue = newMotorSetValue;
		//fix global usages
		chassis.tankDrive(ctl.lastMotorSetValue, ctr.lastMotorSetValue, false);
    }

    public void brakemodeDriveMotors(boolean pBreak){
		driveMotorL1.enableBrakeMode(pBreak);
		driveMotorL2.enableBrakeMode(pBreak);
		driveMotorR1.enableBrakeMode(pBreak);
		driveMotorR2.enableBrakeMode(pBreak);
	}
	
	public void resetStats(){
		resetStatsCT(ctl);
		resetStatsCT(ctr);
	}
	
	void resetStatsCT(ControlTracker ct){
		ct.encoder.reset();
		ct.deltaTicksPerSec = 0;
		ct.ticksToStop = 0;
		ct.ticksToGo = 0;
		ct.maxTicksPerSec = 0;
		ct.currentFinalTickCount = 0;
		ct.direction = 1;
		ct.finalInchesToTravel=0;

		ct.lastMotorSetValue = 0;
		ct.lastTickCount = 0;
		ct.lastTicksPerSec = 0;
		ct.currentTickCount = 0;
		ct.currentTicksPerSec = 0;
		ct.desiredTicksPerSec = 0;
		for (int i = 0;i<ct.averageBucketCount;i++)
			ct.runningAverage[i] = 0;
		ct.averageTicksPerSec = 0;
		ct.index = 0;
		ct.desiredInchesPerSec = 0;
	}
	
	public void updateStatus(){
		updateStatusCT(ctl);
		updateStatusCT(ctr);
	}
	
	void updateStatusCT(ControlTracker ct){
		SmartDashboard.putNumber(ct.desc+"DriveMotor", ct.motor.get());
		SmartDashboard.putNumber(ct.desc+"DriveEncoder", ct.encoder.getRaw());

		SmartDashboard.putNumber(ct.desc+"currentTicksPerSec", ct.currentTicksPerSec);
		SmartDashboard.putNumber(ct.desc+"lastMotorSetValue", ct.lastMotorSetValue);
		SmartDashboard.putNumber(ct.desc+"deltaTicksPerSec", ct.deltaTicksPerSec);
		SmartDashboard.putNumber(ct.desc+"ticksToStop", ct.ticksToStop);
		SmartDashboard.putNumber(ct.desc+"ticksToGo", ct.ticksToGo);
		SmartDashboard.putNumber(ct.desc+"maxTicksPerSec", ct.maxTicksPerSec);

		SmartDashboard.putNumber(ct.desc+"currentFinalTickCount", ct.currentFinalTickCount);
		SmartDashboard.putNumber(ct.desc+"direction", ct.direction);
		SmartDashboard.putNumber(ct.desc+"inchesPerSec", ct.currentTicksPerSec/ct.ticksPerInch);
		SmartDashboard.putNumber(ct.desc+"currentPosInInches", (double)ct.encoder.getRaw()/ct.ticksPerInch);
		SmartDashboard.putNumber(ct.desc+"desiredInchesPerSec", ct.desiredInchesPerSec);
		SmartDashboard.putNumber(ct.desc+"desiredTicksPerSec", ct.desiredTicksPerSec);
		SmartDashboard.putNumber(ct.desc+"averageTicksPerSec", ct.averageTicksPerSec);
	}
}

class ControlTracker {
	ControlTracker(Encoder e, String d, CANTalon c, boolean bMotorFlip, double pTicksPerInch){
		encoder = e;
		desc = d;
		motor = c;
		if (bMotorFlip)
			motorFlip = -1;
		ticksPerInch = pTicksPerInch;
	}
	double motorFlip = 1;
	CANTalon motor;
	String desc;
	Encoder encoder;
    double deltaTicksPerSec;
    double desiredInchesPerSec;
    double desiredTicksPerSec;
    final int averageBucketCount = 5; 
    double[] runningAverage = new double[averageBucketCount];
    double averageTicksPerSec = 0;
    int index = 0;
    double ticksToStop;
    double ticksToGo;
    double maxTicksPerSec;
    double currentFinalTickCount;
    double direction = 1;
    double finalInchesToTravel=0;
	double ticksPerInch; //3110 ticks per revolution/24 inch wheel circumference
    double lastMotorSetValue = 0;
    double lastTickCount = 0;
    double lastTimestamp = 0;
    double lastTicksPerSec = 0;
    double currentTickCount = 0;
    double currentTimestamp = 0;
    double currentTicksPerSec = 0;
}
