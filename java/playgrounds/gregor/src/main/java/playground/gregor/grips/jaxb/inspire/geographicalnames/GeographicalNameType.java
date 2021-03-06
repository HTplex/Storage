//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.08.04 at 02:05:46 PM CEST 
//


package playground.gregor.grips.jaxb.inspire.geographicalnames;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import net.opengis.gml.v_3_2_1.CodeType;


/**
 * <p>Java class for GeographicalNameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeographicalNameType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="language">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="nativeness">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
 *                 &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="nameStatus">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
 *                 &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sourceOfName">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pronunciation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{urn:x-inspire:specification:gmlas:GeographicalNames:3.0}PronunciationOfName"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="spelling" type="{urn:x-inspire:specification:gmlas:GeographicalNames:3.0}SpellingOfNamePropertyType" maxOccurs="unbounded"/>
 *         &lt;element name="grammaticalGender" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
 *                 &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="grammaticalNumber" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
 *                 &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeographicalNameType", propOrder = {
    "language",
    "nativeness",
    "nameStatus",
    "sourceOfName",
    "pronunciation",
    "spelling",
    "grammaticalGender",
    "grammaticalNumber"
})
public class GeographicalNameType {

    @XmlElement(required = true, nillable = true)
    protected GeographicalNameType.Language language;
    @XmlElement(required = true, nillable = true)
    protected GeographicalNameType.Nativeness nativeness;
    @XmlElement(required = true, nillable = true)
    protected GeographicalNameType.NameStatus nameStatus;
    @XmlElement(required = true, nillable = true)
    protected GeographicalNameType.SourceOfName sourceOfName;
    @XmlElement(required = true, nillable = true)
    protected GeographicalNameType.Pronunciation pronunciation;
    @XmlElement(required = true)
    protected List<SpellingOfNamePropertyType> spelling;
    @XmlElementRef(name = "grammaticalGender", namespace = "urn:x-inspire:specification:gmlas:GeographicalNames:3.0", type = JAXBElement.class)
    protected JAXBElement<GeographicalNameType.GrammaticalGender> grammaticalGender;
    @XmlElementRef(name = "grammaticalNumber", namespace = "urn:x-inspire:specification:gmlas:GeographicalNames:3.0", type = JAXBElement.class)
    protected JAXBElement<GeographicalNameType.GrammaticalNumber> grammaticalNumber;

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link GeographicalNameType.Language }
     *     
     */
    public GeographicalNameType.Language getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicalNameType.Language }
     *     
     */
    public void setLanguage(GeographicalNameType.Language value) {
        this.language = value;
    }

    public boolean isSetLanguage() {
        return (this.language!= null);
    }

    /**
     * Gets the value of the nativeness property.
     * 
     * @return
     *     possible object is
     *     {@link GeographicalNameType.Nativeness }
     *     
     */
    public GeographicalNameType.Nativeness getNativeness() {
        return nativeness;
    }

    /**
     * Sets the value of the nativeness property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicalNameType.Nativeness }
     *     
     */
    public void setNativeness(GeographicalNameType.Nativeness value) {
        this.nativeness = value;
    }

    public boolean isSetNativeness() {
        return (this.nativeness!= null);
    }

    /**
     * Gets the value of the nameStatus property.
     * 
     * @return
     *     possible object is
     *     {@link GeographicalNameType.NameStatus }
     *     
     */
    public GeographicalNameType.NameStatus getNameStatus() {
        return nameStatus;
    }

    /**
     * Sets the value of the nameStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicalNameType.NameStatus }
     *     
     */
    public void setNameStatus(GeographicalNameType.NameStatus value) {
        this.nameStatus = value;
    }

    public boolean isSetNameStatus() {
        return (this.nameStatus!= null);
    }

    /**
     * Gets the value of the sourceOfName property.
     * 
     * @return
     *     possible object is
     *     {@link GeographicalNameType.SourceOfName }
     *     
     */
    public GeographicalNameType.SourceOfName getSourceOfName() {
        return sourceOfName;
    }

    /**
     * Sets the value of the sourceOfName property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicalNameType.SourceOfName }
     *     
     */
    public void setSourceOfName(GeographicalNameType.SourceOfName value) {
        this.sourceOfName = value;
    }

    public boolean isSetSourceOfName() {
        return (this.sourceOfName!= null);
    }

    /**
     * Gets the value of the pronunciation property.
     * 
     * @return
     *     possible object is
     *     {@link GeographicalNameType.Pronunciation }
     *     
     */
    public GeographicalNameType.Pronunciation getPronunciation() {
        return pronunciation;
    }

    /**
     * Sets the value of the pronunciation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicalNameType.Pronunciation }
     *     
     */
    public void setPronunciation(GeographicalNameType.Pronunciation value) {
        this.pronunciation = value;
    }

    public boolean isSetPronunciation() {
        return (this.pronunciation!= null);
    }

    /**
     * Gets the value of the spelling property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spelling property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpelling().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpellingOfNamePropertyType }
     * 
     * 
     */
    public List<SpellingOfNamePropertyType> getSpelling() {
        if (spelling == null) {
            spelling = new ArrayList<SpellingOfNamePropertyType>();
        }
        return this.spelling;
    }

    public boolean isSetSpelling() {
        return ((this.spelling!= null)&&(!this.spelling.isEmpty()));
    }

    public void unsetSpelling() {
        this.spelling = null;
    }

    /**
     * Gets the value of the grammaticalGender property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GeographicalNameType.GrammaticalGender }{@code >}
     *     
     */
    public JAXBElement<GeographicalNameType.GrammaticalGender> getGrammaticalGender() {
        return grammaticalGender;
    }

    /**
     * Sets the value of the grammaticalGender property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GeographicalNameType.GrammaticalGender }{@code >}
     *     
     */
    public void setGrammaticalGender(JAXBElement<GeographicalNameType.GrammaticalGender> value) {
        this.grammaticalGender = ((JAXBElement<GeographicalNameType.GrammaticalGender> ) value);
    }

    public boolean isSetGrammaticalGender() {
        return (this.grammaticalGender!= null);
    }

    /**
     * Gets the value of the grammaticalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GeographicalNameType.GrammaticalNumber }{@code >}
     *     
     */
    public JAXBElement<GeographicalNameType.GrammaticalNumber> getGrammaticalNumber() {
        return grammaticalNumber;
    }

    /**
     * Sets the value of the grammaticalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GeographicalNameType.GrammaticalNumber }{@code >}
     *     
     */
    public void setGrammaticalNumber(JAXBElement<GeographicalNameType.GrammaticalNumber> value) {
        this.grammaticalNumber = ((JAXBElement<GeographicalNameType.GrammaticalNumber> ) value);
    }

    public boolean isSetGrammaticalNumber() {
        return (this.grammaticalNumber!= null);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
     *       &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class GrammaticalGender
        extends CodeType
    {

        @XmlAttribute
        protected List<String> nilReason;

        /**
         * Gets the value of the nilReason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nilReason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNilReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNilReason() {
            if (nilReason == null) {
                nilReason = new ArrayList<String>();
            }
            return this.nilReason;
        }

        public boolean isSetNilReason() {
            return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
        }

        public void unsetNilReason() {
            this.nilReason = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
     *       &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class GrammaticalNumber
        extends CodeType
    {

        @XmlAttribute
        protected List<String> nilReason;

        /**
         * Gets the value of the nilReason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nilReason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNilReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNilReason() {
            if (nilReason == null) {
                nilReason = new ArrayList<String>();
            }
            return this.nilReason;
        }

        public boolean isSetNilReason() {
            return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
        }

        public void unsetNilReason() {
            this.nilReason = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Language {

        @XmlValue
        protected String value;
        @XmlAttribute
        protected List<String> nilReason;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        public boolean isSetValue() {
            return (this.value!= null);
        }

        /**
         * Gets the value of the nilReason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nilReason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNilReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNilReason() {
            if (nilReason == null) {
                nilReason = new ArrayList<String>();
            }
            return this.nilReason;
        }

        public boolean isSetNilReason() {
            return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
        }

        public void unsetNilReason() {
            this.nilReason = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
     *       &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NameStatus
        extends CodeType
    {

        @XmlAttribute
        protected List<String> nilReason;

        /**
         * Gets the value of the nilReason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nilReason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNilReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNilReason() {
            if (nilReason == null) {
                nilReason = new ArrayList<String>();
            }
            return this.nilReason;
        }

        public boolean isSetNilReason() {
            return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
        }

        public void unsetNilReason() {
            this.nilReason = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opengis.net/gml/3.2>CodeType">
     *       &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Nativeness
        extends CodeType
    {

        @XmlAttribute
        protected List<String> nilReason;

        /**
         * Gets the value of the nilReason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nilReason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNilReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNilReason() {
            if (nilReason == null) {
                nilReason = new ArrayList<String>();
            }
            return this.nilReason;
        }

        public boolean isSetNilReason() {
            return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
        }

        public void unsetNilReason() {
            this.nilReason = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{urn:x-inspire:specification:gmlas:GeographicalNames:3.0}PronunciationOfName"/>
     *       &lt;/sequence>
     *       &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pronunciationOfName"
    })
    public static class Pronunciation {

        @XmlElement(name = "PronunciationOfName", required = true)
        protected PronunciationOfNameType pronunciationOfName;
        @XmlAttribute
        protected List<String> nilReason;

        /**
         * Gets the value of the pronunciationOfName property.
         * 
         * @return
         *     possible object is
         *     {@link PronunciationOfNameType }
         *     
         */
        public PronunciationOfNameType getPronunciationOfName() {
            return pronunciationOfName;
        }

        /**
         * Sets the value of the pronunciationOfName property.
         * 
         * @param value
         *     allowed object is
         *     {@link PronunciationOfNameType }
         *     
         */
        public void setPronunciationOfName(PronunciationOfNameType value) {
            this.pronunciationOfName = value;
        }

        public boolean isSetPronunciationOfName() {
            return (this.pronunciationOfName!= null);
        }

        /**
         * Gets the value of the nilReason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nilReason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNilReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNilReason() {
            if (nilReason == null) {
                nilReason = new ArrayList<String>();
            }
            return this.nilReason;
        }

        public boolean isSetNilReason() {
            return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
        }

        public void unsetNilReason() {
            this.nilReason = null;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="nilReason" type="{http://www.opengis.net/gml/3.2}NilReasonType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class SourceOfName {

        @XmlValue
        protected String value;
        @XmlAttribute
        protected List<String> nilReason;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        public boolean isSetValue() {
            return (this.value!= null);
        }

        /**
         * Gets the value of the nilReason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nilReason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNilReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getNilReason() {
            if (nilReason == null) {
                nilReason = new ArrayList<String>();
            }
            return this.nilReason;
        }

        public boolean isSetNilReason() {
            return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
        }

        public void unsetNilReason() {
            this.nilReason = null;
        }

    }

}
