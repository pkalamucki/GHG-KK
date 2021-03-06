//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.07 at 09:49:45 PM CEST 
//


package m3objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for Network_complexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Network_complexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.openM3.org/m3}GenericDescription_group"/>
 *         &lt;element name="VirtualNetwork" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="aggregationType" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="node">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;extension base="{http://www.openM3.org/m3}GenericItem_complexType">
 *                   &lt;sequence minOccurs="0">
 *                     &lt;group ref="{http://www.openM3.org/m3}GenericAggregate_group"/>
 *                   &lt;/sequence>
 *                 &lt;/extension>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="arc">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;extension base="{http://www.openM3.org/m3}GenericItem_complexType">
 *                   &lt;sequence>
 *                     &lt;element name="predecessor">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="successor">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/extension>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="isDirected" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Network_complexType", propOrder = {
    "name",
    "description",
    "virtualNetwork",
    "nodeOrArc"
})
public class NetworkComplexType {

    protected String name;
    protected String description;
    @XmlElement(name = "VirtualNetwork")
    protected NetworkComplexType.VirtualNetwork virtualNetwork;
    @XmlElements({
        @XmlElement(name = "arc", type = NetworkComplexType.Arc.class),
        @XmlElement(name = "node", type = NetworkComplexType.Node.class)
    })
    protected List<GenericItemComplexType> nodeOrArc;
    @XmlAttribute
    protected Boolean isDirected;

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
     * Gets the value of the virtualNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkComplexType.VirtualNetwork }
     *     
     */
    public NetworkComplexType.VirtualNetwork getVirtualNetwork() {
        return virtualNetwork;
    }

    /**
     * Sets the value of the virtualNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkComplexType.VirtualNetwork }
     *     
     */
    public void setVirtualNetwork(NetworkComplexType.VirtualNetwork value) {
        this.virtualNetwork = value;
    }

    /**
     * Gets the value of the nodeOrArc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nodeOrArc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNodeOrArc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NetworkComplexType.Arc }
     * {@link NetworkComplexType.Node }
     * 
     * 
     */
    public List<GenericItemComplexType> getNodeOrArc() {
        if (nodeOrArc == null) {
            nodeOrArc = new ArrayList<GenericItemComplexType>();
        }
        return this.nodeOrArc;
    }

    /**
     * Gets the value of the isDirected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsDirected() {
        if (isDirected == null) {
            return true;
        } else {
            return isDirected;
        }
    }

    /**
     * Sets the value of the isDirected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDirected(Boolean value) {
        this.isDirected = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.openM3.org/m3}GenericItem_complexType">
     *       &lt;sequence>
     *         &lt;element name="predecessor">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="successor">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
     *               &lt;/restriction>
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
    @XmlType(name = "", propOrder = {
        "predecessor",
        "successor"
    })
    public static class Arc
        extends GenericItemComplexType
    {

        @XmlElement(required = true)
        protected NetworkComplexType.Arc.Predecessor predecessor;
        @XmlElement(required = true)
        protected NetworkComplexType.Arc.Successor successor;

        /**
         * Gets the value of the predecessor property.
         * 
         * @return
         *     possible object is
         *     {@link NetworkComplexType.Arc.Predecessor }
         *     
         */
        public NetworkComplexType.Arc.Predecessor getPredecessor() {
            return predecessor;
        }

        /**
         * Sets the value of the predecessor property.
         * 
         * @param value
         *     allowed object is
         *     {@link NetworkComplexType.Arc.Predecessor }
         *     
         */
        public void setPredecessor(NetworkComplexType.Arc.Predecessor value) {
            this.predecessor = value;
        }

        /**
         * Gets the value of the successor property.
         * 
         * @return
         *     possible object is
         *     {@link NetworkComplexType.Arc.Successor }
         *     
         */
        public NetworkComplexType.Arc.Successor getSuccessor() {
            return successor;
        }

        /**
         * Sets the value of the successor property.
         * 
         * @param value
         *     allowed object is
         *     {@link NetworkComplexType.Arc.Successor }
         *     
         */
        public void setSuccessor(NetworkComplexType.Arc.Successor value) {
            this.successor = value;
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
         *       &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Predecessor {

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
         *       &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Successor {

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

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.openM3.org/m3}GenericItem_complexType">
     *       &lt;sequence minOccurs="0">
     *         &lt;group ref="{http://www.openM3.org/m3}GenericAggregate_group"/>
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
        "aggregates"
    })
    public static class Node
        extends GenericItemComplexType
    {

        protected List<NetworkComplexType.Node.Aggregates> aggregates;

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
         * {@link NetworkComplexType.Node.Aggregates }
         * 
         * 
         */
        public List<NetworkComplexType.Node.Aggregates> getAggregates() {
            if (aggregates == null) {
                aggregates = new ArrayList<NetworkComplexType.Node.Aggregates>();
            }
            return this.aggregates;
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
     *       &lt;attribute name="aggregationType" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class VirtualNetwork {

        @XmlAttribute
        protected QName aggregationType;

        /**
         * Gets the value of the aggregationType property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getAggregationType() {
            return aggregationType;
        }

        /**
         * Sets the value of the aggregationType property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setAggregationType(QName value) {
            this.aggregationType = value;
        }

    }

}
