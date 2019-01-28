package org.firstinspires.ftc.teamcode.libs;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;
//import org.firstinspires.ftc.teamcode.libs.Telemetry_RoboCorp;
import  com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;


//import org.firstinspires.ftc.teamcode.libs.RobotUtils;

public class RobotInit{


    public DcMotor leftBackDrive = null;
    public DcMotor leftFrontDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotor rightFrontDrive = null;

    public DcMotor leftLiftMotor = null;
    public DcMotor rightLiftMotor = null;

    public Telemetry telemetry = null;

    public ElapsedTime time = null;


    private boolean _isAuto = false;


    public Servo marker = null;

    public Gyroscope gyroscope = null;

    HardwareMap _hwMap = null;


    //called at the start of an OpMode
    //maps the motors and the sensors from the hardwaremap
    public void init(HardwareMap hwMap, boolean isAuto) {
        _hwMap = hwMap;
        leftBackDrive = _hwMap.dcMotor.get("backleft_drive");
        leftFrontDrive =_hwMap.dcMotor.get("frontleft_drive");
        rightBackDrive = _hwMap.dcMotor.get("backright_drive");
        rightFrontDrive = _hwMap.dcMotor.get("frontright_drive");
        leftLiftMotor = _hwMap.dcMotor.get("left_lift");
        rightLiftMotor = _hwMap.dcMotor.get("right_lift");
        marker = _hwMap.servo.get("marker");
        time = new ElapsedTime();

        //TODO: check motor rotations

        //setting drive direction, so motors rotate "as expected"
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);


        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

       //sets whether robot is in autonomous mode or controlled mode
        if(isAuto){
            gyroscope = new Gyroscope();
            _isAuto = true;
            initAuto();
            gyroscope.init(_hwMap);
            marker.setPosition(0.70);
        }
    }

    public void initAuto(){
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    /*
     * @param float dis - the number of field's
     * @param String direction - the direction to move:
     *      "back" - the robot moves backwards
     *      anything else - the robot moves forwards
     */

    public void setEncoderBlocks(float dis, String direction){

        //constants, the number of rotations for an encoder to travel a "field length" (23.5 inch)
        final int tetrix = 2750;
        final int newrest = 2136;
        int numBlocksTetrix = Math.round(tetrix * dis);
        int numBlocksNewrest = Math.round(newrest * dis);

        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftBackDrive.setTargetPosition(-numBlocksNewrest);
            leftFrontDrive.setTargetPosition(numBlocksTetrix);
            rightBackDrive.setTargetPosition(-numBlocksNewrest);
            rightFrontDrive.setTargetPosition(numBlocksTetrix);
            this.SetMotorPower(0.3);

            //TODO fix while issue: add check if the robot is s
        // till allowed to move
            while(leftFrontDrive.getCurrentPosition() < 2750 * dis){

            }
            leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);




    }



    /*Sets the power for the motors,
     *Soon to be deprecated in favor of a pid controller
     *
     *@param double power - power given to the motors from the
     *[-1, 1] interval
     */


    public void SetMotorPower(double power){

        leftBackDrive.setPower(-power);
        leftFrontDrive.setPower(-power);
        rightBackDrive.setPower(power);
        rightFrontDrive.setPower(power);
    }


    public void SetMotorPower(double power, String direction){
        if(direction == "back"){
            this.SetMotorPower(power);
        }
        else
            this.SetMotorPower(-power);
    }


    public void SetMotorPowerRotation(double power){
        leftBackDrive.setPower(power);
        leftFrontDrive.setPower(power);
        rightBackDrive.setPower(-power);
        rightFrontDrive.setPower(-power);

    }

    public void SetMotorPowerRotationCclw(double power){
        leftBackDrive.setPower(-(power + 0.1) );
        leftFrontDrive.setPower(-(power + 0.1));
        rightBackDrive.setPower(-power);
        rightFrontDrive.setPower(-power);
    }



    //when time runs out during an OpMode motors are stopped to avoid penalty
    public void stopMotors(){
        leftBackDrive.setPower(0);
        leftFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
    }



    // Since the TeamCode is single threaded,
    // to stop the robot we just sleep the current thread
    //
    // @param int millisec the time to sleep in milliseconds
    public void sleep(int millisec){
        try{
            Thread.sleep(millisec);
        }catch (InterruptedException ex){
            //TODO print exception in telemetry
        }
    }





    //TODO add telemetry
    public void turn(int target, int range, double minSpeed, double addSpeed){
        boolean Cclw = (target > 180 || target < 0);
        int current = gyroscope.getAngle();
        while(!isok(current, target, range)){

            current = gyroscope.getAngle();
            int mod = target - current;
            mod /= 30;
            telemetry.addLine()
                    .addData("current angle: ", current )
                    .addData("speed: ", minSpeed + addSpeed * Math.abs(mod));
            telemetry.update();
            if(Cclw)
                this.SetMotorPowerRotationCclw(minSpeed + addSpeed * Math.abs(mod));
            else
                this.SetMotorPowerRotation(minSpeed + addSpeed * Math.abs(mod));
        }
        this.stopMotors();
        gyroscope.reset();
    }







    private boolean isok(int a, int b, int c){
        return ((a >= b - c && a <= b + c));
    }




}


