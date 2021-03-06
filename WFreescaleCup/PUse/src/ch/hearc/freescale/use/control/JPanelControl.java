
package ch.hearc.freescale.use.control;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.hearc.freescale.use.Client;

public class JPanelControl extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelControl(Client client)
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
		panelCroixDirectionnelle = new JPanelCroixDirectionnelle(client);
		panelProfilsVitesse = new JPanelProfilsVitesse();
		panelOptions = new JPanelOptions(client);

		// Layout : Specification
			{
			//			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			//			setLayout(flowlayout);

			//			layout = new GridLayout(3, 1); //2 colonnes, nb de lignes calcul�s automatiquement, ici 3
			//			setLayout(layout);

			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);

			//borderLayout.setHgap(20);
			//borderLayout.setVgap(20);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		//		add(panelCroixDirectionnelle);
		//		add(panelProfilsVitesse);
		//		add(panelOptions);

		add(panelCroixDirectionnelle, BorderLayout.NORTH);
		add(panelProfilsVitesse, BorderLayout.CENTER);
		add(panelOptions, BorderLayout.SOUTH);
		}

	private void control()
		{
		//rien
		}

	private void appearance()
		{
		//		layout.setHgap(50);
		//		layout.setVgap(20);

		//debug
		//panelCroixDirectionnelle.setBackground(Color.CYAN);
		//panelProfilsVitesse.setBackground(Color.RED);
		//panelOptions.setBackground(Color.GREEN);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Client client;

	private JPanelCroixDirectionnelle panelCroixDirectionnelle;
	private JPanelProfilsVitesse panelProfilsVitesse;
	private JPanelOptions panelOptions;

	//private GridLayout layout;
	}
