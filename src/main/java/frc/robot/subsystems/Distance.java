package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;

public class Distance {
	private static Distance m_singleton = null;
	private AnalogInput m_sonic;

	private Distance() {
		m_sonic = new AnalogInput(RobotMap.adcSonicChannel);
	}

	public double getVoltage() {
		return m_sonic.getAverageVoltage();
	}

	public double getInches() {
		return (m_sonic.getAverageVoltage() / 0.281764) * 12;
	}

	public String getDistanceString() {
		double inches = getInches();

		if (inches < 6) {
			return "Too close";
		} else if (inches > 192) {
			return "Out of range";
		}

		long feet = Math.round(Math.floor(inches / 12));
		long inch = Math.round(inches % 12);

		return (feet + "\' " + inch + "\"");
	}

	public static Distance getInstance() {
		if (m_singleton == null) {
			m_singleton = new Distance();
		}
		return m_singleton;
	}
}