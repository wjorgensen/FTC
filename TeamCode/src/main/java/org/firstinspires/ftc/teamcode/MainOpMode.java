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
    private DcMotor tableMotor;
    private Servo arm1;
    private Servo arm2;
    private DcMotor slider;


    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        tableMotor = hardwareMap.get(DcMotor.class, "motorT");
        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");
        slider = hardwareMap.get(DcMotor.class, "slider");

        //slider.setPower(-0.12);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double leftPower = 0;
        double rightPower = 0;
        double leftsideways = 0;
        double rightsideways = 0;
        boolean tablePower = false;
        boolean armFlipFlop = false;
        boolean sliderPowerUp = false;
        boolean sliderPowerDown = false;
        boolean reverseTablePower = false;
        boolean arms = false;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            leftPower = this.gamepad1.left_stick_y;
            rightPower = -this.gamepad1.right_stick_y;
            leftsideways = -this.gamepad1.left_stick_x;
            rightsideways = -this.gamepad1.right_stick_x;


            tablePower = this.gamepad1.right_bumper;
            reverseTablePower = this.gamepad1.left_bumper;
            arms = this.gamepad1.x;
            sliderPowerUp = this.gamepad1.y;
            sliderPowerDown = this.gamepad1.a;

            if(leftPower != 0 || rightPower != 0) {
                motor1.setPower(leftPower);
                motor2.setPower(-leftPower);
                motor3.setPower(rightPower);
                motor4.setPower(rightPower);
            }

            if (tablePower)
            {
                tableMotor.setPower(0.75);
            }
            if(!tablePower){
                tableMotor.setPower(0);
            }
            if (reverseTablePower)
            {
                tableMotor.setPower(-0.75);
            }
            if(!reverseTablePower){
                tableMotor.setPower(0);
            }
            if(sliderPowerUp && !sliderPowerDown){
                slider.setPower(-0.5);
            }
            if(!sliderPowerUp && sliderPowerDown){
                slider.setPower(0.5);
            }
            if(!sliderPowerUp && !sliderPowerDown){
                slider.setPower(-0.08);
            }
            if(arms){
               if(armFlipFlop){
                   armFlipFlop = false;
               }
               else{
                   armFlipFlop = true;
               }
            }
            if(armFlipFlop){
                arm1.setPosition(0.0);
                arm2.setPosition(1.0);
            }
            if(!armFlipFlop){
                arm1.setDirection(Servo.Direction.FORWARD);
                arm2.setDirection(Servo.Direction.REVERSE);
                arm2.setPosition(0.15);
                arm1.setPosition(0.15);
            }
            telemetry.update();
        }
    }
}