package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    
    private VictorSP motor;
    public Arm(){
        motor = new VictorSP(2);
    }
    public void liftArm(double speed){
        motor.set(speed);
    }
    public void stopArm(){
        motor.stopMotor();
    }
}
