//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.08.11 at 02:16:01 PM CEST 
//


package playground.gregor.grips.jaxb.EDL.building;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the playground.gregor.grips.jaxb.EDL.building package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Building_QNAME = new QName("esdl", "building");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: playground.gregor.grips.jaxb.EDL.building
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuildingType }
     * 
     */
    public BuildingType createBuildingType() {
        return new BuildingType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuildingType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "esdl", name = "building", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "_Feature")
    public JAXBElement<BuildingType> createBuilding(BuildingType value) {
        return new JAXBElement<BuildingType>(_Building_QNAME, BuildingType.class, null, value);
    }

}