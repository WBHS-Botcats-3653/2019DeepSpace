/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.commands.ArcadeIntakeCommand;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
	private static Intake m_singleton = null;
	private Spark m_intakeMotor = null;
	private DoubleSolenoid m_hatchSolenoid = null;
	private DoubleSolenoid m_cargoSolenoid = null;

	private Intake() {
		setName("Intake");

		m_hatchSolenoid = new DoubleSolenoid(RobotMap.canPCM, RobotMap.pcmFCHatch, RobotMap.pcmRCHatch);
		m_cargoSolenoid = new DoubleSolenoid(RobotMap.canPCM, RobotMap.pcmFCCargo, RobotMap.pcmRCCargo);
		m_intakeMotor = new Spark(RobotMap.pwmIntakeMotor);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeIntakeCommand());
	}

	public void startHatchOutputIn() {
		m_hatchSolenoid.set(Value.kReverse);
	}

	public void intake(double speed) {
		m_intakeMotor.setSpeed(speed);
	}

	/*
	 * public void hatchEject(boolean out) { m_hatchSolenoid.set(out ?
	 * Value.kForward : Value.kReverse);//this is for holding to extend }
	 */
	public void cargoEject(boolean out) {
		m_cargoSolenoid.set(out ? Value.kForward : Value.kReverse);
	}

	public void hatchEject() {
		if (m_hatchSolenoid.get() == Value.kReverse) {
			m_hatchSolenoid.set(Value.kForward);
		} else {
			m_hatchSolenoid.set(Value.kReverse);
		}
	}

	public static Intake getInstance() {
		if (m_singleton == null) {
			m_singleton = new Intake();
		}
		return m_singleton;
	}
}
