package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSArmsSolenoids extends Subsystem {
    public boolean armLIsClosed = false;
    public boolean armRIsClosed = false;
	DoubleSolenoid armOpenCloseL, armOpenCloseR;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new C_ArmsUpDown());
    }
    public SSArmsSolenoids(){
    	armOpenCloseL= new DoubleSolenoid(7,6);
    	armOpenCloseR = new DoubleSolenoid(1,0);
    	armLOpen();
    	armROpen();
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
    public void updateStatus(){
    	if(armRIsClosed){
        	SmartDashboard.putString("ArmSolenoidR", "close");
    	}else{
    		SmartDashboard.putString("ArmSolenoidR", "open");
    	}
    	if(armLIsClosed){
        	SmartDashboard.putString("ArmSolenoidL", "close");
    	}else{
    		SmartDashboard.putString("ArmSolenoidL", "open");
    	}
    }
}
