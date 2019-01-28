/*package org.firstinspires.ftc.teamcode.openCV;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import java.util.List;
import java.util.Locale;

@TeleOp(name="OpenCV test")
public class openCV_test extends OpMode{

    private GoldVision goldVision;


    @Override
    public void init(){
        goldVision = new GoldVision();
        goldVision.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        goldVision.setShowContours(true);

        goldVision.enable();

    }


    @Override
    public void loop(){
        List<MatOfPoint> contours = goldVision.getContours();
        for(int i = 0; i < contours.size(); i++) {
            Rect boundingRect = Imgproc.boundingRect(contours.get(i));
            telemetry.addData("contour" + Integer.toString(i),
                    String.format(Locale.getDefault(), "(%d, %d)", (boundingRect.x + boundingRect.width) / 2, (boundingRect.y + boundingRect.height) / 2));
        }
    }

}*/