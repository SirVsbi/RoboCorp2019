package org.firstinspires.ftc.teamcode.OpModes.TeleOP;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.libs.RobotInit;

import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;

import static java.lang.Math.abs;

@TeleOp(name = "Drive", group = "Test")
public  class Controller extends LinearOpMode{

    private RobotInit robot = null;
    AndroidTextToSpeech TTS = new AndroidTextToSpeech();

    private final double smallPower = 0.2;
    private final double bigPower = 0.65;
    private final double powerLimit = 0.8;
    private final double limitMoveTurnStop = 0.51;



    @Override
    public void runOpMode(){
        TTS.initialize();
        robot = new RobotInit();
        robot.init(hardwareMap, false);
        waitForStart();
        while(opModeIsActive()){


            if (gamepad1.left_stick_y >= limitMoveTurnStop || gamepad1.left_stick_y <= -limitMoveTurnStop) {

                if (gamepad1.left_stick_y <= powerLimit && gamepad1.left_stick_y>= -powerLimit){
                    robot.leftBackDrive.setPower(-smallPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.leftFrontDrive.setPower(-smallPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.rightBackDrive.setPower(-smallPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.rightFrontDrive.setPower(-smallPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                }

                if(gamepad1.left_stick_y > powerLimit || gamepad1.left_stick_y < -powerLimit){

                    robot.leftBackDrive.setPower(-bigPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.leftFrontDrive.setPower(-bigPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.rightBackDrive.setPower(-bigPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.rightFrontDrive.setPower(-bigPower * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                }
            }

            else {
                // TODO: Remove the next line for the competition
                TTS.speak("Hey hey Monica, hey padi Monica!");
                if (gamepad1.left_stick_x > limitMoveTurnStop || gamepad1.left_stick_x < -limitMoveTurnStop) {

                    if (gamepad1.left_stick_x <= powerLimit && gamepad1.left_stick_x >= -powerLimit){

                        robot.leftBackDrive.setPower(smallPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.leftFrontDrive.setPower(smallPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.rightBackDrive.setPower(-smallPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.rightFrontDrive.setPower(-smallPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                    }
                    if (gamepad1.left_stick_x > powerLimit || gamepad1.left_stick_x < -powerLimit){

                        robot.leftBackDrive.setPower(bigPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.leftFrontDrive.setPower(bigPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.rightBackDrive.setPower(-bigPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.rightFrontDrive.setPower(-bigPower * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                    }
                }
                else{

                    robot.leftBackDrive.setPower(0);
                    robot.leftFrontDrive.setPower(0);
                    robot.rightBackDrive.setPower(0);
                    robot.rightFrontDrive.setPower(0);
                }
            }






            telemetry.addLine()
                    .addData("Status: ", "Running")
                    .addData("Left drive power: ", robot.leftBackDrive.getPower())
                    .addData("Right drive power:", robot.rightBackDrive.getPower());
            telemetry.update();

        }


    }


}