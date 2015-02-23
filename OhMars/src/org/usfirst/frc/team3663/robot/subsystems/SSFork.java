package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class SSFork extends Subsystem {
    
    Talon fork;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public SSFork()
    {
    	fork = new Talon(4);
    }
    
    public void set(double speed)
    {
    	fork.set(speed);
    }
    public void updateStatus(){
    	SmartDashboard.putNumber("ForkSpeed", Robot.ssFork.fork.get());
    }
}

