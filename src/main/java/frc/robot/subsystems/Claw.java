/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

import frc.robot.commands.ArcadeLiftCommand;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Claw extends Subsystem {
	private static Claw m_singleton = null;
	private VictorSP m_leftArmMotor;
	private VictorSP m_rightArmMotor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Claw() {
		m_leftArmMotor = new VictorSP(RobotMap.pwmLeftArmMotor);
		m_rightArmMotor = new VictorSP(RobotMap.pwmRightArmMotor);
		// Not working? m_rightArmMotor.setInverted( false );
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeLiftCommand());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void intake(double speed) {
		m_leftArmMotor.setSpeed(speed);
		m_rightArmMotor.setSpeed(-speed);
	}

	public static Claw getInstance() {
		if (m_singleton == null) {
			m_singleton = new Claw();
		}
		return m_singleton;
	}
}
