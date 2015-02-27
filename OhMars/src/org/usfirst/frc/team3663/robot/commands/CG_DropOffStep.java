package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ElevMoveToPos;
import org.usfirst.frc.team3663.robot.commands.C_ForkOut;

/**
 *
 */
public class CG_DropOffStep extends CommandGroup {
    
    public  CG_DropOffStep() {
    	SmartDashboard.putString("ssElevatorCG", "CG_DropOffStep start");
    	addSequential(new C_ElevMoveToPos(Robot.ssElevator.onStepPos));
    	addSequential(new C_ForkOut(true, 7));
    	addSequential(new C_ElevMoveToPos(Robot.ssElevator.lowStepPos));
    	addSequential(new C_ForkOut(false, 7));
    	addSequential(new C_ElevMoveToPos(Robot.ssElevator.noTotePos));
    	SmartDashboard.putString("ssElevatorCG", "CG_DropOffStep end");
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
