/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.util.Color;

/**
 * This is a sample program demonstrating how to use an ultrasonic sensor and
 * proportional control to maintain a set distance from an object.
 */

public class Robot extends TimedRobot {

  
  private ShuffleboardTab dataTab = Shuffleboard.getTab("Sensor");
  private NetworkTableEntry redEntry, greenEntry, blueEntry, irEntry;
  Color detectedColor;
  double IR;
  
  /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  
  public Robot() {
    redEntry = dataTab
      .add("colorSensor/red", detectedColor.red)
      .getEntry();
    greenEntry = dataTab
      .add("colorSensor/green", detectedColor.green)
      .getEntry();
    blueEntry = dataTab
      .add("colorSensor/blue", detectedColor.blue)
      .getEntry();
    irEntry = dataTab
      .add("colorSensor/ir", IR)
      .getEntry();
  }


  @Override
  public void teleopPeriodic() {
    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
    detectedColor = colorSensor.getColor();

    /**
     * The sensor returns a raw IR value of the infrared light detected.
     */
    IR = colorSensor.getIR();

    redEntry.setDouble(detectedColor.red);
    greenEntry.setDouble(detectedColor.green);
    blueEntry.setDouble(detectedColor.blue);
    irEntry.setDouble(IR);

  }
}