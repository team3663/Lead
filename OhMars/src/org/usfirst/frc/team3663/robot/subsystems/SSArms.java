package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SSArms extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon intakeMotor1, intakeMotor2, armsUpAndDownMotor1, armsUpAndDownMotor2;
	DoubleSolenoid armOpenClose1, armOpenClose2;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public SSArms(){
    	intakeMotor1 = new Talon(0);
    	intakeMotor2 = new Talon(3);
    	armsUpAndDownMotor1 = new Talon(1);
    	armsUpAndDownMotor2 = new Talon(2);
    	
    	armOpenClose2 = new DoubleSolenoid(0,1);
    	armOpenClose1 = new DoubleSolenoid(6,7);
    }
    
    public void intakeMotorsSet(double speed){
    	intakeMotor1.set(speed);
    	intakeMotor2.set(-speed);
    }
    public void intakeMotor1Set(double speed)
    {
    	intakeMotor1.set(speed);
    }
    public void intakeMotor2Set(double speed)
    {
    	intakeMotor2.set(speed);
    }
    public void arm1UpDown(double speed){
    	armsUpAndDownMotor1.set(speed);
    }
    public void arm2UpDown(double speed){
    	armsUpAndDownMotor2.set(speed);
    }
    public void arm1OutIn(boolean in){
    	if(in){
    		armOpenClose1.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		armOpenClose1.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    public void arm2OutIn(boolean in){
    	if(in){
    		armOpenClose2.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		armOpenClose2.set(DoubleSolenoid.Value.kReverse);
    	}
    }
}

