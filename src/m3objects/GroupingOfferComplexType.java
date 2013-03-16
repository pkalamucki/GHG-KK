//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.08 at 11:36:36 PM CEST 
//


package m3objects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for GroupingOffer_complexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupingOffer_complexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.openM3.org/m3}GenericDescription_group" minOccurs="0"/>
 *         &lt;element ref="{http://www.openM3.org/m3}offeredBy"/>
 *         &lt;element name="groups" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="groupedCommodity">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attGroup ref="{http://www.openM3.org/m3}GenericRef_attributeGroup"/>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="groupedOffer">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attGroup ref="{http://www.openM3.org/m3}GenericRef_attributeGroup"/>
 *                           &lt;attribute name="coefficientValue" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SystemConstraint" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.openM3.org/m3}GenericId_attributeGroup"/>
 *       &lt;attribute name="groupingFunction" use="required" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
 *       &lt;attribute name="groupingCost" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupingOffer_complexType", propOrder = {
    "name",
    "description",
    "offeredBy",
    "groups",
    "systemConstraint"
})
public class GroupingOfferComplexType {

    protected String name;
    protected String description;
    @XmlElement(required = true)
    protected OfferedBy offeredBy;
    @XmlElement(required = true)
    protected List<GroupingOfferComplexType.Groups> groups;
    @XmlElement(name = "SystemConstraint")
    protected Object systemConstraint;
    @XmlAttribute(required = true)
    protected QName groupingFunction;
    @XmlAttribute
    protected BigDecimal groupingCost;
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
     * Gets the value of the groups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroupingOfferComplexType.Groups }
     * 
     * 
     */
    public List<GroupingOfferComplexType.Groups> getGroups() {
        if (groups == null) {
            groups = new ArrayList<GroupingOfferComplexType.Groups>();
        }
        return this.groups;
    }

    /**
     * Gets the value of the systemConstraint property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSystemConstraint() {
        return systemConstraint;
    }

    /**
     * Sets the value of the systemConstraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSystemConstraint(Object value) {
        this.systemConstraint = value;
    }

    /**
     * Gets the value of the groupingFunction property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getGroupingFunction() {
        return groupingFunction;
    }

    /**
     * Sets the value of the groupingFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setGroupingFunction(QName value) {
        this.groupingFunction = value;
    }

    /**
     * Gets the value of the groupingCost property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGroupingCost() {
        return groupingCost;
    }

    /**
     * Sets the value of the groupingCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGroupingCost(BigDecimal value) {
        this.groupingCost = value;
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
     *         &lt;element name="groupedCommodity">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attGroup ref="{http://www.openM3.org/m3}GenericRef_attributeGroup"/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="groupedOffer">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attGroup ref="{http://www.openM3.org/m3}GenericRef_attributeGroup"/>
     *                 &lt;attribute name="coefficientValue" type="{http://www.w3.org/2001/XMLSchema}double" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
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
    @XmlType(name = "", propOrder = {
        "groupedCommodity",
        "groupedOffer"
    })
    public static class Groups {

        @XmlElement(required = true)
        protected GroupingOfferComplexType.Groups.GroupedCommodity groupedCommodity;
        @XmlElement(required = true)
        protected GroupingOfferComplexType.Groups.GroupedOffer groupedOffer;

        /**
         * Gets the value of the groupedCommodity property.
         * 
         * @return
         *     possible object is
         *     {@link GroupingOfferComplexType.Groups.GroupedCommodity }
         *     
         */
        public GroupingOfferComplexType.Groups.GroupedCommodity getGroupedCommodity() {
            return groupedCommodity;
        }

        /**
         * Sets the value of the groupedCommodity property.
         * 
         * @param value
         *     allowed object is
         *     {@link GroupingOfferComplexType.Groups.GroupedCommodity }
         *     
         */
        public void setGroupedCommodity(GroupingOfferComplexType.Groups.GroupedCommodity value) {
            this.groupedCommodity = value;
        }

        /**
         * Gets the value of the groupedOffer property.
         * 
         * @return
         *     possible object is
         *     {@link GroupingOfferComplexType.Groups.GroupedOffer }
         *     
         */
        public GroupingOfferComplexType.Groups.GroupedOffer getGroupedOffer() {
            return groupedOffer;
        }

        /**
         * Sets the value of the groupedOffer property.
         * 
         * @param value
         *     allowed object is
         *     {@link GroupingOfferComplexType.Groups.GroupedOffer }
         *     
         */
        public void setGroupedOffer(GroupingOfferComplexType.Groups.GroupedOffer value) {
            this.groupedOffer = value;
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
         *       &lt;attGroup ref="{http://www.openM3.org/m3}GenericRef_attributeGroup"/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class GroupedCommodity {

            @XmlAttribute(required = true)
            protected QName ref;

            /**
             * Gets the value of the ref property.
             * 
             * @return
             *     possible object is
             *     {@link QName }
             *     
             */
            public QName getRef() {
                return ref;
            }

            /**
             * Sets the value of the ref property.
             * 
             * @param value
             *     allowed object is
             *     {@link QName }
             *     
             */
            public void setRef(QName value) {
                this.ref = value;
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
         *       &lt;attGroup ref="{http://www.openM3.org/m3}GenericRef_attributeGroup"/>
         *       &lt;attribute name="coefficientValue" type="{http://www.w3.org/2001/XMLSchema}double" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class GroupedOffer {

            @XmlAttribute
            protected Double coefficientValue;
            @XmlAttribute(required = true)
            protected QName ref;

            /**
             * Gets the value of the coefficientValue property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getCoefficientValue() {
                return coefficientValue;
            }

            /**
             * Sets the value of the coefficientValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setCoefficientValue(Double value) {
                this.coefficientValue = value;
            }

            /**
             * Gets the value of the ref property.
             * 
             * @return
             *     possible object is
             *     {@link QName }
             *     
             */
            public QName getRef() {
                return ref;
            }

            /**
             * Sets the value of the ref property.
             * 
             * @param value
             *     allowed object is
             *     {@link QName }
             *     
             */
            public void setRef(QName value) {
                this.ref = value;
            }

        }

    }

}