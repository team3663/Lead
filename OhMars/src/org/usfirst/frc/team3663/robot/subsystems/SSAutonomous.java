package org.usfirst.frc.team3663.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSAutonomous extends Subsystem {
    public DigitalInput topAutoSwitch, bottomAutoSwitch;
    
	protected void initDefaultCommand() {
	}
    public SSAutonomous(){
        topAutoSwitch = new DigitalInput(10);
        bottomAutoSwitch = new DigitalInput(11);
    }
	
    public boolean getTopSwitch(){
    	SmartDashboard.putBoolean("TopAuto", getTopSwitch());
    	return topAutoSwitch.get();
    }
    
    public boolean getBottomSwitch(){
    	SmartDashboard.putBoolean("BottomAuto", getBottomSwitch());
    	return bottomAutoSwitch.get();
    }
}

