package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

public class Distance extends Subsystem {

  private AnalogInput m_sonic;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Distance() {
    m_sonic = new AnalogInput(RobotMap.sonicChannel);
  }

  public double getVoltage() {
    return m_sonic.getAverageVoltage();
  }
}