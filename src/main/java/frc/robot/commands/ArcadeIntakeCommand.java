/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.OI;

public class ArcadeIntakeCommand extends Command {
	private OI m_oi = null;
	private Intake m_intake = null;

	public ArcadeIntakeCommand() {
		super("Intake Control");

		m_oi = OI.getInstance();
		m_intake = Intake.getInstance();

		requires(m_intake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		m_intake.intake(m_oi.getIntakeCtrl());
		m_intake.cargoEject(m_oi.getCargoEject());
		if (m_oi.getHatchEject()) {
			m_intake.hatchEject();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
