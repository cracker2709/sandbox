package jaxb;


import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public final class JaxbHelper {
    public JaxbHelper() {
        // DO NOTHING
    }

    /**
     * Permet de désérialiser l'objet de type <code>typeObjet</code> sous forme
     * XML contenu dans le {@link InputStream} en objet Java.
     *
     * @param typeObjet
     *            Le type de la {@link Class} de l'objet à désérialiser.
     * @param in
     *            {@link InputStream} contenant le format pivot sous forme XML.
     * @return L'objet de type <code>typeObjet</code>
     * @throws JAXBException
     *             En cas d'erreur lors de la désérialisation
     */
    public static <T> T unmarshall(final Class<T> typeObjet,
                                   final InputStream in) throws JAXBException {
        return JAXBContext.newInstance(typeObjet).createUnmarshaller()
                .unmarshal(new StreamSource(in), typeObjet).getValue();
    }

    /**
     * Permet de désérialiser l'objet de type <code>typeObjet</code> sous forme
     * XML contenu dans le {@link InputStream} en objet Java.
     *
     * @param rootType
     *            Le type de la {@link Class} de l'objet à désérialiser.
     * @param subClasses
     *            Tableau de sous-classes composant le <code>rootType</code>
     * @param in
     *            {@link InputStream} contenant le format pivot sous forme XML.
     * @return L'objet de type <code>typeObjet</code>
     * @throws JAXBException
     *             En cas d'erreur lors de la désérialisation
     */
    public static <T> T unmarshall(final Class<T> rootType,
                                   final Class<?>[] subClasses, final InputStream in) throws JAXBException {
        final List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(rootType);
        list.addAll(Arrays.asList(subClasses));
        return JAXBContext.newInstance(list.toArray(new Class<?>[list.size()]))
                .createUnmarshaller().unmarshal(new StreamSource(in), rootType)
                .getValue();
    }

    /**
     * Permet de sérialiser l'objet <code>objet</code> de type
     * <code>typeObjet</code> passé en paramètre, en XML dans le
     * {@link OutputStream}.
     *
     * @param typeObjet
     *            Le type de la {@link Class} de l'objet à sérialiser.
     * @param objet
     *            L'objet de type <code>typeObjet</code> à sérialiser.
     * @param out
     *            {@link OutputStream} permettant stocker l'<code>objet</code>
     *            en XML.
     * @return true si le marshaller s'est effectuer, false sinon
     * @throws JAXBException
     *             En cas d'erreur lors de la sérialisation
     */
    public static <T> boolean marshall(final Class<T> typeObjet, final T objet,
                                       final OutputStream out) throws JAXBException {
        boolean isOk = false;
        if (objet != null) {
            JAXBContext
                    .newInstance(typeObjet)
                    .createMarshaller()
                    .marshal(
                            new JAXBElement<T>(
                                    buildQNameFromTypeObject(typeObjet),
                                    typeObjet, objet), out);
            isOk = true;
        }
        return isOk;
    }

    /**
     * Permet de sérialiser l'objet <code>objet</code> de type
     * <code>typeObjet</code> passé en paramètre, en XML dans le
     * {@link OutputStream}.
     *
     * @param rootType
     *            Le type de la {@link Class} de l'objet à sérialiser.
     * @param subClasses
     *            Tableau de sous-classes composant le <code>rootType</code>
     * @param objet
     *            L'objet de type <code>typeObjet</code> à sérialiser.
     * @param out
     *            {@link OutputStream} permettant stocker l'<code>objet</code>
     *            en XML.
     * @return true si le marshaller s'est effectuer, false sinon
     * @throws JAXBException
     *             En cas d'erreur lors de la sérialisation
     */
    private static <T> boolean marshall(final Class<T> rootType,
                                        final Class<?>[] subClasses, final T objet, final OutputStream out) throws JAXBException {
        boolean isOk = false;
        if (objet != null) {
            final List<Class<?>> list = new ArrayList<Class<?>>();
            list.add(rootType);
            list.addAll(Arrays.asList(subClasses));
            JAXBContext
                    .newInstance(list.toArray(new Class<?>[list.size()]))
                    .createMarshaller()
                    .marshal(
                            new JAXBElement<T>(
                                    buildQNameFromTypeObject(rootType),
                                    rootType, objet), out);
            isOk = true;
        }
        return isOk;
    }

    private static <T> QName buildQNameFromTypeObject(final Class<T> typeObjet) {
        final String className = typeObjet.getName();
        final int lastPoint = className.lastIndexOf('.');
        return new QName("urn:" + className.substring(0, lastPoint),
                className.substring(lastPoint + 1));
    }


    /**
     * Methode de formattage de xml
     * @param unformattedXml
     * @return
     */
    public String format(String unformattedXml) {

        try {
            final InputSource src = new InputSource(new StringReader(unformattedXml));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            final Boolean keepDeclaration = Boolean.valueOf(unformattedXml.startsWith("<?xml"));

            //May need this: System.setProperty(DOMImplementationRegistry.PROPERTY,"com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");


            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            final LSSerializer writer = impl.createLSSerializer();

            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); // Set this to true if the output needs to be beautified.
            writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); // Set this to true if the declaration is needed to be outputted.

            return writer.writeToString(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode de parsing du xml
     * @param in
     * @return
     */
    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatResponse(Object obj) {
        String response = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( obj.getClass() );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            jaxbMarshaller.marshal(obj, out);
            response = new JaxbHelper().format(out.toString());
        }catch (JAXBException e) {
            log.error("Erreur lors du formattage de la reponse", e);
        }
        return response;

    }
}