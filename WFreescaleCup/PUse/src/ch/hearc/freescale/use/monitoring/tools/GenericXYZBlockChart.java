package ch.hearc.freescale.use.monitoring.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;

public class GenericXYZBlockChart extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public GenericXYZBlockChart(String title, String xName, String yName, Dimension chartSize)
		{
		this.title = title;
		this.xName = xName;
		this.yName = yName;
		this.chartSize = chartSize;

		this.xValues = new double[chartSize.height * chartSize.width];
		this.yValues = new double[chartSize.height * chartSize.width];
		this.zValues = new double[chartSize.height * chartSize.width];
		this.dataset = new DefaultXYZDataset();
		this.dataset.addSeries("title", new double[][] { xValues, yValues, zValues });
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addValue(double[] values)
	{
	for(int i = 0; i < this.chartSize.width; i++)
		{

		}
	}
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation

		// Layout : Specification
			{
			BorderLayout layout = new BorderLayout();
			setLayout(layout);
			}

		// JComponent : add
		add(new ChartPanel(createChart(this.dataset)));
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	private JFreeChart createChart(XYZDataset dataset)
		{
		NumberAxis xAxis = new NumberAxis(this.xName);
		NumberAxis yAxis = new NumberAxis(this.yName);
		XYBlockRenderer renderer = new XYBlockRenderer();
		LookupPaintScale paintScale = new LookupPaintScale(0.5, 3.5, Color.black);
//		paintScale.add(0.5, Color.green);
//		paintScale.add(1.5, Color.orange);
//		paintScale.add(2.5, Color.red);
//		renderer.setPaintScale(paintScale);
		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
		JFreeChart chart = new JFreeChart(this.title, plot);
		return chart;
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private String title;
	private String xName;
	private String yName;
	private Dimension chartSize;
	//tools
	private double[] xValues;
	private double[] yValues;
	private double[] zValues;
	private DefaultXYZDataset dataset;

	}


