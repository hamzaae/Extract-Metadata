package service;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "folder")
public class MetaDatas {
    private ArrayList<MetaData> folder = new ArrayList<>();

    public ArrayList<MetaData> getMetaDatas() {
        return folder;
    }

    public void setMetaDatas(ArrayList<MetaData> metaDatas) {
        this.folder = metaDatas;
    }

    public void addMetadata(MetaData metaData){
        this.folder.add(metaData);
    }
}
