package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.commands.C_ArmsIntake;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SSArmsIntake extends Subsystem {
    public double lArmIntakeSpeed = 0.0;
    public double rArmIntakeSpeed = 0.0;
    
	Talon intakeMotorL, intakeMotorR;
    public void initDefaultCommand() {
    	setDefaultCommand(new C_ArmsIntake());
    }
    public SSArmsIntake(){
    	intakeMotorL = new Talon(3);
    	intakeMotorR = new Talon(0);
    }
    
    public void intakeMotorsSet(double speed){
    	intakeMotorL.set(-speed);
    	intakeMotorR.set(speed);
        lArmIntakeSpeed = -speed;
        rArmIntakeSpeed = speed;
    }
    public void intakeMotorLSet(double speed)
    {
    	intakeMotorL.set(-speed);
        lArmIntakeSpeed = -speed;
    }
    public void intakeMotorRSet(double speed)
    {
    	intakeMotorR.set(speed);
        rArmIntakeSpeed = speed;
    }
    
}
