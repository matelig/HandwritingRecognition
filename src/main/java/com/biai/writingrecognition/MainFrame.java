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
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.Propagation;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.manhattan.ManhattanPropagation;
import org.encog.neural.networks.training.propagation.quick.QuickPropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

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
        drawingField1 = new com.biai.writingrecognition.view.DrawingField();
        clearButton = new javax.swing.JButton();
        letterComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        recognizeButton = new javax.swing.JButton();
        downsampledDataJPanel1 = new com.biai.writingrecognition.view.DownsampledDataJPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lettersList = new javax.swing.JList<>();
        trainButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        percentTextArea = new javax.swing.JTextArea();

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

        javax.swing.GroupLayout drawingField1Layout = new javax.swing.GroupLayout(drawingField1);
        drawingField1.setLayout(drawingField1Layout);
        drawingField1Layout.setHorizontalGroup(
            drawingField1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        drawingField1Layout.setVerticalGroup(
            drawingField1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        letterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

        jLabel4.setText("Train as");

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

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

        saveButton.setText("save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setText("load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        percentTextArea.setEditable(false);
        percentTextArea.setColumns(7);
        percentTextArea.setRows(5);
        jScrollPane1.setViewportView(percentTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(clearButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 48, Short.MAX_VALUE)
                        .addComponent(drawingField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addButton)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(letterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(saveButton)
                                    .addComponent(loadButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(trainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(recognizeButton)))
                        .addGap(13, 13, 13)))
                .addComponent(downsampledDataJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel3))
                            .addComponent(loadButton, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(drawingField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(saveButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(letterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(addButton)
                                        .addGap(11, 11, 11)
                                        .addComponent(recognizeButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(trainButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearButton))
                            .addComponent(downsampledDataJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 131, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        drawingField1.clearImage();
    }//GEN-LAST:event_clearButtonActionPerformed

    char[] mapNeurons() {
        char map[] = new char[letterListModel.size()];

        for (int i = 0; i < map.length; i++) {
            map[i] = '?';
        }
        for (int i = 0; i < letterListModel.size(); i++) {
            final MLData input = new BasicMLData(Config.DOWNSAMPLE_HEIGHT * Config.DOWNSAMPLE_WIDTH);
            int idx = 0;
            final DownsampledData ds = (DownsampledData) letterListModel
                    .getElementAt(i);
            for (int y = 0; y < ds.getHeight(); y++) {
                for (int x = 0; x < ds.getWidth(); x++) {
                    input.setData(idx++, ds.getDataForPixel(x, y) ? .5 : -.5);
                }
            }

            int best = network.classify(input);
            map[best] = ds.getLetter();
        }
        return map;
    }
    private void recognizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recognizeButtonActionPerformed

        if (network == null) {
            JOptionPane.showMessageDialog(this, "I need to be trained first!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        drawingField1.downSample();

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

        final int best = this.network.classify(input);
        final char map[] = mapNeurons();
        JOptionPane
                .showMessageDialog(this, "  " + map[best] + "   (Neuron #"
                        + best + " fired)", "That Letter Is",
                        JOptionPane.PLAIN_MESSAGE);


    }//GEN-LAST:event_recognizeButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int i;

        final String letter = (String) letterComboBox.getSelectedItem();
        if (letter == null) {
            return;
        }

        drawingField1.downSample();
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
        lettersList.setSelectedIndex(i);
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
    private com.biai.writingrecognition.view.DownsampledDataJPanel downsampledDataJPanel1;
    private com.biai.writingrecognition.view.DrawingField drawingField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> letterComboBox;
    private javax.swing.JList<String> lettersList;
    private javax.swing.JButton loadButton;
    private javax.swing.JTextArea percentTextArea;
    private javax.swing.JButton recognizeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton trainButton;
    // End of variables declaration//GEN-END:variables

    private void initialise() {
        drawingField1.setDownsampledDataJPanel(downsampledDataJPanel1);
        lettersList.setModel(letterListModel);
        lettersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                onLetterSelected(e);
            }

        });
    }

    private void train() {
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

        int epochsCount = 100;

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

}
