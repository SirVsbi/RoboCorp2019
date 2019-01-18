package org.firstinspires.ftc.teamcode.libs;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.teamcode.libs.Telemetry_RoboCorp;
import  com.qualcomm.robotcore.util.ElapsedTime;

public class RobotInit{


    public DcMotor leftBackDrive = null;
    public DcMotor leftFrontDrive = null;
    public DcMotor rightBackDrive = null;
    public DcMotor rightFrontDrive = null;

    public DcMotor leftLiftMotor = null;
    public DcMotor rightLiftMotor = null;

    public Telemetry_RoboCorp telemetry = null;

    public ElapsedTime time = null;


    private boolean _isAuto = false;


    public Gyroscope gyroscope = null;

    HardwareMap _hwMap = null;


    //called at the start of an OpMode
    //maps the motors and the sensors from the hardwaremap
    public void init(HardwareMap hwMap, boolean isAuto) {
        _hwMap = hwMap;
        leftBackDrive = _hwMap.dcMotor.get("leftBack_drive");
        leftFrontDrive =_hwMap.dcMotor.get("leftFront_drive");
        rightBackDrive = _hwMap.dcMotor.get("rightBack_drive");
        rightFrontDrive = _hwMap.dcMotor.get("rightFront_drive");
        leftLiftMotor = _hwMap.dcMotor.get("leftLift_motor");
        rightLiftMotor = _hwMap.dcMotor.get("rightLift_motor");
        telemetry = new Telemetry_RoboCorp();
        time = new ElapsedTime();

        //TODO: check motor rotations

        //TODO: add arm motors

        //setting drive direction, so motors rotate "as expected"
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);


        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        //sets whether robot is in autonomous mode or controlled mode
        if(isAuto){
            _isAuto = true;
            initAuto();
            gyroscope.init(_hwMap);
        }
    }

    public void initAuto(){
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


}


