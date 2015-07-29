/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamfacedetect;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author duo
 */
public class Processor {

    private final CascadeClassifier face_cascade;

    // Create a constructor method  
    public Processor() {
        String CASCADEFILE = getClass().
                getResource("/res/classifiers/haarcascade_frontalface_alt.xml").getPath();
        System.out.println("Cascade file: " + CASCADEFILE);
        face_cascade = new CascadeClassifier(CASCADEFILE);
        if (face_cascade == null) {
            System.out.println(String.format("Error loading %s", CASCADEFILE));
            System.exit(1);
        } else {
            System.out.println("Face classifier correctly loaded.");
        }
    }

    public Mat detect(Mat inputframe) {
        Mat mRgba = new Mat();
        Mat mGrey = new Mat();
        MatOfRect faces = new MatOfRect();
        inputframe.copyTo(mRgba);
        inputframe.copyTo(mGrey);
        Imgproc.cvtColor(mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(mGrey, mGrey);
        face_cascade.detectMultiScale(mGrey, faces);
        System.out.println(String.format("Detected %s faces.", 
                faces.toArray().length));
        for (Rect rect : faces.toArray()) {
            Point center = new Point(rect.x + rect.width * 0.5, 
                    rect.y + rect.height * 0.5);
            Imgproc.ellipse(mRgba, center, new Size(rect.width * 0.5, 
                    rect.height * 0.5), 0, 0, 360, new Scalar(255, 0, 255), 4, 8, 0);
        }
        return mRgba;
    }
}
