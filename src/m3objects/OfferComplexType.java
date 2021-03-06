//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.08 at 11:36:36 PM CEST 
//


package m3objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for Offer_complexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Offer_complexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.openM3.org/m3}GenericDescription_group" minOccurs="0"/>
 *         &lt;element ref="{http://www.openM3.org/m3}offeredBy"/>
 *         &lt;element name="offerStatus" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="durationPeriod" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="startTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                           &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="status" use="required" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="volumeRange" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="minValue" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                 &lt;attribute name="maxValue" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;choice>
 *           &lt;element name="ElementaryOffer">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.openM3.org/m3}offeredCommodity"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="BundledOffer">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.openM3.org/m3}offeredCommodity" maxOccurs="unbounded"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.openM3.org/m3}GenericId_attributeGroup"/>
 *       &lt;attribute name="offeredPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="acceptedVolume" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Offer_complexType", propOrder = {
    "name",
    "description",
    "offeredBy",
    "offerStatus",
    "volumeRange",
    "elementaryOffer",
    "bundledOffer"
})
public class OfferComplexType implements Serializable{

    protected String name;
    protected String description;
    @XmlElement(required = true)
    protected OfferedBy offeredBy;
    @XmlElement(required = true)
    protected List<OfferComplexType.OfferStatus> offerStatus;
    @XmlElement(required = true)
    protected List<OfferComplexType.VolumeRange> volumeRange;
    @XmlElement(name = "ElementaryOffer")
    protected OfferComplexType.ElementaryOffer elementaryOffer;
    @XmlElement(name = "BundledOffer")
    protected OfferComplexType.BundledOffer bundledOffer;
    @XmlAttribute
    protected BigDecimal offeredPrice;
    @XmlAttribute
    protected Double acceptedVolume;
    @XmlAttribute
    protected Double sellPrice;

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }
    @XmlAttribute
    protected Double buyPrice;
    @XmlAttribute(required = true)
    protected QName id;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the offeredBy property.
     * 
     * @return
     *     possible object is
     *     {@link OfferedBy }
     *     
     */
    public OfferedBy getOfferedBy() {
        return offeredBy;
    }

    /**
     * Sets the value of the offeredBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferedBy }
     *     
     */
    public void setOfferedBy(OfferedBy value) {
        this.offeredBy = value;
    }

    /**
     * Gets the value of the offerStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OfferComplexType.OfferStatus }
     * 
     * 
     */
    public List<OfferComplexType.OfferStatus> getOfferStatus() {
        if (offerStatus == null) {
            offerStatus = new ArrayList<OfferComplexType.OfferStatus>();
        }
        return this.offerStatus;
    }

    /**
     * Gets the value of the volumeRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the volumeRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVolumeRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OfferComplexType.VolumeRange }
     * 
     * 
     */
    public List<OfferComplexType.VolumeRange> getVolumeRange() {
        if (volumeRange == null) {
            volumeRange = new ArrayList<OfferComplexType.VolumeRange>();
        }
        return this.volumeRange;
    }

    /**
     * Gets the value of the elementaryOffer property.
     * 
     * @return
     *     possible object is
     *     {@link OfferComplexType.ElementaryOffer }
     *     
     */
    public OfferComplexType.ElementaryOffer getElementaryOffer() {
        return elementaryOffer;
    }

    /**
     * Sets the value of the elementaryOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferComplexType.ElementaryOffer }
     *     
     */
    public void setElementaryOffer(OfferComplexType.ElementaryOffer value) {
        this.elementaryOffer = value;
    }

    /**
     * Gets the value of the bundledOffer property.
     * 
     * @return
     *     possible object is
     *     {@link OfferComplexType.BundledOffer }
     *     
     */
    public OfferComplexType.BundledOffer getBundledOffer() {
        return bundledOffer;
    }

    /**
     * Sets the value of the bundledOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferComplexType.BundledOffer }
     *     
     */
    public void setBundledOffer(OfferComplexType.BundledOffer value) {
        this.bundledOffer = value;
    }

    /**
     * Gets the value of the offeredPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOfferedPrice() {
        return offeredPrice;
    }

    /**
     * Sets the value of the offeredPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOfferedPrice(BigDecimal value) {
        this.offeredPrice = value;
    }

    /**
     * Gets the value of the acceptedVolume property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAcceptedVolume() {
        return acceptedVolume;
    }

    /**
     * Sets the value of the acceptedVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAcceptedVolume(Double value) {
        this.acceptedVolume = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setId(QName value) {
        this.id = value;
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
     *         &lt;element ref="{http://www.openM3.org/m3}offeredCommodity" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "offeredCommodity"
    })
    public static class BundledOffer implements Serializable {

        @XmlElement(required = true)
        protected List<OfferedCommodity> offeredCommodity;

        /**
         * Gets the value of the offeredCommodity property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the offeredCommodity property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOfferedCommodity().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OfferedCommodity }
         * 
         * 
         */
        public List<OfferedCommodity> getOfferedCommodity() {
            if (offeredCommodity == null) {
                offeredCommodity = new ArrayList<OfferedCommodity>();
            }
            return this.offeredCommodity;
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
     *         &lt;element ref="{http://www.openM3.org/m3}offeredCommodity"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "offeredCommodity"
    })
    public static class ElementaryOffer  implements Serializable {

        @XmlElement(required = true)
        protected OfferedCommodity offeredCommodity;

        /**
         * Gets the value of the offeredCommodity property.
         * 
         * @return
         *     possible object is
         *     {@link OfferedCommodity }
         *     
         */
        public OfferedCommodity getOfferedCommodity() {
            return offeredCommodity;
        }

        /**
         * Sets the value of the offeredCommodity property.
         * 
         * @param value
         *     allowed object is
         *     {@link OfferedCommodity }
         *     
         */
        public void setOfferedCommodity(OfferedCommodity value) {
            this.offeredCommodity = value;
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
     *         &lt;element name="durationPeriod" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="startTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *                 &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="status" use="required" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "durationPeriod"
    })
    public static class OfferStatus  implements Serializable {

        @XmlElement(required = true)
        protected List<OfferComplexType.OfferStatus.DurationPeriod> durationPeriod;
        @XmlAttribute(required = true)
        protected QName status;

        /**
         * Gets the value of the durationPeriod property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the durationPeriod property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDurationPeriod().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OfferComplexType.OfferStatus.DurationPeriod }
         * 
         * 
         */
        public List<OfferComplexType.OfferStatus.DurationPeriod> getDurationPeriod() {
            if (durationPeriod == null) {
                durationPeriod = new ArrayList<OfferComplexType.OfferStatus.DurationPeriod>();
            }
            return this.durationPeriod;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setStatus(QName value) {
            this.status = value;
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
         *       &lt;attribute name="startTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *       &lt;attribute name="endTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class DurationPeriod  implements Serializable {

            @XmlAttribute(required = true)
            protected XMLGregorianCalendar startTime;
            @XmlAttribute(required = true)
            protected XMLGregorianCalendar endTime;

            /**
             * Gets the value of the startTime property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getStartTime() {
                return startTime;
            }

            /**
             * Sets the value of the startTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setStartTime(XMLGregorianCalendar value) {
                this.startTime = value;
            }

            /**
             * Gets the value of the endTime property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getEndTime() {
                return endTime;
            }

            /**
             * Sets the value of the endTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setEndTime(XMLGregorianCalendar value) {
                this.endTime = value;
            }

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
     *       &lt;attribute name="minValue" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *       &lt;attribute name="maxValue" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class VolumeRange  implements Serializable {

        @XmlAttribute(required = true)
        protected double minValue;
        @XmlAttribute(required = true)
        protected double maxValue;

        /**
         * Gets the value of the minValue property.
         * 
         */
        public double getMinValue() {
            return minValue;
        }

        /**
         * Sets the value of the minValue property.
         * 
         */
        public void setMinValue(double value) {
            this.minValue = value;
        }

        /**
         * Gets the value of the maxValue property.
         * 
         */
        public double getMaxValue() {
            return maxValue;
        }

        /**
         * Sets the value of the maxValue property.
         * 
         */
        public void setMaxValue(double value) {
            this.maxValue = value;
        }

    }

}
