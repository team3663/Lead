package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_ElevMoveToPos extends Command {

	int ticks, origTicks;
	boolean finished;
	
    public C_ElevMoveToPos(int pTicks) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssElevator);
        origTicks = pTicks;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.runCommand = true;
    	ticks = getTicks();
    	Robot.ssElevator.prepForMove(ticks);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finished = Robot.ssElevator.moveToPos(ticks);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (finished || !Robot.runCommand || !Robot.runCG)
        {
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssElevator.terminateMove();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    int getTicks()
    {
    	if (origTicks < 0)
    	{
    		if (origTicks == -49)
    		{
    			origTicks = (int)(Robot.ssDashBoard.getFromDashInt("encoderPosition: "));
    		}
    		switch(origTicks)
    		{
    		case -1:
    			ticks = -15;//lowest position we want
    			break;
    		case -5:
    			ticks = 50;//pick up/lower drop off on step
    			break;
    		case -10:
    			ticks = 275;//tote scoring platform
    			break;
    		case -15:
    			ticks = 525;//tote step
    			break;
    		case -20:
    			ticks = 600;//no tote in/beginning of match position
    			break;
    		case -25:
    			ticks = 1075;//totes up & ready for next tote
    		case -50:
    			ticks = (int)(SmartDashboard.getNumber("encoderTicks: "));
    			break;
    		}
    	}
    	else 
    	{
    		ticks = origTicks;
    	}
    	return ticks;
    }
    
}
