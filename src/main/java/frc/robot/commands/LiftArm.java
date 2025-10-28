package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class LiftArm extends Command {
    private Arm arm;
    private double speed;
    public LiftArm(Arm a, double s){
        arm = a;
        speed = s;
        addRequirements(arm);
    }

    @Override
    public void execute(){
        arm.liftArm(speed);
    }

    @Override
    public void end(boolean i){
        arm.stopArm();
    }
}
