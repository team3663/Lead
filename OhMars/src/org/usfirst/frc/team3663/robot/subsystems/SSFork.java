package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import org.usfirst.frc.team3663.robot.commands.C_ManualForkInAndOut;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class SSFork extends Subsystem {
    
    Talon fork;
    
    double speed = 0;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new C_ManualForkInAndOut());
    }
    
    public SSFork()
    {
    	fork = new Talon(4);
    }
    
    public void set(double pSpeed)
    {
    	fork.set(pSpeed);
    }
    
    public void moveOut(boolean movingOut)
    {
    	if (movingOut)
    	{
    		speed = 1;
    	}
    	else
    	{
    		speed = -1;
    	}
    	set(speed);
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("ForkSpeed", Robot.ssFork.fork.get());
    }
}

