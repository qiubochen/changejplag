package jplag.qiubochenutil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import jplagUtils.PropertiesLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XMLUtil {
    public static final String DATABASE_XML = "database.xml";
    private XStream xStream;
    void insertToXML(MydatabaseBean mydatabaseBean){
        xStream=new XStream(new DomDriver());
        try {
            File file = new File(DATABASE_XML);
            System.out.println("file >>>> " + file.getAbsolutePath());
            xStream.toXML(mydatabaseBean,new FileOutputStream(DATABASE_XML));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    MydatabaseBean getFromXML() throws FileNotFoundException {
        xStream=new XStream(new DomDriver());
        MydatabaseBean mydatabaseBean= null;

        mydatabaseBean = (MydatabaseBean) xStream.fromXML(new FileInputStream(DATABASE_XML));
        return mydatabaseBean;
    }
}
