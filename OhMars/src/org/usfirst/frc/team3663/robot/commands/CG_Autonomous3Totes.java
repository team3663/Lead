package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CG_Autonomous3Totes extends CommandGroup {
    public  CG_Autonomous3Totes() {
    	SmartDashboard.putString("ssAutonomousCG", "CG_Autonomous3Totes start");
    	//84 inches from beginning of one starting box to beginning of the next
/********FIRST TOTE********/
    	addParallel(new C_ArmsIntakeSet(0.8,0.8));
    	addParallel(new C_ArmsOpenCloseTogether(false)); //close both Arms
    	addSequential(new C_ElevMoveAndSetZero());
    	addSequential(new C_FindTote());
    	addParallel(new C_ArmsIntakeSet(0.0,0.0));
    	addParallel(new C_ArmsOpenCloseIndividual(true,false)); //open right arm
    	addParallel(new C_ElevMoveToPos(Robot.ssElevator.nextToteReadyPos));
    	addSequential(new C_Delay(0.5));
    	addParallel(new C_ArmsIntakeSet(-0.7,0.0));
/********SECOND TOTE********/
    	addSequential(new C_EncoderDriveStraight(42, 0.7));
    	addParallel(new C_ArmsOpenCloseIndividual(true,true)); //open left arm
    	addSequential(new C_EncoderDriveStraight(42, 0.7));
    	addParallel(new C_ArmsIntakeSet(0.8,0.8));
    	addParallel(new C_ArmsOpenCloseTogether(false)); //close both arms
    	addSequential(new C_FindTote());
    	addParallel(new C_ArmsIntakeSet(0.0,0.0));
    	addParallel(new C_ArmsOpenCloseIndividual(true,false)); //open right arm
    	addParallel(new CG_PickUp());
    	addSequential(new C_Delay(0.3));
    	addParallel(new C_ArmsIntakeSet(-0.7,0.0));
/********THIRD TOTE********/
    	addSequential(new C_EncoderDriveStraight(42, 0.7));
    	addParallel(new C_ArmsOpenCloseIndividual(true,true)); //open left arm
    	addSequential(new C_EncoderDriveStraight(42, 0.7));
    	addParallel(new C_ArmsOpenCloseTogether(false)); //close both arms
    	addParallel(new C_ArmsIntakeSet(0.8,0.8));
    	addSequential(new C_FindTote());
    	addParallel(new C_ArmsIntakeSet(0.0,0.0));
/********DROP OFF********/
    	addSequential(new C_TurnWithGyro(0.9,90));
    	addSequential(new C_EncoderDriveStraight(100, 0.7));
    	addSequential(new C_TurnWithGyro(0.9,-90));
    	addParallel(new C_ArmsOpenCloseTogether(true));//open both arms
    	addSequential(new C_ElevMoveToPos(Robot.ssElevator.puttingStackPos));
    	addSequential(new C_EncoderDriveStraight(-10, 0.5));
    	SmartDashboard.putString("ssAutonomousCG", "CG_Autonomous3Totes end");
        
    }
}
