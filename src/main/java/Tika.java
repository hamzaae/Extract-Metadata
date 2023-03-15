import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.parser.ParseContext;

public class Tika {
    public static void main(String[] args) {
        try {
            // Create a new file object
            File file = new File("C:\\Users\\Microsoft\\Downloads\\WhatsApp Image 2023-01-02 at 12.38.19.jpeg");

            // Create a parser object
            Parser parser = new AutoDetectParser();

            // Create a metadata object to hold the extracted metadata
            Metadata metadata = new Metadata();

            parser.parse(new FileInputStream(file), new BodyContentHandler(-1), metadata, new ParseContext());

            // Print out the metadata
            String[] metadataNames = metadata.names();
            for (String name : metadataNames) {
                System.out.println(name + ":\t" + metadata.get(name));
            }
            // Catch exceptions
        } catch (IOException | SAXException | TikaException e) {
            e.printStackTrace();
        }
    }
}
