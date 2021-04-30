package strategies;

import utils.Campo;

public class ValidarCampos implements IStrategy<Boolean, Campo> {
	public Boolean processa(Campo obj) {
		Campo campo = (Campo) obj;
		// valida obrigatoriedade
		if((campo == null
		 || campo.getValor() == null
		 || campo.getValor().length() <= 0 
		 || campo.getValor().trim().length() <= 0)
		 && campo.getObrigatorio() == true) {
			campo.setValido(false);
            System.out.println(campo.getNome());
			campo.setMensagemErro("Esse campo precisa ser preenchido");
			return false;
		}

        if (campo.getTipo() == 6 && campo.getObrigatorio() == false) {
            return true;
        }
		
		// valida conteudo do campo de acordo com o tipo
		 switch (campo.getTipo()) {
		 	//int e long
            case 1:  
        		if (campo.getValor().matches("^[0-9]+$") == false) {
        			campo.setValido(false);
					campo.setMensagemErro("Esse campo precisa ser um número inteiro válido");
					return false;
        		}
                break;
            //double
            case 2:   
        		if (campo.getValor().matches("^[0-9]+(.|,)[0-9]+$") == false) {
        			campo.setValido(false);
					campo.setMensagemErro("Esse campo precisa ser um número decimal válido");
					return false;
        		}
                break;
            //date
            case 3:   
        		if (campo.getValor().matches("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$") == false) {
        			campo.setValido(false);
					campo.setMensagemErro("Esse campo precisa ser uma data válida");
					return false;
        		}
                break;
            //cpf
            case 4:
            	ValidarCPF validarCPF = new ValidarCPF();
            	if (validarCPF.processa(campo.getValor()) == false) {
            		campo.setValido(false);
					campo.setMensagemErro("Esse campo precisa ser um CPF válido");
					return false;
            	}
                break;

            //cep
            case 5:
                if (campo.getValor().matches("^[0-9]{5}-?[0-9]{3}$") == false) {
                    campo.setValido(false);
                    campo.setMensagemErro("Esse campo precisa ser um CEP válido");
                    return false;
                }
                break;

            //array
            case 6:
                if (campo.getValor().matches("^(.*,)+$") == false) {
                    campo.setValido(false);
                    campo.setMensagemErro("Esse campo precisa ser um array válido");
                    return false;
                }
                break;

            //email:
            case 7:
                if (campo.getValor().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$") == false) {
                    campo.setValido(false);
                    campo.setMensagemErro("Esse campo precisa ser um e-mail válido");
                    return false;
                }
                break;

            //senha
            case 8:
                if (campo.getValor().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") == false) {
                    campo.setValido(false);
                    campo.setMensagemErro("Esse campo precisa ser uma senha valida");
                    return false;
                }
                break;
        }
		return true;
	}

}
