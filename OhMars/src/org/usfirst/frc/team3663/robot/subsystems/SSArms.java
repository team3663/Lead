package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.commands.C_ArmsUpDown;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSArms extends Subsystem {
    public boolean lArmClose = false;
    public boolean rArmClose = false;
    public double lArmIntakeSpeed = 0.0;
    public double rArmIntakeSpeed = 0.0;
    
    public double lArmUpDownPos = 0;
    public double rArmUpDownPos = 0;
    public double lastTimeLArmUD = 0;
    public double lastSpeedLArmUD = 0;
    
	Talon intakeMotorL, intakeMotorR, armsUpAndDownMotorL, armsUpAndDownMotorR;
	DoubleSolenoid armOpenCloseL, armOpenCloseR;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new C_ArmsUpDown());
    }
    public SSArms(){
    	intakeMotorL = new Talon(3);
    	intakeMotorR = new Talon(0);
    	armsUpAndDownMotorL = new Talon(2);
    	armsUpAndDownMotorR = new Talon(1);
    	armOpenCloseL= new DoubleSolenoid(7,6);
    	armOpenCloseR = new DoubleSolenoid(1,0);
    	armLOpen();
    	armROpen();
    }
    
    public void intakeMotorsSet(double speed){
    	intakeMotorL.set(-speed);
    	intakeMotorR.set(speed);
        lArmIntakeSpeed = -speed;
        rArmIntakeSpeed = speed;
    }
    public void intakeMotorLSet(double speed)
    {
    	intakeMotorL.set(-speed);
        lArmIntakeSpeed = -speed;
    }
    public void intakeMotorRSet(double speed)
    {
    	intakeMotorR.set(speed);
        rArmIntakeSpeed = speed;
    }
    public void armUpDownLSet(double speed){
    	double currentTime = Timer.getFPGATimestamp();
    	double deltaTime = currentTime - lastTimeLArmUD;
    	double scaleFactor = 1;
    	SmartDashboard.putNumber("L_ArmScaleFactor", 1.135);
    	if(lastSpeedLArmUD < 0) scaleFactor = SmartDashboard.getNumber("L_ArmScaleFactor");
    	//Higher sF if pos reads higher consistenly
    	//Use a Lower sF is pos reads as too low after moving
    	lArmUpDownPos += deltaTime * lastSpeedLArmUD*scaleFactor;
    	lastTimeLArmUD = currentTime;
    	lastSpeedLArmUD = speed;
    	armsUpAndDownMotorL.set(speed);
    	
    	SmartDashboard.putNumber("LeftArmPos", lArmUpDownPos);
    	SmartDashboard.putNumber("LeftArmSpeed", speed);
    	SmartDashboard.putNumber("LeftArmLastSpeed", lastSpeedLArmUD);
    	SmartDashboard.putNumber("deltaTime", deltaTime);
    	
    }
    public void armUpDownRSet(double speed){
    	armsUpAndDownMotorR.set(speed);
    }
    public void armLClose(){
    	if(!lArmClose){
    		armOpenCloseL.set(DoubleSolenoid.Value.kForward);
    		lArmClose = true;
    	}
    }
	public void armLOpen(){
		if(lArmClose){
    		armOpenCloseL.set(DoubleSolenoid.Value.kReverse);
    		lArmClose = false;
    	}
    }
    public void armRClose(){
    	if(!rArmClose){
    		armOpenCloseR.set(DoubleSolenoid.Value.kForward);
    		rArmClose = true;
    	}
    }
	public void armROpen(){
		if(rArmClose){
    		armOpenCloseR.set(DoubleSolenoid.Value.kReverse);
    		rArmClose = false;
    	}
    }
    public void toggleArmLOpenClose(){
    	if(lArmClose){
    		armLOpen();
    	}
    	else{
    		armLClose();
    	}
    }
    public void toggleArmROpenClose(){
    	if(rArmClose){
    		armROpen();
    	}
    	else{
    		armRClose();
    	}
    }
}
