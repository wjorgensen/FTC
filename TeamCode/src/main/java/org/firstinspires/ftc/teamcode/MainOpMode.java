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
        leftTableMotor = hardwareMap.get(DcMotor.class, "motorTL");
        rightTableMotor = hardwareMap.get(DcMotor.class, "motorTR");


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double x = 0;
        double y = 0;
        double clockwise = 0;
        boolean tablePower = false;
        boolean reverseTablePower = false;
        boolean slowDown = false;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            y = -this.gamepad1.right_stick_y;
            x = -this.gamepad1.right_stick_x;
            clockwise = -this.gamepad1.left_stick_x;

            tablePower = this.gamepad1.right_bumper;
            reverseTablePower = this.gamepad1.left_bumper;
            slowDown = this.gamepad1.a;

             double duckSpinPower = 0.75;

            telemetry.addData("Status", "Running");
            telemetry.update();



            if(tablePower){
                leftTableMotor.setPower(0.5);
                rightTableMotor.setPower(-duckSpinPower);
            }
            if(reverseTablePower){
                leftTableMotor.setPower(-0.5);
                rightTableMotor.setPower(duckSpinPower);
            }
            if(!reverseTablePower && !tablePower){
                leftTableMotor.setPower(0);
                rightTableMotor.setPower(0);
            }

            double fl = y - x - clockwise;
            double fr = y + x + clockwise;
            double bl = y + x - clockwise;
            double br = y - x + clockwise;



            motor1.setPower(fl);
            motor2.setPower(-bl);
            motor3.setPower(br);
            motor4.setPower(fr);
            
            telemetry.update();
        }
    }
}