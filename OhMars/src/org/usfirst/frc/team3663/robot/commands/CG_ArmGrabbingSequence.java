package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_ArmGrabbingSequence extends CommandGroup {
    public  CG_ArmGrabbingSequence() {
    	addParallel(new C_ArmsIntakeSet(1.0,1.0));
    	addParallel(new C_ArmsOpenCloseTogether(false));
    	addSequential(new C_WaitForToteSensor());
		addParallel(new C_ArmsIntakeSet(0.0,0.0));
		addParallel(new C_ArmsOpenCloseTogether(true));
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
