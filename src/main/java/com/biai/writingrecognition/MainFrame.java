/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biai.writingrecognition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.encog.engine.network.activation.ActivationElliott;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.Propagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;

/**
 *
 * @author m_lig
 */
public class MainFrame extends javax.swing.JFrame {

    private DefaultListModel letterListModel = new DefaultListModel();
    private BasicNetwork network;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        initialise();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        letterComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        recognizeButton = new javax.swing.JButton();
        downsampledDataJPanel1 = new com.biai.writingrecognition.view.DownsampledDataJPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lettersList = new javax.swing.JList<>();
        trainButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        percentTextArea = new javax.swing.JTextArea();
        drawingPanel1 = new com.biai.writingrecognition.view.DrawingPanel();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        saveNetworkButton = new javax.swing.JButton();
        loadNetworkButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Draw letter");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        letterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

        jLabel4.setText("Train as");

        recognizeButton.setText("Recognize");
        recognizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recognizeButtonActionPerformed(evt);
            }
        });

        downsampledDataJPanel1.setPreferredSize(new java.awt.Dimension(220, 220));

        javax.swing.GroupLayout downsampledDataJPanel1Layout = new javax.swing.GroupLayout(downsampledDataJPanel1);
        downsampledDataJPanel1.setLayout(downsampledDataJPanel1Layout);
        downsampledDataJPanel1Layout.setHorizontalGroup(
            downsampledDataJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        downsampledDataJPanel1Layout.setVerticalGroup(
            downsampledDataJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        lettersList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lettersList);

        trainButton.setText("Train network");
        trainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        percentTextArea.setEditable(false);
        percentTextArea.setColumns(7);
        percentTextArea.setRows(5);
        jScrollPane1.setViewportView(percentTextArea);

        drawingPanel1.setPreferredSize(new java.awt.Dimension(220, 220));

        javax.swing.GroupLayout drawingPanel1Layout = new javax.swing.GroupLayout(drawingPanel1);
        drawingPanel1.setLayout(drawingPanel1Layout);
        drawingPanel1Layout.setHorizontalGroup(
            drawingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        drawingPanel1Layout.setVerticalGroup(
            drawingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        saveNetworkButton.setText("Save network");
        saveNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNetworkButtonActionPerformed(evt);
            }
        });

        loadNetworkButton.setText("Load network");
        loadNetworkButton.setToolTipText("");
        loadNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadNetworkButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(clearButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(drawingPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(letterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(downsampledDataJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveNetworkButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loadNetworkButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(recognizeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(trainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(drawingPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(downsampledDataJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(letterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(deleteButton)
                                    .addComponent(addButton))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(recognizeButton)
                                    .addComponent(trainButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(loadButton)
                                    .addComponent(saveButton))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveNetworkButton)
                            .addComponent(loadNetworkButton))
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

        drawingPanel1.clear();

    }//GEN-LAST:event_clearButtonActionPerformed


    private void recognizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recognizeButtonActionPerformed

        if (network == null) {
            JOptionPane.showMessageDialog(this, "I need to be trained first!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        drawingPanel1.downsample();

        final MLData input = new BasicMLData(Config.DOWNSAMPLE_HEIGHT * Config.DOWNSAMPLE_WIDTH);
        int idx = 0;
        final DownsampledData downsampledData = downsampledDataJPanel1.getDownsampledData();
        for (int y = 0; y < downsampledData.getHeight(); y++) {
            for (int x = 0; x < downsampledData.getWidth(); x++) {
                input.setData(idx++, downsampledData.getDataForPixel(x, y) ? .5 : -.5);
            }
        }
        MLData result = network.compute(input);
        displayResults(result);

        final char best = getWinnerLetter(result);
    
        final DownsampledData recognizedLetter = new DownsampledData(best, Config.DOWNSAMPLE_WIDTH, Config.DOWNSAMPLE_HEIGHT);
        recognizedLetter.setPixels(GoodPixels.getInstance().getGoodPixels(best));
        downsampledDataJPanel1.setData(recognizedLetter);
        downsampledDataJPanel1.repaint();

        JOptionPane
                .showMessageDialog(this, "  " + best, "That Letter Is",
                        JOptionPane.PLAIN_MESSAGE);


    }//GEN-LAST:event_recognizeButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int i;

        final String letter = (String) letterComboBox.getSelectedItem();
        if (letter == null) {
            return;
        }

        drawingPanel1.downsample();
        DownsampledData downsampledData = (DownsampledData) downsampledDataJPanel1.getDownsampledData().clone();

        downsampledData.setLetter(letter.charAt(0));

        for (i = 0; i < letterListModel.size(); i++) {
            final Comparable str = (Comparable) letterListModel
                    .getElementAt(i);
            if (str.equals(letter)) {
                JOptionPane.showMessageDialog(this,
                        "That letter is already defined, delete it first!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (str.compareTo(downsampledData) > 0) {

                letterListModel.add(i, downsampledData);
                return;
            }
        }
        letterListModel.add(this.letterListModel.size(), downsampledData);
        downsampledDataJPanel1.repaint();

    }//GEN-LAST:event_addButtonActionPerformed

    private void trainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainButtonActionPerformed
        train();
    }//GEN-LAST:event_trainButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            OutputStream os;// the actual file stream
            PrintStream ps;// used to read the file line by line

            os = new FileOutputStream("./sample.dat", false);
            ps = new PrintStream(os);

            for (int i = 0; i < letterListModel.size(); i++) {
                final DownsampledData ds = (DownsampledData) letterListModel
                        .elementAt(i);
                ps.print(ds.getLetter() + ":");
                for (int y = 0; y < ds.getHeight(); y++) {
                    for (int x = 0; x < ds.getWidth(); x++) {
                        ps.print(ds.getDataForPixel(x, y) ? "1" : "0");
                    }
                }
                ps.println("");
            }

            ps.close();
            os.close();

            JOptionPane.showMessageDialog(this, "Saved to 'sample.dat'.",
                    "Training", JOptionPane.PLAIN_MESSAGE);

        } catch (final Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e, "Training",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        try {
            FileReader f;// the actual file stream
            BufferedReader r;// used to read the file line by line

            f = new FileReader(new File("./sample.dat"));
            r = new BufferedReader(f);
            String line;
            int i = 0;

            letterListModel.clear();

            while ((line = r.readLine()) != null) {
                final DownsampledData ds = new DownsampledData(line.charAt(0),
                        Config.DOWNSAMPLE_WIDTH, Config.DOWNSAMPLE_HEIGHT);
                letterListModel.add(i++, ds);
                int idx = 2;
                for (int y = 0; y < ds.getHeight(); y++) {
                    for (int x = 0; x < ds.getWidth(); x++) {
                        ds.setPixelData(x, y, line.charAt(idx++) == '1');
                    }
                }
            }

            r.close();
            f.close();

            JOptionPane.showMessageDialog(this, "Loaded from 'sample.dat'.",
                    "Training", JOptionPane.PLAIN_MESSAGE);

        } catch (final Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e, "Training",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        final int i = lettersList.getSelectedIndex();

        if (i == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a letter to delete.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        letterListModel.remove(i);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void saveNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNetworkButtonActionPerformed
        System.out.println("Saving network");
        EncogDirectoryPersistence.saveObject(new File("network"), network);
    }//GEN-LAST:event_saveNetworkButtonActionPerformed

    private void loadNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadNetworkButtonActionPerformed

        network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File("network"));
    }//GEN-LAST:event_loadNetworkButtonActionPerformed

    void onLetterSelected(ListSelectionEvent event) {
        if (lettersList.getSelectedIndex() == -1) {
            return;
        }
        final DownsampledData selectedItem = (DownsampledData) letterListModel
                .getElementAt(lettersList.getSelectedIndex());
        downsampledDataJPanel1.setData((DownsampledData) selectedItem.clone());
        downsampledDataJPanel1.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private com.biai.writingrecognition.view.DownsampledDataJPanel downsampledDataJPanel1;
    private com.biai.writingrecognition.view.DrawingPanel drawingPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> letterComboBox;
    private javax.swing.JList<String> lettersList;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton loadNetworkButton;
    private javax.swing.JTextArea percentTextArea;
    private javax.swing.JButton recognizeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveNetworkButton;
    private javax.swing.JButton trainButton;
    // End of variables declaration//GEN-END:variables

    private void initialise() {
        this.setTitle("Letters recognition");
        drawingPanel1.setDownsampledDataJPanel(downsampledDataJPanel1);
        lettersList.setModel(letterListModel);
        lettersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                onLetterSelected(e);
            }

        });
        drawingPanel1.init(drawingPanel1.getWidth(), drawingPanel1.getHeight(), 20);
    }

    private void train() {
        if (letterListModel.size() == 0) {
            JOptionPane.showMessageDialog(this, "Add some letters first!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        final int inputNeuron = Config.DOWNSAMPLE_HEIGHT
                * Config.DOWNSAMPLE_WIDTH;
        final MLDataSet trainingSet = new BasicMLDataSet();
        for (int i = 0; i < letterListModel.size(); i++) {
            final MLData item = new BasicMLData(inputNeuron);
            int idx = 0;
            final DownsampledData downsampledData = (DownsampledData) this.letterListModel
                    .getElementAt(i);
            for (int y = 0; y < downsampledData.getHeight(); y++) {
                for (int x = 0; x < downsampledData.getWidth(); x++) {
                    item.setData(idx++, downsampledData.getDataForPixel(x, y) ? .5 : -.5);
                }
            }
            MLData ideal = getIdealForLetter(downsampledData.getLetter());
            trainingSet.add(new BasicMLDataPair(item, ideal));
        }

        network = new BasicNetwork();

        int hiddenLayerNeuronsCount = 100;

        network.addLayer(new BasicLayer(null, true, Config.DOWNSAMPLE_HEIGHT
                * Config.DOWNSAMPLE_WIDTH));
        network.addLayer(new BasicLayer(new ActivationElliott(), true, hiddenLayerNeuronsCount));
        network.addLayer(new BasicLayer(new ActivationElliott(), false, 26));

        network.getStructure().finalizeStructure();
        network.reset();

        final Propagation train = new ResilientPropagation(network, trainingSet);

        int epochsCount = 1000;

        for (int epoch = 1; epoch < epochsCount; epoch++) {
            train.iteration();
            System.out.println("epoch #" + epoch + " error: " + train.getError());

        }

        train.finishTraining();
        double error = network.calculateError(trainingSet);
        System.out.println("error" + error);
        JOptionPane.showMessageDialog(this, "Training has completed.",
                "Training", JOptionPane.PLAIN_MESSAGE);

    }

    private MLData getIdealForLetter(char letter) {
        int inputNeuron = Config.DOWNSAMPLE_HEIGHT * Config.DOWNSAMPLE_WIDTH;
        final MLData item = new BasicMLData(inputNeuron);
        item.setData(Outputs.getInstance().getGoodOutput(letter));
        return item;
    }

    private void displayResults(MLData result) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < result.getData().length; i++) {
            builder.append((char) (65 + (i)));
            builder.append(": ");
            builder.append(String.format("%.5f", result.getData(i)));
            builder.append("\n");
        }
        percentTextArea.setText(builder.toString());
    }

    private char getWinnerLetter(MLData result) {
        int winnerIndex = 0;
        double bestValue = 0.0;
        for (int i = 0; i < result.getData().length; i++) {
            if (result.getData()[i] > bestValue) {
                winnerIndex = i;
                bestValue = result.getData()[i];
            }

        }
        return (char) (winnerIndex + 65);

    }

}
