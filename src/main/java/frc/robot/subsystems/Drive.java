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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.MotorFilter;
import frc.robot.subsystems.Heading;
import frc.robot.commands.ArcadeDriveCommand;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
	private static Drive m_singleton = null;
	private static final double m_wheelDiameter = 6.0; // inches
	private static final int m_countPerRev = 360; // Optical encoder 360 pulse per rev
	private Heading m_heading = null;
	private WPI_TalonSRX m_leftDriveMaster, m_rightDriveMaster;
	private WPI_VictorSPX m_leftDriveSlave, m_rightDriveSlave;
	private DifferentialDrive m_drive;
	private MotorFilter m_driveFilter;
	private MotorFilter m_turnFilter;
	private DoubleSolenoid m_climbSolenoid = null;

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

		m_driveFilter = new MotorFilter(10);
		m_turnFilter = new MotorFilter(10);

		m_climbSolenoid = new DoubleSolenoid(RobotMap.canPCM, RobotMap.pcmFCClimb, RobotMap.pcmRCClimb);
	}

	public double getHeading() {
		return m_heading.getAngle();
	}

	public void arcadeDrive(double xSpeed, double zRotation) {
		m_drive.arcadeDrive(m_driveFilter.update(xSpeed), m_turnFilter.update(zRotation));
	}

	public int getLeftEncoder() {
		return m_leftDriveMaster.getSelectedSensorPosition();
	}

	public int getRightEncoder() {
		return m_rightDriveMaster.getSelectedSensorPosition();
	}

	public double getSpeed() {
		int speedLeft = m_leftDriveMaster.getSelectedSensorVelocity();
		int speedRight = m_rightDriveMaster.getSelectedSensorVelocity();
		double speedAve = (double) (speedLeft + speedRight) / 2.0;
		return speedAve * Math.PI * m_wheelDiameter / 12.0 / m_countPerRev;
	}

	public void climbEject() {
		if (m_climbSolenoid.get() == Value.kReverse) {
			m_climbSolenoid.set(Value.kForward);
		} else {
			m_climbSolenoid.set(Value.kReverse);
		}
	}

	public void reset() {
		m_drive.arcadeDrive(0.0, 0.0);
		m_driveFilter.reset();
		m_turnFilter.reset();
	}

	public static Drive getInstance() {
		if (m_singleton == null) {
			m_singleton = new Drive();
		}
		return m_singleton;
	}
}
