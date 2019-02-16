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
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * Manage Shuffleboard wigits.
 */
public class DashBoard {
	private static DashBoard m_singleton = null;

	private NetworkTableEntry m_nteMaxSpeed = null;
	private NetworkTableEntry m_nteMaxTurn = null;
	private NetworkTableEntry m_nteMaxIn = null;
	private NetworkTableEntry m_nteMaxOut = null;
	private NetworkTableEntry m_nteMaxArmUpSpd = null;
	private NetworkTableEntry m_nteMaxArmDnSpd = null;
	private NetworkTableEntry m_nteDistB = null;
	private NetworkTableEntry m_nteDistM = null;
	private NetworkTableEntry m_nteArmLwLmt = null;
	private NetworkTableEntry m_nteArmUpLmt = null;

	private DashBoard() {
		// ShuffleboardTab tabStatus = Shuffleboard.getTab("Status");
		ShuffleboardTab tabConfig = Shuffleboard.getTab("Config");
		// ShuffleboardTab tabTest = Shuffleboard.getTab("Test");

		m_nteMaxSpeed = tabConfig.addPersistent("Max Speed", 1.0).getEntry();
		m_nteMaxTurn = tabConfig.addPersistent("Max Turn", 1.0).getEntry();
		m_nteMaxIn = tabConfig.addPersistent("Max Intake", 0.5).getEntry();
		m_nteMaxOut = tabConfig.addPersistent("Max Outtake", 0.5).getEntry();
		m_nteMaxArmDnSpd = tabConfig.addPersistent("Max Arm dn", 1.0).getEntry();
		m_nteMaxArmUpSpd = tabConfig.addPersistent("Max Arm Up", 1.0).getEntry();
		m_nteDistB = tabConfig.addPersistent("Distance B", 0.0).getEntry();
		m_nteDistM = tabConfig.addPersistent("Distance M", 1.0).getEntry();
		m_nteArmLwLmt = tabConfig.addPersistent("Arm lower", 0).getEntry();
		m_nteArmUpLmt = tabConfig.addPersistent("Arm upper", 4048).getEntry();
	}

	public void refresh() {
		OI ctrl = OI.getInstance();

		ctrl.setMaxSpeed(m_nteMaxSpeed.getDouble(1.0));
		ctrl.setMaxTurn(m_nteMaxTurn.getDouble(1.0));
	}

	public static DashBoard getInstance() {
		if (m_singleton == null) {
			m_singleton = new DashBoard();
		}
		return m_singleton;
	}

}
