/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDriveCommand;
/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Drive m_singleton = null;
	private VictorSP m_leftDrive, m_rightDrive;
	private DifferentialDrive m_drive;

 


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDriveCommand());
  }

  private Drive()
  {
		m_rightDrive = new VictorSP(RobotMap.pwmRightDriveMotor);
    m_leftDrive = new VictorSP(RobotMap.pwmLeftDriveMotor);
    m_drive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  }

  public void arcadeDrive(double xSpeed, double zRotation)
	{
		m_drive.arcadeDrive(xSpeed, zRotation);
  }
  
  public static Drive getInstance()
	{
		if (m_singleton == null)
		{
			m_singleton = new Drive();
		}
		return m_singleton;
	}
}
