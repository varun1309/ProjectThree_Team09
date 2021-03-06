package ser516.project3.client.controller;

import ser516.project3.client.view.PerformanceMetricView;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ControllerInterface;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.model.PerformanceMetricModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The PerformanceMetricController class is used to initialize the performance
 * view on ClientUI and update the performance data received from the server
 *
 * @author vsriva12
 */
public class PerformanceMetricController implements ControllerInterface {
    private PerformanceMetricModel performanceMetricModel;
    private PerformanceMetricView performanceMetricView;

    private GraphController graphController;

    /**
     * Constructor to set the private variables with the passed parameters
     *
     * @param performanceMetricModel the model to store the performance metrics
     * @param performanceMetricView  the view to show the performance metrics
     * @param graphController        the controller which controls the graph view
     *                               on the performance metrics tab
     */
    public PerformanceMetricController(PerformanceMetricModel performanceMetricModel,
                                       PerformanceMetricView performanceMetricView, GraphController graphController) {
        this.performanceMetricModel = performanceMetricModel;
        this.performanceMetricView = performanceMetricView;
        this.graphController = graphController;
    }

    /**
     * Method is used to initialize performance view
     * where emotion buttons and display length text field is added
     */
    @Override
    public void initializeView() {
        graphController.setNoOfChannels(6);
        graphController.setXLength(performanceMetricModel.getDisplayLength());
        Color channelColors[] = {performanceMetricModel.getInterestColor(),
                performanceMetricModel.getEngagementColor(), performanceMetricModel.getStressColor(),
                performanceMetricModel.getRelaxationColor(), performanceMetricModel.getExcitementColor(),
                performanceMetricModel.getFocusColor()};
        graphController.setChannelColors(channelColors);
        graphController.updateGraphView();
        ViewInterface clientViewInterface[] = {graphController.getView()};
        performanceMetricView.initializeView(clientViewInterface);
        performanceMetricView.addEmotionButtonsListener(new EmotionButtonsListener());
        performanceMetricView.addDisplayLengthListener(new DisplayLengthKeyListener(),
                new DisplayLengthDocumentListener());
    }

    /**
     * Method to get PerformanceMetric view
     *
     * @return performancemetric view object
     */
    @Override
    public PerformanceMetricView getView() {
        return performanceMetricView;
    }

    /**
     * Returns the set of sub controllers in case any
     *
     * @return array containing sub controllers
     */
    @Override
    public ControllerInterface[] getSubControllers() {
        ControllerInterface[] subControllers = {graphController};
        return subControllers;
    }

    /**
     * Class implemented to handle action listener
     * of all emotion buttons like Stress, Interest, Focus, Excitement, Engagement
     * and Relaxation
     */
    public class EmotionButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color initialBackground;
            Color newBackground;
            switch (e.getActionCommand()) {
                case ClientConstants.INTEREST:
                    initialBackground = performanceMetricModel.getInterestColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.INTEREST + " Color",
                            initialBackground);
                    if (newBackground != null) {
                        performanceMetricModel.setInterestColor(newBackground);
                    }
                    break;
                case ClientConstants.ENGAGEMENT:
                    initialBackground = performanceMetricModel.getEngagementColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.ENGAGEMENT + " Color",
                            initialBackground);
                    if (newBackground != null) {
                        performanceMetricModel.setEngagementColor(newBackground);
                    }
                    break;
                case ClientConstants.STRESS:
                    initialBackground = performanceMetricModel.getStressColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.STRESS + " Color",
                            initialBackground);
                    if (newBackground != null) {
                        performanceMetricModel.setStressColor(newBackground);
                    }
                    break;
                case ClientConstants.RELAXATION:
                    initialBackground = performanceMetricModel.getRelaxationColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.RELAXATION + " Color",
                            initialBackground);
                    if (newBackground != null) {
                        performanceMetricModel.setRelaxationColor(newBackground);
                    }
                    break;
                case ClientConstants.EXCITEMENT:
                    initialBackground = performanceMetricModel.getExcitementColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.EXCITEMENT + " Color",
                            initialBackground);
                    if (newBackground != null) {
                        performanceMetricModel.setExcitementColor(newBackground);
                    }
                    break;
                case ClientConstants.FOCUS:
                    initialBackground = performanceMetricModel.getFocusColor();
                    newBackground = JColorChooser.showDialog(null, "Change " + ClientConstants.FOCUS + " Color",
                            initialBackground);
                    if (newBackground != null) {
                        performanceMetricModel.setFocusColor(newBackground);
                    }
                    break;
            }

            Color channelColors[] = {performanceMetricModel.getInterestColor(),
                    performanceMetricModel.getEngagementColor(), performanceMetricModel.getStressColor(),
                    performanceMetricModel.getRelaxationColor(), performanceMetricModel.getExcitementColor(),
                    performanceMetricModel.getFocusColor()};
            graphController.setChannelColors(channelColors);
            graphController.updateGraphView();
            performanceMetricView.revalidate();
            performanceMetricView.repaint();
            performanceMetricView.updatePerformanceMetricView(performanceMetricModel);
        }
    }

    /**
     * Class created to support display length key listener
     * to update graph controller and performance metric view
     * based on key event
     */
    public class DisplayLengthKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                graphController.updateGraphView();
                performanceMetricView.updatePerformanceMetricView(performanceMetricModel);
                performanceMetricView.revalidate();
                performanceMetricView.repaint();
            }
        }
    }

    /**
     * Class implemented to handle document listener of all
     * display length related components
     */
    class DisplayLengthDocumentListener implements DocumentListener {
        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                if (e.getDocument().getLength() == 0) {
                    performanceMetricModel.setDisplayLength(1);
                    graphController.setXLength(1);
                } else {
                    performanceMetricModel.setDisplayLength(
                            Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())));
                    graphController.setXLength(performanceMetricModel.getDisplayLength());
                }
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                if (Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())) == 0) {
                    performanceMetricModel.setDisplayLength(1);
                    graphController.setXLength(1);
                } else {
                    performanceMetricModel.setDisplayLength(
                            Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength())));
                    graphController.setXLength(performanceMetricModel.getDisplayLength());
                }
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
}
