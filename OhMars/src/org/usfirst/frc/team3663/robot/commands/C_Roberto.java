package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_Roberto extends Command {

	int i,j;
	
    public C_Roberto() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	i = j = 0;
    	Robot.ssDashBoard.putDashString("Roberto: ", "What's up, guys. It's Roberto.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ssDashBoard.putDashNumber("i: ", i++);
    	Robot.ssDashBoard.putDashString("Roberto: ", "...Leave me alone....I'm working");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.ssDashBoard.putDashString("Roberto: ", "Almost done...just wait a bit");
        if (i == 300)
    	{
        	return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDashBoard.putDashString("Roberto: ", "Done. This should work...");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ssDashBoard.putDashString("Roberto: ", "Don't interrupt me.");
    }
}
