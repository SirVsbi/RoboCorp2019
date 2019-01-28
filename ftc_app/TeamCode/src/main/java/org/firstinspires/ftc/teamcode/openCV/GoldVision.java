/*package org.firstinspires.ftc.teamcode.openCV;


import android.provider.ContactsContract;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;


public class GoldVision extends OpenCVPipeline{


    private boolean showContours = true;


    private Mat hsv = new Mat();
    private Mat thresholded = new Mat();


    private List<MatOfPoint> contours = new ArrayList<>();


    public synchronized void setShowContours(boolean enabled){
        showContours = enabled;
    }


    public synchronized List<MatOfPoint> getContours(){
        return contours;
    }




    @Override
    public Mat processFrame(Mat rgba, Mat gray){
        Imgproc.cvtColor(rgba, hsv, Imgproc.COLOR_RGB2HSV, 3);

        Core.inRange(hsv, new Scalar(90, 128, 30), new Scalar(170,255,255), thresholded);

        Imgproc.blur(thresholded, thresholded, new Size(3,3));


        contours = new ArrayList<>();

        Imgproc.findContours(thresholded, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);


        if(showContours){
            Imgproc.drawContours(rgba, contours, -1, new Scalar(0,255,0), 2, 8 );
        }

        return rgba;
    }

}
*/