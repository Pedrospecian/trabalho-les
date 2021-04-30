package strategies;

import model.Endereco;

public class ValidacaoEnderecosCadastro implements IStrategy<Boolean, Endereco[]> {
	public Boolean processa(Endereco[] obj) {
        boolean temEntrega = false;
        boolean temCobranca = false;

        for(int i = 0; i < obj.length; i++) {
           if (obj[i].getLogradouro().trim().equals("")
            || obj[i].getNumero().trim().equals("")
            || obj[i].getCep().trim().equals("")
            || obj[i].getTipoEndereco().getId() <= 0
            || obj[i].getBairro().getDescricao().trim().equals("")
            || obj[i].getBairro().getCidade().getDescricao().trim().equals("")
            || obj[i].getBairro().getCidade().getEstado().getDescricao().trim().equals("")
            || obj[i].getBairro().getCidade().getEstado().getPais().getId() <= 0) {
            System.out.println("deu ruim endereco!");
            System.out.println(obj[i].getLogradouro());
            return false;
           }

           if(obj[i].getFuncaoEndereco().getId() == 1 || obj[i].getFuncaoEndereco().getId() == 3) {
            temCobranca = true;
           }

           if(obj[i].getFuncaoEndereco().getId() == 2 || obj[i].getFuncaoEndereco().getId() == 3) {
            temEntrega = true;
           }
        }

        return true && temEntrega && temCobranca;
	}

}
