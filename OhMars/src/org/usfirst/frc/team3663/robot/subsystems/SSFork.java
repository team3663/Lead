package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
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
}

