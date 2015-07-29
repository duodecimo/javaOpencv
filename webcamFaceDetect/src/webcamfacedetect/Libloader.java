/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamfacedetect;

import org.opencv.core.Core;

/**
 *
 * @author duo
 */
public class Libloader {
    public static void load() {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
