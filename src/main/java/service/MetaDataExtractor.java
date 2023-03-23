package service;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

        public void setInput(String input) {
                this.input = input;
        }

        public void setOutput(String output) {
                this.output = output;
        }
        public void itterateFolder(String folderPath) throws TikaException, IOException, SAXException, JAXBException {
                File folder = new File(folderPath);
                File[] files = folder.listFiles();
                File file_xml = new File(this.output);

                //iterate the files array
                assert files != null;
                //ArrayList<MetaData> filesM= new ArrayList<>();
                MetaDatas folderr = new MetaDatas();
                for(File file:files) {
                        //check if the file
                        if(file.isFile()) {
                                System.out.println("File - "+file.getName());
                                MetaData metaDataObj = extractMetaData(file.getPath());
                                folderr.addMetadata(metaDataObj);
                        }else
                        if(file.isDirectory()) {
                                System.out.println("Folder - "+file.getName());
                        }
                }
                marshall(folderr, file_xml);
        }
        @WebMethod
        public MetaData extractMetaData(String filePath) throws IOException, TikaException, SAXException {
                // Create a new file object
                File file = new File(filePath);
                // Create a parser object
                Parser parser = new AutoDetectParser();
                // Create a metadata object to hold the extracted metadata
                Metadata metadata = new Metadata();
                parser.parse(new FileInputStream(file), new BodyContentHandler(-1), metadata, new ParseContext());
                // Create a new instance of the Metadata class
                MetaData metadataObj = new MetaData();
                // Set the extracted metadata values in the Metadata object
                metadataObj.setName(file.getName());
                metadataObj.setTitle(metadata.get("title"));
                metadataObj.setAuthor(metadata.get("Author"));
                metadataObj.setDate(metadata.get("date"));
                metadataObj.setType(metadata.get("Content-Type"));
                return metadataObj;
        }
        @WebMethod
        public void marshall(MetaDatas folderr, File xmlFile) throws JAXBException {
                // Marshalling
                JAXBContext jaxbContext = JAXBContext.newInstance(MetaDatas.class);

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                marshaller.marshal(folderr, xmlFile);

        }

        public static void main(String[] args) throws IOException, SAXException, TikaException, JAXBException {

                MetaDataExtractor metaDataExtractor = new MetaDataExtractor();
                metaDataExtractor.setInput("C:\\Users\\Microsoft\\Desktop\\test");
                metaDataExtractor.setOutput("src\\main\\resources\\metadata.xml");
                metaDataExtractor.itterateFolder(metaDataExtractor.input);

        }
}
