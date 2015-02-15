package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_MotorDriveTest extends Command {
	
	boolean finished;
	int i;
	
    public C_MotorDriveTest() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	i = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (Robot.testMotor)
    	{
		case 0:
	    	Robot.ssDriveTrain.motor1Set(Robot.motorTestSpeed);
    		break;
		case 1:
			Robot.ssDriveTrain.motor2Set(Robot.motorTestSpeed);
			break;
		case 2:
			Robot.ssDriveTrain.motor3Set(Robot.motorTestSpeed);
			break;
		case 3:
			Robot.ssDriveTrain.motor4Set(Robot.motorTestSpeed);
			break;
		case 4:
	    	Robot.ssDriveTrain.motor1Set(Robot.motorTestSpeed);
	    	Robot.ssDriveTrain.motor2Set(Robot.motorTestSpeed);
			break;
		case 5:
	    	Robot.ssDriveTrain.motor3Set(Robot.motorTestSpeed);
	    	Robot.ssDriveTrain.motor4Set(Robot.motorTestSpeed);
			break;
		case 6:
			Robot.ssElevator.motor1Set(Robot.motorTestSpeed);
			break;
		case 7:
			Robot.ssElevator.motor2Set(Robot.motorTestSpeed);
			break;
		case 8:
			Robot.ssElevator.motor1Set(Robot.motorTestSpeed);
			Robot.ssElevator.motor2Set(Robot.motorTestSpeed);
			break;
		case 9:
			Robot.ssElevator.moveInAndOut(Robot.motorTestSpeed);
			break;
		case 10:
			SmartDashboard.putNumber("i: ", i++);
			Robot.ssArms.intakeMotorLSet(Robot.motorTestSpeed);
			break;
		case 11:
			Robot.ssArms.intakeMotorRSet(Robot.motorTestSpeed);
			break;
		case 12:
			Robot.ssArms.intakeMotorsSet(Robot.motorTestSpeed);
			break;
		case 13:
			finished = Robot.ssElevator.moveToPos(Robot.encoderTicks);
		default:
			break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (finished || i > 100)
    	{
        	return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	switch (Robot.testMotor)
    	{
		case 0:
	    	Robot.ssDriveTrain.motor1Set(0);
    		break;
		case 1:
			Robot.ssDriveTrain.motor2Set(0);
			break;
		case 2:
			Robot.ssDriveTrain.motor3Set(0);
			break;
		case 3:
			Robot.ssDriveTrain.motor4Set(0);
			break;
		case 4:
	    	Robot.ssDriveTrain.motor1Set(0);
	    	Robot.ssDriveTrain.motor2Set(0);
			break;
		case 5:
	    	Robot.ssDriveTrain.motor3Set(0);
	    	Robot.ssDriveTrain.motor4Set(0);
			break;
		case 6:
			Robot.ssElevator.motor1Set(0);
			break;
		case 7:
			Robot.ssElevator.motor2Set(0);
			break;
		case 8:
			Robot.ssElevator.motor1Set(0);
			Robot.ssElevator.motor2Set(0);
			break;
		case 9:
			Robot.ssElevator.moveInAndOut(0);
			break;
		case 10:
			Robot.ssArms.intakeMotorLSet(0);
			break;
		case 11:
			Robot.ssArms.intakeMotorRSet(0);
			break;
		case 12:
			Robot.ssArms.intakeMotorsSet(0);
			break;
		case 13: 
			Robot.ssElevator.motorsSet(0);
			Robot.ssElevator.bikeBrakeTriggerClose();
		default:
			break;
    	}
    }
}