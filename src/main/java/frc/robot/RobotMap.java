/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int canPDP = 0;
	public static final int canPCM = 1;

	public static final int canLeftDriveMotorMaster = 9;
	public static final int canRightDriveMotorMaster = 10;
	public static final int canLeftDriveMotorSlave = 12;
	public static final int canRightDriveMotorSlave = 11;

	public static final int dioLowerLimitSwitch = 0;
	public static final int dioUpperLimitSwitch = 1;

	public static final int pwmRightArmMotor = 0;
	public static final int pwmLeftArmMotor = 1;
	public static final int pwmIntakeMotor = 2;

	public static final int adcArmEncoderChannel = 0;
	public static final int adcSonicChannel = 1;

	public static int pcmFCHatch = 5;
	public static int pcmRCHatch = 4;

	public static final SPI.Port spiGyroPort = SPI.Port.kOnboardCS0;
}
