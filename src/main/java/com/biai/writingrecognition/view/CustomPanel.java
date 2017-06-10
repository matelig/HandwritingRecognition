/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biai.writingrecognition.view;

/**
 *
 * @author alachman
 */
import com.biai.writingrecognition.DownsampledData;
import javax.swing.*;
import java.awt.*;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

public class CustomPanel extends JPanel {

    protected ArrayList<Section> sections = new ArrayList<>();
    private int width;
    private int height;
    private int count;
    private DownsampledDataJPanel downsampledDataJPanel;

    public CustomPanel() {
        super();
    }

    public void init(int w, int h, int count) {
        this.width = w;
        this.height = h;
        this.count = count;

        setPreferredSize(new Dimension(w, h));
        setBackground(Color.WHITE);

        generateSections();
    }

    private void generateSections() {
        sections = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                sections.add(new Section(i * (width / count), j * (height / count), width / count, height / count));
            }
        }

        repaint();
    }

    public DownsampledDataJPanel getDownsampledDataJPanel() {
        return downsampledDataJPanel;
    }

    public void setDownsampledDataJPanel(DownsampledDataJPanel downsampledDataJPanel) {
        this.downsampledDataJPanel = downsampledDataJPanel;
    }

    public void downsample() {
        final int width = getWidth();
        final int height = getHeight();

        DownsampledData data = downsampledDataJPanel.getDownsampledData();

        for (int y = 0; y < data.getHeight(); y++) {
            for (int x = 0; x < data.getWidth(); x++) {
                if (getPixelAt(x, y)) {
                    data.setPixelData(x, y, true);
                } else {
                    data.setPixelData(x, y, false);
                }
            }
        }

//        downsampledDataJPanel.repaint();
        repaint();

    }

    private boolean getPixelAt(int x, int y) {

        if ((getPixels().get((x*count) + y)) == 1) {
            return true;
        }
        else return false;
                
            
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        generateSections(g);
        drawSections(g);

    }

    private void generateSections(Graphics g) {
        if (g == null) {
            return;
        }
        if (width == 0) {
            return;
        }
        g.setColor(Color.BLACK);

        for (Section s : sections) {
            g.drawLine(0, s.getY(), width, s.getY());
            g.drawLine(s.getX(), 0, s.getX(), height);
        }
    }

    private void drawSections(Graphics g) {
        g.setColor(Color.BLACK);
        if (sections == null) {
            return;
        }
        for (Section s : sections) {
            if (s.isActive()) {
                g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
            }
        }
    }

    public ArrayList<Integer> getPixels() {
        ArrayList<Integer> pixels = new ArrayList<>();
        for (Section s : sections) {
            if (s.isActive()) {
                pixels.add(1);
            } else {
                pixels.add(0);
            }
        }

        return pixels;
    }

    public void clear() {
        for (Section s : sections) {
            s.setActive(false);
        }

        repaint();
    }

    public void drawLetter(ArrayList<Integer> pixels) {
        for (int i = 0; i < pixels.size(); i++) {
            if (pixels.get(i) == 1) {
                sections.get(i).setActive(true);
            } else {
                sections.get(i).setActive(false);
            }
        }

        repaint();
    }

    public ArrayList<Section> getSections() {
        return sections;
    }
}
