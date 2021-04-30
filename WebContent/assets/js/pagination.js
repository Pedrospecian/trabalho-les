const list_items = document.querySelectorAll(".js-paginated-table tbody tr");
console.log(list_items);
const paginatedTable = document.querySelector(".js-paginated-table");
const itensPorPagina = paginatedTable.attributes["data-itensPorPagina"].value;

let newTabela = document.createElement("div");
newTabela.classList.add("tabela-paginada-wrapper");

console.log(itensPorPagina);

let totalPaginas = Math.ceil(list_items.length / itensPorPagina);

for (var i = 0; i < totalPaginas; i++) {
	newTabela.innerHTML += `<div class="tabela-pagina-single" data-page-number="${i+1}">
		<table cellpadding="0" cellspacing="0" >
			<thead>${paginatedTable.querySelector("thead").innerHTML}</thead>
			<tbody></tbody>
		</table>
	</div>`;
}

let atual = 1;
let itempercorrido = 1;

for (var i = 0; i < list_items.length; i++) {
	newTabela.querySelector(".tabela-pagina-single[data-page-number='"+atual+"'] table tbody").appendChild(list_items[i]);
	console.log(itempercorrido);

	if (itempercorrido >= itensPorPagina) {
		console.log("AAASAS");
		itempercorrido = 1;
		atual++;
	} else {
		itempercorrido++;
	}
}

document.querySelector('.paginated-table-wrapper').appendChild(newTabela);

let paginationLinks = document.querySelector(".js-pagination-links");

for (var i = 0; i < totalPaginas; i++) {
	paginationLinks.innerHTML += `<button type="button" data-js-linktopage="${i+1}">${i+1}</button>`;
}

const showPage = function(number) {
	let allPaginas = document.querySelectorAll('.paginated-table-wrapper .tabela-pagina-single');

	for (var i = 0; i<allPaginas.length; i++) {
		allPaginas[i].classList.remove("active");
		let currentActive = document.querySelector("[data-js-linktopage].active");
		if (currentActive) {
			currentActive.classList.remove("active");
		}
	}

	document.querySelector("[data-js-linktopage]:nth-child(" + number + ")").classList.add("active");

	let alvo = document.querySelector(`.paginated-table-wrapper .tabela-pagina-single[data-page-number="${number}"]`);
	alvo.classList.add("active");
}

for (var o = 0; o < totalPaginas; o++) {
	document.querySelector(`[data-js-linktopage="${o+1}"]`).addEventListener("click", function(e) {
		e.preventDefault();
		showPage(e.target.attributes['data-js-linktopage'].value);
	});
}

document.querySelector(`[data-js-linktopage="1"]`).click();