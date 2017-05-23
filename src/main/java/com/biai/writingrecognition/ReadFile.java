/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biai.writingrecognition;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author m_lig
 */
public class ReadFile {

    JFileChooser fileChooser;
    StringBuilder sb = new StringBuilder();

    public ReadFile() {
        fileChooser = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(imageFilter);
    }

    public String chooseFile() throws Exception {

        String filePath = null;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filePath = file.getPath();
        }
        return filePath;
    }
}
