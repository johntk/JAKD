package reports;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class QuartReports extends JFrame
{
	private static final long serialVersionUID = 1L;

	public QuartReports()
	{
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset, "Quarterly Revenue Report");
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
		setContentPane(chartPanel);
		pack();
		setVisible(true);
	}

	/*
	 Creates a sample dataset 
	 */
	private  PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Quarter One", 29);
		result.setValue("Quarter Two", 20);
		result.setValue("Quarter Three", 51);
		result.setValue("Quarter Four", 51);
		return result;

	}

	/*
	 Creates a chart
	 */
	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
				dataset,                // data
				true,                   // include legend
				true,
				false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}
}
