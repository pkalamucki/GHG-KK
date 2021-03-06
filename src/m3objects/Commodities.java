//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.08 at 11:59:46 PM CEST 
//


package m3objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Commodity" type="{http://www.openM3.org/m3}Commodity_complexType" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.openM3.org/m3}commodityKinds" minOccurs="0"/>
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
    "commodity",
    "commodityKinds"
})
@XmlRootElement(name = "commodities")
public class Commodities {

    @XmlElement(name = "Commodity", required = true)
    protected List<CommodityComplexType> commodity;
    protected CommodityKinds commodityKinds;

    /**
     * Gets the value of the commodity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commodity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommodity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommodityComplexType }
     * 
     * 
     */
    public List<CommodityComplexType> getCommodity() {
        if (commodity == null) {
            commodity = new ArrayList<CommodityComplexType>();
        }
        return this.commodity;
    }

    /**
     * Gets the value of the commodityKinds property.
     * 
     * @return
     *     possible object is
     *     {@link CommodityKinds }
     *     
     */
    public CommodityKinds getCommodityKinds() {
        return commodityKinds;
    }

    /**
     * Sets the value of the commodityKinds property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommodityKinds }
     *     
     */
    public void setCommodityKinds(CommodityKinds value) {
        this.commodityKinds = value;
    }

}
