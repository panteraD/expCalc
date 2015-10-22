import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chern on 21.10.2015.
 */

public class XMLSource implements Source {
    public static final String ROOT_ELEMENT_NAME = "transactions";
    public static final String NODE_ELEMENT_NAME = "transaction";
    public static final String NODE_ATTR1_NAME = "value";
    public static final String NODE_ATTR2_NAME = "info";
    public static final String FILE_NAME = "report.xml";

    @Override
    public void save(List<Transaction> transactions) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();

            Element rootElement = doc.createElement(ROOT_ELEMENT_NAME);
            doc.appendChild(rootElement);

            for (Transaction transaction : transactions) {
                Element node = doc.createElement(NODE_ELEMENT_NAME);
                Attr value = doc.createAttribute(NODE_ATTR1_NAME);
                value.setValue(transaction.getValue().toString());
                Attr info = doc.createAttribute(NODE_ATTR2_NAME);
                info.setValue(transaction.getInfo());
                node.setAttributeNode(value);
                node.setAttributeNode(info);
                rootElement.appendChild(node);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(FILE_NAME));
            transformer.transform(domSource, streamResult);

            System.out.println("file saved");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> load() {
        List<Transaction> transactions = new LinkedList<>();
        try {
            File file = new File(FILE_NAME);
            transactions = new LinkedList<>();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList nodeList = document.getElementsByTagName(NODE_ELEMENT_NAME);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    transactions.add(new Transaction(
                            Long.parseLong(element.getAttribute(NODE_ATTR1_NAME)),
                            element.getAttribute(NODE_ATTR2_NAME)));
                }
            }
            System.out.println("file loaded");
        } catch (Exception e) {
            System.out.println("Something went wrong with loading from file");
            e.printStackTrace();

        } finally {
            return transactions;
        }

    }

    /**
     * this method does nothing
     */
    @Override
    public void close() {

    }
}
