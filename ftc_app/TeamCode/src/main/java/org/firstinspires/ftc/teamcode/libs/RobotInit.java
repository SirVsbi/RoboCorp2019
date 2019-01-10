package org.firstinspires.ftc.teamcode.libs;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;


public class RobotInit{


    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;



    private boolean _isAuto = false;


    public Gyroscope gyroscope = null;

    HardwareMap _hwMap = null;


    //called at the start of an OpMode
    //maps the motors and the sensors from the hardwaremap
    public void init(HardwareMap hwMap, boolean isAuto) {
        _hwMap = hwMap;
        leftDrive = _hwMap.dcMotor.get("left_drive");
        rightDrive = _hwMap.dcMotor.get("right_drive");


        //setting drive direction, so motors rotate "as expected"
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);


        //sets whether robot is in autonomous mode or controlled mode
        if(isAuto){
            _isAuto = true;
            initAuto();
            gyroscope.init(_hwMap);
        }
    }

    public void initAuto(){
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


}
