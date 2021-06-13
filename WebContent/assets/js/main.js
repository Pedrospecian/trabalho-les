var element = document.querySelector('.js-cep-mask');
var maskOptions = {
  mask: '00000-000'
};

if (element) var mask = IMask(element, maskOptions);

const tiposDocumentos = ["CPF", "CNPJ", "RG", "Social Security Card"];
const tiposEnderecos = ["Residencial", "Comercial"];
const tiposTelefones = ["Residencial", "Celular"];
const bandeirasCartoes = ["Visa", "MasterCard"];

const paises = ["Brasil", "EUA"];
const tiposResidencia = ["Casa", "Apartamento", "Outro"];
const funcoesEnderecos = ["Cobrança", "Entrega", "Cobrança e Entrega"];
const tiposLogradouros = ["Rua", "Avenida", "Viela", "Outro"];

let documentosArray = [];
let enderecosArray = [];
let cartoesArray = [];
let telefonesArray = [];
let categoriasArray = [];
let cuponsTrocaArray = [];

const formatarData = function(dateString) {
  if (!dateString.match(/[0-9]{4}\-[0-9]{2}-[0-9]{2}/)) return dateString;
  var dateArr = dateString.split('-');
  return dateArr[2].trim() + '/' + dateArr[1].trim() + '/' + dateArr[0].trim();
}

var xhttp_cep = new XMLHttpRequest();
xhttp_cep.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
  	var retorno = JSON.parse(this.responseText);
  	if (retorno && !retorno.erro) {	  	
	    console.log(retorno);
	    document.querySelector('.js-logradouro').value = retorno.logradouro;
	    document.querySelector('.js-bairro').value = retorno.bairro;
	    document.querySelector('.js-cidade').value = retorno.localidade;
	    document.querySelector('.js-uf').value = retorno.uf;
	}
   }
};

var xhttp_zip = new XMLHttpRequest();

xhttp_zip.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
  	if (this.responseText) {
	  	var retorno = JSON.parse(this.responseText);
	    console.log(retorno);
	}
   }
};

var elementCepApi = document.querySelector('.js-cep-api');
if (elementCepApi) {
  elementCepApi.addEventListener('change', function() {
    var cepval = elementCepApi.value;

    if (cepval.match(/[0-9]{5}\-?[0-9]{3}/)) {  	
  	  xhttp_cep.open("GET", 'https://viacep.com.br/ws/' + cepval + '/json', true);
  	  xhttp_cep.send();
    }
  });
}

var btnAdicionarCartao = document.querySelector('.js-adicionar-cartao');
if (btnAdicionarCartao) {
  btnAdicionarCartao.addEventListener('click', function() {
    if (document.querySelector('.js-numero-cartao').value === ''
     || document.querySelector('.js-nome-cartao').value === ''
     || document.querySelector('.js-bandeira-cartao').value === ''
     || document.querySelector('.js-cvv-cartao').value === ''
     || document.querySelector('.js-validade-cartao').value === '' ) {
      alert('Por favor, insira um cartão válido!');
    } else {
      var novoCartao = document.createElement("div");
      novoCartao.classList.add('box-single');
      novoCartao.innerHTML = `<a href="#" class="button-close js-remove">X</a>
                <input name="cartaoPreferencial" type="radio" value="${document.querySelector('.js-numero-cartao').value}"> Cartão preferencial
                <div class="info-wrapper">
                  <strong>${bandeirasCartoes[document.querySelector('.js-bandeira-cartao').value - 1]}</strong>
                  <br>
                  ${document.querySelector('.js-nome-cartao').value}
                  <br>
                  ${document.querySelector('.js-numero-cartao').value}
                  <br>
                  Cód. Segurança: ${document.querySelector('.js-cvv-cartao').value}
                  <br><br>
                  validade: <span class="js-date-value">${formatarData(document.querySelector('.js-validade-cartao').value)}</span>
                </div>`;

      var numeroCartaoHidden = document.querySelector(".js-arrNumeroCartao");
      var nomeCartaoHidden = document.querySelector(".js-arrNomeCartao");
      var bandeiraCartaoHidden = document.querySelector(".js-arrBandeiraCartao");
      var codigoCartaoHidden = document.querySelector(".js-arrCodigoCartao");
      var validadeCartaoHidden = document.querySelector(".js-arrValidadeCartao");

      numeroCartaoHidden.value = numeroCartaoHidden.value + document.querySelector('.js-numero-cartao').value + ',';
      nomeCartaoHidden.value = nomeCartaoHidden.value + document.querySelector('.js-nome-cartao').value + ',';
      bandeiraCartaoHidden.value = bandeiraCartaoHidden.value + document.querySelector('.js-bandeira-cartao').value + ',';
      codigoCartaoHidden.value = codigoCartaoHidden.value + document.querySelector('.js-cvv-cartao').value + ',';
      validadeCartaoHidden.value = validadeCartaoHidden.value + document.querySelector('.js-validade-cartao').value + ',';

      document.querySelector('.boxes-wrapper.js-boxes-cartoes').appendChild(novoCartao);

      const identificador = Math.floor(Math.random() * 100000); 
      cartoesArray.push({
        numero: document.querySelector('.js-numero-cartao').value,
        nome: document.querySelector('.js-nome-cartao').value,
        bandeira: document.querySelector('.js-validade-cartao').value,
        codigo: document.querySelector('.js-cvv-cartao').value,
        validade: document.querySelector('.js-validade-cartao').value,
        identificador: identificador
      });

      document.querySelector('.js-numero-cartao').value = '';
      document.querySelector('.js-nome-cartao').value = '';
      document.querySelector('.js-bandeira-cartao').value = '';
      document.querySelector('.js-cvv-cartao').value = '';
      document.querySelector('.js-validade-cartao').value = '';

      novoCartao.querySelector('.button-close').addEventListener('click', function(e) {
        e.preventDefault();
        let indexRemoved = 0;
        numeroCartaoHidden.value = '';
        nomeCartaoHidden.value = '';
        bandeiraCartaoHidden.value = '';
        codigoCartaoHidden.value = '';
        validadeCartaoHidden.value = '';
        for (var i = 0; i < cartoesArray.length; i++) {
           if (cartoesArray[i].identificador === identificador) {
             indexRemoved = i;
           } else {
             numeroCartaoHidden.value = numeroCartaoHidden.value + cartoesArray[i].numero + ',';
             nomeCartaoHidden.value = nomeCartaoHidden.value + cartoesArray[i].nome + ',';
             bandeiraCartaoHidden.value = bandeiraCartaoHidden.value + cartoesArray[i].bandeira + ',';
             codigoCartaoHidden.value = codigoCartaoHidden.value + cartoesArray[i].codigo + ',';
             validadeCartaoHidden.value = validadeCartaoHidden.value + cartoesArray[i].validade + ',';
           }
        }

        cartoesArray.splice(indexRemoved, 1);
        novoCartao.remove();
      });

    }
  });
}

var btnAdicionarEndereco = document.querySelector('.js-adicionar-endereco');
if (btnAdicionarEndereco) {
  btnAdicionarEndereco.addEventListener('click', function() {
    if (document.querySelector('.js-tipo-endereco').value === ''
     || document.querySelector('.js-nome-endereco').value === ''
     || document.querySelector('.js-tipo-residencia').value === ''
     || document.querySelector('.js-funcao-endereco').value === ''
     || document.querySelector('.js-tipo-logradouro').value === ''
     || document.querySelector('.js-cep').value === ''
     || document.querySelector('.js-logradouro').value === ''
     || document.querySelector('.js-numero').value === ''
     || document.querySelector('.js-bairro').value === ''
     || document.querySelector('.js-cidade').value === ''
     || document.querySelector('.js-uf').value === ''
     || document.querySelector('.js-pais').value === '') {
      alert('Por favor, insira um endereço válido!');
    } else {
      var novoEndereco = document.createElement("div");
      novoEndereco.classList.add('box-single');
      novoEndereco.innerHTML = `<a href="#" class="button-close js-remove">X</a>
                <div class="info-wrapper">         
                  ${document.querySelector('.js-nome-endereco').value}
                  <br><br>
                  Tipo de residência: ${tiposResidencia[document.querySelector('.js-tipo-residencia').value - 1]}
                  <br>
                  Função do endereço: ${funcoesEnderecos[document.querySelector('.js-funcao-endereco').value - 1]}
                  <br><br>
                  Endereço <span class="text-lowercase">${tiposEnderecos[document.querySelector('.js-tipo-endereco').value - 1]}</span>
                  <br><br>
                  ${document.querySelector('.js-cep').value}
                  <br><br>
                  ${document.querySelector('.js-logradouro').value} (tipo do logradouro: ${tiposLogradouros[document.querySelector('.js-tipo-logradouro').value - 1]}),
                  n° ${document.querySelector('.js-numero').value}
                  ${document.querySelector('.js-complemento').value}
                  <br>
                  ${document.querySelector('.js-bairro').value},
                  ${document.querySelector('.js-cidade').value} - 
                  ${document.querySelector('.js-uf').value} - 
                  ${paises[document.querySelector('.js-pais').value - 1]}
                  <br>
                  Observações: ${document.querySelector('.js-observacoes').value}
                </div>`;

      var tipoEnderecoHidden = document.querySelector(".js-arrTipoEndereco");
      var nomeEnderecoHidden = document.querySelector(".js-arrNomeEndereco");
      var cepHidden = document.querySelector(".js-arrCep");
      var logradouroHidden = document.querySelector(".js-arrLogradouro");
      var numeroHidden = document.querySelector(".js-arrNumero");
      var complementoHidden = document.querySelector(".js-arrComplemento");
      var bairroHidden = document.querySelector(".js-arrBairro");
      var cidadeHidden = document.querySelector(".js-arrCidade");
      var ufHidden = document.querySelector(".js-arrUf");
      var paisHidden = document.querySelector(".js-arrPais");
      var tipoResidenciaHidden = document.querySelector(".js-arrTipoResidencia");
      var tipoLogradouroHidden = document.querySelector(".js-arrTipoLogradouro");
      var funcaoEnderecoHidden = document.querySelector(".js-arrFuncaoEndereco");
      var observacoesHidden = document.querySelector(".js-arrObservacoes");

      tipoEnderecoHidden.value = tipoEnderecoHidden.value + document.querySelector('.js-tipo-endereco').value + ',';
      nomeEnderecoHidden.value = nomeEnderecoHidden.value + document.querySelector('.js-nome-endereco').value + ',';
      cepHidden.value = cepHidden.value + document.querySelector('.js-cep').value + ',';
      logradouroHidden.value = logradouroHidden.value + document.querySelector('.js-logradouro').value + ',';
      numeroHidden.value = numeroHidden.value + document.querySelector('.js-numero').value + ',';
      complementoHidden.value = complementoHidden.value + document.querySelector('.js-complemento').value + ',';
      bairroHidden.value = bairroHidden.value + document.querySelector('.js-bairro').value + ',';
      cidadeHidden.value = cidadeHidden.value + document.querySelector('.js-cidade').value + ',';
      ufHidden.value = ufHidden.value + document.querySelector('.js-uf').value + ',';
      paisHidden.value = paisHidden.value + document.querySelector('.js-pais').value + ',';
      observacoesHidden.value = observacoesHidden.value + document.querySelector('.js-observacoes').value + ',';
      
      tipoResidenciaHidden.value = tipoResidenciaHidden.value + document.querySelector('.js-tipo-residencia').value + ',';
      tipoLogradouroHidden.value = tipoLogradouroHidden.value + document.querySelector('.js-tipo-logradouro').value + ',';
      funcaoEnderecoHidden.value = funcaoEnderecoHidden.value + document.querySelector('.js-funcao-endereco').value + ',';

      document.querySelector('.boxes-wrapper.js-boxes-enderecos').appendChild(novoEndereco);

      const identificador = Math.floor(Math.random() * 100000); 
      enderecosArray.push({
        tipo: document.querySelector('.js-tipo-endereco').value,
        nome: document.querySelector('.js-nome-endereco').value,
        cep: document.querySelector('.js-cep').value,
        logradouro: document.querySelector('.js-logradouro').value,
        numero: document.querySelector('.js-numero').value,
        complemento: document.querySelector('.js-complemento').value,
        bairro: document.querySelector('.js-bairro').value,
        cidade: document.querySelector('.js-cidade').value,
        uf: document.querySelector('.js-uf').value,
        pais: document.querySelector('.js-pais').value,
        observacoes: document.querySelector('.js-observacoes').value,

        tipoResidencia: document.querySelector('.js-tipo-residencia').value,
        tipoLogradouro: document.querySelector('.js-tipo-logradouro').value,
        funcaoEndereco: document.querySelector('.js-funcao-endereco').value,

        identificador: identificador
      });

      document.querySelector('.js-tipo-endereco').value = '';
      document.querySelector('.js-nome-endereco').value = '';
      document.querySelector('.js-cep').value = '';
      document.querySelector('.js-logradouro').value = '';
      document.querySelector('.js-numero').value = '';
      document.querySelector('.js-complemento').value = '';
      document.querySelector('.js-bairro').value = '';
      document.querySelector('.js-cidade').value = '';
      document.querySelector('.js-uf').value = '';
      document.querySelector('.js-pais').value = '';
      document.querySelector('.js-tipo-residencia').value = '';
      document.querySelector('.js-tipo-logradouro').value = '';
      document.querySelector('.js-funcao-endereco').value = '';
      document.querySelector('.js-observacoes').value = '';

      novoEndereco.querySelector('.button-close').addEventListener('click', function(e) {
        e.preventDefault();

        let indexRemoved = 0;
        tipoEnderecoHidden.value = '';
        nomeEnderecoHidden.value = '';
        cepHidden.value = '';
        logradouroHidden.value = '';
        numeroHidden.value = '';
        complementoHidden.value = '';
        bairroHidden.value = '';
        cidadeHidden.value = '';
        ufHidden.value = '';
        paisHidden.value = '';
        observacoesHidden.value = '';

        tipoResidenciaHidden.value = '';
        tipoLogradouroHidden.value = '';
        funcaoEnderecoHidden.value = '';
        for (var i = 0; i < enderecosArray.length; i++) {
          if (enderecosArray[i].identificador === identificador) {
            indexRemoved = i;
          } else {
            tipoEnderecoHidden.value = tipoEnderecoHidden.value + enderecosArray[i].tipo + ',';
            nomeEnderecoHidden.value = nomeEnderecoHidden.value + enderecosArray[i].nome + ',';
            cepHidden.value = cepHidden.value + enderecosArray[i].cep + ',';
            logradouroHidden.value = logradouroHidden.value + enderecosArray[i].logradouro + ',';
            numeroHidden.value = numeroHidden.value + enderecosArray[i].numero + ',';
            complementoHidden.value = complementoHidden.value + enderecosArray[i].complemento + ',';
            bairroHidden.value = bairroHidden.value + enderecosArray[i].bairro + ',';
            cidadeHidden.value = cidadeHidden.value + enderecosArray[i].cidade + ',';
            ufHidden.value = ufHidden.value + enderecosArray[i].uf + ',';
            paisHidden.value = paisHidden.value + enderecosArray[i].pais + ',';
            observacoesHidden.value = observacoesHidden.value + enderecosArray[i].observacoes + ',';

            tipoResidenciaHidden.value = tipoResidenciaHidden.value + enderecosArray[i].tipoResidencia + ',';
            tipoLogradouroHidden.value = tipoLogradouroHidden.value + enderecosArray[i].tipoLogradouro + ',';
            funcaoEnderecoHidden.value = funcaoEnderecoHidden.value + enderecosArray[i].funcaoEndereco + ',';
          }
        }

        console.log(enderecosArray);
        enderecosArray.splice(indexRemoved, 1);
        console.log(enderecosArray);

        novoEndereco.remove();
      });

    }
  });
}

var btnAdicionarDocumento = document.querySelector('.js-adicionar-documento');
if (btnAdicionarDocumento) {
  btnAdicionarDocumento.addEventListener('click', function() {
    if (document.querySelector('.js-tipo-documento').value === ''
     || document.querySelector('.js-numero-documento').value === ''
     || document.querySelector('.js-validade-documento').value === '') {
      alert('Por favor, insira um documento válido!');
    } else {
      var novoDocumento = document.createElement("div");
      novoDocumento.classList.add('box-single');
      novoDocumento.innerHTML = `<a href="#" class="button-close js-remove">X</a>
                <div class="info-wrapper">
                  <strong>${tiposDocumentos[document.querySelector('.js-tipo-documento').value - 1]}</strong>
                  <br>
                  ${document.querySelector('.js-numero-documento').value}
                  <br><br>
                  validade: <span class="js-date-value">${formatarData(document.querySelector('.js-validade-documento').value)}</span>
                </div>`;

      var tipoDocumentoHidden = document.querySelector(".js-arrTipoDocumento");
      var numeroDocumentoHidden = document.querySelector(".js-arrNumeroDocumento");
      var validadeDocumentoHidden = document.querySelector(".js-arrValidadeDocumento");

      tipoDocumentoHidden.value = tipoDocumentoHidden.value + document.querySelector('.js-tipo-documento').value + ',';
      numeroDocumentoHidden.value = numeroDocumentoHidden.value + document.querySelector('.js-numero-documento').value + ',';
      validadeDocumentoHidden.value = validadeDocumentoHidden.value + document.querySelector('.js-validade-documento').value + ',';

      document.querySelector('.boxes-wrapper.js-boxes-documentos').appendChild(novoDocumento);

      const identificador = Math.floor(Math.random() * 100000); 
      documentosArray.push({
        tipo: document.querySelector('.js-tipo-documento').value,
        numero: document.querySelector('.js-numero-documento').value,
        validade: document.querySelector('.js-validade-documento').value,
        identificador: identificador
      });

      document.querySelector('.js-tipo-documento').value = '';
      document.querySelector('.js-numero-documento').value = '';
      document.querySelector('.js-validade-documento').value = '';

      novoDocumento.querySelector('.button-close').addEventListener('click', function(e) {
        e.preventDefault();
        let indexRemoved = 0;
        tipoDocumentoHidden.value = '';
        numeroDocumentoHidden.value = '';
        validadeDocumentoHidden.value = '';
        for (var i = 0; i < documentosArray.length; i++) {
           if (documentosArray[i].identificador === identificador) {
             indexRemoved = i;
           } else {
             tipoDocumentoHidden.value = tipoDocumentoHidden.value + documentosArray[i].tipo + ',';
             numeroDocumentoHidden.value = numeroDocumentoHidden.value + documentosArray[i].numero + ',';
             validadeDocumentoHidden.value = validadeDocumentoHidden.value + documentosArray[i].validade + ',';
           }
        }

        console.log(documentosArray);
        documentosArray.splice(indexRemoved, 1);
        console.log(documentosArray);
        novoDocumento.remove();
      });

    }
  });
}




var btnAdicionarTelefone = document.querySelector('.js-adicionar-telefone');
if (btnAdicionarTelefone) {
  btnAdicionarTelefone.addEventListener('click', function() {
    if (document.querySelector('.js-tipo-telefone').value === ''
     || document.querySelector('.js-ddd-telefone').value === ''
     || document.querySelector('.js-numero-telefone').value === '') {
      alert('Por favor, insira um telefone válido!');
    } else {
      var novoTelefone = document.createElement("div");
      novoTelefone.classList.add('box-single');
      novoTelefone.innerHTML = `<a href="#" class="button-close js-remove">X</a>
                <div class="info-wrapper">
                  <strong>${tiposTelefones[document.querySelector('.js-tipo-telefone').value - 1]}</strong>
                  <br>
                  (${document.querySelector('.js-ddd-telefone').value})
                  ${document.querySelector('.js-numero-telefone').value}
                </div>`;

      var tipoTelefoneHidden = document.querySelector(".js-arrTipoTelefone");
      var numeroTelefoneHidden = document.querySelector(".js-arrNumeroTelefone");
      var dddTelefoneHidden = document.querySelector(".js-arrDDDTelefone");

      tipoTelefoneHidden.value = tipoTelefoneHidden.value + document.querySelector('.js-tipo-telefone').value + ',';
      numeroTelefoneHidden.value = numeroTelefoneHidden.value + document.querySelector('.js-numero-telefone').value + ',';
      dddTelefoneHidden.value = dddTelefoneHidden.value + document.querySelector('.js-ddd-telefone').value + ',';

      document.querySelector('.boxes-wrapper.js-boxes-telefones').appendChild(novoTelefone);

      const identificador = Math.floor(Math.random() * 100000); 
      telefonesArray.push({
        tipo: document.querySelector('.js-tipo-telefone').value,
        numero: document.querySelector('.js-numero-telefone').value,
        ddd: document.querySelector('.js-ddd-telefone').value,
        identificador: identificador
      });

      document.querySelector('.js-tipo-telefone').value = '';
      document.querySelector('.js-numero-telefone').value = '';
      document.querySelector('.js-ddd-telefone').value = '';

      novoTelefone.querySelector('.button-close').addEventListener('click', function(e) {
        e.preventDefault();
        let indexRemoved = 0;
        tipoTelefoneHidden.value = '';
        numeroTelefoneHidden.value = '';
        dddTelefoneHidden.value = '';
        for (var i = 0; i < telefonesArray.length; i++) {
           if (telefonesArray[i].identificador === identificador) {
             indexRemoved = i;
           } else {
             tipoTelefoneHidden.value = tipoTelefoneHidden.value + telefonesArray[i].tipo + ',';
             numeroTelefoneHidden.value = numeroTelefoneHidden.value + telefonesArray[i].numero + ',';
             dddTelefoneHidden.value = dddTelefoneHidden.value + telefonesArray[i].ddd + ',';
           }
        }

        console.log(telefonesArray);
        telefonesArray.splice(indexRemoved, 1);
        console.log(telefonesArray);
        novoTelefone.remove();
      });

    }
  });
}





var btnAdicionarCategoria = document.querySelector('.js-adicionar-categoria');
if (btnAdicionarCategoria) {
  btnAdicionarCategoria.addEventListener('click', function() {
    if (document.querySelector('.js-id-categoria').value === '') {
      alert('Por favor, selecione uma categoria válida!');
    } else {
      var novaCategoria = document.createElement("div");
      novaCategoria.classList.add('box-single');
      novaCategoria.innerHTML = `<a href="#" class="button-close js-remove">X</a>
                <div class="info-wrapper">
                  ${document.querySelector('.js-id-categoria').options[document.querySelector('.js-id-categoria').selectedIndex].innerHTML}
                </div>`;

      var idCategoriaHidden = document.querySelector(".js-arrIdCategoria");
      idCategoriaHidden.value = idCategoriaHidden.value + document.querySelector('.js-id-categoria').value + ',';

      document.querySelector('.boxes-wrapper.js-boxes-categorias').appendChild(novaCategoria);

      const identificador = Math.floor(Math.random() * 100000); 
      categoriasArray.push({
        id: document.querySelector('.js-id-categoria').value,
        identificador: identificador
      });

      document.querySelector('.js-id-categoria').value = '';

      novaCategoria.querySelector('.button-close').addEventListener('click', function(e) {
        e.preventDefault();
        let indexRemoved = 0;
        idCategoriaHidden.value = '';
        for (var i = 0; i < categoriasArray.length; i++) {
           if (categoriasArray[i].identificador === identificador) {
             indexRemoved = i;
           } else {
             idCategoriaHidden.value = idCategoriaHidden.value + categoriasArray[i].id + ',';
           }
        }

        console.log(categoriasArray);
        categoriasArray.splice(indexRemoved, 1);
        console.log(categoriasArray);
        novaCategoria.remove();
      });

    }
  });
}

var btnRemoverDocumentos = document.querySelectorAll('.js-remove-existing-documento');

for(var i = 0; i < btnRemoverDocumentos.length; i++) {
  btnRemoverDocumentos[i].addEventListener('click', function(e) {
    e.preventDefault();

    var removerDocumento = document.querySelector(".js-removerDocumentos");
    removerDocumento.value =  removerDocumento.value + event.target.attributes['data-id'].value + ",";

    event.target.parentNode.remove();
  });
}

var btnRemoverEnderecos = document.querySelectorAll('.js-remove-existing-endereco');

console.log(btnRemoverEnderecos);

for(var i = 0; i < btnRemoverEnderecos.length; i++) {
  console.log(btnRemoverEnderecos[i]);
  btnRemoverEnderecos[i].addEventListener('click', function(e) {
    e.preventDefault();

    var removerEndereco = document.querySelector(".js-removerEnderecos");
    removerEndereco.value =  removerEndereco.value + event.target.attributes['data-id'].value + ",";

    event.target.parentNode.remove();
  });
}




var btnRemoverTelefones = document.querySelectorAll('.js-remove-existing-telefone');

for(var i = 0; i < btnRemoverTelefones.length; i++) {
  btnRemoverTelefones[i].addEventListener('click', function(e) {
    e.preventDefault();

    var removerTelefone = document.querySelector(".js-removerTelefones");
    removerTelefone.value =  removerTelefone.value + event.target.attributes['data-id'].value + ",";

    event.target.parentNode.remove();
  });
}

var btnRemoverCartoes = document.querySelectorAll('.js-remove-existing-cartao');

console.log(btnRemoverCartoes);

for(var i = 0; i < btnRemoverCartoes.length; i++) {
  console.log(btnRemoverCartoes[i]);
  btnRemoverCartoes[i].addEventListener('click', function(e) {
    e.preventDefault();

    var removerCartao = document.querySelector(".js-removerCartoes");
    removerCartao.value =  removerCartao.value + event.target.attributes['data-id'].value + ",";

    event.target.parentNode.remove();
  });
}

var btnRemoverCategorias = document.querySelectorAll('.js-remove-existing-categoria');

for(var i = 0; i < btnRemoverCategorias.length; i++) {
  console.log(btnRemoverCategorias[i]);
  btnRemoverCategorias[i].addEventListener('click', function(e) {
    e.preventDefault();

    var removerCategoria = document.querySelector(".js-removerCategorias");
    removerCategoria.value =  removerCategoria.value + event.target.attributes['data-id'].value + ",";

    event.target.parentNode.remove();
  });
}






var spanDatas = document.querySelectorAll('.js-date-value');

for(let i = 0; i < spanDatas.length; i++) {
  spanDatas[i].innerHTML = formatarData(spanDatas[i].innerHTML);
}

var spanTextos = document.querySelectorAll('.js-string-value');

var removeCapa = document.querySelector('.js-remove-capa');
var removeCapaWrapper = document.querySelector('.js-remove-capa-wrapper');
var mudaCapa = document.querySelector('.js-muda-capa');

if (removeCapa) {
  removeCapa.addEventListener('click', function(){    
    removeCapaWrapper.remove();
    mudaCapa.style.display = 'block';
  });
}

var links = document.querySelectorAll('a');

for(let i = 0; i < links.length; i++) {
  links[i].attributes['href'].value = links[i].attributes['href'].value.replace('8080', '8082');
}

var checkoutNovoEndereco = document.querySelectorAll('input[name="enderecoEntrega"]');
for (var i = 0; i < checkoutNovoEndereco.length; i++) {
  checkoutNovoEndereco[i].addEventListener('change', function() {
    document.querySelector('.js-checkout-form-novo-endereco').style.display = event.target.value === "0" ? 'block' : 'none';
  });
}

var checkoutNovoCartao = document.querySelectorAll('input[name="cartaoCreditoCompra"]');
for (var i = 0; i < checkoutNovoCartao.length; i++) {
  checkoutNovoCartao[i].addEventListener('change', function() {
    document.querySelector('.js-checkout-form-novo-cartao').style.display = event.target.value === "0" ? 'block' : 'none';
  });
}

var inativarCarrinho = document.querySelector('.js-inativar-carrinho');
if (inativarCarrinho) {
  inativarCarrinho.addEventListener('click', function() {
    document.querySelector('.js-carrinho-ativo').style.display = 'none';
    document.querySelector('.js-carrinho-inativo').style.display = 'block';    
  });
}




var checkoutNewCardButton = document.querySelector('.js-checkout-new-card');
var cartoesNovosWrapper = document.querySelector('.js-cartoes-novos-wrapper');
if (checkoutNewCardButton && cartoesNovosWrapper) {
  var numeroCartoesNovos = 1;
  checkoutNewCardButton.addEventListener('click', function() {
    let novoCartaoCheckout = document.createElement("div");
    novoCartaoCheckout.classList.add('box-single');
    novoCartaoCheckout.innerHTML = `
                <div class="info-wrapper">
            <input type="text" name="cartaoNumero_${numeroCartoesNovos}" placeholder="Número do cartão" class="js-numero-cartao" required>
            <input type="text" name="cartaoNome_${numeroCartoesNovos}" placeholder="Nome impresso no cartão" class="js-nome-cartao" required>
            <select name="cartaoBandeira_${numeroCartoesNovos}" class="js-bandeira-cartao" required>
              <option value="">Bandeira do Cartão</option>
              <option value="1">Visa</option>
              <option value="2">MasterCard</option>
            </select>
            <input type="text" name="cartaoCodigo_${numeroCartoesNovos}" placeholder="Código de segurança" class="js-cvv-cartao" required>
            <input type="date" name="cartaoValidade_${numeroCartoesNovos}" placeholder="Data de validade do cartão" class="js-validade-cartao" required>
            <input type="checkbox" name="cadastrarCartao_${numeroCartoesNovos}" id="cadastrarCartao_${numeroCartoesNovos}">
            <label for="cadastrarCartao_${numeroCartoesNovos}">Associar esse cartão à minha conta</label>
                  <input type="hidden" name="idCartaoCredito" value="0">
                  <input type="number" name="valorPagoCartaoNovo_${numeroCartoesNovos}" class="js-valorPagamento" step="0.01" min="10" placeholder="Insira o valor que este cartão irá pagar">
                </div>`;
    cartoesNovosWrapper.appendChild(novoCartaoCheckout);
    document.querySelector('.js-numeroCartoesNovos').value = numeroCartoesNovos;
    numeroCartoesNovos++;
  });
}

var dinheiroAll = document.querySelectorAll('.js-dinheiro');

for (var i = 0; i < dinheiroAll.length; i++) {
  console.log(dinheiroAll[i].innerHTML);
  dinheiroAll[i].innerHTML = "R$ " + parseFloat(dinheiroAll[i].innerHTML).toFixed(2);
}