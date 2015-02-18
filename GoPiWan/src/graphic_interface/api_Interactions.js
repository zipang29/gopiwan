var output = document.getElementById("output"),
    piAddress = "127.0.0.1",
    display = document.getElementById("VideoDisplay"),
    delay = 2000,
    debug = true ;

/*
 * Envoie un ordre de manière asynchrone au GoPiGo
 * L'ordre envoyé est en fait une requete HTTP de type GET
 * qui sera traitée par la partie jetty du programme serveur
 */
function send(order){
    if (debug) console.log("Send order : "+order) ;
    
	var http_request = getXMLHttpRequest();
	
	http_request.onreadystatechange = function() {
		if (http_request.readyState == 4 && (http_request.status == 200 || http_request.status == 0)) { // tout s'est bien passé
            if (debug) console.log("[request]   succès de la requete.\nDonnées recues :     "+http_request.responseXML) ;
            
		}else if (http_request.readyState < 4) {
            log(output, "Envoi de la requete...") ;
		}else{
            log(output, "HTTP error : "+http_request.status+" "+http_request.statusText) ;   
        }
	};
	
	http_request.open("GET", piAddress, true); // requete GET en mode asynchrone
    http_request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.timeout = delay;
    xhr.ontimeout = function(){
        log(output, "TimeOut") ;   
    };
    try{
    http_request.send(order) ;
    }catch(e){ log(output, e.message) ; }
    
    
}

function receiveFluxVideo(){
    
    
}

/**
 *  Retourne un objet XMLHttpRequest selon le navigateur
 */
function getXMLHttpRequest() {
	var http_request = null;
	
	if (window.XMLHttpRequest || window.ActiveXObject) {
		if (window.ActiveXObject) { // IE7
			try {
				http_request = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			}
		} else { // les autres
			http_request = new XMLHttpRequest();
		}
	} else { // vieux IE
		alert("Votre vieux navigateur ne supporte pas l'objet XMLHTTPRequest... Tant pis pour vous");
		http_request = null;
	}
	return http_request;
}

/**
 * Ecriture de text a la suite de ce qui est deja present dans element
 */
function log(element, text){
    element.value = element.value + "\n" + text ;
}
