import java.io.IOException;

public class WriteAndReadDataSetTest {
    public static void main(String[] args) throws Exception {

        WriteAndReadDataSet wards =  new WriteAndReadDataSet();

        wards.setSensorName("MyGoodOldSensor");
        wards.setFilename("testFile.txt");

        wards.FileOStream();

        wards.FileIStream(6);//Anzahl der Werte


    }
}
