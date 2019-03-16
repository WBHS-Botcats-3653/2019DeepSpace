/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;

import frc.robot.OI;
import frc.robot.subsystems.Arm;
//import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Heading;

/**
 * Manage Shuffleboard wigits.
 */
public class DashBoard {
	private static DashBoard m_singleton = null;

	private UsbCamera cam0 = null;

	// Config Tab
	private NetworkTableEntry m_nteMaxSpd = null;
	private NetworkTableEntry m_nteMaxArmSpd = null;
	private NetworkTableEntry m_nteMaxIntake = null;
	private NetworkTableEntry m_nteArmDownEnc = null;

	// Test Tab
	private NetworkTableEntry m_nteArmEncoderRaw = null;
	private NetworkTableEntry m_nteDriveEncLeft = null;
	private NetworkTableEntry m_nteDriveEncRight = null;
	private NetworkTableEntry m_nteArmUpLimit = null;
	private NetworkTableEntry m_nteArmDnLimit = null;

	// Drive Tab
	private NetworkTableEntry m_nteArmAngle = null;
	private NetworkTableEntry m_nteDriveSpeed = null;
	// private NetworkTableEntry m_nteGyro = null;

	private DashBoard() {
		ShuffleboardTab tabConfig = Shuffleboard.getTab("Config");
		ShuffleboardTab tabDrive = Shuffleboard.getTab("Drive");
		ShuffleboardTab tabTest = Shuffleboard.getTab("Test");

		// Config Tab
		m_nteMaxSpd = tabConfig.addPersistent("Max Speed", 1.0).getEntry();
		m_nteMaxArmSpd = tabConfig.addPersistent("Max Arm Spd", 1.0).getEntry();
		m_nteMaxIntake = tabConfig.addPersistent("Max Intake", 1.0).getEntry();
		m_nteArmDownEnc = tabConfig.addPersistent("Arm Down", 1024.0).getEntry();

		// Drive Tab
		m_nteArmAngle = tabDrive.add("Arm", 0.0).withWidget(BuiltInWidgets.kDial)
				.withProperties(Map.of("min", 0, "max", 180)).getEntry();
		m_nteDriveSpeed = tabDrive.add("Speed", 0.0).withWidget(BuiltInWidgets.kNumberBar)
				.withProperties(Map.of("min", 0, "max", 10)).getEntry();
		tabDrive.add("Gyro", Heading.getInstance().getGyro());

		cam0 = CameraServer.getInstance().startAutomaticCapture(0);
		cam0.setResolution(142, 90);
		cam0.setFPS(15);
		tabDrive.add("Field View", cam0);

		// Test Tab
		m_nteArmEncoderRaw = tabTest.add("Arm Encoder", 1024).getEntry();
		m_nteDriveEncLeft = tabTest.add("Drive Left", 0).getEntry();
		m_nteDriveEncRight = tabTest.add("Drive Right", 0).getEntry();
		m_nteArmDnLimit = tabTest.add("Arm Down", false).getEntry();
		m_nteArmUpLimit = tabTest.add("Arm Up", false).getEntry();
	}

	public void refresh() {
		OI ctrl = OI.getInstance();

		ctrl.setMaxDriveSpeed(m_nteMaxSpd.getDouble(1.0));
		ctrl.setMaxArmSpeed(m_nteMaxArmSpd.getDouble(1.0));
		ctrl.setMaxIntakeSpeed(m_nteMaxIntake.getDouble(1.0));

		Arm.setArmEncoderFloor((int) m_nteArmDownEnc.getDouble(1024));
	}

	public void telopPeriodic() {
		Arm arm = Arm.getInstance();
		Drive drive = Drive.getInstance();
		m_nteArmAngle.setDouble(arm.getAngle());
		m_nteDriveSpeed.setDouble(Math.abs(drive.getSpeed()));
	}

	public void testPeriodic() {
		Arm arm = Arm.getInstance();
		Drive drive = Drive.getInstance();

		m_nteArmEncoderRaw.setNumber(arm.getRawEncoder());
		m_nteArmDnLimit.setBoolean(arm.getLowerLimitSwitch());
		m_nteArmUpLimit.setBoolean(arm.getUpperLimitSwitch());
		m_nteDriveEncLeft.setNumber(drive.getLeftEncoder());
		m_nteDriveEncRight.setNumber(drive.getRightEncoder());
	}

	public static DashBoard getInstance() {
		if (m_singleton == null) {
			m_singleton = new DashBoard();
		}
		return m_singleton;
	}
}
