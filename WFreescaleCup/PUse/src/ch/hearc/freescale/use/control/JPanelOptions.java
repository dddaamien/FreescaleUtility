
package ch.hearc.freescale.use.control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.freescale.use.Client;

public class JPanelOptions extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelOptions(Client client)
		{
		this.client = client;
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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		cbLed = new JCheckBox("led");

		tempsExpo = new JSlider();
		labelTempsExpo = new JLabel("Temps d’exposition de la caméra [ms]");

		// Layout : Specification
			{
			//			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			//			setLayout(flowlayout);

			layout = new GridLayout(2, 2); //2 colonnes, nb de lignes calculés automatiquement, ici 3
			setLayout(layout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(new JPanel());
		add(labelTempsExpo);
		add(cbLed);
		add(tempsExpo);
		}

	private void control()
		{
		cbLed.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent arg0)
				{
				client.setLed(cbLed.isSelected());
				}
			});

		tempsExpo.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				client.setExpoCam(tempsExpo.getValue());
				System.out.println(tempsExpo.getValue());
				}
			});
		}

	private void appearance()
		{
		//		layout.setHgap(50);
		//		layout.setVgap(20);

		//slider temps d'exposition
		tempsExpo.setMajorTickSpacing(50);
		tempsExpo.setMinorTickSpacing(10);
		tempsExpo.setPaintTicks(true);
		tempsExpo.setPaintLabels(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Client client;

	private JCheckBox cbLed;

	private JLabel labelTempsExpo;
	private JSlider tempsExpo;

	private GridLayout layout;
	}
