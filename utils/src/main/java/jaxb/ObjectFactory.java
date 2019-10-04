//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.10.04 à 05:28:18 PM CEST 
//


package jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb package. 
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

    private final static QName _List_QNAME = new QName("http://jaxb", "List");
    private final static QName _GlobalList_QNAME = new QName("http://jaxb", "GlobalList");
    private final static QName _Elem_QNAME = new QName("http://jaxb", "Elem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link List }
     * 
     */
    public List createList() {
        return new List();
    }

    /**
     * Create an instance of {@link GlobalList }
     * 
     */
    public GlobalList createGlobalList() {
        return new GlobalList();
    }

    /**
     * Create an instance of {@link Elem }
     * 
     */
    public Elem createElem() {
        return new Elem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxb", name = "List")
    public JAXBElement<List> createList(List value) {
        return new JAXBElement<List>(_List_QNAME, List.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GlobalList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxb", name = "GlobalList")
    public JAXBElement<GlobalList> createGlobalList(GlobalList value) {
        return new JAXBElement<GlobalList>(_GlobalList_QNAME, GlobalList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Elem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxb", name = "Elem")
    public JAXBElement<Elem> createElem(Elem value) {
        return new JAXBElement<Elem>(_Elem_QNAME, Elem.class, null, value);
    }

}
