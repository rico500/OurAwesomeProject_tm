package com.awesome.gui;

import com.awesome.cognitive.Colours;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

/**
 * Created by ebrunner on 20/11/16.
 */
public class GUI {

    private JFrame mainFrame;
    private JPanel SamplesPanel;
    private JPanel SuddenPanel;
    private WebcamPanel VideoPanel;

    Webcam webcam;

    public GUI(Webcam webcam) {
        this.webcam = webcam;
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Demo Control Panel");
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(new GridLayout(3 , 1));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SamplesPanel = new JPanel();
        SamplesPanel.setLayout(new FlowLayout());
        VideoPanel = new WebcamPanel(webcam);
        VideoPanel.setFPSDisplayed(true);
        VideoPanel.setDisplayDebugInfo(true);
        VideoPanel.setImageSizeDisplayed(true);
        VideoPanel.setMirrored(true);
        SuddenPanel = new JPanel();
        SuddenPanel.setLayout(new FlowLayout());
        SuddenPanel.setPreferredSize(new Dimension(50, 600));

        mainFrame.add(SamplesPanel);
        mainFrame.add(SuddenPanel);
        mainFrame.add(VideoPanel);

        JLabel historySamplesSliderLabel = new JLabel("Number of history samples");
        SamplesPanel.add(historySamplesSliderLabel);

        JSlider historySamplesSlider = new JSlider(JSlider.HORIZONTAL,
                1, 17, Colours.getEmotionQueueLength());
        SamplesPanel.add(historySamplesSlider);
        historySamplesSlider.addChangeListener(new HistorySamplesSliderListener());
        historySamplesSlider.setMajorTickSpacing(8);
        historySamplesSlider.setMinorTickSpacing(4);
        historySamplesSlider.setPaintTicks(true);
        historySamplesSlider.setPaintLabels(true);

        JLabel SuddenEmotionChangeLabel = new JLabel("Detect Sudden Emotion Changes");
        SuddenPanel.add(SuddenEmotionChangeLabel);

        JCheckBox SuddenEmotionChangeCheckBox = new JCheckBox("", Colours.getDetectSuddenEmotions());
        SuddenPanel.add(SuddenEmotionChangeCheckBox);
        SuddenEmotionChangeCheckBox.addChangeListener(new SuddenEmotionChangeCheckBoxListener());


        mainFrame.setVisible(true);
    }

    private class HistorySamplesSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e){
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                Colours.setEmotionQueueLength(source.getValue());

            }
        }

    }

    private class SuddenEmotionChangeCheckBoxListener implements ChangeListener{
        public void stateChanged(ChangeEvent e ){
            JCheckBox source = (JCheckBox)e.getSource();
            Colours.setDetectSuddenEmotions(source.isSelected());
        }
    }

}