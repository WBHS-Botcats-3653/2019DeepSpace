/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

import frc.robot.commands.ArcadeArmCommand;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Subsystem to control the Arm. 1) Motors to raise and lower arm. 2) Absolute
 * encoder to measure arm angle. 3) Limit switches to detect upper and lower
 * range of arm.
 */
public class Arm extends Subsystem {
	private static Arm m_singleton = null;
	private VictorSP m_leftArmMotor;
	private VictorSP m_rightArmMotor;
	private DigitalInput lowerLimitSwitch;
	private DigitalInput upperLimitSwitch;


	private Arm() {
		m_leftArmMotor = new VictorSP(RobotMap.pwmLeftArmMotor);
		m_rightArmMotor = new VictorSP(RobotMap.pwmRightArmMotor);

		lowerLimitSwitch = new DigitalInput(0);
		upperLimitSwitch = new DigitalInput(1);
		// Not working? m_rightArmMotor.setInverted( true );
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeArmCommand());
	}

	public void move(double speed) 
	{
		if((speed>=0 && !lowerLimitSwitch.get()) || (speed<=0 && !upperLimitSwitch.get()))
		{
			m_leftArmMotor.setSpeed(speed);
			// Use (-speed) since setInverted(true) is not working.
			m_rightArmMotor.setSpeed(-speed);
		}
	}

	public static Arm getInstance() {
		if (m_singleton == null) {
			m_singleton = new Arm();
		}
		return m_singleton;
	}
}
