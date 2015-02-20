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
    public boolean armLIsClosed = false;
    public boolean armRIsClosed = false;
    public double lArmIntakeSpeed = 0.0;
    public double rArmIntakeSpeed = 0.0;

    public double lArmUpDownPos = 0;
    public double rArmUpDownPos = 0;
    public double lastTimeLArmUD = 0;
    public double lastSpeedLArmUD = 0;
    public double lastTimeRArmUD = 0;
    public double lastSpeedRArmUD = 0;
    
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
    	if(lastSpeedLArmUD < 0) scaleFactor = 1.228;/*SmartDashboard.getNumber("L_ArmScaleFactor");*/
    	//Higher sF if pos reads higher consistenly
    	//Use a Lower sF is pos reads as too low after moving
    	lArmUpDownPos += deltaTime * lastSpeedLArmUD*scaleFactor;
    	lastTimeLArmUD = currentTime;
    	lastSpeedLArmUD = speed;
    	armsUpAndDownMotorL.set(speed);
    	
    	SmartDashboard.putNumber("LeftArmPos", lArmUpDownPos);
    }
    public void armUpDownRSet(double speed){
    	double currentTime = Timer.getFPGATimestamp();
    	double deltaTime = currentTime - lastTimeRArmUD;
    	double scaleFactor = 1;
    	if(lastSpeedLArmUD < 0) scaleFactor = 1.228;/*SmartDashboard.getNumber("R_ArmScaleFactor");*/
    	//Higher sF if pos reads higher consistenly
    	//Use a Lower sF is pos reads as too low after moving
    	rArmUpDownPos += deltaTime * lastSpeedRArmUD*scaleFactor;
    	lastTimeRArmUD = currentTime;
    	lastSpeedRArmUD = speed;
    	armsUpAndDownMotorR.set(speed);

    	SmartDashboard.putNumber("RightArmPos", rArmUpDownPos);
    }
    public void armLClose(){
    	//if(!lArmClose){
    		armOpenCloseL.set(DoubleSolenoid.Value.kForward);
    		armLIsClosed = true;
    	//}
    }
	public void armLOpen(){
		//if(lArmClose){
    		armOpenCloseL.set(DoubleSolenoid.Value.kReverse);
    		armLIsClosed = false;
    	//}
    }
    public void armRClose(){
    	//if(!rArmClose){
    		armOpenCloseR.set(DoubleSolenoid.Value.kForward);
    		armRIsClosed = true;
    	//}
    }
	public void armROpen(){
		//if(rArmClose){
    		armOpenCloseR.set(DoubleSolenoid.Value.kReverse);
    		armRIsClosed = false;
    	//}
    }
}
