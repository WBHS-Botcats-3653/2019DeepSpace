/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Sendable;

/**
 * Wrapper class to gyro on Roborio.
 */
public class Heading {
	private static Heading m_singleton;
	private ADXRS450_Gyro m_gyro;

	private Heading() {
		m_gyro = new ADXRS450_Gyro(RobotMap.spiGyroPort);
		m_gyro.calibrate();
	}

	public Sendable getGyro() {
		return m_gyro;
	}

	public double getAngle() {
		return m_gyro.getAngle();
	}

	public static Heading getInstance() {
		if (m_singleton == null) {
			m_singleton = new Heading();
		}
		return m_singleton;
	}
}
