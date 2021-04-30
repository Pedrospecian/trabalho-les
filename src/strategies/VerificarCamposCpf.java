package strategies;

import model.Documento;

public class VerificarCamposCpf implements IStrategy<Boolean, Documento[]> {
	public Boolean processa(Documento[] obj) {
        for(int i = 0; i < obj.length; i++) {
            System.out.println((int)obj[i].getTipoDocumento().getId());
            if ((int)obj[i].getTipoDocumento().getId() == 1) {
                return true;
            }
        }

        System.out.println("O usuario a ser cadastrado precisa possuir um cpf!");
        return false;
	}

}
