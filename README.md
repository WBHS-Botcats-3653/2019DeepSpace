# 2019DeepSpace
2019 FRC Robot code

## Development Environment
* Install VSCode and National Instrument [tools](https://wpilib.screenstepslive.com/s/currentCS/m/cpp/l/1027500-installing-c-and-java-development-tools-for-frc).
* The [CTRE](http://www.ctr-electronics.com) libraries should be installed when you first build the robot code. 

## Hardware Mappings
The left and right Toughbox Mini gear boxes each have an optical uadrature encoder installed. The encoders are wired directly to the corresponding Talon SRX drive motor. Both the practice and competion robot are using the E4T Miniature Optical Kit Encoder.

|Device|Module|ID|Function|
|------|------|------|--------|
|PDP   |can| 0    | Power Distribution Panel|
|PCM   |can| 1    | Pneumatic Control Module|
|Talon SRX|can| 9 | Left, Rear, Master drive controller|
|Talon SRX|can| 10| Right, Rear, Master drive controller|
|Victor SPX|can|11| Right, Front, Slave drive controller|
|Victor SPX|can|12| Left, Front, Slave drive controller|
|Limit Switch|dio|0|Arm lower limit|
|Limit Switch|dio|1|Arm upper limit|
|VictorSP|pwm|0|Right arm motor|
|VictorSP|pwm|1|Left arm motor|
|Spark|pwm|2|Intake motor|
|MA3 Miniature Absolute Magnetic Shaft Encoder|adc|0|Arm Encoder|
|LV‑MaxSonar‑EZ|adc|1|Distance sensor|
|Double solenoid|pcm|4|Hatch forward channel|
|Double solenoid|pcm|5|Hatch reverse channel|

## Glossary

* ADC - Analog/Digital Converter
* CAN - Controller Area Network, Bi-directional data bus
* CTRE - CTR Electronics
* DIO - Digital Input/Output
* FRC - FIRST Robotics Competition
* PCM - Pneumatic Control Module
* PDP - Power Distribution Panel
* PWM - Pulse Width Modulation
