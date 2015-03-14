package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 ***PROOF OF CONCEPT RUN***
 */
public class CG_Autonomous3TotesPoC extends CommandGroup {
    public  CG_Autonomous3TotesPoC() {
    	SmartDashboard.putString("ssAutonomousCG", "CG_Autonomous3TotesPoC start");
    	addParallel(new C_ArmsIntakeSet(0.8,0.8));
    	addParallel(new C_ArmsOpenCloseTogether(false)); //close both Arms
    	addSequential(new C_ElevMoveAndSetZero());
    	addSequential(new C_FindTote());
    	addParallel(new C_ArmsIntakeSet(0.0,0.0));
    	addParallel(new C_ArmsOpenCloseIndividual(true,false)); //open right arm
    	addParallel(new C_ElevMoveToPos(Robot.ssElevator.nextToteReadyPos));
    	addSequential(new C_Delay(1.0));
    	addParallel(new C_ArmsIntakeSet(-0.7,0.0));
    	addSequential(new C_EncoderDriveStraight(54, 0.5));
    	addParallel(new C_ArmsOpenCloseIndividual(true,true)); //open left arm
    	addSequential(new C_TurnWithGyro(0.9,90));
    	addSequential(new C_Delay(0.5));
    	addSequential(new C_TurnWithGyro(0.9,-90));
    	SmartDashboard.putString("ssAutonomousCG", "CG_Autonomous3TotesPoC end");
    }
}
