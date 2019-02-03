/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

import frc.robot.commands.ArcadeIntakeCommand;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  private static Intake m_singleton = null;
	private VictorSP m_intakeMotor;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Intake()
  {
    m_intakeMotor = new VictorSP(RobotMap.pwmIntakeMotor);
  }
  @Override
  public void initDefaultCommand() 
  {
    setDefaultCommand(new ArcadeIntakeCommand());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void intake(double speed) {
		m_intakeMotor.setSpeed(speed);
		
  }
  
  public static Intake getInstance() {
		if (m_singleton == null) {
			m_singleton = new Intake();
		}
		return m_singleton;
	}
}
