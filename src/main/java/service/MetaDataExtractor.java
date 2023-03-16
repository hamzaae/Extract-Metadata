package service;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.nio.file.Path;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@WebService
public class MetaDataExtractor {
        private String input;
        private String output;

        public String getInput() {
                return input;
        }
        public void setInput(String input) {
                this.input = input;
        }
        public String getOutput() {
                return output;
        }
        public void setOutput(String output) {
                this.output = output;
        }
        @WebMethod
        public MetaData extractMetaData() throws IOException, TikaException, SAXException {
                // Create a new file object
                File file = new File(this.input);
                // Create a parser object
                Parser parser = new AutoDetectParser();
                // Create a metadata object to hold the extracted metadata
                Metadata metadata = new Metadata();
                parser.parse(new FileInputStream(file), new BodyContentHandler(-1), metadata, new ParseContext());
                // Create a new instance of the Metadata class
                MetaData metadataObj = new MetaData();
                // Set the extracted metadata values in the Metadata object
                metadataObj.setTitle(metadata.get("title"));
                metadataObj.setAuthor(metadata.get("Author"));
                metadataObj.setDate(metadata.get("date"));
                metadataObj.setType(metadata.get("Content-Type"));
                return metadataObj;
        }
        @WebMethod
        public void marshall(MetaData metaDataObj) throws JAXBException {
                // Marshalling
                JAXBContext jaxbContext = JAXBContext.newInstance(MetaData.class);

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                File file_xml = new File(this.output);

                marshaller.marshal(metaDataObj, file_xml);
        }

        /*public static void main(String[] args) throws IOException, SAXException, TikaException, JAXBException {

                MetaDataExtractor metaDataExtractor = new MetaDataExtractor();
                metaDataExtractor.setInput("C:\\Users\\Microsoft\\Downloads\\WhatsApp Image 2023-01-02 at 12.38.19.jpeg");
                metaDataExtractor.setOutput("src\\main\\resources\\metadata.xml");
                MetaData metaDataObj = metaDataExtractor.extractMetaData();
                metaDataExtractor.marshall(metaDataObj);
        }*/
}
