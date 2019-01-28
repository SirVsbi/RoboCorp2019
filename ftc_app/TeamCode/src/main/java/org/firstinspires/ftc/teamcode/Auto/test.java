package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.RobotInit;
import com.qualcomm.robotcore.hardware.DcMotor;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
@Autonomous(name ="test", group = "final")
public class test extends LinearOpMode {

    private  RobotInit robot;

    public void runOpMode(){

        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, false);
        robot.sleep(5000);
        robot.SetMotorPower(0.7

        );
        robot.sleep(2000);
        robot.stopMotors();


    }

}