package strategies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import facades.FachadaPedido;
import utils.DadosCalculoFrete;

public class CalculaValorFrete implements IStrategy<Double[], DadosCalculoFrete> {
	public Double[] processa(DadosCalculoFrete obj) {
        double valorFrete = 0;

        double totalAlturas = obj.getTotalAlturas();
        double totalPesos = obj.getTotalPesos();
        double maiorLargura = obj.getMaiorLargura();
        double maiorProfundidade = obj.getMaiorProfundidade();
        String cepOrigem = obj.getCepOrigem();
        String cepDestino = obj.getCepDestino();
        String cdServico = obj.getTipoServico(); //04014 => sedex; //04510 => pac
        
        try {
            URLConnection connection = new URL("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=&sDsSenha=&sCepOrigem=" + cepOrigem + "&sCepDestino=" + cepDestino + "&nVlPeso=" + totalPesos + "&nCdFormato=1&nVlComprimento=" + maiorProfundidade + "&nVlAltura=" + totalAlturas + "&nVlLargura=" + maiorLargura + "&sCdMaoPropria=n&nVlValorDeclarado=0&sCdAvisoRecebimento=n&nCdServico=" + cdServico + "&nVlDiametro=0&StrRetorno=xml").openConnection();
            System.out.println("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=&sDsSenha=&sCepOrigem=" + cepOrigem + "&sCepDestino=" + cepDestino + "&nVlPeso=" + totalPesos + "&nCdFormato=1&nVlComprimento=" + maiorProfundidade + "&nVlAltura=" + totalAlturas + "&nVlLargura=" + maiorLargura + "&sCdMaoPropria=n&nVlValorDeclarado=0&sCdAvisoRecebimento=n&nCdServico=" + cdServico + "&nVlDiametro=0&StrRetorno=xml");

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String linha;
            String xml = "";
            while ((linha = bufferedReader.readLine()) != null) {
                xml = linha;
            }
            bufferedReader.close();
        
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xml));

            Document document = documentBuilder.parse(inputSource);
            valorFrete = Double.parseDouble(document.getElementsByTagName("Valor").item(0).getTextContent().replace(",", "."));
            double prazo = Double.parseDouble(document.getElementsByTagName("PrazoEntrega").item(0).getTextContent());

            Double[] arr = {valorFrete, prazo};
            return arr;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

	}

}
