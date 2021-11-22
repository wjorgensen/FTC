package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;

@TeleOp
public class Servos extends LinearOpMode {
    private Servo arm1;
    private Servo arm2;
    double ARM_POSITION = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        arm1.setPosition(ARM_POSITION);
        arm1.setDirection(FORWARD);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a){
                arm2.setDirection(Servo.Direction.FORWARD);
            }
            if(gamepad1.y){
                arm2.setDirection(REVERSE);
            }
            if(gamepad1.b){
                ARM_POSITION = ARM_POSITION+0.1;
                arm2.setPosition(ARM_POSITION);
            }
            if (gamepad1.x){
                ARM_POSITION = ARM_POSITION-0.1;
                arm2.setPosition(ARM_POSITION);
            }
            ARM_POSITION = ARM_POSITION+0.1;
            arm1.setPosition(ARM_POSITION);
            sleep(5000);
            telemetry.addData(""+ARM_POSITION, "Arm Position");
            telemetry.update();
        }

    }
}
