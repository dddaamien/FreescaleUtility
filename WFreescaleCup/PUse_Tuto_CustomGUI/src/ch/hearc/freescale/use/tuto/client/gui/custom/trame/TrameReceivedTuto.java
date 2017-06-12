
package ch.hearc.freescale.use.tuto.client.gui.custom.trame;

import ch.hearc.freescale.api.image.Image;
import ch.hearc.freescale.api.protocol.Trame_I;

/**
 * Hypothese :
 *
 * 			La voiture envoie une trame contenant les valeures ordonnées suivantes :
 * 					Byte : AppID 	(constant pour une même trame)
 * 					Byte : TrameID 	(constant pour une même trame)
 * 					Float
 * 					Integer [-2^31, 2^31-1]
 * 					Short 	[-2^15, 2^15-1]
 * 					Byte	[-128, 127]
 * 					Image	Byte[145]
 *
 *
 * Contrainte :
 *
 * 			Implementer une classe :
 * 				(C1) avec les attribus ci-dessus
 * 				(C2) Avec un constructeur vide
 * 				(C3) Avec un contructeur plein
 * 				(C4) Implemantant Trame_I
 *
 * 			Eventuellement
 * 				Une méthode toString
 *
 * But :
 *
 * 			Afficher le contenu de la trame dans une console
 *
 * Note :
 *
 * 			Voir pUse_Tuto_ClientConsole pour un affichage dans la console
 * 			Voir pUse_Tuto_ClientGui pour un affichage graphique
 * 			Voir pUse_Tuto_Server pour un l'utilisation du simulateur
 *
 */
public class TrameReceivedTuto implements Trame_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * Constructeur "vide" obligatoire
	 */
	public TrameReceivedTuto()
		{
		//Rien
		}

	/**
	 * Constructeur "plein" obligatoire
	 */
	public TrameReceivedTuto(Float floatField, Integer integerField, Short shortField, Byte byteField, Image imageField)
		{
		this.floatField = floatField;
		this.integerField = integerField;
		this.shortField = shortField;
		this.byteField = byteField;
		this.imageField = imageField;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("DemoTrame [floatField=");
		builder.append(this.floatField);
		builder.append(", integerField=");
		builder.append(this.integerField);
		builder.append(", shortField=");
		builder.append(this.shortField);
		builder.append(", byteField=");
		builder.append(this.byteField);
		builder.append(", imageField=");
		builder.append(this.imageField);
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/**
	 * Le systeme embarquer peut envoyer des trames differentes, par exemple a des frequences differentes avec des attributs differents.
	 * Une trame possédant un meme contenu doit avoir le meme id
	 */
	@Override
	public byte getTrameID()
		{
		return 1;
		}

	/**
	 * Utiliser dans le GUI!
	 *
	 * Changer cette valeur pour ecraser les préférences des positions des graphiques dans le conteneur.
	 * Utile lorsqu'on enleve ou ajoute un attribut dans la classe, et plus globalement lorsque le nombre de graphique change
	 */
	@Override
	public byte getAppID()
		{
		return 10;
		}

	public Short getShortField()
		{
		return this.shortField;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Meme ordre que les parametres envoyé par la voiture, sans ni AppID ni TrameID
	private Float floatField;
	private Integer integerField;
	private Short shortField;
	private Byte byteField;
	private Image imageField;
	}
