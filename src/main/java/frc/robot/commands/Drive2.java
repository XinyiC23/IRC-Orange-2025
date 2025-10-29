package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class Drive2 extends Command {
    private Drivetrain drivetrain;
    private CommandXboxController controller;

    public Drive2(Drivetrain drivetrain, CommandXboxController controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        // 1) Read raw joystick axes
        double rawForward = -controller.getLeftY(); // invert Y so push forward -> positive
        double rawTurn = controller.getLeftX();     // left/right for turning

        // 2) Deadband small values so stick jitter doesn't move the robot
        rawForward = applyDeadband(rawForward, 0.05);
        rawTurn = applyDeadband(rawTurn, 0.05);

        // 3) Optional input shaping: square while preserving sign (gentler low speed control) 
        double forward = Math.copySign(rawForward * rawForward, rawForward);
        double turn = Math.copySign(rawTurn * rawTurn, rawTurn);

        // 4) Combine forward and turn into left/right motor outputs (mixing) 
        //Why this works:
	// •	If turn = 0 -> left = forward, right = forward -> robot goes straight.
	// •	If forward = 0 and turn = +1 -> left = +1, right = -1 -> spin clockwise.
	// •	If forward = +0.6 and turn = +0.4 -> left = 1.0, right = 0.2 -> forward-right arc.

        double left = forward + turn;
        double right = forward - turn;
        

        // 5) Normalize so neither output exceeds magnitude 1.0 
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            left /= max;
            right /= max;
        }

        //6) Optional: global speed limit for testing/tuning 
        double speedLimit = 0.5; // set to 1.0 for full speed or lower for safe testing
        left *= speedLimit;
        right *= speedLimit;

        // 7) Send to drivetrain 
        drivetrain.drive(left, right);
    }

    private double applyDeadband(double value, double deadband) {
        if (Math.abs(value) <= deadband) {
            return 0.0;
        }
        // Optional: rescale to remove gap, but simple zeroing is fine:
        return value;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}