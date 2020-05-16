
package ComunicationPackages;

import java.io.Serializable;
import java.util.ArrayList;

public class Package implements Serializable {
    
    private PackageEnum operationCode;
    private ArrayList<IListable> entities;
    
    public Package(PackageEnum operationCode){
        this.operationCode = operationCode;
    }
    
    public PackageEnum getAction(){
        return operationCode;
    }

    public ArrayList<IListable> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<IListable> entities) {
        this.entities = entities;
    }
    
    
}