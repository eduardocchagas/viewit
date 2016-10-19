package automationFramework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
//import org.jfree.ui.Spacer;

/**
 * A demo showing the addition and removal of secondary datasets / renderers.
 *
 */
public class SecondaryDatasetDemo2 extends ApplicationFrame implements ActionListener {

    /** The plot. */
    private CategoryPlot plot;
 
    /** The index of the last dataset added. */
    private int secondaryDatasetIndex = 0;
    
    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public SecondaryDatasetDemo2(final String title) {

        super(title);
        final CategoryDataset dataset1 = createRandomDataset("Series 1");
        final JFreeChart chart = ChartFactory.createLineChart(
            "Secondary Dataset Demo 2", "Category", "Value", 
            dataset1, PlotOrientation.VERTICAL, true, true, false
        );
        chart.setBackgroundPaint(Color.white);
        
        this.plot = chart.getCategoryPlot();
        this.plot.setBackgroundPaint(Color.lightGray);
        this.plot.setDomainGridlinePaint(Color.white);
        this.plot.setRangeGridlinePaint(Color.white);
//        this.plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 4, 4, 4, 4));
        
        final NumberAxis rangeAxis = (NumberAxis) this.plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false);
        
        final JPanel content = new JPanel(new BorderLayout());

        final ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel);
        
        final JButton button1 = new JButton("Add Dataset");
        button1.setActionCommand("ADD_DATASET");
        button1.addActionListener(this);
        
        final JButton button2 = new JButton("Remove Dataset");
        button2.setActionCommand("REMOVE_DATASET");
        button2.addActionListener(this);

        final JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        
        content.add(buttonPanel, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);

    }

    /**
     * Creates a random dataset.
     * 
     * @param name  the series name.
     * 
     * @return A random dataset.
     */
    private CategoryDataset createRandomDataset(final String name) {
        final DefaultCategoryDataset result = new DefaultCategoryDataset();
        double value = 100.0;
        for (int i = 0; i < 10; i++) {
            final String key = "T" + i;
            value = value * (1.0 + Math.random() / 100);
            result.addValue(value, name, key);
        }
        return result;
    }
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Handles a click on the button by adding new (random) data.
     *
     * @param e  the action event.
     */
    public void actionPerformed(final ActionEvent e) {
       
        if (e.getActionCommand().equals("ADD_DATASET")) {
            if (this.secondaryDatasetIndex < 20) {
                this.secondaryDatasetIndex++;
                this.plot.setDataset(
                    this.secondaryDatasetIndex, 
                    createRandomDataset("S" + this.secondaryDatasetIndex)
                );
//                this.plot.setRenderer(
  //                  this.secondaryDatasetIndex, new LineAndShapeRenderer(LineAndShapeRenderer.LINES)
    //            );
            }
        }
        else if (e.getActionCommand().equals("REMOVE_DATASET")) {
            if (this.secondaryDatasetIndex > 0) {
                this.plot.setDataset(this.secondaryDatasetIndex, null);
                this.plot.setRenderer(this.secondaryDatasetIndex, null);
                this.secondaryDatasetIndex--;
            }
        }
        
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final SecondaryDatasetDemo2 demo = new SecondaryDatasetDemo2("Secondary Dataset Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
