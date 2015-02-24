package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_MotorDriveTest extends Command {
	
	boolean finished;
	
    public C_MotorDriveTest() {
        // Use requires() here to declare subsystem dependencies
       // requires(Robot.ssElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.runCommand = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	motorTestCases(Robot.motorTestSpeed);
    }
    
    boolean motorTestCases(double speed)
    {
    	switch (Robot.testMotor)
    	{
		case 0:
	    	//Robot.ssDriveTrain.motor1Set(speed);
    		break;
		case 1:
			//Robot.ssDriveTrain.motor2Set(speed);
			break;
		case 2:
			//Robot.ssDriveTrain.motor3Set(speed);
			break;
		case 3:
			//Robot.ssDriveTrain.motor4Set(speed);
			break;
		case 4:
	    	//Robot.ssDriveTrain.motor1Set(speed);
	    	//Robot.ssDriveTrain.motor2Set(speed);
			break;
		case 5:
	    	//Robot.ssDriveTrain.motor3Set(speed);
	    	//Robot.ssDriveTrain.motor4Set(speed);
			break;
		case 6:
			Robot.ssElevator.motor1Set(speed);
			break;
		case 7:
			Robot.ssElevator.motor2Set(speed);
			break;
		case 8:
			Robot.ssElevator.motor1Set(speed);
			Robot.ssElevator.motor2Set(speed);
			break;
		case 9:
			Robot.ssFork.set(speed);
			break;
		case 10:
			Robot.ssArmsIntake.intakeMotorLSet(speed);
			break;
		case 11:
			Robot.ssArmsIntake.intakeMotorRSet(speed);
			break;
		case 12:
			Robot.ssArmsIntake.intakeMotorsSet(speed);
			break;
		case 13:
			finished = Robot.ssElevator.moveToPos(-50, 1.0);
			SmartDashboard.putNumber("elevWinchEncoder: ", Robot.ssElevator.winchEncoder.get());
			return finished;
		default:
			break;
    	}
    	return false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (finished)
    	{
        	return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	switch (Robot.testMotor)
    	{
		case 13: 
			Robot.ssElevator.motorsSet(0);
			Robot.ssElevator.bikeBrakeTriggerClose();
			break;
		default:
			motorTestCases(0);
			break;
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
