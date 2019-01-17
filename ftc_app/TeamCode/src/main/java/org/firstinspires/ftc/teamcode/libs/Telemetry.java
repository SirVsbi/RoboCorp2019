package org.firstinspires.ftc.teamcode.libs;

import org.firstinspires.ftc.robotcore.external.Telemetry;


class Telemetry_RoboCorp extends RobotInit {

    Telemetry telemetry;


    void generalPrint(){
        telemetry.addLine("Running")
                .addData("Elapsed time", time.seconds());

    }

    void printMotors(){
        generalPrint();
        telemetry.addLine()
                .addData("Left Back Drive power: ", leftBackDrive.getPower())
                .addData("Left Front Drive power:", leftFrontDrive.getPower())
                .addData("Right Back Drive power: ", rightBackDrive.getPower())
                .addData("Right Front Drive power: ", rightFrontDrive.getPower());
        telemetry.update();
    }


    void printRotation(){
        generalPrint();
        printMotors();
        telemetry.addLine()
                .addData("Current angle", gyroscope.getAngle());
        telemetry.update();

    }

}
