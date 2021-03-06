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
import playground.gregor.grips.jaxb.inspire.basetypes.IdentifierPropertyType;
import playground.gregor.grips.jaxb.inspire.network.NetworkType;


/**
 * <p>Java class for TransportNetworkType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransportNetworkType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:x-inspire:specification:gmlas:Network:3.2}NetworkType">
 *       &lt;sequence>
 *         &lt;element name="inspireId" type="{urn:x-inspire:specification:gmlas:BaseTypes:3.2}IdentifierPropertyType"/>
 *         &lt;element name="typeOfTransport" type="{urn:x-inspire:specification:gmlas:CommonTransportElements:3.0}TransportTypeValueType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransportNetworkType", propOrder = {
    "inspireId",
    "typeOfTransport"
})
public class TransportNetworkType
    extends NetworkType
{

    @XmlElement(required = true)
    protected IdentifierPropertyType inspireId;
    @XmlElement(required = true)
    protected TransportTypeValueType typeOfTransport;

    /**
     * Gets the value of the inspireId property.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierPropertyType }
     *     
     */
    public IdentifierPropertyType getInspireId() {
        return inspireId;
    }

    /**
     * Sets the value of the inspireId property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierPropertyType }
     *     
     */
    public void setInspireId(IdentifierPropertyType value) {
        this.inspireId = value;
    }

    public boolean isSetInspireId() {
        return (this.inspireId!= null);
    }

    /**
     * Gets the value of the typeOfTransport property.
     * 
     * @return
     *     possible object is
     *     {@link TransportTypeValueType }
     *     
     */
    public TransportTypeValueType getTypeOfTransport() {
        return typeOfTransport;
    }

    /**
     * Sets the value of the typeOfTransport property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportTypeValueType }
     *     
     */
    public void setTypeOfTransport(TransportTypeValueType value) {
        this.typeOfTransport = value;
    }

    public boolean isSetTypeOfTransport() {
        return (this.typeOfTransport!= null);
    }

}
