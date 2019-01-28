package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.RobotInit;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;

@Disabled
@Autonomous(name = "depo", group = "final")
public class depo_test extends LinearOpMode{

    private RobotInit robot;


    final int tetrix = 2750;
    final int newrest = 2136;

    final float dis = (float)2.1213;

    public void runOpMode(){

        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, false);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int disNewrest = Math.round(dis * newrest);
        int disTetrix = Math.round(dis * tetrix);


        robot.leftBackDrive.setTargetPosition(-disNewrest);
        robot.leftFrontDrive.setTargetPosition(disTetrix);
        robot.rightBackDrive.setTargetPosition(disNewrest);
        robot.rightFrontDrive.setTargetPosition(-disNewrest);
        robot.SetMotorPower(0.3);

        while(opModeIsActive() && robot.leftFrontDrive.getCurrentPosition() < disTetrix){
            telemetry.addLine("Status: Running")
                    .addData("left back current position: ", robot.leftBackDrive.getCurrentPosition())
                    .addData("left front current position: ", robot.leftFrontDrive.getCurrentPosition())
                    .addData("right back current position: ", robot.rightBackDrive.getCurrentPosition())
                    .addData("right front current position: ", robot.rightFrontDrive.getCurrentPosition())
                    .addData("left back current power: ", robot.leftBackDrive.getPower())
                    .addData("left front current power: ", robot.leftFrontDrive.getPower())
                    .addData("right back current power: ", robot.rightBackDrive.getPower())
                    .addData("right front current power: ", robot.rightFrontDrive.getPower());

        }
        robot.stopMotors();
        robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);




        robot.marker.setPosition(0.20);



    }


}