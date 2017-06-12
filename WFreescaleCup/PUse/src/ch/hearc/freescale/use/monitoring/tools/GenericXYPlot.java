
package ch.hearc.freescale.use.monitoring.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GenericXYPlot extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public GenericXYPlot(String title, String xName, String yName, JLabel labelClose)
		{
		this.chartTitle = title;
		this.xName = xName;
		this.yName = yName;
		this.labelClose = labelClose;
		labelClose.setText("X");

		this.listSeries = new LinkedList<XYSeries>();
		this.dataset = new XYSeriesCollection();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void init()
		{
		geometry();
		control();
		appearance();
		}

	public void addSerie(XYSeries serie)
		{
		this.listSeries.add(serie);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/
	protected static void setNbSamplesDisplayed(int nbSamplesDisplayed)
		{
		GenericXYPlot.nbSamplesDisplayed = nbSamplesDisplayed;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		for(XYSeries xySeries:listSeries)
			{
			xySeries.setMaximumItemCount(nbSamplesDisplayed);
			dataset.addSeries(xySeries);
			}
		chart = ChartFactory.createXYLineChart(chartTitle, xName, yName, dataset);
		// Layout : Specification
			{
			BorderLayout layout = new BorderLayout();
			setLayout(layout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		//TODO: modifier taille
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setMinimumSize(new Dimension(30, 30));
		chartPanel.setSize(new Dimension(30, 30));
		chartPanel.setMaximumSize(new Dimension(30, 30));
		chartPanel.repaint();
		add(labelClose,BorderLayout.NORTH);
		add(chartPanel,BorderLayout.CENTER);
		}

	private void control()
		{

		}

	private void appearance()
		{
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Input
	private String chartTitle;
	private String xName;
	private String yName;
	private JLabel labelClose;
	// Tools
	private JFreeChart chart;
	private List<XYSeries> listSeries;
	private XYSeriesCollection dataset;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/
	private static int nbSamplesDisplayed = 100;
	}
