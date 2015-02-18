package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Roberto extends Command {

	int i,j = 0;
	
    public C_Roberto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("What's up, guys. It's Roberto.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(i++);
    	System.out.println("...Leave me alone....I'm working");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Almost done...just wait a bit");
        if (i == 300)
    	{
        	return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Done. This should work...");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Don't interrupt me.");
    }
}
