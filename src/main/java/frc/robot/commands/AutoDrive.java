package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class AutoDrive extends Command {

    private Drivetrain drivetrain;
    private double leftSpeed;
    private double rightSpeed;

    public AutoDrive(Drivetrain dt, double l, double r){
        drivetrain = dt;
        leftSpeed = l;
        rightSpeed = r;
        addRequirements(drivetrain);
    }

    @Override
    public void execute(){
        drivetrain.drive(leftSpeed, rightSpeed);
    }

    @Override
    public void end(boolean i){
        drivetrain.stop();
    }
}