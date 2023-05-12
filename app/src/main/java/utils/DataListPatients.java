package utils;

public class DataListPatients {

    private String dataName;
    private String dataSurname;

    public DataListPatients(String dataName, String dataSurname) {
        this.dataName = dataName;
        this.dataSurname = dataSurname;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataSurname() {
        return dataSurname;
    }

    public DataListPatients(){

    }

}
