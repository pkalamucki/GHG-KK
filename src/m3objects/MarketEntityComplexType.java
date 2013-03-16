//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.07 at 09:49:40 PM CEST 
//


package m3objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for MarketEntity_complexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarketEntity_complexType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openM3.org/m3}GenericItem_complexType">
 *       &lt;sequence>
 *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="VirtualMarketEntity" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;group ref="{http://www.openM3.org/m3}GenericAggregate_group" minOccurs="0"/>
 *         &lt;element name="relatedTo" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{http://www.openM3.org/m3}GenericRef_attributeGroup"/>
 *                 &lt;attribute name="relationshipType" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="role" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketEntity_complexType", propOrder = {
    "uri",
    "virtualMarketEntity",
    "aggregates",
    "relatedTo"
})
public class MarketEntityComplexType
    extends GenericItemComplexType
{

    @XmlSchemaType(name = "anyURI")
    protected String uri;
    @XmlElement(name = "VirtualMarketEntity")
    protected Object virtualMarketEntity;
    protected List<MarketEntityComplexType.Aggregates> aggregates;
    protected List<MarketEntityComplexType.RelatedTo> relatedTo;
    @XmlAttribute
    protected QName role;

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the virtualMarketEntity property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getVirtualMarketEntity() {
        return virtualMarketEntity;
    }

    /**
     * Sets the value of the virtualMarketEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVirtualMarketEntity(Object value) {
        this.virtualMarketEntity = value;
    }

    /**
     * Gets the value of the aggregates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aggregates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAggregates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketEntityComplexType.Aggregates }
     * 
     * 
     */
    public List<MarketEntityComplexType.Aggregates> getAggregates() {
        if (aggregates == null) {
            aggregates = new ArrayList<MarketEntityComplexType.Aggregates>();
        }
        return this.aggregates;
    }

    /**
     * Gets the value of the relatedTo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedTo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketEntityComplexType.RelatedTo }
     * 
     * 
     */
    public List<MarketEntityComplexType.RelatedTo> getRelatedTo() {
        if (relatedTo == null) {
            relatedTo = new ArrayList<MarketEntityComplexType.RelatedTo>();
        }
        return this.relatedTo;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setRole(QName value) {
        this.role = value;
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
    public static class Aggregates {

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
     *       &lt;attribute name="relationshipType" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class RelatedTo {

        @XmlAttribute
        protected QName relationshipType;
        @XmlAttribute(required = true)
        protected QName ref;

        /**
         * Gets the value of the relationshipType property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getRelationshipType() {
            return relationshipType;
        }

        /**
         * Sets the value of the relationshipType property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setRelationshipType(QName value) {
            this.relationshipType = value;
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