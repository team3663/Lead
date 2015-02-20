package org.usfirst.frc.team3663.robot.subsystems;


import org.usfirst.frc.team3663.robot.commands.C_AutonomousMasterChoosing;

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
   // 	SmartDashboard.putBoolean("TopAuto", getTopSwitch());
    	return topAutoSwitch.get();
    }
    
    public boolean getBottomSwitch(){
//    	SmartDashboard.putBoolean("BottomAuto", getBottomSwitch());
    	return bottomAutoSwitch.get();
    }
    public int getAutonomousRoutine(){
    	if(!getTopSwitch()){
    		if(!getBottomSwitch()){
    			return 0;
    		}
    		return 1;
    	}else{
    		if(!getBottomSwitch()){
    			return 2;
    		}
    		return 3;
    	}
    	/*****************************
    	 * 0 = top FALSE, bottom FALSE
    	 * 1 = top FALSE, bottom TRUE
    	 * 2 = top TRUE , bottom FALSE
    	 * 3 = top TRUE , bottom TRUE
    	 *****************************/
    	/*****************************
    	 * IN ENGLISH
    	 * 0 = top DOWN, bottom DOWN
    	 * 1 = top DOWN, bottom UP
    	 * 2 = top UP  , bottom DOWN
    	 * 3 = top UP  , bottom UP
    	 *****************************/
    }
}

