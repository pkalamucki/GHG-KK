//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.08 at 11:59:46 PM CEST 
//


package m3objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openM3.org/m3}KindDefinition_complexType">
 *       &lt;attribute name="category" use="required" type="{http://www.openM3.org/m3}DomainDefinedQName_simpleType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "CommodityKind")
public class CommodityKind
    extends KindDefinitionComplexType
{

    @XmlAttribute(required = true)
    protected QName category;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setCategory(QName value) {
        this.category = value;
    }

}
