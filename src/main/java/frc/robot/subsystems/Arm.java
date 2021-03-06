/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.commands.ArcadeArmCommand;
import frc.robot.RobotMap;

/**
 * Subsystem to control the Arm. 1) Motors to raise and lower arm. 2) Absolute
 * encoder to measure arm angle. 3) Limit switches to detect upper and lower
 * range of arm.
 */
public class Arm extends Subsystem {
	private static final int m_maxEncoder = 4096;
	private static final double m_encTicksPerAngle = 11.0444;
	private static int m_encFloor = 1064;
	private static Arm m_singleton = null;
	private VictorSP m_leftArmMotor;
	private VictorSP m_rightArmMotor;
	private DigitalInput lowerLimitSwitch;
	private DigitalInput upperLimitSwitch;
	private AnalogInput m_encoder;

	private Arm() {
		setName("Arm");

		m_leftArmMotor = new VictorSP(RobotMap.pwmLeftArmMotor);
		m_rightArmMotor = new VictorSP(RobotMap.pwmRightArmMotor);
		// Not working? m_rightArmMotor.setInverted( true );

		lowerLimitSwitch = new DigitalInput(RobotMap.dioLowerLimitSwitch);
		upperLimitSwitch = new DigitalInput(RobotMap.dioUpperLimitSwitch);

		m_encoder = new AnalogInput(RobotMap.adcArmEncoderChannel);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeArmCommand());
	}

	public int getRawEncoder() {
		return m_encoder.getValue();
	}

	public void move(double speed) {
		if (speed > 0.0) {
			if (!upperLimitSwitch.get() /* || getRawEncoder() > 2050 */) {
				speed = 0.0;
			}
		} else if (speed < 0.0) {
			if (!lowerLimitSwitch.get()/* || getRawEncoder() < m_encFloor*/) {
				speed = 0.0;
			}
		}

		m_leftArmMotor.setSpeed(speed);
		// Use (-speed) since setInverted(true) is not working.
		m_rightArmMotor.setSpeed(-speed);
	}

	public boolean getLowerLimitSwitch() {
		return lowerLimitSwitch.get();
	}

	public boolean getUpperLimitSwitch() {
		return upperLimitSwitch.get();
	}

	public void setArmVertical(boolean up) {
		if (up) {
			double deltaAng = 86 - getAngle();
			if (Math.abs(deltaAng) < 5.0) {
				move(0);
			} else if (deltaAng > 0.0) {
				move(0.3);
			} else if (deltaAng < 0.0) {
				move(-0.3);
			}
		}
	}

	public void setArmStow(boolean stow) {
		if (stow) {
			move(0.3);
		}
	}

	public void setArmDown(boolean down) {
		if (down) {
			move(-0.3);
		}
	}

	public static Arm getInstance() {
		if (m_singleton == null) {
			m_singleton = new Arm();
		}
		return m_singleton;
	}

	public static void setArmEncoderFloor(int floor) {
		if (0 <= floor && floor < m_maxEncoder) {
			m_encFloor = floor;
		}
	}

	public double getAngle() {
		return (getRawEncoder() - m_encFloor) / m_encTicksPerAngle;
	}
}
