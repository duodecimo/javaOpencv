/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamfacedetect;

import java.util.Iterator;
import javax.swing.JFrame;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author duo
 */
public class WebcamFaceDetect {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Load the native library.
        Libloader.load();
        String window_name = "Capture - Face detection";
        JFrame frame = new JFrame(window_name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 800);
        FaceDetect faceDetect = new FaceDetect();
        Processor my_processor = new Processor();
        frame.setContentPane(faceDetect);
        frame.setVisible(true);
        // Read the video stream  
        Mat webcam_image = new Mat();
        VideoCapture capture = null;
        for (int numCam = 0; numCam < 5; numCam++) {
            try {
                capture = new VideoCapture(numCam);
                break;
            } catch (Exception e) {
                System.out.println("Webcam number " + numCam + " unavailable ...");
            }
        }
        if (capture.isOpened()) {
            while (true) {
                capture.read(webcam_image);
                if (!webcam_image.empty()) {
                    frame.setSize(webcam_image.width() + 40, webcam_image.height() + 60);
                    // Apply the classifier to the captured image  
                    webcam_image = my_processor.detect(webcam_image);
                    // Display recognized image  
                    faceDetect.MatToBufferedImage(webcam_image);
                    faceDetect.repaint();
                } else {
                    System.out.println("No captured frame. Exit.");
                    break;
                }
            }
        }
    }
    
}
