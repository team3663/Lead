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
	
	public int finalTicksL, finalTicksR;
	public double speedR, speedL;
	public boolean encoderDriving = false;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_ArcadeDrive());
    }
    
    public SSDriveTrain()
    {
    	driveMotorL1 = new CANTalon(10);
    	driveMotorL2 = new CANTalon(11);
    	driveMotorR1 = new CANTalon(20);
    	driveMotorR2 = new CANTalon(21);
    	
    	chassis = new RobotDrive(driveMotorL1, driveMotorL2, driveMotorR1, driveMotorR2);
    	
    	leftEncoder = new Encoder(3,4);
    	rightEncoder = new Encoder(5,6);
    	
    	System.out.println("SSDriveTrain created");
    }
    
    public void arcadeDrive(double yDirection, double xDirection)
    {
    	chassis.arcadeDrive(yDirection, xDirection);;
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
    public boolean driveForwardDistance(){
    	if(leftEncoder.get() >= finalTicksL){
    		return true;
    	}
    	else if(rightEncoder.get() >= finalTicksR){
    		return true;
    	}
    	return false;
    }
    
    /*NOTES FOR DISTANCE
    /* cicumfrance of the wheel is 12.56inch
    /* one revolution of the wheels is 250ticks
    /* this makes it so that there is aproximently 19 ticks per inch
    /* distance between the wheels 26 inches
     */
    public void setFinalLeft(int pDistance){
    	finalTicksL = ((int)(/*250/(4*Math.PI)*/19 * pDistance) + leftEncoder.get());
    }
    
    public void setFinalRight(int pDistance){
    	finalTicksR = ((int)(/*250/(4*Math.PI)*/19 * pDistance) + leftEncoder.get());
    }
    
    public void eDistanceArk(int pRadius, int pAngel){
    	/*NOTES ON THIS METHOD: 
    	 * (Length = (angle/360)*(2piR))
    	 * (Angle = (360*Length)/(2piR))
    	 * these numbers are multiplyed by ten to make sure there are no doubles in this numbers*/
    	setFinalLeft((int)((pAngel*10/360)*(2*Math.PI*(pRadius - 13))/10));
    	setFinalRight((int)((pAngel*10/360)*(2*Math.PI*(pRadius + 13))/10));
    }
    
    public void zeroMotors(){
    	driveMotorL1.set(0);
    	driveMotorL2.set(0);
    	driveMotorR1.set(0);
    	driveMotorR2.set(0);
    }
    
    public void breakmodeDriveMotors(boolean pBreak){
    	driveMotorL1.enableBrakeMode(pBreak);
    	driveMotorL2.enableBrakeMode(pBreak);
    	driveMotorR1.enableBrakeMode(pBreak);
    	driveMotorR2.enableBrakeMode(pBreak);
    }
    
    public void setTheSpeedsLeft(double highspeed){
    	/*NOTES
    	 *RT = D */
    	speedR = highspeed;
    	float time = (float)(finalTicksR/speedR);
    	speedL = finalTicksL/time;
    	
    }
    
    public boolean checkIfLeftDone(){
    	return (leftEncoder.get() >= finalTicksL);
    }

    public boolean checkIfRightDone(){
    	return (rightEncoder.get() >= finalTicksR);
    }
}


