package less.android.Factories;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Collection;

public class CollectionXmlSerializer {
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder;
    private DOMImplementation dom;
    private Document document;
    private Element collection;
    private String output;

    public CollectionXmlSerializer(Collection collection, String output) {
        this.output = output;
        createDom();

        for (Object node: collection) {
            writeNode(node);
        }

        print();
        write();
    }

    private void writeNode(Object element) {
        Class _class = element.getClass();
        String[] classPath = _class.getName().split("\\.");
        String className = classPath[classPath.length - 1];
        Element node = document.createElement(className);
        writeFields(element, node);
        writeMethods(element, node);
        collection.appendChild(node);
    }

    private void writeMethods(Object element, Element parent) {
        parent = createWrapper("methods", parent);

        for (Method method: element.getClass().getDeclaredMethods()) {
            Element node = document.createElement("method");
            node.setAttribute("name", method.getName());
            writeArgs(method, node);
            parent.appendChild(node);
        }
    }

    private void writeArgs(Method method, Element parent) {
        for (Parameter parameter: method.getParameters()) {
            Element node = document.createElement("arg");
            node.setAttribute("name", parameter.getName());
            node.setAttribute("type", getType(parameter));
            parent.appendChild(node);
        }
    }

    private void writeFields(Object element, Element parent) {
        parent = createWrapper("fields", parent);
        Field[] fields = element.getClass().getDeclaredFields();
        for (Field field: fields) {
            if ((field.getModifiers() & Modifier.TRANSIENT) > 0) {
                continue;
            }
            Element node = document.createElement("field");
            node.setAttribute("name", field.getName());
            node.setAttribute("type", getType(field));
            try {
                field.setAccessible(true);
                node.setAttribute("value", field.get(element).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }
            parent.appendChild(node);
        }
    }

    private Element createWrapper(String name, Element parent) {
        Element node = document.createElement(name);
        parent.appendChild(node);
        return node;
    }

    private void createDom() {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        dom = builder.getDOMImplementation();
        document = dom.createDocument(null, null, null);
        collection = document.createElement("Collection");
        document.appendChild(collection);
    }


    private String getType(Parameter field) {
        return field.getType().getSimpleName();
    }

    private String getType(Field field) {
        return field.getType().getSimpleName();
    }

    private void print() {
        StreamResult result = new StreamResult(System.out);//Может быть любой поток вывода (файл, сокет ....)
        transform(result);
    }

    private void write() {
        StreamResult result = null;
        try {
            result = new StreamResult(new FileOutputStream(output));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        transform(result);
    }

    private void transform(StreamResult result) {
        DOMSource source = new DOMSource(document);

        TransformerFactory transFactory = TransformerFactory.newInstance(); // Об этом подробней в 4 вопросе

        Transformer transformer = null;
        try {
            transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void serialize(Collection collection) {

    }
}
