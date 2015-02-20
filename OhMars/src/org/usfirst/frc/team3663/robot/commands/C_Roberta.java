package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 *
 */
public class C_Roberta extends Command {

	int i,j;
	
    public C_Roberta() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ssDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	i = j = 0;
    	Robot.ssDashBoard.putDashString("Roberta: ", "Roberta is here!!!!");
    	//Robot.ssDashBoard.putDashString("RobertaInit: ", "Roberta is here!!!!!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(i++);
    	Robot.ssDashBoard.putDashString("Roberta: ", "Roberta is doing things! don't interfere!! -*-");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.ssDashBoard.putDashInt("j: ", j++);
    	Robot.ssDashBoard.putDashString("Roberta: ", "Am I finished? why would you even ask that?");
        if (i == 300)
    	{
        	return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ssDashBoard.putDashString("Roberta: ", "Roberta is done.... do whatever you want now @-@");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ssDashBoard.putDashString("Roberta: ", "hey!!! don't do that! I'm not done yet!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! GO LEAAAAVE");
    }
}
