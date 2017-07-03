
package ch.hearc.freescale.use;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ch.hearc.freescale.use.config.JPanelConfig;
import ch.hearc.freescale.use.control.JPanelControl;
import ch.hearc.freescale.use.monitoring.JPanelMonitoring;

public class JFrameFreescale extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameFreescale()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void stop()
		{
		client.stop();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation

		panelMonitoring = new JPanelMonitoring();
		this.client = new Client(panelMonitoring);
		panelConfig = new JPanelConfig(client);
		panelControl = new JPanelControl(client);
		// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);

			// borderLayout.setHgap(20);
			// borderLayout.setVgap(20);
			}

		// JComponent : add
		add(panelConfig, BorderLayout.NORTH);
		add(panelMonitoring, BorderLayout.CENTER);
		add(panelControl, BorderLayout.WEST);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setSize(1280, 720);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private Client client;
	private JPanelConfig panelConfig;
	private JPanelMonitoring panelMonitoring;
	private JPanelControl panelControl;
	}
