package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command{
    private Drivetrain drivetrain;
    private CommandXboxController controller;
    

    public Drive( Drivetrain drivetrain, CommandXboxController controller){
        this.drivetrain = drivetrain;
        this.controller = controller;
        addRequirements(drivetrain);
    }

    @Override
    public void execute(){

        drivetrain.drive(controller.getLeftY(), controller.getRightY());
    }

    public void end(boolean interrupted){
        drivetrain.stop();
    }
}
