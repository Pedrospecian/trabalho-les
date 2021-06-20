<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Gráfico de vendas</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<header>
		${headerHTML}
	</header>
	<main class="main-default">
		<div class="container">
			<h1>Gráfico de vendas</h1>
			<form  action="/trabalho-les/gerarGrafico" method="post" class="js-pristine-validation">
				<div class="form-group">
					<input type="date" name="dataInicio" placeholder="Data de início" required data-pristine-required-message="Este campo é obrigatório" cypress-dataInicio value="${dataInicio}">
				</div>
				<div class="form-group">
					<input type="date" name="dataFim" placeholder="Data de fim" required data-pristine-required-message="Este campo é obrigatório" cypress-dataFim value="${dataFim}">
				</div>
				<div class="form-group">
					<select name="tipo" cypress-tipo>
						<option value="categoria" ${tipo.equals("categoria") ? "selected" : ""}>Por categoria</option>
						<option value="livro" ${tipo.equals("livro") ? "selected" : ""}>Por livro</option>
					</select>
				</div>
				<button type="submit" cypress-submitGrafico>Gerar</button>
			</form>

			<div class="dados">
				<c:forEach var="item" items="${itens}">	
				<div data-qt="${item.getValor()}" data-date="${item.getData()}" data-label="${item.getLabel()}" class="js-itensGraficos"></div>
				</c:forEach>
			</div>

			<canvas id="myChart" width="600" height="300"></canvas>
		</div>
	</main>
	<footer>
		<div class="container">
			<p>LES - Laboratório de Engenharia de Software</p>
			<br>
			Aline Laconca - RA: 1840481922014
			<br>
			Pedro Toupitzen Specian - RA: 1840481923023
		</div>
	</footer>
	<script type="text/javascript" src="assets/js/vendor/imask.js"></script>
	<script type="text/javascript" src="assets/js/vendor/pristine.min.js"></script>
	<script type="text/javascript" src="assets/js/vendor/Chart.bundle.min.js"></script>
	<script type="text/javascript" src="assets/js/main.js"></script>
	<script type="text/javascript">
		const cores = ['#ee1000', '#33aaee', '#3440e9', '#73ff32', '#e220fe', '#eac221', '#08af50', '#432100', '#657442'];
		var ctx = document.getElementById('myChart');

		var itensGraficos = document.querySelectorAll('.js-itensGraficos');
		var labelsStr = '';
		var datesStr = '';

		//preenche as labels com as datas
		for (let i = 0; i < itensGraficos.length; i++) {
			console.log(itensGraficos[i].attributes["data-date"].value);
			if (!datesStr.includes(itensGraficos[i].attributes["data-date"].value+",")) {
				datesStr += itensGraficos[i].attributes["data-date"].value+",";
			}

			if (!labelsStr.includes(itensGraficos[i].attributes["data-label"].value+",")) {
				labelsStr += itensGraficos[i].attributes["data-label"].value+",";
			}
		}

		var dates = datesStr.split(",");
		var labels = labelsStr.split(",");

		dates.pop();
		labels.pop();

		console.log(dates);
		console.log(labels);

		var cjtoItens = [];

		for (let i = 0; i < labels.length; i++) {
			cjtoItens.push({
				label: labels[i],
				data: [],
				borderWidth: 1,
				borderColor: cores[i % cores.length],
				backgroundColor: cores[i % cores.length] + "40"
			});
		}



		for (let i = 0; i < cjtoItens.length; i++) {
			for (let j = 0; j < dates.length; j++) {
				cjtoItens[i].data.push(dates[j]);
			}
		}

		console.log(cjtoItens);

		for (let i = 0; i < itensGraficos.length; i++) {
			let dat = itensGraficos[i].attributes["data-date"].value;
			let qt = itensGraficos[i].attributes["data-qt"].value;
			let labe = itensGraficos[i].attributes["data-label"].value;

			for (let j = 0; j < cjtoItens.length; j++) {
				console.log(cjtoItens[j].label);
				console.log(cjtoItens[j].data);
				if (labe === cjtoItens[j].label) {
					for (let k = 0; k < cjtoItens[j].data.length; k++) {
						if (cjtoItens[j].data[k] === dat) {
							cjtoItens[j].data[k] = qt;
							break;
						}
					}
				}
			}
		}

		for (let i = 0; i < cjtoItens.length; i++) {
			for (let j = 0; j < cjtoItens[i].data.length; j++) {
				console.log("a data");
				console.log(cjtoItens[i].data[j]);
				if(!cjtoItens[i].data[j].match("^[0-9]+$")) {
					cjtoItens[i].data[j] = 0;
				}
			}
		}

		console.log(dates);
		console.log(cjtoItens);

		var myChart = new Chart(ctx, {
		    type: 'line',
		    data: {
		        labels: dates,
		        datasets: cjtoItens
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero: true
		                }
		            }]
		        }
		    }
		});
	</script>
</body>
</html>