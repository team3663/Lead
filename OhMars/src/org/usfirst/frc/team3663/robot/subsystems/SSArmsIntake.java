package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_ArmsIntake;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SSArmsIntake extends Subsystem {
    
	Talon intakeMotorL, intakeMotorR;
    public void initDefaultCommand() {
    	setDefaultCommand(new C_ArmsIntake());
    }
    public SSArmsIntake(){
    	intakeMotorL = new Talon(3);
    	intakeMotorR = new Talon(0);
    }
    
    public void intakeMotorsSet(double speed){
    	intakeMotorLSet(speed);
    	intakeMotorRSet(speed);
    }
    public void intakeMotorLSet(double speed)
    {
    	intakeMotorL.set(-speed);
    }
    public void intakeMotorRSet(double speed)
    {
    	intakeMotorR.set(speed);
    }
    public void updateStatus(){
        SmartDashboard.putNumber("ArmIntakeL", Robot.ssArmsIntake.intakeMotorL.get());
        SmartDashboard.putNumber("ArmIntakeR", Robot.ssArmsIntake.intakeMotorR.get());
    }
    
}
