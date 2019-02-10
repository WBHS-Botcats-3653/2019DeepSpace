# 2019DeepSpace
2019 FRC Robot code

## Development Environment
* Install VSCode and National Instrument [tools](https://wpilib.screenstepslive.com/s/currentCS/m/cpp/l/1027500-installing-c-and-java-development-tools-for-frc).
* The [CTRE](http://www.ctr-electronics.com) libraries should be installed when you first build the robot code. 

## Hardware Mappings
### CAN IDs

|Device|CAN ID|Function|
|------|------|--------|
|PDP   | 0    | Power Distribution Panel|
|PCM   | 1    | Pneumatic Control Module|
|Talon SRX| 9 | Left, Rear, Master drive controller|
|Talon SRX| 10| Right, Rear, Master drive controller|
|Victor SPX|11| Right, Front, Slave drive controller|
|Victor SPX|12| Left, Front, Slave drive controller|

### Digital IO

|Device|Channel|Function|
|------|-------|--------|
|Limit Switch|0|Arm lower limit|
|Limit Switch|1|Arm upper limit|

### Pulse Width Modulation (PWM)

|Device|Channel|Function|
|------|-------|--------|
|VictorSP|0|Right arm motor|
|VictorSP|1|Left arm motor|
|Spark|2|Intake motor|

### Analog Digital Converter (ADC)

|Device|Channel|Function|
|------|-------|--------|
|MA3 Miniature Absolute Magnetic Shaft Encoder|0|Arm Encoder|
|LV‑MaxSonar‑EZ|1|Distance sensor|

### Pneumatic Control Module (PCM)

|Device|Channel|Function|
|------|-------|--------|
|Double solenoid|4|Hatch forward channel|
|Double solenoid|5|Hatch reverse channel|
