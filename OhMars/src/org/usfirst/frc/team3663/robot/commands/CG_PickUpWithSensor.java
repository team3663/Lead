package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CG_PickUpWithSensor extends CommandGroup {
    
    public  CG_PickUpWithSensor() {
    	SmartDashboard.putString("ssElevatorCG", "CG_PickUpWithSensor start");
    	addSequential(new C_ResetRuns());
    	addSequential(new C_FindTote());
    	addSequential(new CG_PickUp());
    	SmartDashboard.putString("ssElevatorCG", "CG_PickUpWithSensor end");
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
