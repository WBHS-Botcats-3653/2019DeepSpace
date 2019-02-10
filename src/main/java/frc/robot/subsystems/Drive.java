/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.subsystems.Heading;
import frc.robot.commands.ArcadeDriveCommand;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
	private static Drive m_singleton = null;
	private Heading m_heading = null;
	private WPI_TalonSRX m_leftDriveMaster, m_rightDriveMaster;
	private WPI_VictorSPX m_leftDriveSlave, m_rightDriveSlave;
	private DifferentialDrive m_drive;

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveCommand());
	}

	private Drive() {
		setName("Drive");

		m_heading = Heading.getInstance();

		m_rightDriveMaster = new WPI_TalonSRX(RobotMap.canRightDriveMotorMaster);
		m_leftDriveMaster = new WPI_TalonSRX(RobotMap.canLeftDriveMotorMaster);
		m_rightDriveSlave = new WPI_VictorSPX(RobotMap.canRightDriveMotorSlave);
		m_leftDriveSlave = new WPI_VictorSPX(RobotMap.canLeftDriveMotorSlave);

		m_rightDriveSlave.follow(m_rightDriveMaster);
		m_leftDriveSlave.follow(m_leftDriveMaster);

		m_leftDriveMaster.setInverted(true);
		m_leftDriveSlave.setInverted(InvertType.FollowMaster);

		m_drive = new DifferentialDrive(m_leftDriveMaster, m_rightDriveMaster);
		m_drive.setRightSideInverted(false);
	}

	public double getHeading() {
		return m_heading.getAngle();
	}

	public void arcadeDrive(double xSpeed, double zRotation) {
		m_drive.arcadeDrive(xSpeed, zRotation);
	}

	public static Drive getInstance() {
		if (m_singleton == null) {
			m_singleton = new Drive();
		}
		return m_singleton;
	}
}
