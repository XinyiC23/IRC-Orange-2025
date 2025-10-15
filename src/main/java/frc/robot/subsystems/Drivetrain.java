package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private VictorSP leftMotor;
    private VictorSP rightMotor;

    public Drivetrain(){
        
        leftMotor = new VictorSP(0);
        rightMotor = new VictorSP(1);

        leftMotor.setInverted(true);
    }

    public void drive(double l, double r){

        leftMotor.set(l);
        rightMotor.set(r);

    }

    public void stop(){
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }
}
