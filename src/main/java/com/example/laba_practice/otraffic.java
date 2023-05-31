package com.example.laba_practice;

import jakarta.xml.bind.annotation.*;

import javax.xml.datatype.XMLGregorianCalendar;



/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="traffic" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="street" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "traffic",
        "street",
        "date"
})
@XmlRootElement(name = "steet_traffic")
public class otraffic {

    protected int traffic;
    protected String street;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;

    /**
     * Gets the value of the temperature property.
     *
     */
    public int getTraffic() {
        return traffic;
    }

    /**
     * Sets the value of the temperature property.
     *
     */
    public void setTraffic(int value) {
        this.traffic = value;
    }

    /**
     * Gets the value of the pressure property.
     *
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the pressure property.
     *
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the date property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

}
