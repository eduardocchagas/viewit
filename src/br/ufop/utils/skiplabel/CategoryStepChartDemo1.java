package br.ufop.utils.skiplabel;
import java.awt.Color;
import java.awt.Dimension;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.CategoryStepRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a step chart 
 * using data from a {@link CategoryDataset}.
 */
public class CategoryStepChartDemo1 extends ApplicationFrame {
	private Comparable[] columnKeys = null;
	private Comparable[] rowKeys = null;
	private double[][] data = null;
    /**
     * Creates a new demo application.
     *
     * @param title  the frame title.
     */
    public CategoryStepChartDemo1(String title, Comparable[] columnKeys, Comparable[] rowKeys, double[][] data) {
        super(title);
        this.columnKeys = columnKeys;
        this.rowKeys = rowKeys;
        this.data = data;
        
        createChart();
    }


    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart() {
    	CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
        CategoryItemRenderer renderer = new CategoryStepRenderer(true);
        CategoryAxis domainAxis = new CategoryAxisSkipLabels();  // new CategoryAxis("Category");
        domainAxis.setTickMarksVisible(true);
        ((CategoryAxisSkipLabels)domainAxis).setDisplaySkippedTickMarks(true);
        NumberAxis rangeAxis = new NumberAxis("Value");
        CategoryPlot plot = new CategoryPlot(
            dataset, domainAxis, rangeAxis, renderer
        );
  
        JFreeChart chart = new JFreeChart("Category Step Chart", plot);
 
        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
        
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle(0 * Math.PI / 2.0);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
        return chart;
    }
    
 
   
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void run(Comparable[] columnKeys, Comparable[] rowKeys, double[][] data) throws Exception {
  		System.out.println("Attempting chart draw");
  		//initialKeys = copyKeys(columnKeys);
        CategoryStepChartDemo1 demo = new CategoryStepChartDemo1(
            "Category Step Renderer Demo", columnKeys, rowKeys, data
	        );
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	       
	    demo.setVisible(true);    

        System.out.println("xx");
    }
 
}
