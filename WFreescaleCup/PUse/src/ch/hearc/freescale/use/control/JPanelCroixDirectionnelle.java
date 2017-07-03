
package ch.hearc.freescale.use.control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.freescale.use.Client;

public class JPanelCroixDirectionnelle extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelCroixDirectionnelle(Client client)
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
		haut = new JButton("Haut");
		bas = new JButton("Bas");
		droite = new JButton("Droite");
		gauche = new JButton("Gauche");
		stop = new JButton("Stop");

		// Layout : Specification
			{
			//			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			//			setLayout(flowlayout);

			layout = new GridLayout(3, 3); //2 colonnes, nb de lignes calculés automatiquement, ici 3
			setLayout(layout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(new JPanel());
		add(haut);
		add(new JPanel());
		add(gauche);
		add(stop);
		add(droite);
		add(new JPanel());
		add(bas);
		add(new JPanel());
		}

	private void control()
		{
		haut.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				client.setVitesseDroite(JPanelProfilsVitesse.getVitesse());
				client.setVitesseGauche(JPanelProfilsVitesse.getVitesse());
				System.out.println("Accelere");
				}
			});

		bas.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				client.setVitesseDroite(-JPanelProfilsVitesse.getVitesse());
				client.setVitesseGauche(-JPanelProfilsVitesse.getVitesse());
				System.out.println("Decelere");
				}
			});

		droite.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				client.setVitesseDroite(0);
				client.setVitesseGauche(JPanelProfilsVitesse.getVitesse());
				System.out.println("Tourne a droite");
				}
			});

		gauche.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				client.setVitesseDroite(JPanelProfilsVitesse.getVitesse());
				client.setVitesseGauche(0);
				System.out.println("Tourne a gauche");
				}
			});

		stop.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				client.setVitesseDroite(0);
				client.setVitesseGauche(0);
				System.out.println("Stop");
				}
			});

		}

	private void appearance()
		{
		layout.setHgap(5);
		layout.setVgap(5);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Client client;

	private JButton haut;
	private JButton bas;
	private JButton droite;
	private JButton gauche;
	private JButton stop;

	private GridLayout layout;
	}
