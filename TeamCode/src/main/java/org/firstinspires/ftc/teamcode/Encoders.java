package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class Encoders extends LinearOpMode {

    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private DcMotor tableMotor;
    private Servo arm1;
    private Servo arm2;
    private DcMotor slider;

    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        tableMotor = hardwareMap.get(DcMotor.class, "motorT");
        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");
        slider = hardwareMap.get(DcMotor.class, "slider");

        waitForStart();

        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setPosition(360);






    }

    public void setPosition(int ticks){
        motor1.setTargetPosition(ticks);
        motor2.setTargetPosition(ticks);
        motor3.setTargetPosition(ticks);
        motor4.setTargetPosition(ticks);

        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int forward(int power) {
        motor1.setPower(power);
    }
    public int backward(int motor) {
        switch (motor) {
            case 1:
                return 1;
            case 2:
                return -1;
            case 3:
                return -1;
            case 4:
                return -1;
            default:
                return 0;
        }
    }
    public void turnRight(){
        motor1.setPower(backward(1));
        motor2.setPower(backward(2));
        motor3.setPower(forward(3));
        motor4.setPower(forward(4));
    }
    public void turnLeft(){
        motor1.setPower(forward(1));
        motor2.setPower(forward(2));
        motor3.setPower(backward(3));
        motor4.setPower(backward(4));
    }

    public void distance(double feet){
        double constant = 7.5/1000;
        sleep((long) (feet/constant));
    }
}
