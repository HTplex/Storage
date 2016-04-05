//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2011.08.04 at 02:05:46 PM CEST
//


package playground.gregor.grips.jaxb.inspire.commontransportelements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.opengis.gml.v_3_2_1.AbstractMetadataPropertyType;
import org.isotc211.iso19139.d_2007_04_17.gmd.CICitationType;


/**
 * <p>Java class for OwnerAuthorityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OwnerAuthorityType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:x-inspire:specification:gmlas:CommonTransportElements:3.0}TransportPropertyType">
 *       &lt;sequence>
 *         &lt;element name="authority">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.opengis.net/gml/3.2}AbstractMetadataPropertyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.isotc211.org/2005/gmd}CI_Citation"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OwnerAuthorityType", propOrder = {
		"authority"
})
public class OwnerAuthorityType
extends TransportPropertyType
{

	@XmlElement(required = true)
	protected OwnerAuthorityType.Authority authority;

	/**
	 * Gets the value of the authority property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link OwnerAuthorityType.Authority }
	 * 
	 */
	public OwnerAuthorityType.Authority getAuthority() {
		return this.authority;
	}

	/**
	 * Sets the value of the authority property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link OwnerAuthorityType.Authority }
	 * 
	 */
	public void setAuthority(OwnerAuthorityType.Authority value) {
		this.authority = value;
	}

	public boolean isSetAuthority() {
		return (this.authority!= null);
	}


	/**
	 * <p>Java class for anonymous complex type.
	 * 
	 * <p>The following schema fragment specifies the expected content contained within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;extension base="{http://www.opengis.net/gml/3.2}AbstractMetadataPropertyType">
	 *       &lt;sequence>
	 *         &lt;element ref="{http://www.isotc211.org/2005/gmd}CI_Citation"/>
	 *       &lt;/sequence>
	 *     &lt;/extension>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {
			"ciCitation"
	})
	public static class Authority
	extends AbstractMetadataPropertyType
	{

		@XmlElement(name = "CI_Citation", namespace = "http://www.isotc211.org/2005/gmd", required = true)
		protected CICitationType ciCitation;

		/**
		 * Gets the value of the ciCitation property.
		 * 
		 * @return
		 *     possible object is
		 *     {@link CICitationType }
		 * 
		 */
		public CICitationType getCICitation() {
			return this.ciCitation;
		}

		/**
		 * Sets the value of the ciCitation property.
		 * 
		 * @param value
		 *     allowed object is
		 *     {@link CICitationType }
		 * 
		 */
		public void setCICitation(CICitationType value) {
			this.ciCitation = value;
		}

		public boolean isSetCICitation() {
			return (this.ciCitation!= null);
		}

		@Override
		public Object createNewInstance() {
			// TODO Auto-generated method stub
			return null;
		}

	}


	@Override
	public Object createNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
