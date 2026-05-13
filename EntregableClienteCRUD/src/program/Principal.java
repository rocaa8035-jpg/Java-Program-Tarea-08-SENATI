package program;

import views.ClienteFrm; //Importar el Formulario

public class Principal {
    
    public static void main(String[] args) {
        
        ClienteFrm clienteFrm = new ClienteFrm();
        clienteFrm.setLocationRelativeTo(null);
        clienteFrm.setVisible(true);
    }
}
