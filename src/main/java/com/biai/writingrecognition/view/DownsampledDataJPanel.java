package com.biai.writingrecognition.view;

import com.biai.writingrecognition.Config;
import com.biai.writingrecognition.DownsampledData;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author alachman
 */
public class DownsampledDataJPanel extends JPanel {

    private DownsampledData downsampledData;

    public DownsampledDataJPanel() {
        downsampledData = new DownsampledData(' ', Config.DOWNSAMPLE_WIDTH, Config.DOWNSAMPLE_HEIGHT);

    }

    public DownsampledData getDownsampledData() {
        return downsampledData;
    }

    public void setData(DownsampledData downsampledData) {
        this.downsampledData = downsampledData;
    }

    @Override
    public void paint(Graphics graphics) {
        if (downsampledData == null) {
            return;
        }

        int x, y;
        final int vcell = getHeight() / downsampledData.getHeight();
        final int hcell = getWidth() / downsampledData.getWidth();

        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.black);
        for (y = 0; y < downsampledData.getHeight(); y++) {
            graphics.drawLine(0, y * vcell, getWidth(), y * vcell);
        }
        for (x = 0; x < downsampledData.getWidth(); x++) {
            graphics.drawLine(x * hcell, 0, x * hcell, getHeight());
        }

        for (y = 0; y < downsampledData.getHeight(); y++) {
            for (x = 0; x < downsampledData.getWidth(); x++) {
                if (downsampledData.getDataForPixel(x, y)) {
                    graphics.fillRect(x * hcell, y * vcell, hcell, vcell);
                }
            }
        }

        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

}
