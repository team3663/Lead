package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ArcadeDrive;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class SSDriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon driveMotorL1, driveMotorL2, driveMotorR1, driveMotorR2;
	public Encoder leftEncoder, rightEncoder;
	RobotDrive chassis;
	
	
	public int finalTicksR, finalTicksL, timeRunningR = 0, timeRunningL = 0, diffTicksR = 0, diffTicksL = 0;
	public double speedR, speedL;
	public boolean encoderDriving = false;
    public int lastDistanceR;
    public int lastDistanceL;
    public Gyro theG;
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_ArcadeDrive());
    }
    
    public SSDriveTrain()
    {
    	//Timer.getFPGATimestamp();
    	theG = new Gyro(0);
    	driveMotorL1 = new CANTalon(10);
    	/*11 and 21 are not connected*/
    	//driveMotorL2 = new CANTalon(11);
    	driveMotorR1 = new CANTalon(20);
    	//driveMotorR2 = new CANTalon(21);
    	
    	//chassis = new RobotDrive(driveMotorL1, driveMotorL2, driveMotorR1, driveMotorR2);
    	chassis = new RobotDrive(driveMotorL1, driveMotorR1);
    	
    	leftEncoder = new Encoder(3,4);
    	rightEncoder = new Encoder(5,6);
    	lastDistanceL = rightEncoder.get();
    	lastDistanceR = leftEncoder.get();
    }
    
    public void arcadeDrive(double pYDirection, double pXDirection)
    {
    	chassis.arcadeDrive(pYDirection, pXDirection);
    }
    
    public void motor1Set(double pSpeed)
    {
    	driveMotorL1.set(pSpeed);
    }
    
    

    public void motor3Set(double pSpeed)
    {
    	driveMotorR1.set(pSpeed);
    }

    
    //the speed of the motors are negative for the reason that
    //the right reversed from 
    public void motorRightSet(double pSpeed){
    	//driveMotorR2.set(-speed);
    	driveMotorR1.set(-pSpeed);
    }
    
    public void motorLeftSet(double pSpeed){
    	//driveMotorL2.set(speed);
    	driveMotorL1.set(pSpeed);
    }
    
    /**all of the encoder driving stuff**/
    public boolean driveForwardDistance(double pSpeed)
    {
    	int leftEncoderTicks = leftEncoder.get(), rightEncoderTicks = rightEncoder.get();
    	if(leftEncoderTicks >= finalTicksL && pSpeed >= 0)
    	{
    		return true;
    	}
    	else if(rightEncoderTicks >= finalTicksR && pSpeed >= 0)
    	{
    		return true;
    	}
    	else if(leftEncoderTicks <= finalTicksL && pSpeed <= 0)
    	{
    		return true;
    	}
    	else if(rightEncoderTicks <= finalTicksR && pSpeed <= 0)
    	{
    		return true;
    	}
    	return false;
    }
    
    /*NOTES FOR DISTANCE
    /* circumference of the wheel is 12.56inch
    /* one revolution of the wheels is 250ticks
    /* this makes it so that there is approximately 19 ticks per inch
    /* distance between the wheels 26 inches
     */
    public void setFinalLeft(int pDistance)// this is in inches
    {
    	finalTicksL = ((int)(/*250/(4*Math.PI) = */19 * pDistance) + leftEncoder.get());
    	//250 are ticks per revolution the wheels are 4" diameter
    }
    
    public void setFinalRight(int pDistance)// this is in inches
    {
    	finalTicksR = ((int)(/*250/(4*Math.PI) = */19 * pDistance) + rightEncoder.get());
    	//250 are ticks per revolution the wheels are 4" diameter
    }
    
    public void eDistanceArc(int pRadius, int pAngle, boolean turnLeft){
    	/*NOTES ON THIS METHOD: 
    	 * (Length = (angle/360)*(2piR))
    	 * (Angle = (360*Length)/(2piR))
    	 * these numbers are multiplied by ten to make sure there are no doubles in this numbers*/
    	if(turnLeft){
	    	setFinalLeft((int)((pAngle*10/360)*(2*Math.PI*(pRadius - 13))/10));
	    	setFinalRight((int)((pAngle*10/360)*(2*Math.PI*(pRadius + 13))/10));
    	}
    	else{
	    	setFinalLeft((int)((pAngle*10/360)*(2*Math.PI*(pRadius + 13))/10));
	    	setFinalRight((int)((pAngle*10/360)*(2*Math.PI*(pRadius - 13))/10));
    	}
    }
       
    public void breakmodeDriveMotors(boolean pBreak){
    	driveMotorL1.enableBrakeMode(pBreak);
    	//driveMotorL2.enableBrakeMode(pBreak);
    	driveMotorR1.enableBrakeMode(pBreak);
    	//driveMotorR2.enableBrakeMode(pBreak);
    }
    
    public void setTheSpeeds(double pHighSpeed){
    	/*NOTES
    	 *RT = D */
    	if(diffTicksR > diffTicksL)
    	{
        	speedR = pHighSpeed;
        	float time = (float)(diffTicksR/speedR);
        	speedL = diffTicksL/time;
    	}
    	else{
        	speedL = pHighSpeed;
        	float time = (float)(diffTicksL/speedL);
        	speedR = diffTicksR/time;
    	}
    	//speedL = .4;
    }
    
    public boolean checkIfLeftDone(){
    	if(speedL > 0){
        	return (leftEncoder.get() >= finalTicksL);
    	}
    	else if(speedL < 0){
        	return (leftEncoder.get() <= finalTicksL);
    	}
    	return false;
    }

    public boolean checkIfRightDone(){
    	if(speedR > 0){
        	return (rightEncoder.get() >= finalTicksR);
    	}
    	else if(speedR < 0){
        	return (rightEncoder.get() <= finalTicksR);
    	}
    	return false;
    }
    
    public void difference(){
    	diffTicksR = finalTicksR - rightEncoder.get();
    	diffTicksL = finalTicksL - leftEncoder.get();
    }  
    
    public boolean rampDown(double pNumberMultiplied)
    {
    	int currentDistanceR = rightEncoder.get(), currentDistanceL = leftEncoder.get(); 
    	if(!(lastDistanceR < 10 && lastDistanceR > 10) && diffTicksR > diffTicksL)
    	{
    		if(speedR > 0 && ((currentDistanceR - lastDistanceR)*pNumberMultiplied >= diffTicksR))
    		{
    			setTheSpeeds(speedR - .1);
    			//speedR -= .1;
    		}
    		else if(speedR < 0 && ((currentDistanceR - lastDistanceR)*pNumberMultiplied <= diffTicksR))
    		{
    			setTheSpeeds(speedR + .1);
    			//speedR += .1;
    		}
    		lastDistanceR = currentDistanceR;
    		lastDistanceL = currentDistanceL;
    		return false;
    	}
    	else if(!(lastDistanceL < 10 && lastDistanceL > 10) && diffTicksR < diffTicksL)
    	{
    		if(speedL > 0 && ((currentDistanceL - lastDistanceL)*pNumberMultiplied >= diffTicksL))
    		{
    			setTheSpeeds(speedR - .1);
    			//speedL -= .1;
    		}
    		else if(speedL < 0 && ((currentDistanceL - lastDistanceL)*pNumberMultiplied <= diffTicksL))
    		{
    			setTheSpeeds(speedR + .1);
    			//speedL += .1;
    		}
    		lastDistanceR = currentDistanceR;
    		lastDistanceL = currentDistanceL;
    		return false;
    	}
		lastDistanceR = currentDistanceR;
		lastDistanceL = currentDistanceL;
    	return true;
    }
    
    public void rampUp(double pRampUpSpeed, double pTopSpeed)
    {
    	if(!rampDown(4) && (speedL < pTopSpeed || speedR < pTopSpeed))
    	{
    		if(diffTicksR > diffTicksL)
    		{
    			speedR += pRampUpSpeed;
    			setTheSpeeds(speedR);    			
    		}
    		else 
    		{
    			speedL += pRampUpSpeed;
    			setTheSpeeds(speedL);    	
    		}
    	}
    }
    
    /*this is not used in the current build of the Bot (we need to have a gyro)
     * NOTES
     * -> as the gyro turns right it goes positive*/
    public int gyroFinal;
    public boolean negative;
       public void setFinalGyro(int pAngle){
       		gyroFinal = (int)(pAngle + theG.getAngle());
       		if(pAngle > 0){
       			negative = false;
       		}
       		else{
       		negative = true;
       		}
       }
       
     public boolean CheckGyro(){
    	if(theG.getAngle() > gyroFinal && !negative){
    		return true;
    	}
    	else if(theG.getAngle() < gyroFinal && negative){
    		return true;
    	}
    	return false;
    }
    public void updateStatus(){
    	//SmartDashboard.putNumber("Gyro Angle", Robot.ssDriveTrain.theG.getAngle());
    	SmartDashboard.putNumber("DriveMotorL1", Robot.ssDriveTrain.driveMotorL1.get());
    	SmartDashboard.putNumber("DriveMotorR1", Robot.ssDriveTrain.driveMotorR1.get());
    	SmartDashboard.putNumber("DriveEncoderL", Robot.ssDriveTrain.leftEncoder.get());
    	SmartDashboard.putNumber("DriveEncoderR", Robot.ssDriveTrain.rightEncoder.get());
    	SmartDashboard.putNumber("DriveMotorL1Draw", Robot.ssDriveTrain.driveMotorL1.getOutputCurrent());
    	SmartDashboard.putNumber("DriveMotorR1Draw", Robot.ssDriveTrain.driveMotorR1.getOutputCurrent());
    }
    
    public int finalTime;
    public void setEndTime(int seconds){
    	finalTime = (int)(seconds + Timer.getFPGATimestamp());
    }
    
    public boolean CheckFinalTime(){
    	if(finalTime < Timer.getFPGATimestamp())
    	{
    		return true;
    	}
    	return false;
    }
}

