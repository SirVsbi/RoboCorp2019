package org.firstinspires.ftc.teamcode.Auto;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.RobotInit;
//import org.firstinspires.ftc.teamcode.libs.RobotUtils;


@Disabled
@Autonomous(name ="blue depo", group = "test")
public class blue_depo extends LinearOpMode{


    private RobotInit robot;


    public void runOpMode(){

        robot = new RobotInit();
        waitForStart();
        robot.init(hardwareMap, true);
        robot.sleep(2000);
        robot.setEncoderBlocks((float) 2.12, "forward");


    }


}