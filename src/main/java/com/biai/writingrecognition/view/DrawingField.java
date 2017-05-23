package com.biai.writingrecognition.view;

import com.biai.writingrecognition.DownsampledData;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.PixelGrabber;

import javax.swing.JPanel;

/**
 *
 * @author alachman
 */
public class DrawingField extends JPanel {

    protected Image image;
    protected Graphics graphicsHandle;

    //positions of last user's draw
    protected int lastX = -1;
    protected int lastY = -1;

    //boundaries of cropping rectangle
    protected int downSampleLeft;
    protected int downSampleRight;
    protected int downSampleTop;
    protected int downSampleBottom;

    //downsample ratios
    protected double ratioX;
    protected double ratioY;

    private DownsampledDataJPanel downsampledDataJPanel;
    protected int pixelMap[];

    public DownsampledDataJPanel getDownsampledDataJPanel() {
        return downsampledDataJPanel;
    }

    public void setDownsampledDataJPanel(DownsampledDataJPanel downsampledDataJPanel) {
        this.downsampledDataJPanel = downsampledDataJPanel;
    }

    public DrawingField() {
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK
                | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.COMPONENT_EVENT_MASK);
    }

    public void clearImage() {
        graphicsHandle.setColor(Color.white);
        graphicsHandle.fillRect(0, 0, getWidth(), getHeight());
        downSampleBottom = downSampleTop = downSampleLeft = downSampleRight = 0;
        repaint();
    }

    public void downSample() {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);

        final PixelGrabber grabber = new PixelGrabber(image, 0, 0, width,
                height, true);
        try {

            grabber.grabPixels();
            pixelMap = (int[]) grabber.getPixels();
            findBounds(width, height);

            DownsampledData data = downsampledDataJPanel.getDownsampledData();

            ratioX = (double) (downSampleRight - downSampleLeft)
                    / (double) data.getWidth();
            ratioY = (double) (downSampleBottom - downSampleTop)
                    / (double) data.getHeight();

            for (int y = 0; y < data.getHeight(); y++) {
                for (int x = 0; x < data.getWidth(); x++) {
                    if (downSampleRegion(x, y)) {
                        data.setPixelData(x, y, true);
                    } else {
                        data.setPixelData(x, y, false);
                    }
                }
            }

            downsampledDataJPanel.repaint();
            repaint();
        } catch (final InterruptedException e) {
        }
    }

    protected boolean downSampleRegion(int x, int y) {
        final int width = image.getWidth(this);
        final int startX = (int) (downSampleLeft + (x * ratioX));
        final int startY = (int) (downSampleTop + (y * ratioY));
        final int endX = (int) (startX + ratioX);
        final int endY = (int) (startY + ratioY);

        for (int yy = startY; yy <= endY; yy++) {
            for (int xx = startX; xx <= endX; xx++) {
                int loc = xx + (yy * width);

                if (pixelMap[loc] != -1) {
                    return true;
                }
            }
        }

        return false;
    }

    protected void findBounds(int width, int height) {

        for (int y = 0; y < height; y++) {
            if (!isHorizontalLineClear(y)) {
                downSampleTop = y;
                break;
            }

        }

        for (int y = height - 1; y >= 0; y--) {
            if (!isHorizontalLineClear(y)) {
                downSampleBottom = y;
                break;
            }
        }

        for (int x = 0; x < width; x++) {
            if (!isVerticalLineClear(x)) {
                downSampleLeft = x;
                break;
            }
        }

        for (int x = width - 1; x >= 0; x--) {
            if (!isVerticalLineClear(x)) {
                downSampleRight = x;
                break;
            }
        }
    }

    protected boolean isHorizontalLineClear(final int y) {
        final int width = image.getWidth(this);
        for (int i = 0; i < width; i++) {
            if (pixelMap[(y * width) + i] != -1) {
                return false;
            }
        }
        return true;
    }

    protected boolean isVerticalLineClear(final int x) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        for (int i = 0; i < height; i++) {
            if (pixelMap[(i * width) + x] != -1) {
                return false;
            }
        }
        return true;
    }

    protected void initImage() {
        image = createImage(getWidth(), getHeight());
        graphicsHandle = image.getGraphics();
        graphicsHandle.setColor(Color.white);
        graphicsHandle.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void paint(final Graphics g) {
        if (image == null) {
            initImage();
        }
        g.drawImage(image, 0, 0, this);
        g.setColor(Color.black);
        g.drawRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.drawRect(downSampleLeft, downSampleTop,
                downSampleRight - downSampleLeft,
                downSampleBottom - downSampleTop);

    }

    @Override
    protected void processMouseEvent(final MouseEvent e) {
        if (e.getID() != MouseEvent.MOUSE_PRESSED) {
            return;
        }
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    protected void processMouseMotionEvent(final MouseEvent e) {
        if (e.getID() != MouseEvent.MOUSE_DRAGGED) {
            return;
        }

        graphicsHandle.setColor(Color.black);
        graphicsHandle.drawLine(lastX, lastY, e.getX(), e.getY());
        getGraphics().drawImage(image, 0, 0, this);
        lastX = e.getX();
        lastY = e.getY();
    }

}
