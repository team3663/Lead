package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CG_Autonomous3Totes extends CommandGroup {
    public  CG_Autonomous3Totes() {
    	SmartDashboard.putString("ssAutonomousCG", "start");
    	addSequential(new C_ElevMoveAndSetZero());
    	
    	addSequential(new CG_ArmGrabbingSequence());
    	addParallel(new CG_PickUp());
    	
    	addSequential(new CG_ArmGrabbingSequence());
    	addParallel(new CG_PickUp());
    	addSequential(new C_EncoderDriveStraight(81, .7));
    	
    	addSequential(new CG_ArmGrabbingSequence());
    	addParallel(new CG_PickUp());
    	addSequential(new C_EncoderDriveStraight(81, .7));
    	
    	addSequential(new C_EncoderTurn(0,90, true, .7));
    	addSequential(new C_EncoderDriveStraight(108, .7));
    	addSequential(new C_ElevMoveToPos(-1));
    	addSequential(new C_EncoderDriveStraight(-24, .7));
    	//addSequential(new C_ElevMoveToPos(-20));
    	SmartDashboard.putString("ssAutonomousCG", "end");
        
    }
}
