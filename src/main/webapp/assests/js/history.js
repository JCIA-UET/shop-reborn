function loadData(id) {
	var xmlhttp;
	var url = "ProductService?action=gpboid&orderid=" + id;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
	};

	xmlhttp.onreadystatechange = function() {
		// Remove all content of tag has id is 'body'
		document.getElementById("body").innerHTML = "";
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var json = JSON.parse(xmlhttp.responseText);
			var size = json.length;
			if(size == 0) {
				var str = '<p>There is no item in this order.</p>';
				document.getElementById("body").insertAdjacentHTML('beforeend', str);
			}
			else {
				for (var i = 0; i < size; i++) {
					var obj = json[i];
					var str = '<table class=\"table table-striped\"><thead><tr><th>No.</th><th>Image</th><th>Product Name</th><th>Quantity</th><th>Total</th></tr></thead><tbody><tr><td>' + (i+1) + '</td><td><img src=\"' + obj.imageLink + '\" style=\"width:50px;height:50px;\"/></td><td>' + obj.name + '</td><td>' + obj.quantity + '</td><td>' + obj.total + '</td></tr></tbody></table>';
					document.getElementById("body").insertAdjacentHTML('beforeend', str);	
				};
			}
		}
	};

	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}