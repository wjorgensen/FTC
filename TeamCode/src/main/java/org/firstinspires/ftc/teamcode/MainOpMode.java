package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MainOpMode extends LinearOpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private DcMotor rightTableMotor;
    private DcMotor leftTableMotor;


    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        leftTableMotor = hardwareMap.get(DcMotor.class, "motorT");
        rightTableMotor = hardwareMap.get(DcMotor.class, "motorT");


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double power = 0;
        double sideways = 0;
        boolean tablePower = false;
        boolean reverseTablePower = false;
        boolean slowDown = false;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            power = -this.gamepad1.right_stick_y;
            sideways = -this.gamepad1.right_stick_x;

            tablePower = this.gamepad1.right_bumper;
            reverseTablePower = this.gamepad1.left_bumper;
            slowDown = this.gamepad1.a;

             double duckSpinPower = 0.75;
           

            if(power>0.05) {
                motor1.setPower(power);
                motor2.setPower(-power);
                motor3.setPower(power);
                motor4.setPower(power);
            }
            if(power > 0.05 && slowDown) {
                motor1.setPower(power/2);
                motor2.setPower(-power/2);
                motor3.setPower(power/2);
                motor4.setPower(power/2);
            }
            if(sideways > 0.05) {
                motor1.setPower(-power);
                motor2.setPower(-power);
                motor3.setPower(power);
                motor4.setPower(-power);
            }
            if(sideways < -0.05) {
                motor1.setPower(power);
                motor2.setPower(power);
                motor3.setPower(-power);
                motor4.setPower(power);
            }
            if(tablePower){
                leftTableMotor.setPower(duckSpinPower);
                rightTableMotor.setPower(duckSpinPower);
            }
            if(reverseTablePower){
                leftTableMotor.setPower(-duckSpinPower);
                rightTableMotor.setPower(-duckSpinPower);
            }
            if(!reverseTablePower && !tablePower){
                leftTableMotor.setPower(0);
                rightTableMotor.setPower(0);
            }

            
            
            
            telemetry.update();
        }
    }
}