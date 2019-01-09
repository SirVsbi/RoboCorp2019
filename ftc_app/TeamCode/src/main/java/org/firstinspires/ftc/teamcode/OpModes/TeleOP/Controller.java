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


    @Override
    public void runOpMode(){
        TTS.initialize();
        robot = new RobotInit();
        robot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            if (gamepad1.left_stick_y >= 0.51 || gamepad1.left_stick_y <= -0.51) {

                if (gamepad1.left_stick_y <= 0.8 && gamepad1.left_stick_y>= -0.8){
                    robot.leftDrive.setPower(-0.2 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.rightDrive.setPower(-0.2 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                }

                if(gamepad1.left_stick_y > 0.8 || gamepad1.left_stick_y < -0.8){

                    robot.leftDrive.setPower(-0.65 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                    robot.rightDrive.setPower(-0.65 * (gamepad1.left_stick_y / abs(gamepad1.left_stick_y)));
                }
            }

            else {
               // TTS.speak("");
                if (gamepad1.left_stick_x >= 0.52 || gamepad1.left_stick_x <= -0.52) {

                    /*/robot.backLeftDrive.setPower(gamepad1.left_stick_x/2);
                    robot.backRightDrive.setPower(-gamepad1.left_stick_x/2);
                    robot.leftDrive.setPower(gamepad1.left_stick_x/2);
                    robot.rightDrive.setPower(-gamepad1.left_stick_x/2);/*/

                    if (gamepad1.left_stick_x <= 0.8 && gamepad1.left_stick_x >= -0.8){

                        robot.leftDrive.setPower(0.2 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.rightDrive.setPower(-0.2 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                    }
                    if (gamepad1.left_stick_x > 0.8 || gamepad1.left_stick_x < -0.8){

                        robot.leftDrive.setPower(0.65 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                        robot.rightDrive.setPower(-0.65 * (gamepad1.left_stick_x / abs(gamepad1.left_stick_x)));
                    }
                }
                else{

                    robot.leftDrive.setPower(0);
                    robot.rightDrive.setPower(0);
                }
            }






            telemetry.addLine()
                    .addData("Status: ", "Running")
                    .addData("Left drive power: ", robot.leftDrive.getPower())
                    .addData("Right drive power:", robot.rightDrive.getPower());
            telemetry.update();

        }


    }


}