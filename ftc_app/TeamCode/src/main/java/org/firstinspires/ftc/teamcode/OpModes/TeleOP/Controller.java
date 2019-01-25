package org.firstinspires.ftc.teamcode.OpModes.TeleOP;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.libs.RobotInit;
import static java.lang.Math.abs;

@TeleOp(name = "Drive", group = "Linear Opmode")
public class Controller extends LinearOpMode{

    private RobotInit robot = null;

    private final double smallPower = 0.2;
    private double bigPower = 0.6;
    private final double powerLimit = 0.8;
    private final double limitMoveTurnStop = 0.51;
    private double liftPower = 0.12;



    public void setLiftPower(double _liftPower){
        liftPower = _liftPower;
    }

    public double getLiftPower(){
        return liftPower;
    }

    public double getBigPower(){
        return bigPower;
    }


    @Override
    public void runOpMode(){
        robot = new RobotInit();
        robot.init(hardwareMap, false);
        waitForStart();
        while(opModeIsActive()){
            movTurn(gamepad1.left_stick_x, gamepad1.left_stick_y);
            lift(gamepad1.a, gamepad1.b, gamepad1.x, gamepad1.y);
            telemetry.addLine()
                    .addData("Drive bigPower", getBigPower())
                    .addData("Lift power: ", getLiftPower())
                    .addData("Left Back Drive power: ", robot.leftBackDrive.getPower())
                    .addData("Left Front Drive power:", robot.leftFrontDrive.getPower())
                    .addData("Right Back Drive power: ", robot.rightBackDrive.getPower())
                    .addData("Right Front Drive power: ", robot.rightFrontDrive.getPower());


            // TODO: Fix next line
            //robot.telemetry.printMotors();
        }
    }

    private void movTurn(double stickX, double stickY)
    {

        setBigPower(gamepad1.dpad_down, gamepad1.dpad_up);
        if (abs(stickX) >= limitMoveTurnStop)
        {
            if (abs(stickX) <= powerLimit)
            {
                robot.leftBackDrive.setPower(-smallPower * (stickX / abs(stickX)));
                robot.leftFrontDrive.setPower(-smallPower * (stickX / abs(stickX)));
                robot.rightBackDrive.setPower(-smallPower * (stickX / abs(stickX)));
                robot.rightFrontDrive.setPower(-smallPower * (stickX / abs(stickX)));
            }
            else
            {
                robot.leftBackDrive.setPower(-bigPower * (stickX / abs(stickX)));
                robot.leftFrontDrive.setPower(-bigPower * (stickX / abs(stickX)));
                robot.rightBackDrive.setPower(-bigPower * (stickX / abs(stickX)));
                robot.rightFrontDrive.setPower(-bigPower * (stickX / abs(stickX)));
            }
        }
        else if (abs(stickY) >= limitMoveTurnStop)
        {
            if (abs(stickY) <= powerLimit)
            {
                robot.leftBackDrive.setPower(smallPower * (stickY / abs(stickY)));
                robot.leftFrontDrive.setPower(smallPower * (stickY / abs(stickY)));
                robot.rightBackDrive.setPower(-smallPower * (stickY / abs(stickY)));
                robot.rightFrontDrive.setPower(-smallPower * (stickY / abs(stickY)));
            }
            else
            {
                robot.leftBackDrive.setPower(bigPower * (stickY / abs(stickY)));
                robot.leftFrontDrive.setPower(bigPower * (stickY / abs((stickY))));
                robot.rightBackDrive.setPower(-bigPower * (stickY / abs(stickY)));
                robot.rightFrontDrive.setPower(-bigPower * (stickY / abs(stickY)));
            }
        }
        else
        {
            robot.leftBackDrive.setPower(0);
            robot.leftFrontDrive.setPower(0);
            robot.rightBackDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
        }
    }

    private void lift(boolean key_a, boolean key_b, boolean key_x, boolean key_y)
    {
        if(key_x && liftPower < 1.0){
            liftPower += 0.01;
        }
        if(key_y && liftPower > 0.0){
            liftPower -= 0.01;
        }
        if (key_a)
        {
            robot.leftLiftMotor.setPower(liftPower);
            robot.rightLiftMotor.setPower(-liftPower);
        }
        else if (key_b)
        {
            robot.leftLiftMotor.setPower(-liftPower);
            robot.rightLiftMotor.setPower(liftPower);
        }
        else
        {
            robot.leftLiftMotor.setPower(0);
            robot.rightLiftMotor.setPower(0);
        }

    }


    private void setBigPower(boolean down, boolean up){
        if(down && bigPower > 0.3){
            bigPower -= 0.1;
        }
        else if(up && bigPower < 1.0){
            bigPower += 0.1;
        }
    }
}