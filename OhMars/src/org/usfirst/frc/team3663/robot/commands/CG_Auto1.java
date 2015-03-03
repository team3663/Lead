package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto1 extends CommandGroup {
    
    public  CG_Auto1() {
        // Add Commands here:
    	addParallel(new CG_RestartToStartPos());
    	addSequential(new C_EncoderDriveStraight(3, .2));
    	addSequential(new C_ArmsOpenCloseTogether(false));
    	addParallel(new C_ArmsIntakeSet(1.0,1.0));
        addSequential(new C_EncoderDriveStraight(-84,.5));
    	addSequential(new C_ArmsOpenCloseTogether(true));
        addSequential(new C_EncoderDriveStraight(-5,.2));
        addSequential(new C_ArmsIntakeSet(0.0,0.0));
    	addParallel(new CG_RestartToStartPos());
        
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
