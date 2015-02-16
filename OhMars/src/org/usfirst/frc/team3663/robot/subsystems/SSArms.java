package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.commands.C_ArmsUpDown;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SSArms extends Subsystem {
    public boolean lArmClose = false;
    public boolean rArmClose = false;
    public boolean manualControlOC = true;
    public boolean manualControlUD = true;
    public boolean manualControlIM = true;
    
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
    	armsUpAndDownMotorL = new Talon(1);
    	armsUpAndDownMotorR = new Talon(2);
    	armOpenCloseL= new DoubleSolenoid(7,6);
    	armOpenCloseR = new DoubleSolenoid(1,0);
    	armLOpen();
    	armROpen();
    }
    
    public void intakeMotorsSet(double speed){
    	intakeMotorL.set(-speed);
    	intakeMotorR.set(speed);
    }
    public void intakeMotorLSet(double speed)
    {
    	intakeMotorL.set(-speed);
    }
    public void intakeMotorRSet(double speed)
    {
    	intakeMotorR.set(speed);
    }
    public void armUpDownLSet(double speed){
    	armsUpAndDownMotorL.set(speed);
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
