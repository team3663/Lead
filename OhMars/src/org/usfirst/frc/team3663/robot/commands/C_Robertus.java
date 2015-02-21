package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Robertus extends Command {

	int i,j;
	
    public C_Robertus() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	i = j = 0;
    	Robot.ssDashBoard.putDashString("Robertus: ", "hola");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDashBoard.putDashNumber("x: ", i++);
    	Robot.ssDashBoard.putDashString("Robertus: ", "comoestas");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.ssDashBoard.putDashString("Robertus: ", "mui bien");
        if (i > 300)
    	{
        	return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDashBoard.putDashString("Robertus: ", "el bano");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ssDashBoard.putDashString("Robertus: ", "I'm not gonna try anymore");
    }
}
