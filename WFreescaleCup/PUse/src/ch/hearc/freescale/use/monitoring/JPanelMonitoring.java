
package ch.hearc.freescale.use.monitoring;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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

		sPlaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chartAcceleration, chartGyro);
		sPlaneH2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, chartVitesse, sPlaneH);
		sPlaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new ChartPanel(chartImage), sPlaneH2);

		add(sPlaneV, BorderLayout.CENTER);
		add(panelMonitoringConfig, BorderLayout.SOUTH);
		sPlaneV.setDividerLocation(300);//this.getHeight()/2);
		sPlaneH.setDividerLocation(250);//this.getWidth()/3);
		sPlaneH2.setDividerLocation(250);
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



	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input

	// Tools
	private JPanelMonitoringConfig panelMonitoringConfig;
	private JSplitPane sPlaneV;
	private JSplitPane sPlaneH;
	private JSplitPane sPlaneH2;

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
