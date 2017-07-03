
package ch.hearc.freescale.use.config.tools;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.freescale.use.Client;

public class JPanelFileChooser extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelFileChooser(Client client)
		{
		this.client = client;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void setEnabled(boolean enable)
		{
		eblLog.setEnabled(enable);
		updateLogState();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		fileName = new String("Freescale" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".xls");
		// JComponent : Instanciation
		eblLog = new JCheckBox();
		btnSearch = new JButton(fileName);

		updateLogState();
		// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(new JLabel("Log: "));
		add(eblLog);
		add(btnSearch);
		}

	private void control()
		{
		btnSearch.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent arg0)
				{
				// création du filechooser
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Selection du fichier..");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.showOpenDialog(null);
				System.out.println("getSelectedFile() : " + fileChooser.getSelectedFile());
				if (fileChooser.getSelectedFile() != null)
					{
					client.setWorkbookName(fileChooser.getSelectedFile() + File.separator + fileName);
					}
				}
			});
		eblLog.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent arg0)
				{
				updateLogState();
				}
			});
		}

	private void appearance()
		{
		// rien
		}

	private void updateLogState()
		{
		if (eblLog.isSelected())
			{
			btnSearch.setEnabled(true);
			}
		else
			{
			btnSearch.setEnabled(false);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JFileChooser fileChooser;
	private JButton btnSearch;
	private String fileName;
	private JCheckBox eblLog;
	private Client client;
	}
