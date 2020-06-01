
package Model;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ErrorManager {
    
    public static void orderUpdateError (SQLException exception) {
        String message = exception.getMessage();
        infoBox (message,"Operación no realizada");
    }
    
    public static void detailInsertError (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains ("NULL into column 'PRECIO'")) {
            infoBox ("La clave de orden dada no corresponde a ninguna orden guardada en el sistema","Operación no realizada");
        }
        else if (message.contains ("Incorrect syntax")||message.contains ("Invalid column name")) 
            infoBox ("Se deben digitar únicamente números en los campos "
                    + "clave de orden, clave de parte y cantidad. Favor revisar dichos campos","Operación no realizada");
        else
            infoBox (message,"Operación no realizada");
    }
    
    
    public static void orderInsertError (SQLException exception,boolean organization) {
        String message = exception.getMessage();
        if (message.contains ("NULL into column 'ID_CLIENTE'")) {
            if (organization)
                infoBox ("La cedula dada no coincide con ninguna organizacion en el sistema","Operación no realizada");
            else
                infoBox ("La cedula dada no coincide con ninguna persona en el sistema","Operación no realizada");
        }
        else if (message.contains ("Conversion failed")) {
            infoBox ("La fecha introducida es inválida","Operación no realizada");
        }
        else if (message.contains ("Client suspended")) {
            infoBox ("El cliente indicado se encuentra suspendido","Operación no realizada");
        }
        else
            infoBox (message,"Operación no realizada");
    }
    
    
    
    public static void provitionUpdateError (SQLException exception) {
        String message = exception.getMessage();       
        if (message.contains ("Incorrect syntax")||message.contains ("Invalid column name")) 
            infoBox ("Se deben digitar únicamente números en los campos clave de parte, "
                    + "precio del proveedor y porcentaje de ganancia. Favor revisar dichos campos","Operación no realizada");
        else if (message.contains ("Arithmetic overflow"))
            infoBox ("El monto ingresado en el precio de proveedor y/o porcentaje de ganancia es demasiado alto o bajo","Operación no realizada");
        else if (message.contains ("CH_PRECPROV"))
            infoBox ("El precio de proveedor dado tiene que ser mayor a cero","Operación no realizada");
        else if (message.contains ("CH_PORGAN"))
            infoBox ("El porcentaje de ganancia dado tiene que ser mayor a cero","Operación no realizada");
        else if (message.equals ("No rows affected")) 
            infoBox ("No existe ninguna provisión en el sistema entre la parte y el proveedor dado","Operación no realizada");
        else
            infoBox (message,"Operación no realizada");
    }
    
    
    public static void deletePartError (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains ("Incorrect syntax")||message.contains ("Invalid column name"))
            infoBox ("Se deben digitar únicamente números en el campo clave de parte "
                    + "favor revisar dicho campo","Operación no realizada");
        else if (message.equals ("No rows affected")) 
            infoBox ("El ID dado no corresponde a ninguna parte guardada en el sistema","Operación no realizada");
        else{
            infoBox (message,"Operación no realizada");
        }  
    }
    
    public static void insertCorrespondenceError (SQLException exception) 
    {
        String message = exception.getMessage();
        if (message.contains ("FK_CORR_PARTE")) 
            infoBox ("La clave de parte dada no corresponde a ninguna parte almacenada en el sistema","Operación no realizada");
        else if (message.contains ("Incorrect syntax")||message.contains ("Invalid column name"))
            infoBox ("Se deben digitar únicamente números en los campos clave de parte y año de fabricacion, "
                    + "favor revisar dichos campos","Operación no realizada");
        else if (message.contains ("NULL into column 'ID_AUTOMOVIL'")) 
            infoBox ("El modelo y año dados no corresponden a ningún vehículo guardado en el sistema","Operación no realizada");
        else if (message.contains ("PK_CORR")) 
            infoBox ("La parte indicada ya se encuentra asociada al vehículo indicado","Operación no realizada");
        else{
            infoBox (message,"Operación no realizada");
        }     
    }
    
    public static void provitionInsertError (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains ("FK_PROVI_PARTE")) 
            infoBox ("La clave de parte dada no corresponde a ninguna parte almacenada en el sistema","Operación no realizada");       
        else if (message.contains ("NULL into column 'ID_PROVEEDOR'")) 
            infoBox ("El nombre de proveedor dado no se encuentra almacenado en el sistema","Operación no realizada");
        else if (message.contains ("Incorrect syntax")||message.contains ("Invalid column name")) 
            infoBox ("Se deben digitar únicamente números en los campos clave de parte, "
                    + "precio del proveedor y porcentaje de ganancia. Favor revisar dichos campos","Operación no realizada");
        else if (message.contains ("Arithmetic overflow"))
            infoBox ("El monto ingresado en el precio de proveedor y/o porcentaje de ganancia es demasiado alto o bajo","Operación no realizada");
        else if (message.contains ("CH_PRECPROV"))
            infoBox ("El precio de proveedor dado tiene que ser mayor a cero","Operación no realizada");
        else if (message.contains ("CH_PORGAN"))
            infoBox ("El porcentaje de ganancia dado tiene que ser mayor a cero","Operación no realizada");
        else if (message.contains ("PK_PROVI"))
            infoBox ("Ya existen datos en el sistema sobre la provision de la parte dada por el proveedor indicado","Operación no realizada");
        else
            infoBox (message,"Operación no realizada");
    }
    
    
    public static void clientInsertError (SQLException exception) 
    {
        String message = exception.getMessage();
        if (message.contains ("NULL into column 'ID_ESTADO'")) {
            infoBox ("El estado de cliente dado no se encuentra almacenado en el sistema","Operación no realizada");
        }
        else{
            infoBox (message,"Operación no realizada");
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
            infoBox (message,"Operación no realizada");
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
            infoBox (message,"Operación no realizada");
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
            infoBox (message,"Telefono ignorado");
        }
    }
    
    public static void partInsertError (SQLException exception) {
        String message = exception.getMessage();
        if (message.contains("NULL into column 'ID_FABRICANTE'"))
            infoBox ("El nombre de fabricante dado no esta registrado en el sistema","Operacion no realizada");
        else if (message.contains("NULL into column 'ID_MARCA'"))
            infoBox ("El nombre de marca dado no esta registrado en el sistema","Operacion no realizada");
        else if (message.contains("AK_PARTE"))
            infoBox ("Ya existe una parte con el nombre dado en el sistema","Operacion no realizada");
        else
            infoBox (message,"Operacion no realizada");
    }
    
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
