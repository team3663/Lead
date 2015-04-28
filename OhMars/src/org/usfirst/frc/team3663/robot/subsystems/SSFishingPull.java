package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SSFishingPull extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public boolean down = false;
	public DoubleSolenoid armSolenoid;
	
	public SSFishingPull(){
		armSolenoid = new DoubleSolenoid(4, 5);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSolenoid(boolean pState){
    	down = pState;
    	if(pState){
    		armSolenoid.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		armSolenoid.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void toggleArm(){
    	setSolenoid(!down);
    }
}

