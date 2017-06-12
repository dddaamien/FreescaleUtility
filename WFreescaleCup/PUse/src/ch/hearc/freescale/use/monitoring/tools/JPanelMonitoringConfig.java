
package ch.hearc.freescale.use.monitoring.tools;

import java.awt.FlowLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JPanelMonitoringConfig extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMonitoringConfig()
		{
		this.samplingTime = 1;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int getSamplingTime()
		{
		return this.samplingTime;
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		sliderSamplingTime = new JSlider(SwingConstants.HORIZONTAL, 1, 100, 1); // min,max,current
		sliderSamplesDisplayed = new JSlider(SwingConstants.HORIZONTAL, 5, 1000, 100); // min,max,current

		// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(sliderSamplesDisplayed);
		add(sliderSamplingTime);
		}

	private void control()
		{
		sliderSamplingTime.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent arg0)
				{
				samplingTime = sliderSamplingTime.getValue();
				}
			});
		sliderSamplesDisplayed.addChangeListener(new ChangeListener()
			{

			//TODO: ne fonctionne qu'à l'init..
			@Override
			public void stateChanged(ChangeEvent e)
				{
				GenericXYPlot.setNbSamplesDisplayed(sliderSamplesDisplayed.getValue());
				}
			});
		}

	private void appearance()
		{
		// Slider undersampling
			{
			Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
			table.put(1, new JLabel("1"));
			table.put(50, new JLabel("50"));
			table.put(100, new JLabel("100"));
			this.sliderSamplingTime.setLabelTable(table);
			this.sliderSamplingTime.setPaintTicks(true);
			this.sliderSamplingTime.setPaintLabels(true);
			}
		// Slider nb samples displayed
			{
			Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
			table.put(10, new JLabel("10"));
			table.put(500, new JLabel("500"));
			table.put(1000, new JLabel("1000"));
			this.sliderSamplesDisplayed.setLabelTable(table);
			this.sliderSamplesDisplayed.setPaintTicks(true);
			this.sliderSamplesDisplayed.setPaintLabels(true);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JSlider sliderSamplingTime;
	private JSlider sliderSamplesDisplayed;

	private int samplingTime;
	}
