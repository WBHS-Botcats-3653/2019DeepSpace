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

  public double getInches() {
    return m_sonic.getAverageVoltage() / 0.281764 * 12;
  }

  public String getDistanceString() {
    if (getInches() < 12)
      return "Too close";
    if (getInches() > 192)
      return "Out of range";
    long feet = Math.round(Math.floor(getInches() / 12));
    long inches = Math.round(getInches() % 12);
    return (feet + "\' " + inches + "\"");

  }
}