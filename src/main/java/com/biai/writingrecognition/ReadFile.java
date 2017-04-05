/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biai.writingrecognition;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author m_lig
 */
public class ReadFile {
    JFileChooser fileChooser = new JFileChooser();
    StringBuilder sb = new StringBuilder();
    
    public String chooseFile() throws Exception {
        String filePath = null;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filePath = file.getPath();
        }
        return filePath;
    }
}
