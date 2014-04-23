package reports;

import java.awt.*;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class QuartReports
{
	private ReportOperations ro;
	private JFrame frame;
	private JPanel panel,top;
	private JLabel select;
	private JComboBox<String> jcb;

	public QuartReports(ReportOperations r)
	{
		ro = r;
		panel = new JPanel(new BorderLayout());
		top = new JPanel();

		select = new JLabel("Select a year: ");
		select.setFont(new Font("Calibri", Font.PLAIN, 25));
		top.add(select);
		String[] years = {"2013","2014"};
		jcb = new JComboBox<>(years);
		top.add(jcb);

		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset, "Quarterly Revenue Report");
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
		
		panel.add(top, BorderLayout.NORTH);
		panel.add(chartPanel, BorderLayout.CENTER);
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

		JFreeChart chart = ChartFactory.createPieChart3D(title,dataset,true,true,false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}
}
