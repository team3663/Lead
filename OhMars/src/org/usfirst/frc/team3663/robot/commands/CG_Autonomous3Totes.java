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
    	addParallel(new C_ArmsIntakeSet(0.8,0.8));
    	addParallel(new C_ArmsOpenCloseTogether(false));
    	addSequential(new C_ElevMoveAndSetZero());
    	addSequential(new C_FindTote());
    	addParallel(new C_ArmsIntakeSet(0.0,0.0));
    	addParallel(new C_ArmsOpenCloseTogether(true));
    	addSequential(new C_ElevMoveToPos(Robot.ssElevator.nextToteReadyPos));
    	//Maybe change this^ to parallel if too slow
    	//GET THE CAN OUTTA THE WAY SOMEHOW
    	addSequential(new C_EncoderDriveStraight(108, 0.7));
    	addParallel(new C_ArmsIntakeSet(0.8,0.8));
    	addParallel(new C_ArmsOpenCloseTogether(false));
    	addSequential(new C_FindTote());
    	addParallel(new C_ArmsIntakeSet(0.0,0.0));
    	addParallel(new C_ArmsOpenCloseTogether(true));
    	addSequential(new CG_PickUp());
    	//Maybe change this^ to parallel if too slow
    	//GET THE CAN OUTTA THE WAY SOMEHOW
    	addSequential(new C_EncoderDriveStraight(108, 0.7));//<Make this less to have it only in the arms
    	addParallel(new C_ArmsOpenCloseTogether(false));
    	addParallel(new C_ArmsIntakeSet(0.8,0.8));
    	addSequential(new C_FindTote());
    	addParallel(new C_ArmsIntakeSet(0.0,0.0));
    	//Sequential turn 90 degrees right
    	addSequential(new C_EncoderDriveStraight(100, 0.7));
    	addParallel(new C_ArmsOpenCloseTogether(true));
    	//Turn Sideways/angled
    	addSequential(new C_ElevMoveToPos(500));//Change to Robot.ssElevator.stackpos (or something) when completed
    	addSequential(new C_EncoderDriveStraight(-10, 0.5));
    	SmartDashboard.putString("ssAutonomousCG", "end");
        
    }
}
