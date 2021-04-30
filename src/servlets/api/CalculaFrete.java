package servlets.api;

import java.io.BufferedReader;

/*import java.io.*;
import java.net.*;
import javax.xml.xpath.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.xml.sax.*;*/

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


public class CalculaFrete extends HttpServlet {
   private static final long serialVersionUID = 12;
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      FachadaPedido fachada = new FachadaPedido();

      DadosCalculoFrete dadosCalculoFrete = fachada.getDadosCalculoFrete(Long.parseLong(req.getParameter("idCarrinho")));

      dadosCalculoFrete.setCepDestino(req.getParameter("cepDestino").replace("-", ""));
      dadosCalculoFrete.setTipoServico( req.getParameter("tipoFrete") );

      double[] valorFrete = fachada.calculaValorFrete(dadosCalculoFrete);

      PrintWriter out = resp.getWriter();  
            out.println("{\"valorFrete\": " + valorFrete[0] + ", \"prazo\": " + ((int)valorFrete[1]) + "}");
   }
}