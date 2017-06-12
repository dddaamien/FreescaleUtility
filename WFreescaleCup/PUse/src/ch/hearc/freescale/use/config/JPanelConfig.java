
package ch.hearc.freescale.use.config;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.freescale.use.Client;
import ch.hearc.freescale.use.config.tools.JPanelFileChooser;

//import javafx.scene.paint.Color;

public class JPanelConfig extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelConfig(Client client)
		{
		this.client = client;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		btnConnect = new JButton();
		port = new JSpinner();
		panelFileChoser = new JPanelFileChooser(client);
		try
			{
			ipAddress = new JTextField(InetAddress.getLocalHost().getHostAddress());
			}
		catch (UnknownHostException e)
			{
			ipAddress = new JTextField("XXX.XXX.XXX.XXX");
			//e.printStackTrace();
			}
		switchConnectState();
		// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(panelFileChoser);
		add(ipAddress);
		add(port);
		add(btnConnect);
		}

	private void control()
		{
		btnConnect.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				switchConnectState();
				}
			});
		ipAddress.setInputVerifier(new InputVerifier()
			{

			@Override
			public boolean verify(JComponent input)
				{
				return checkIp();
				}
			});
		//		ipAddress.addFocusListener(new FocusAdapter()
		//			{
		//
		//			@Override
		//			public void focusLost(FocusEvent e)
		//				{
		//				checkIp();
		//				}
		//			});
		//ne fonctionne pas (checkPort() n'est jamais appelé
		//		port.setInputVerifier(new InputVerifier()
		//			{
		//
		//			@Override
		//			public boolean verify(JComponent input)
		//				{
		//				return checkPort();
		//				}
		//			});
		port.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent arg0)
				{
				checkPort();
				}
			});
		}

	private void appearance()
		{

		}

	private void switchConnectState()
		{
		if (btnConnect.getText().equals(CONNECT_TEXT))
			{
			//TODO: junit test sur ip ?

			btnConnect.setText(DISCONNECT_TEXT);
			ipAddress.setEnabled(false);
			port.setEnabled(false);
			panelFileChoser.setEnabled(false);

			client.setServerName(ipAddress.getText());
			client.setServerPort((Integer)port.getValue());
			System.out.println("Connexion à: " + client.getServerName() + " port: " + client.getServerPort());
			client.start();
			}
		else
			{
			btnConnect.setText(CONNECT_TEXT);
			ipAddress.setEnabled(true);
			port.setEnabled(true);
			panelFileChoser.setEnabled(true);

			System.out.println("Déconnexion...");
			client.stop();
			}
		}

	private boolean checkIp()
		{
		if (IP_PATTERN.matcher(ipAddress.getText()).matches())
			{
			ipAddress.setBackground(Color.WHITE);
			btnConnect.setEnabled(true);
			}
		else
			{
			ipAddress.setBackground(Color.RED);
			btnConnect.setEnabled(false);
			JOptionPane.showMessageDialog(null, "IP non valide", "Erreur! ", JOptionPane.WARNING_MESSAGE);
			}
		return IP_PATTERN.matcher(ipAddress.getText()).matches();
		}

	private boolean checkPort()
		{
		int val = (Integer)port.getValue();
		if ((val >= 0) && (val <= 0xFFFF))
			{
			return true;
			}
		else
			{
			port.setValue(5001);
			return false;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private Client client;
	// Tools
	private JButton btnConnect;
	private JPanelFileChooser panelFileChoser;
	private JTextField ipAddress;
	private JSpinner port;
	//static
	private static final Pattern IP_PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	private static final String CONNECT_TEXT = "Connect";
	private static final String DISCONNECT_TEXT = "Disconnect";
	}
