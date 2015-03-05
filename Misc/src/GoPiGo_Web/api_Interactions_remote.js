var piAddressForm = document.getElementById("piAddressForm"),
    piAddress,
    output = document.getElementById("output"),
    connectDelay = 1000,
    debug = true ;

/**
 * Test de connexion du pi à l'adresse indiquée dans le champ
 * Recupère dans un premier temps l'adresse entrée par l'utilisateur
 * Envoie une requete de type 'ping' pour tester la liaison
 */
function connect(){

    piAddress = piAddressForm.value ; if (debug) console.log("Trying to connect "+piAddress) ;
    ping(piAddress) ;
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

/**
 *  Envoie une requete a l'url passée en parametres
 */
function ping(ip){
    
    log(output, "Test connexion...") ;
    var ping = $.now();
    
    
    
/*        $.ajax({ 
            type: "HEAD",
            url: "http://"+ip,
            cache:false,
            complete: function(out){ 
                ping = $.now() - ping;
                log(output, "#"+ip+" Ping : "+ping+" ms");
            },
            success(data, textStatus, jqXHR){
                var statusCode = jqXHR.status;
                var statusText = jqXHR.statusText;
            },
            timeout:connectDelay,
            error: function(out){
               log(output, out.name) ;
                console.log(out);
            }
        });*/
    
    
    var jqxhr = $.ajax( {
    type: "HEAD",
    url: "http://"+ip,
    cache:false,
    data: null,
    dataType: 'json',
    timeout: connectDelay,
    } )

    .done(function( data, textStatus, jqXHR ) {
        alert( "success" );
    })
    .fail(function( jqXHR, textStatus, errorThrown ) {
        alert( "error" );
    })

    .always(function( data_jqXHR, textStatus, jqXHR_errorThrown ) {

        if (textStatus === 'success') {
            var jqXHR = jqXHR_errorThrown;
        } else {
            var jqXHR = data_jqXHR;
        }
        var data = jqXHR.responseJSON;

        switch (jqXHR.status) {
            case 102: log(output, "error 102") ;
            case 200: log(output, "error 200") ;
            case 201:
            case 401:
            default:
                console.log(data);
                break;
        }   

});
}
