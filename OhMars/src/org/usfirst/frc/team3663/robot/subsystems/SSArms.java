package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.commands.C_Arms;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SSArms extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Talon intakeMotorL, intakeMotorR, armsUpAndDownMotorL, armsUpAndDownMotorR;
	DoubleSolenoid armOpenCloseL, armOpenCloseR;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new C_Arms());
    }
    public SSArms(){
    	intakeMotorL = new Talon(3);
    	intakeMotorR = new Talon(0);
    	armsUpAndDownMotorL = new Talon(1);
    	armsUpAndDownMotorR = new Talon(2);
    	armOpenCloseL= new DoubleSolenoid(7,6);
    	armOpenCloseR = new DoubleSolenoid(1,0);
    }
    
    public void intakeMotorsSet(double speed){
    	intakeMotorL.set(speed);
    	intakeMotorR.set(-speed);
    }
    public void intakeMotorLSet(double speed)
    {
    	intakeMotorL.set(speed);
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
    public void armLClose(boolean close){
    	if(close){
    		armOpenCloseL.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		armOpenCloseL.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    public void armRClose(boolean close){
    	if(close){
    		armOpenCloseR.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		armOpenCloseR.set(DoubleSolenoid.Value.kReverse);
    	}
    }
}
