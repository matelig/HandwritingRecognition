package com.biai.writingrecognition.view;

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

    //The pixel map of what the user has drawn. Used to downsample it.
    protected int pixelMap[];

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

    /**
     * Called to downsample a quadrant of the image.
     *
     * @param x The x coordinate of the resulting downsample.
     * @param y The y coordinate of the resulting downsample.
     * @return Returns true if there were ANY pixels in the specified quadrant.
     */
    protected boolean downSampleRegion(final int x, final int y) {
        final int w = this.image.getWidth(this);
        final int startX = (int) (this.downSampleLeft + (x * this.ratioX));
        final int startY = (int) (this.downSampleTop + (y * this.ratioY));
        final int endX = (int) (startX + this.ratioX);
        final int endY = (int) (startY + this.ratioY);

        for (int yy = startY; yy <= endY; yy++) {
            for (int xx = startX; xx <= endX; xx++) {
                final int loc = xx + (yy * w);

                if (this.pixelMap[loc] != -1) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method is called to automatically crop the image so that whitespace
     * is removed.
     *
     * @param w The width of the image.
     * @param h The height of the image
     */
    protected void findBounds(final int w, final int h) {
        // top line
        for (int y = 0; y < h; y++) {
            if (!isHorizontalLineClear(y)) {
                this.downSampleTop = y;
                break;
            }

        }
        // bottom line
        for (int y = h - 1; y >= 0; y--) {
            if (!isHorizontalLineClear(y)) {
                this.downSampleBottom = y;
                break;
            }
        }
        // left line
        for (int x = 0; x < w; x++) {
            if (!isVerticalLineClear(x)) {
                this.downSampleLeft = x;
                break;
            }
        }

        // right line
        for (int x = w - 1; x >= 0; x--) {
            if (!isVerticalLineClear(x)) {
                downSampleRight = x;
                break;
            }
        }
    }

    /*
     * isHorizontalLineClear and vertical are used to check if there are any
     * pixels in the given line. Used for cropping image.
     */
    protected boolean isHorizontalLineClear(final int y) {
        final int w = image.getWidth(this);
        for (int i = 0; i < w; i++) {
            if (pixelMap[(y * w) + i] != -1) {
                return false;
            }
        }
        return true;
    }

    protected boolean isVerticalLineClear(final int x) {
        final int w = image.getWidth(this);
        final int h = image.getHeight(this);
        for (int i = 0; i < h; i++) {
            if (pixelMap[(i * w) + x] != -1) {
                return false;
            }
        }
        return true;
    }

    //Setup the internal image that the user draws onto.
    protected void initImage() {
        image = createImage(getWidth(), getHeight());
        graphicsHandle = image.getGraphics();
        graphicsHandle.setColor(Color.white);
        graphicsHandle.fillRect(0, 0, getWidth(), getHeight());
    }

    //painting boundary around cropped image
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

    //saving last position where user clicked
    @Override
    protected void processMouseEvent(final MouseEvent e) {
        if (e.getID() != MouseEvent.MOUSE_PRESSED) {
            return;
        }
        lastX = e.getX();
        lastY = e.getY();
    }

    //drawing
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
