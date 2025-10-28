package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;

public class BasicAuto extends SequentialCommandGroup{
    public BasicAuto(Drivetrain drivetrain, Arm arm){

        addCommands(
            new AutoDrive(drivetrain, 0.5, 0.5).withTimeout(2), 
            // drive forward at 50% speed for 2 seconds.
            new LiftArm(arm, 0.4).withTimeout(1),
            // lift arm at 40% speed for 1 second.
            new AutoDrive(drivetrain, -0.5, -0.5).withTimeout(2)
            // drive backward at 50% speed for 2 seconds.
        );
    }
}
