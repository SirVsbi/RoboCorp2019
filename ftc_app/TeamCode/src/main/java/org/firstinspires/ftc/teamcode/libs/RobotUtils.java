package org.firstinspires.ftc.teamcode.libs;


import com.qualcomm.robotcore.hardware.DcMotor;

import static java.lang.Math.abs;


public class RobotUtils extends RobotInit{



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

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //TODO check motors(newrest or tetrix)

        if(direction == "back"){
            leftDrive.setTargetPosition(-numBlocksNewrest);
            rightDrive.setTargetPosition(-numBlocksNewrest);
            this.SetMotorPower(0.3, "back");

            //TODO fix while issue: add check if the robot is still allowed to move
            while(leftDrive.getCurrentPosition() > numBlocksNewrest){
            }
            leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        }
        else{
            leftDrive.setTargetPosition(numBlocksNewrest);
            rightDrive.setTargetPosition(numBlocksNewrest);
            this.SetMotorPower(0.3);

            //TODO fix while issue: add check if the robot is still allowed to move
            while(leftDrive.getCurrentPosition() < numBlocksNewrest){
            }
            leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }



    }



    /*Sets the power for the motors,
    *Soon to be deprecated in favor of a pid controller
    *
    *@param double power - power given to the motors from the
    *[-1, 1] interval
    */


    public void SetMotorPower(double power){
        if(power > 1.0){
            power = 1.0;
        }
        if(power < -1.0){
            power = -1.0;
        }
        leftDrive.setPower(power);
        rightDrive.setPower(power);
    }


    public void SetMotorPower(double power, String direction){
        if(direction == "back"){
            this.SetMotorPower(-power);
        }
        else
            this.SetMotorPower(power);
    }


    public void SetMotorPowerRotation(double power){
        leftDrive.setPower(power);
        rightDrive.setPower(-power);

    }

    public void SetMotorPowerRotationCclw(double power){
        leftDrive.setPower(-power);
        rightDrive.setPower(power);
    }



    //when time runs out during an OpMode motors are stopped to avoid penalty
    public void stopMotors(){
        leftDrive.setPower(0);
        rightDrive.setPower(0);
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
