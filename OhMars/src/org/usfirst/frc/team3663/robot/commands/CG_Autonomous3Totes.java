package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Autonomous3Totes extends CommandGroup {
    public  CG_Autonomous3Totes() {
    	addSequential(new C_ResetRuns());//don't need this?
    	addSequential(new C_ElevMoveAndSetZero());
    	
    	addSequential(new CG_ArmGrabbingSequence());
    	addParallel(new CG_PickUp());
    	addSequential(new C_EncoderDriveStrait(81));
    	
    	addSequential(new CG_ArmGrabbingSequence());
    	addParallel(new CG_PickUp());
    	addSequential(new C_EncoderDriveStrait(81));
    	
    	addSequential(new CG_ArmGrabbingSequence());
    	addParallel(new CG_PickUp());
    	addSequential(new C_EncoderDriveStrait(81));
    	
    	addSequential(new C_EncoderTurnLeft(0,90));
    	addSequential(new C_EncoderDriveStrait(108));
    	addSequential(new C_ElevMoveToPos(-1));
    	//addSequential(new C_EncoderDriveStrait(-24)); commented out b/c can't drive backwards...
    	//addSequential(new C_ElevMoveToPos(-20));
        
    }
}
