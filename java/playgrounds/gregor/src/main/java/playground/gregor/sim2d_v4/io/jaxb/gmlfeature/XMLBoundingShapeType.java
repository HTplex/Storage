//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.18 at 02:24:41 PM CET 
//


package playground.gregor.sim2d_v4.io.jaxb.gmlfeature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Bounding shapes--a Box or a null element are currently allowed.
 *       
 * 
 * <p>Java class for BoundingShapeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BoundingShapeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}Box"/>
 *           &lt;element name="null" type="{http://www.opengis.net/gml}NullType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoundingShapeType", propOrder = {
    "box",
    "_null"
})
public class XMLBoundingShapeType {

    @XmlElement(name = "Box")
    protected XMLBoxType box;
    @XmlElement(name = "null")
    protected XMLNullType _null;

    /**
     * Gets the value of the box property.
     * 
     * @return
     *     possible object is
     *     {@link XMLBoxType }
     *     
     */
    public XMLBoxType getBox() {
        return box;
    }

    /**
     * Sets the value of the box property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLBoxType }
     *     
     */
    public void setBox(XMLBoxType value) {
        this.box = value;
    }

    /**
     * Gets the value of the null property.
     * 
     * @return
     *     possible object is
     *     {@link XMLNullType }
     *     
     */
    public XMLNullType getNull() {
        return _null;
    }

    /**
     * Sets the value of the null property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLNullType }
     *     
     */
    public void setNull(XMLNullType value) {
        this._null = value;
    }

}
