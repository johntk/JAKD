package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;

import operations.ReportOperations;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class QuartReports extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private ReportOperations ro;
	private JPanel top;
	private JLabel select;
	private JComboBox<Object> jcb;
	private ArrayList<String> years;
	private double q1,q2,q3,q4;
	private DecimalFormat d;

	private PieDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;

	public QuartReports(ReportOperations r)
	{
		ro = r;
		d = new DecimalFormat(" €#####.##");

		this.setLayout(new BorderLayout());
		top = new JPanel();

		select = new JLabel("Select a year: ");
		select.setFont(new Font("Calibri", Font.PLAIN, 25));
		top.add(select);
		years = new ArrayList<String>(ro.getTransactionYears());
		jcb = new JComboBox<>(years.toArray());
		jcb.addActionListener(this);
		top.add(jcb);
		jcb.setSelectedIndex(0);
		this.add(top, BorderLayout.NORTH);


	}

	/*
	 Creates a sample dataset 
	 */
	private  PieDataset createDataset(double q1,double q2,double q3,double q4) {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Quarter One\n"+d.format(q1),q1);
		result.setValue("Quarter Two\n"+d.format(q2), q2);
		result.setValue("Quarter Three\n"+d.format(q3), q3);
		result.setValue("Quarter Four\n"+d.format(q4), q4);
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
		plot.setForegroundAlpha(0.7f);
		return chart;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jcb)
		{	
			if(chartPanel != null)
			{
				this.remove(chartPanel);
				this.validate();this.repaint();
			}
			q1 = ro.getFirstQuarterRevenue(jcb.getSelectedItem().toString());
			q2 = ro.getSecondQuarterRevenue(jcb.getSelectedItem().toString());
			q3 = ro.getThirdQuarterRevenue(jcb.getSelectedItem().toString());
			q4 = ro.getFourthQuarterRevenue(jcb.getSelectedItem().toString());

			dataset = createDataset(q1,q2,q3,q4);
			chart = createChart(dataset, "Quarterly Sales Report");
			chartPanel = new ChartPanel(chart);
			this.add(chartPanel, BorderLayout.CENTER);

			chartPanel.validate();chartPanel.repaint();
			this.validate();this.repaint();
		}
	}
}
