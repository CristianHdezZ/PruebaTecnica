function fn() {

   var config = {
	    baseURL : 'https://test-container-qa.prueba.co/v1/entity/novelties/'
	};
    var env = karate.env
    karate.log("Cargando configuraciones",env);
    karate.configure('connectTimeout',5000);
  	karate.configure('readTimeout',5000);
    return config;
}