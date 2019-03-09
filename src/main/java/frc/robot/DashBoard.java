/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.OI;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * Manage Shuffleboard wigits.
 */
public class DashBoard {
	private static DashBoard m_singleton = null;

	private NetworkTableEntry m_nteMaxSpd = null;
	private NetworkTableEntry m_nteMaxArmSpd = null;
	private NetworkTableEntry m_nteMaxIntake = null;

	private DashBoard() {
		ShuffleboardTab tabConfig = Shuffleboard.getTab("Config");

		m_nteMaxSpd = tabConfig.addPersistent("Max Speed", 1.0).getEntry();
		m_nteMaxArmSpd = tabConfig.addPersistent("Max Arm Spd", 1.0).getEntry();
		m_nteMaxIntake = tabConfig.addPersistent("Max Intake", 1.0).getEntry();
	}

	public void refresh() {
		OI ctrl = OI.getInstance();

		ctrl.setMaxDriveSpeed(m_nteMaxSpd.getDouble(1.0));
		ctrl.setMaxArmSpeed(m_nteMaxArmSpd.getDouble(1.0));
		ctrl.setMaxIntakeSpeed(m_nteMaxIntake.getDouble(1.0));
	}

	public static DashBoard getInstance() {
		if (m_singleton == null) {
			m_singleton = new DashBoard();
		}
		return m_singleton;
	}
}
