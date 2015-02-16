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
	
	
	private int finalTicksL;
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
    
    public void arcadeDrive(Joystick driveStick)
    {
    	chassis.arcadeDrive(driveStick);
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
    	driveMotorR2.set(speed);
    	driveMotorR1.set(speed);
    }
    
    public void motorLeftSet(double speed){
    	driveMotorL2.set(speed);
    	driveMotorL1.set(speed);
    }
    
    public void driveForwardDistance(double speed, int inches){
    	if(encoderDriving == false){
        	finalTicksL = (((360/4)*inches) + leftEncoder.get());
    	}
    	if(speed < 0){
    		while(finalTicksL > leftEncoder.get()){
    			motorRightSet(speed);
    			motorLeftSet(speed);
    		}
    	}
    	else if(speed > 0){
    		while(finalTicksL < leftEncoder.get()){
    			motorRightSet(speed);
    			motorLeftSet(speed);
    		}
    	}
    }
}

