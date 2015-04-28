package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoWithFishingPull extends CommandGroup {
    
    public  CG_AutoWithFishingPull() {
    	addSequential(new C_Raise_LowerFishingPull(true));
    	addSequential(new C_Delay(.75));
    	addSequential(new C_EncoderDriveStraight(30, 1));
    	//addSequential(new C_ElevMoveAndSetZero());
    }
}
