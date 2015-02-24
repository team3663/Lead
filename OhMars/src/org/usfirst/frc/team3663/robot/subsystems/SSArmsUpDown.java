package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ArmsUpDown2;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSArmsUpDown extends Subsystem {
    public double lArmUpDownPos = 0;
    public double rArmUpDownPos = 0;
    public double lastTimeLArmUD = 0;
    public double lastSpeedLArmUD = 0;
    public double lastTimeRArmUD = 0;
    public double lastSpeedRArmUD = 0;
    
	Talon armsUpAndDownMotorL, armsUpAndDownMotorR;
    public void initDefaultCommand() {
        setDefaultCommand(new C_ArmsUpDown2());
    }
    public SSArmsUpDown(){
    	armsUpAndDownMotorL = new Talon(2);
    	armsUpAndDownMotorR = new Talon(1);
    }
    public void armUpDownLSet(double speed){
    	double currentTime = Timer.getFPGATimestamp();
    	double deltaTime = currentTime - lastTimeLArmUD;
    	double scaleFactor = 1;
    	if(lastSpeedLArmUD < 0) scaleFactor = 1.228;/*SmartDashboard.getNumber("L_ArmScaleFactor");*/
    	//Higher sF if pos reads higher consistently
    	//Use a Lower sF is pos reads as too low after moving
    	lArmUpDownPos += deltaTime * lastSpeedLArmUD*scaleFactor;
    	lastTimeLArmUD = currentTime;
    	lastSpeedLArmUD = speed;
    	armsUpAndDownMotorL.set(speed);
    	
    	//SmartDashboard.putNumber("LeftArmPos", lArmUpDownPos);
    }
    public void armUpDownRSet(double speed){
    	double currentTime = Timer.getFPGATimestamp();
    	double deltaTime = currentTime - lastTimeRArmUD;
    	double scaleFactor = 1;
    	if(lastSpeedLArmUD < 0) scaleFactor = 1.228;/*SmartDashboard.getNumber("R_ArmScaleFactor");*/
    	//Higher sF if pos reads higher consistently
    	//Use a Lower sF is pos reads as too low after moving
    	rArmUpDownPos += deltaTime * lastSpeedRArmUD*scaleFactor;
    	lastTimeRArmUD = currentTime;
    	lastSpeedRArmUD = speed;
    	armsUpAndDownMotorR.set(speed);

    	//SmartDashboard.putNumber("RightArmPos", rArmUpDownPos);
    }
    public void updateStatus(){
    	SmartDashboard.putNumber("ArmUpDownL", Robot.ssArmsUpDown.armsUpAndDownMotorR.get());
    	SmartDashboard.putNumber("ArmUpDownR", Robot.ssArmsUpDown.armsUpAndDownMotorR.get());
    }
}
