//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2011.08.04 at 02:05:46 PM CEST
//


package playground.gregor.grips.jaxb.inspire.roadtransportnetwork;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.opengis.gml.v_3_2_1.CodeType;
import playground.gregor.grips.jaxb.inspire.commontransportelements.TransportPropertyType;


/**
 * <p>Java class for RoadSurfaceCategoryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoadSurfaceCategoryType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:x-inspire:specification:gmlas:CommonTransportElements:3.0}TransportPropertyType">
 *       &lt;sequence>
 *         &lt;element name="surfaceCategory" type="{http://www.opengis.net/gml/3.2}CodeType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoadSurfaceCategoryType", propOrder = {
		"surfaceCategory"
})
public class RoadSurfaceCategoryType
extends TransportPropertyType
{

	@XmlElement(required = true)
	protected CodeType surfaceCategory;

	/**
	 * Gets the value of the surfaceCategory property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link CodeType }
	 * 
	 */
	public CodeType getSurfaceCategory() {
		return this.surfaceCategory;
	}

	/**
	 * Sets the value of the surfaceCategory property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link CodeType }
	 * 
	 */
	public void setSurfaceCategory(CodeType value) {
		this.surfaceCategory = value;
	}

	public boolean isSetSurfaceCategory() {
		return (this.surfaceCategory!= null);
	}

	@Override
	public Object createNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
