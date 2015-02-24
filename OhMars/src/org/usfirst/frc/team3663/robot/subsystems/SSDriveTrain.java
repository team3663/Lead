package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ArcadeDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

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

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_ArcadeDrive());
    }
    
    public SSDriveTrain()
    {
    	driveMotorL1 = new CANTalon(10);
    	/*11 and 21 are not connected*/
    	//driveMotorL2 = new CANTalon(11);
    	driveMotorR1 = new CANTalon(20);
    	//driveMotorR2 = new CANTalon(21);
    	
    	//chassis = new RobotDrive(driveMotorL1, driveMotorL2, driveMotorR1, driveMotorR2);
    	chassis = new RobotDrive(driveMotorL1, driveMotorR1);
    	
    	leftEncoder = new Encoder(3,4);
    	rightEncoder = new Encoder(5,6);
    }
    
    public void arcadeDrive(double yDirection, double xDirection)
    {
    	chassis.arcadeDrive(yDirection, xDirection);
    }
    
    public void motor1Set(double speed)
    {
    	driveMotorL1.set(speed);
    }
    
    public void motor2Set(double speed)
    {
    	driveMotorL2.set(speed);
    }

    public void motor3Set(double speed)
    {
    	driveMotorR1.set(speed);
    }

    public void motor4Set(double speed)
    {
    	driveMotorR2.set(speed);
    }
    
    public void motorRightSet(double speed){
    	driveMotorR2.set(-speed);
    	driveMotorR1.set(-speed);
    }
    
    public void motorLeftSet(double speed){
    	driveMotorL2.set(speed);
    	driveMotorL1.set(speed);
    }
    
    /**all of the encoder driving stuff**/
    public boolean driveForwardDistance(double speed)
    {
    	int le = leftEncoder.get(), re = rightEncoder.get();
    	if(le >= finalTicksL && speed >= 0)
    	{
    		return true;
    	}
    	else if(re >= finalTicksR && speed >= 0)
    	{
    		return true;
    	}
    	else if(le <= finalTicksL && speed <= 0)
    	{
    		return true;
    	}
    	else if(re <= finalTicksR && speed <= 0)
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
    	//driveMotorL1.enableBrakeMode(pBreak);
    	driveMotorL2.enableBrakeMode(pBreak);
    	// driveMotorR1.enableBrakeMode(pBreak);
    	driveMotorR2.enableBrakeMode(pBreak);
    }
    
    public void setTheSpeeds(double highspeed){
    	/*NOTES
    	 *RT = D */
    	if(diffTicksR > diffTicksL)
    	{
        	speedR = highspeed;
        	float time = (float)(diffTicksR/speedR);
        	speedL = diffTicksL/time;
    	}
    	else{
        	speedL = highspeed;
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
    	diffTicksR = rightEncoder.get() - finalTicksR;
    	diffTicksL = leftEncoder.get() - finalTicksL;
    }  
    
    public int lastDistanceR = leftEncoder.get();
    public int lastDistanceL = rightEncoder.get();
    
    public boolean rampDown(double numberMultiplied)
    {
    	int currentDistanceR = rightEncoder.get(), currentDistanceL = leftEncoder.get(); 
    	if(!(lastDistanceR < 10 && lastDistanceR > 10) && diffTicksR > diffTicksL)
    	{
    		if(speedR > 0 && ((currentDistanceR - lastDistanceR)*numberMultiplied >= diffTicksR))
    		{
    			setTheSpeeds(speedR - .1);
    			//speedR -= .1;
    		}
    		else if(speedR < 0 && ((currentDistanceR - lastDistanceR)*numberMultiplied <= diffTicksR))
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
    		if(speedL > 0 && ((currentDistanceL - lastDistanceL)*numberMultiplied >= diffTicksL))
    		{
    			setTheSpeeds(speedR - .1);
    			//speedL -= .1;
    		}
    		else if(speedL < 0 && ((currentDistanceL - lastDistanceL)*numberMultiplied <= diffTicksL))
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
    
    public void rampUp(double rampUpSpeed, double topSpeed)
    {
    	if(!rampDown(4) && (speedL < topSpeed || speedR < topSpeed))
    	{
    		if(diffTicksR > diffTicksL)
    		{
    			speedR += rampUpSpeed;
    			setTheSpeeds(speedR);    			
    		}
    		else 
    		{
    			speedL += rampUpSpeed;
    			setTheSpeeds(speedL);    	
    		}
    	}
    }
    public void updateStatus(){
    	SmartDashboard.putNumber("DriveMotorL1", Robot.ssDriveTrain.driveMotorL1.get());
    	SmartDashboard.putNumber("DriveMotorR1", Robot.ssDriveTrain.driveMotorR1.get());
    	SmartDashboard.putNumber("DriveEncoderL", Robot.ssDriveTrain.leftEncoder.get());
    	SmartDashboard.putNumber("DriveEncoderR", Robot.ssDriveTrain.rightEncoder.get());
    }
}

