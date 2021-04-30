package strategies;

import model.Documento;

public class ValidacaoDocumentos implements IStrategy<Boolean, Documento[]> {
	public Boolean processa(Documento[] obj) {
        for(int i = 0; i < obj.length; i++) {
            switch ((int)obj[i].getTipoDocumento().getId()) {
                // cpf
                case 1:
                    ValidarCPF validarCPF = new ValidarCPF();
                    if (validarCPF.processa(obj[i].getCodigo()) == false) {
                        return false;
                    }
                    break;
                case 2:
                    if (obj[i].getCodigo().matches("^[0-9]{14}$") == false) {
                        return false;
                    }
                    break;
                case 3:
                    if (obj[i].getCodigo().matches("^[0-9]{9}$") == false) {
                        return false;
                    }
                    break;
            }
        }

        return true;
	}

}
