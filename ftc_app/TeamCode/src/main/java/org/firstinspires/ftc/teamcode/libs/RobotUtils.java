package org.firstinspires.ftc.teamcode.libs;


public class RobotUtils extends RobotInit{


    public void setEncoderBlocks(float a, String direction){

        //the number of rotations for a tetrix motor to travers
        //a <here comes the width of a square>
        final int tetrix = 2750;
        final int newrest = 2136;
        int numBlocksTetrix = Math.round(tetrix * a);
        int numBlocksNewrest = Math.round(newrest * a);
    }



    //Sets the power for the motors,
    //Soon to be deprecated in favor of a pid controller
    //
    //TODO implement PID controller
    //
    //@param double power - power given to the motors from the
    //[-1, 1] interval
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




}
