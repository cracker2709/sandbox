//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.10.04 à 05:28:18 PM CEST 
//


package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Elem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Elem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Resp1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Resp2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateMAJ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Elem", propOrder = {
    "nom",
    "desc",
    "dn",
    "resp1",
    "resp2",
    "dateMAJ"
})
public class Elem {

    @XmlElement(name = "Nom", required = true)
    protected String nom;
    @XmlElement(name = "Desc", required = true)
    protected String desc;
    @XmlElement(name = "DN", required = true)
    protected String dn;
    @XmlElement(name = "Resp1")
    protected int resp1;
    @XmlElement(name = "Resp2", required = true)
    protected String resp2;
    @XmlElement(name = "DateMAJ", required = true)
    protected String dateMAJ;

    /**
     * Obtient la valeur de la propriété nom.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de la propriété nom.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Obtient la valeur de la propriété desc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Définit la valeur de la propriété desc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Obtient la valeur de la propriété dn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDN() {
        return dn;
    }

    /**
     * Définit la valeur de la propriété dn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDN(String value) {
        this.dn = value;
    }

    /**
     * Obtient la valeur de la propriété resp1.
     * 
     */
    public int getResp1() {
        return resp1;
    }

    /**
     * Définit la valeur de la propriété resp1.
     * 
     */
    public void setResp1(int value) {
        this.resp1 = value;
    }

    /**
     * Obtient la valeur de la propriété resp2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResp2() {
        return resp2;
    }

    /**
     * Définit la valeur de la propriété resp2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResp2(String value) {
        this.resp2 = value;
    }

    /**
     * Obtient la valeur de la propriété dateMAJ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateMAJ() {
        return dateMAJ;
    }

    /**
     * Définit la valeur de la propriété dateMAJ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateMAJ(String value) {
        this.dateMAJ = value;
    }

}
