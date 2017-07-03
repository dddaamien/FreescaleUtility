
package ch.hearc.freescale.use.control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class JPanelProfilsVitesse extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelProfilsVitesse()
		{
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

	public static float getVitesse()
		{
		return vitesse;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		lent = new JCheckBox("lent");
		normal = new JCheckBox("normal");
		rapide = new JCheckBox("rapide");

		// Layout : Specification
			{
			//			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			//			setLayout(flowlayout);

			layout = new GridLayout(1, 3); //2 colonnes, nb de lignes calculés automatiquement, ici 3
			setLayout(layout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(lent);
		add(normal);
		add(rapide);
		}

	private void control()
		{
		lent.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent arg0)
				{
				normal.setSelected(false);
				rapide.setSelected(false);
				if (lent.isSelected() == true)
					{
					vitesse = profilVitesse1;
					System.out.println("Vitesse = " + vitesse);
					}
				else
					{
					vitesse = 0;
					System.out.println("Vitesse = " + vitesse);
					}
				}
			});

		normal.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent arg0)
				{
				lent.setSelected(false);
				rapide.setSelected(false);
				if (normal.isSelected() == true)
					{
					vitesse = profilVitesse2;
					System.out.println("Vitesse = " + vitesse);
					}
				else
					{
					vitesse = 0;
					System.out.println("Vitesse = " + vitesse);
					}
				}
			});

		rapide.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent arg0)
				{
				lent.setSelected(false);
				normal.setSelected(false);
				if (rapide.isSelected() == true)
					{
					vitesse = profilVitesse3;
					System.out.println("Vitesse = " + vitesse);
					}
				else
					{
					vitesse = 0;
					System.out.println("Vitesse = " + vitesse);
					}
				}
			});
		}

	private void appearance()
		{
		//		layout.setHgap(10);
		//		layout.setVgap(10);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JCheckBox lent;
	private JCheckBox normal;
	private JCheckBox rapide;

	private static float vitesse;
	final private float profilVitesse1 = (float)0.1;
	final private float profilVitesse2 = (float)0.2;
	final private float profilVitesse3 = (float)0.3;

	private GridLayout layout;
	}
