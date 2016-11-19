package com.awesome.hardware;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Danil on 19.11.16.
 */
public class WebcamSupport {

    private static Webcam webcam;

    public static void init() {
        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(176, 144));
        webcam.open();
    }
    /**
     * Shows live feed of webcam
     */
    public static void showWebCamLiveFeed(){
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        JFrame window = new JFrame("Test webcam panel");
        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    /**
     * Get a snapshot from webcam
     * @return byte array of jpeg format
     */
    public static byte[] getSnapshot(){
        return WebcamUtils.getImageBytes(webcam, ImageUtils.FORMAT_JPG);
    }
}
