/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Running average to filter motor input.
 */
public class MotorFilter {
    private int m_size;
    private int m_index;
    private double[] m_values;
    private double m_running_sum;

    public MotorFilter(int size) {
        m_size = size;
        m_values = new double[size];
        reset();
    }

    public void reset() {
        for (int i = 0; i < m_size; i++) {
            m_values[i] = 0.0;
        }
        m_index = 0;
        m_running_sum = 0.0;
    }

    public double update(double val) {
        // Remove oldest value from running sum
        m_running_sum -= m_values[m_index];
        // Add new value to running sum
        m_running_sum += val;
        // Replace oldest value with new value
        m_values[m_index] = val;
        // Update index to point to oldest value.
        m_index++;
        // Check for roll-over
        if (m_index >= m_size) {
            m_index = 0;
        }
        // Return average
        return m_running_sum / ((double) m_size);
    }
}
