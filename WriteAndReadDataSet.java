import java.io.*;

public class WriteAndReadDataSet {
    static int x;
    static String Filename;
    static String SensorName;
    static OutputStream os;
    static FileInputStream is;

    public void setFilename(String newName) {
        Filename = newName;
    }

    public void setSensorName(String newName) {
        SensorName = newName;
    }

    public long getTimeStamp(int number) {
        long timeStamps[] = new long[3];//Datum

        timeStamps[0] = System.currentTimeMillis(); //Returns the current time in milliseconds.
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        return timeStamps[x];
    }
    public float [] getData(int number) {

            float[][] values = new float[3][];

            // 1st measure .. just one value
            float[] valueSet = new float[1];
            values[0] = valueSet;
            valueSet[0] = (float) 1.5;

            // 2nd measure .. just three values
            valueSet = new float[3];
            values[1] = valueSet;
            valueSet[0] = (float) 0.7;
            valueSet[1] = (float) 1.2;
            valueSet[2] = (float) 2.1;

            // 3rd measure .. two values
            valueSet = new float[2];
            values[2] = valueSet;
            valueSet[0] = (float) 0.7;
            valueSet[1] = (float) 1.2; // example value 1.5 degrees

        return values[number];


    }

    public void DataOStream() {
        DataOutputStream dos = new DataOutputStream(os);
        try {
            for(x = 0; x < 3; x++) {
                for(int i = 0; i < getData(x).length; i++) {

                    dos.writeUTF(SensorName);
                    dos.writeLong(getTimeStamp(x));
                    dos.writeFloat(getData(x)[i]);
                }
            }

        } catch (IOException ex) {
            System.err.println("Couldn't write data - fatal");
            System.exit(0);
        }
    }

    public void FileOStream() {
        try {
            os = new FileOutputStream(Filename);
            DataOStream();

        } catch(FileNotFoundException ex) {
            System.err.println("Couldn't open file - fatal");
            System.exit(0);
        }
    }

    public void DataIStream() {
        DataInputStream dis = new DataInputStream(is);

        try {

            String Sensornamereceived = dis.readUTF();
            System.out.println("read String: " + Sensornamereceived );

            long Timestampreceived = dis.readLong();
            System.out.println("read long: " + Timestampreceived );

            float valuesreceived = dis.readFloat();
            System.out.println("read float: " + valuesreceived );
            System.out.println("");

        } catch (IOException ex) {
            System.err.println("couldn’t read data (fatal)");
            System.exit(0);
        }
    }

    public void FileIStream(int n) {

        try {
            is = new FileInputStream(Filename);
            for(int i = 0; i < n; i++) {
                DataIStream();
            }

        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
            System.exit(0);
        }


    }
}