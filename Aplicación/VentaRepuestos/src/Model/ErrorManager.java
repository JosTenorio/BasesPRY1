
package Model;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ErrorManager {
    
    
    public static void clientInsertError (SQLException exception) 
    {
        String message = exception.getMessage();
        if (message.contains ("NULL into column 'ID_ESTADO'")) {
            infoBox ("El estado de cliente dado no se encuentra almacenado en el sistema","Operación no realizada");
        }
        else{
            infoBox ("Ha ocurrido un error no identificable con la asignacion de estado a un cliente, favor revisar los datos dados","Operación no realizada");
        }     
    }
    
    public static void clientUpdateError (SQLException exception, boolean organization) 
    {
        String message = exception.getMessage();
        if (message.equals("El conjunto de resultados no tiene fila actual."))
            if (organization)
                infoBox ("La cedula dada no coincide con ninguna organizacion en el sistema","Operación no realizada");
            else
                infoBox ("La cedula dada no coincide con ninguna persona en el sistema","Operación no realizada");
        else if (message.contains ("NULL into column 'ID_ESTADO'")){
            infoBox ("El nuevo estado del cliente no esta registrado en el sistema","Operación no realizada");
        }    
        else
            infoBox (message,"Operación no realizada");
    }
    
    public static void organizationInsertError (SQLException exception) 
    {
        String message = exception.getMessage();
        if (message.contains("CH1_CEDJ")||message.contains("CH2_CEDJ")||(message.contains("truncated")&& message.contains("column 'CEDULA_JUR'"))) {
            infoBox ("La cédula jurídica dada es inválida, esta debe estar compuesta exactamente por 10 dígitos","Operación no realizada");
        }
        else if (message.contains("PK_ORG")) {
            infoBox ("La cédula jurídica dada es inválida, esta ya se encuentra registrada en el sistema","Operación no realizada");
        }
        else{
            infoBox ("Ha ocurrido un error no identificable, favor revisar los datos dados","Operación no realizada");
        }
    }
    
    public static void personInsertError (SQLException exception) 
    {
        String message = exception.getMessage();
        if (message.contains("CH1_CED")||message.contains("CH2_CED")||(message.contains("truncated")&& message.contains("column 'CEDULA'"))) {
            infoBox ("La cédula dada es inválida, esta debe estar compuesta exactamente por 9 dígitos","Operación no realizada");
        }
        else if (message.contains("PK_PER")) {
            infoBox ("La cédula dada es inválida, esta ya se encuentra registrada en el sistema","Operación no realizada");
        }
        else{
            infoBox ("Ha ocurrido un error no identificable, favor revisar los datos dados","Operación no realizada");
        }
    }
    
    public static void telephoneInsertError (SQLException exception, String invalidValue) 
    {
        String message = exception.getMessage();
        if (message.contains("truncated")&& message.contains("column 'TELEFONO'")||message.contains("CH2_TELORG")||
                message.contains("CH1_TELORG")||message.contains("CH1_TELPER")||message.contains("CH2_TELPER")) {
            infoBox ("Uno de los telefonos dados es inválido y ha sido ignorado por el sistema, todos deben estar compuestos exactamente por 8 dígitos","Telefono ignorado");
        }
        else if (message.contains("PK_TELORG")||message.contains("PK_TELPER")) {
            infoBox ("Uno de los telefonos dados era un número repetido y ha sido ignorado por el sistema, cada telefono a ingresar debe ser diferente","Telefono ignorado");
        }
        else{
            infoBox ("Ha ocurrido un error no identificable con el registro de telefonos, favor revisar los datos dados","Telefono ignorado");
        }
    }
    
    public static void PartInsertError (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("NULL into column 'ID_FABRICANTE'"))
            infoBox ("El nombre de fabricante dado no esta registrado en el sistema","Operacion no realizada");
        else if (message.contains("NULL into column 'ID_MARCA'"))
            infoBox ("El nombre de marca dado no esta registrado en el sistema","Operacion no realizada");
        else if (message.contains("AK_PARTE"))
            infoBox ("Ya existe una parte con el nombre dado en el sistema","Operacion no realizada");
        else
            infoBox ("Ha ocurrido un error no identificable con el registro de partes, favor revisar los datos dados","Operacion no realizada");
    }
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
