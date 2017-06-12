
package ch.hearc.freescale.use.monitoring;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset;

import ch.hearc.freescale.api.voitureInputStream.TrameEvent;
import ch.hearc.freescale.api.voitureInputStream.TrameListener;
import ch.hearc.freescale.use.monitoring.tools.GenericXYPlot;
import ch.hearc.freescale.use.monitoring.tools.JPanelMonitoringConfig;
import ch.hearc.freescale.use.trames.TrameReceived;

public class JPanelMonitoring extends JPanel implements TrameListener
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMonitoring()
		{
		serieAccX = new XYSeries("X");
		serieAccY = new XYSeries("Y");
		serieAccZ = new XYSeries("Z");
		serieVitesseMotD = new XYSeries("Moteur droit");
		serieVitesseMotG = new XYSeries("Moteur gauche");
		seriePitch = new XYSeries("Pitch");
		serieRoll = new XYSeries("Roll");
		serieYaw = new XYSeries("Yaw");

		serieImage = new XYSeries("Camera");
		datasetImage = new XYSeriesCollection(serieImage);

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void tramePerformed(TrameEvent event)
		{
		if (((temps++) % panelMonitoringConfig.getSamplingTime()) == 0)
			{
			TrameReceived trame = (TrameReceived)event.getTrame();
			System.out.println(trame.toString());

			serieVitesseMotG.add(temps, trame.getVitesseGauche());
			serieVitesseMotD.add(temps, trame.getVitesseDroite());
			serieAccX.add(temps, trame.getAccX());
			serieAccY.add(temps, trame.getAccY());
			serieAccZ.add(temps, trame.getAccZ());
			seriePitch.add(temps, trame.getPitch());
			serieRoll.add(temps, trame.getRoll());
			serieYaw.add(temps, trame.getYaw());

			serieImage.clear();
			for(int i = 0; i < 100; i++)
				{
				serieImage.add(trame.getCamera().getPixel(i), i);
				}
			this.repaint();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		labelCloseAcc = new JLabel();
		chartAcceleration = new GenericXYPlot("Accélération", "Temps", "G", labelCloseAcc);
		chartAcceleration.addSerie(serieAccX);
		chartAcceleration.addSerie(serieAccY);
		chartAcceleration.addSerie(serieAccZ);
		chartAcceleration.init();

		labelCloseVitesse = new JLabel();
		chartVitesse = new GenericXYPlot("Vitesse", "Temps", "rad/sec", labelCloseVitesse);
		chartVitesse.addSerie(serieVitesseMotD);
		chartVitesse.addSerie(serieVitesseMotG);
		chartVitesse.init();

		labelCloseGyro = new JLabel();
		chartGyro = new GenericXYPlot("Gyro", "temps", "Rad", labelCloseGyro);
		chartGyro.addSerie(seriePitch);
		chartGyro.addSerie(serieRoll);
		chartGyro.addSerie(serieYaw);
		chartGyro.init();

		chartImage = ChartFactory.createXYAreaChart("Camera", "x", "y", datasetImage);

		panelMonitoringConfig = new JPanelMonitoringConfig();
		// Layout : Specification
			{
			BorderLayout layout = new BorderLayout();
			setLayout(layout);
			}

		JSplitPane sPlaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chartVitesse, new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chartAcceleration, chartGyro));
		JSplitPane sPlaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new ChartPanel(chartImage), sPlaneH);

		add(sPlaneV, BorderLayout.CENTER);
		add(panelMonitoringConfig, BorderLayout.SOUTH);
		add(new ChartPanel(createChart(createDataset())),BorderLayout.NORTH);
		}

	private void control()
		{
		labelCloseAcc.addMouseListener(new MouseAdapter()
			{

			@Override
			public void mouseClicked(MouseEvent arg0)
				{
				chartAcceleration.setVisible(false);

				}
			});

		labelCloseGyro.addMouseListener(new MouseAdapter()
			{

			@Override
			public void mouseClicked(MouseEvent arg0)
				{
				chartGyro.setVisible(false);

				}
			});

		labelCloseVitesse.addMouseListener(new MouseAdapter()
			{

			@Override
			public void mouseClicked(MouseEvent arg0)
				{
				chartVitesse.setVisible(false);

				}
			});
		}

	private void appearance()
		{

		}

	private static JFreeChart createChart(XYZDataset dataset)
		{
		NumberAxis xAxis = new NumberAxis("X");
//		xAxis.setLowerMargin(0.0);
//		xAxis.setUpperMargin(0.0);
		NumberAxis yAxis = new NumberAxis("Y");
//		yAxis.setAutoRangeIncludesZero(false);
//		yAxis.setInverted(true);
//		yAxis.setLowerMargin(0.0);
//		yAxis.setUpperMargin(0.0);
//		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		XYBlockRenderer renderer = new XYBlockRenderer();
		LookupPaintScale paintScale = new LookupPaintScale(0.5, 3.5, Color.black);
		paintScale.add(0.5, Color.green);
		paintScale.add(1.5, Color.orange);
		paintScale.add(2.5, Color.red);
		renderer.setPaintScale(paintScale);
		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
//		plot.setBackgroundPaint(Color.lightGray);
//		plot.setDomainGridlinePaint(Color.white);
//		plot.setRangeGridlinePaint(Color.white);
//		plot.setForegroundAlpha(0.66f);
//		plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
		JFreeChart chart = new JFreeChart("XYBlockChartDemo3", plot);
//		chart.removeLegend();
//		chart.setBackgroundPaint(Color.white);
//		SymbolAxis scaleAxis = new SymbolAxis(null, new String[] { "", "OK", "Uncertain", "Bad" });
//		scaleAxis.setRange(0.5, 3.5);
//		scaleAxis.setPlot(new PiePlot());
//		scaleAxis.setGridBandsVisible(false);
//		PaintScaleLegend psl = new PaintScaleLegend(paintScale, scaleAxis);
//		psl.setAxisOffset(5.0);
//		psl.setPosition(RectangleEdge.BOTTOM);
//		psl.setMargin(new RectangleInsets(5, 5, 5, 5));
//		chart.addSubtitle(psl);
		return chart;
		}

	private static XYZDataset createDataset()
		{

		double[] xvalues = new double[14 * 60];
		double[] yvalues = new double[14 * 60];
		double[] zvalues = new double[14 * 60];
		double[][] data = new double[][] { xvalues, yvalues, zvalues };

		// set the default z-value to zero throughout the data array.
		for(int c = 0; c < 60; c++)
			{
			for(int r = 8; r < 22; r++)
				{
				setValue(data, c, r, 0.0);
				}
			}

		for(int r = 8; r < 12; r++)
			{
			for(int c = 13; c < 48; c++)
				{
				setValue(data, c, r, 1.0);
				}
			}
		for(int r = 12; r < 20; r++)
			{
			for(int c = 23; c < 43; c++)
				{
				setValue(data, c, r, 1.0);
				}
			}
		setValue(data, 2, 20, 2);
		setValue(data, 5, 20, 3);
		setValue(data, 6, 20, 3);
		setValue(data, 7, 20, 3);
		setValue(data, 8, 20, 3);
		setValue(data, 9, 20, 3);
		setValue(data, 11, 20, 3);
		setValue(data, 17, 20, 2);
		setValue(data, 18, 20, 2);
		setValue(data, 19, 20, 2);
		setValue(data, 20, 20, 2);
		setValue(data, 22, 20, 2);
		setValue(data, 25, 20, 2);
		setValue(data, 28, 20, 2);
		setValue(data, 35, 20, 2);
		for(int c = 40; c < 60; c++)
			{
			setValue(data, c, 20, 3.0);
			}

		for(int c = 23; c < 43; c++)
			{
			setValue(data, c, 21, 1.0);
			}
		DefaultXYZDataset dataset = new DefaultXYZDataset();
		dataset.addSeries("Series 1", data);
		return dataset;
		}

	private static void setValue(double[][] data, int c, int r, double value)
		{

		data[0][(r - 8) * 60 + c] = c;
		data[1][(r - 8) * 60 + c] = r;
		data[2][(r - 8) * 60 + c] = value;

		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input

	// Tools
	private JPanelMonitoringConfig panelMonitoringConfig;

	private JLabel labelCloseVitesse;
	private GenericXYPlot chartVitesse;
	private XYSeries serieVitesseMotG;
	private XYSeries serieVitesseMotD;
	private JLabel labelCloseAcc;
	private GenericXYPlot chartAcceleration;
	private XYSeries serieAccX;
	private XYSeries serieAccY;
	private XYSeries serieAccZ;
	private JLabel labelCloseGyro;
	private GenericXYPlot chartGyro;
	private XYSeries serieRoll;
	private XYSeries seriePitch;
	private XYSeries serieYaw;

	private JFreeChart chartImage;
	private XYDataset datasetImage;
	private XYSeries serieImage;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/
	private static int temps = 0;
	}
