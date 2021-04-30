<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Você está sendo redirecionado...</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
	<script type="text/javascript">
		if (!localStorage.getItem("deslogar")) {
			localStorage.setItem("deslogar", true);
			window.location.href = window.location.href;
		} else {
			localStorage.setItem("deslogar", false);
			window.location.href = "/trabalho-les/home";
		}
	</script>
</body>
</html>