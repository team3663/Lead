package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.commands.A_Log;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSDashBoard extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new A_Log());
    }

	public void putDashNumber(String Title, double Number){
		SmartDashboard.putNumber(Title, Number);
	}
	public void putDashBool(String Title, boolean Bool){
		SmartDashboard.putBoolean(Title, Bool);
	}
	public void putDashString(String Title, String lable){
		SmartDashboard.putString(Title, lable);
	}
	
	
/**NOTE: you must initialize the value that you are trying to get
 * 		before hand. If you try and grab from a value that is not there
 * 		the code will crash (WIP: try and catch statements that will 
 * 		save from the end of the code. The return statements are
 * 		a little problematic due to the fact that there is error return
 * 		that does not crash the code.) 
 */
	@SuppressWarnings("deprecation")
	public double getFromDashNumber(String Title){
		try{
			return SmartDashboard.getNumber(Title);
		}
		catch(Exception E){
			putDashString("ERROR_INT", "Title" + Title);
			putDashNumber(Title, 0);
			return 0;
		}
	}
	public boolean getFromDashBool(String Title){
		try{
			return SmartDashboard.getBoolean(Title);
		}
		catch(Exception E){
			putDashString("ERROR_BOOL", "Title" + Title);
			putDashBool(Title, false);
			return false;
		}
	}
	public String getFromDashString(String Title){
		try{
			return SmartDashboard.getString(Title);
		}
		catch(Exception E){
			putDashString("ERROR_STRING", "Title" + Title);
			putDashString(Title, "ERROR");
			return "ERROR";
		}
	}
}

