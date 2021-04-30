package strategies;

import model.Pedido;

public class ValidarValorCompra implements IStrategy<Boolean, Pedido> {
	public Boolean processa(Pedido obj) {
		Pedido pedido = (Pedido) obj;
		
        //soma o valor de todos os cartoes usados no pedido
        double totalCartoes = 0;

        for(int i = 0; i < pedido.getCartoesCredito().length; i++) {
            //verifica valor pago pra ver se esta acima de 10 reais
            if (pedido.getCartoesCredito()[i].getValorPago() > 0  && pedido.getCartoesCredito()[i].getValorPago() < 10) {
                System.out.println("O valor pago pelo cartao nao pode ser abaixo de 10 reais!");
                return false;
            }


            totalCartoes += pedido.getCartoesCredito()[i].getValorPago();
            System.out.println(pedido.getCartoesCredito()[i].getValorPago());
        }

        //soma o valor de todos os cupons de troca usados no pedido
        double totalCuponsTroca = 0;

        double totalParaVerCuponsTroca = pedido.getValorTotal() + pedido.getValorFrete() - totalCartoes;

        if (pedido.getCuponsTroca() != null) {
	        for(int i = 0; i < pedido.getCuponsTroca().length; i++) {
	            totalCuponsTroca += pedido.getCuponsTroca()[i].getValor();

                if (totalParaVerCuponsTroca < 0) {
                    System.out.println("O valor dos cupons de troca nao pode exceder, desnecessariamente, o valor total da compra!");
                    return false;
                } else {
                    totalParaVerCuponsTroca -= pedido.getCuponsTroca()[i].getValor();
                }
	        }
        }
        System.out.println("ValidarValorCompra");
        System.out.println(totalCartoes);
        System.out.println(pedido.getValorTotal());
        System.out.println(totalCuponsTroca);
        double valorCupomDesconto = 0;
        
        if (pedido.getCupomDesconto() != null) {
        	valorCupomDesconto = pedido.getCupomDesconto().getValor();

            if (totalParaVerCuponsTroca < 0) {
                System.out.println("O valor do cupom de desconto nao pode exceder, desnecessariamente, o valor total da compra!");
                return false;
            }
        }

        System.out.println("COM A FLOOOR =================================================================");
        
        System.out.println((Math.floor(totalCartoes * 100) / 100));
        System.out.println((Math.floor(( pedido.getValorTotal() + pedido.getValorFrete()) * 100) / 100));
        System.out.println((Math.floor((pedido.getValorTotal() + pedido.getValorFrete() - totalCuponsTroca - valorCupomDesconto) * 100) / 100));


        System.out.println("SEM A FLOOOR =================================================================");

        System.out.println(totalCartoes);
        System.out.println(pedido.getValorTotal() + pedido.getValorFrete());
        System.out.println(pedido.getValorTotal() + pedido.getValorFrete() - totalCuponsTroca - valorCupomDesconto);

        
        //compara com o valor total - descontos oriundos dos cupons
        return (Math.floor(totalCartoes * 100) / 100) == (Math.floor(( pedido.getValorTotal() + pedido.getValorFrete()) * 100) / 100)
            || (Math.floor(totalCartoes * 100) / 100) >= (Math.floor((pedido.getValorTotal() + pedido.getValorFrete() - totalCuponsTroca - valorCupomDesconto) * 100) / 100);
	}

}
