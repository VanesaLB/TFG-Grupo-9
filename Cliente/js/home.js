/*
 * Esta función se va a encargar de recoger el .responseText de una petición XMLHttpRequest 
 * y usar los datos que le vengan para modificar el arbol DOM de la pagina html principal
 */


//Funciona. Tenemos que añadir algo para poder seleccionar la cantidad de los platos y que después cuando se manda a la function enviarPedido se mantenga 
//ese campo con el mismo valor, así se podrá seleccionar
function procesarRespuesta(jsonDoc) {

    /**
     * // Convertimos el XMLHttpRequest.responseText en un array de objetos JSON
     * Convertimos el XMLHttpRequest.responseText que nos llegue a un objeto JSON
     */
    console.log(jsonDoc)
    arrayJson = JSON.parse(jsonDoc)
    console.log(arrayJson)
    // EXTRA: Podemos hacer lo contrario con "JSON.stringify(obj)"ç

   // let ArrayPlatosRecibidos = []
    for (let objetoJson of arrayJson) {

// Accedemos a los datos de cada objeto
let idPlato = objetoJson.idPlato;
let ingredientes = objetoJson.ingredientes;
let descripcion = objetoJson.descripcion;
let tipo = objetoJson.tipo;
let vegano = objetoJson.vegano;
let gluten = objetoJson.gluten;
let precio = objetoJson.precio;

/*let elPlato = {
    id: idPlato,
    ingredientes: ingredientes,
    descripcion: descripcion,
    tipo: tipo,
    vegano: vegano,
    gluten: gluten,
    precio: precio
    
}*/

//ArrayPlatosRecibidos.push(elPlato)


// Aquí puedes hacer lo que necesites con los datos, como imprimirlos en la consola
console.log("ID de la entrada:", idPlato);
console.log("ingredientes:", ingredientes);
console.log("Empleado:", descripcion);
console.log("Días previstos:", precio);
console.log("Fecha de incorporación:", tipo);
console.log("Fecha de incorporación:", vegano);
console.log("Fecha de incorporación:", gluten);


// Para acceder a los datos dentro de ingredientes y descripcion, puedes hacer lo siguiente
// let idingredientes = ingredientes.idingredientes;
// let descripcioningredientes = ingredientes.descripcion;
// let idEmpleado = descripcion.idEmpleado;
// let nombreEmpleado = descripcion.nombre;
// ... y así sucesivamente con los campos que necesites

        opcionplato = document.createElement("input")
        opcionplato.type = "checkbox"
        opcionplato.id = idPlato
        opcionplato.name = "plato"
        opcionplato.value = precio

        platosPrincipales.appendChild(opcionplato)

        let labelopcionplato = document.createElement("label")
        let txtlabelopcionplato = document.createTextNode(" Este es el idPlato : " + idPlato)
        labelopcionplato.appendChild(txtlabelopcionplato)
        platosPrincipales.appendChild(labelopcionplato)

        let br = document.createElement("br")
        platosPrincipales.appendChild(br)
        
        

    }
  //  console.log(ArrayPlatosRecibidos);
}
//Funciona
function enviarPedido() {

    losPlatos = document.getElementsByName("plato")
    
    nombresPlatosElegidos = []

    for (let pev1 of losPlatos) {

        if (pev1.checked)
        nombresPlatosElegidos.push(parseInt(pev1.id))

    }

    console.log(nombresPlatosElegidos)
    
    if (nombresPlatosElegidos.length == 0) {
        alert("Debes seleccionar al menos 1 plato.");
        return false

    }
   
    platosPorDarDeAlta = [];
    let iAux = 0
    for (let pev2 of arrayJson) {

        
        if (nombresPlatosElegidos.includes(pev2.idPlato)) {
        
        let platito = {
            
            idMesa: 1,
            idPlato: pev2.idPlato,
            cantidad: 1,
            numeroComensales: 2,
            fecha: new Date(),
            servido: "no"
            // datetime.now().strftime('%Y-%m-%d %H:%M:%S'), (no funciona)
            //.toISOString() (si va)
        }
        platosPorDarDeAlta.push(platito)
    }
}
console.log(platosPorDarDeAlta)

 // Convertir el array de objetos a una cadena JSON
 //empleadosParaAlta = JSON.stringify(platosPorDarDeAlta);
 //console.log(empleadosParaAlta)

 /**
     * En esta parte realizamos la peticion XMLHttpRequest
     */


 const URL_DESTINO_ALTA = "http://localhost:8088"
 const RECURSO_ALTA = "/pedido/altaMuchos"
 let xmlHttpAltaPedido = new XMLHttpRequest()

 xmlHttpAltaPedido.onreadystatechange = function () {
     
    if (this.readyState == 4) {

         if (this.status == 200) {
            alert("PEDIDOS DADOS DE ALTA")
         } else {
             alert("ZASCA!")
         }
     }
 }

 xmlHttpAltaPedido.open('POST', URL_DESTINO_ALTA + RECURSO_ALTA, true)
 xmlHttpAltaPedido.setRequestHeader('Content-Type', 'application/json');
 //xmlHttpAltaPedido.send(platosPorDarDeAlta)
 xmlHttpAltaPedido.send(JSON.stringify(platosPorDarDeAlta))
 //
 return true

    
}

//no funciona como debería
function calcularDias() {




    let precioPorPersona = document.getElementsByName("plato")
    let diasTotales = 0

    /**
     * Este for se encarga de recorrer el array formado por todos los input radio con name="tamaño"
     * y recoger el valor del value del que esté seleccionado (.checked)
     */
    for (let dias of precioPorPersona) {

        if (dias.checked) {

            diaParseado = parseFloat(dias.value)
            diasTotales += diaParseado
        }

    }

   

    /**
     * La letiable precioFinalTotal contendrá la suma del value del input radio seleccionado 
     * y de las sumas de los input checkout seleccionados
     */
    let diasTotalesFinal;
    diasTotalesFinal = diasTotales
    let textoDiasFinal = "La suma de todos los dias previstos por persona es de " + diasTotalesFinal + " dias"

    /**
     * Añadimos al div con id="precioDelPedido" el texto que nos informa del precio final de la pizza
     */

    diasTotalesDiv.innerHTML = textoDiasFinal
}



/**
 * Creamos 2 constantes que usaremos posteriormente
 */
const URL_DESTINO = "http://localhost:8088"
const RECURSO = "/producto/buscarTodos"

/**
 * El window.onload lo usamos para que todo lo que se encuentra dentro de él se ejecute
 * cuando toda la página html haya cargado por completo
 * 
 * 1) Dentro del window.onload vamos a crear una peticion XMLHttpRequest para traernos la informacion
 * de un JSON que se encuentra en servidor, parsear sus datos para crearnos un objeto JSON y 
 * utilizarlo para pasarselo a la funcion procesarRespuesta(jsonDoc) y esta se encargará de 
 * elegir los datos que necesite del JSON y usarlos para insertar información en la pagina html
 * principal
 * 
 * 2) Además vamos a darle funcionalidad a 2 botones el html
 * 
 * 2.1) Al primero, con id="recargarDatosForm" vamos a darle la funcionalidad .onclick, la cual
 * se encargará de que cuando se pulse este boton, se restableceran todos los campos del formulario
 * para que podamos introducirlos de nuevo, y se le asignará a los div que tenian informacion gracias
 * a la peticion XMLHttpRequest, el valor de "". 
 * Además se volverá a ejecutar la peticion XMLHttpRequest y se cargaran de nuevo ciertas partes del html, 
 * y como los div estan vacios gracias a la asignacion de "" anterior, 
 * la pagina no sufre ninguna duplicacion de informacion
 * 
 * 2.2) Al segundo, con id="procesarPedidoFinal" vamos a darle la funcionalidad .onclick, la cual
 * se encargará de que cuando se pulse este boton, si la funcion validacion() devuelve true, se 
 * ejecutara la funcion calcularPrecio(), y si devuelve false, se habrá lanzado alguna alert 
 * por pantalla, indicandonos que hay algun campo obligatorio del formulario sin rellenar
 * 
 */

//Hace lo que debe hacer

window.onload = function () {   


    /**
     * En esta parte realizamos la peticion XMLHttpRequest
     */
    let xmlHttpAltaPedido = new XMLHttpRequest()

    xmlHttpAltaPedido.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                procesarRespuesta(this.responseText)//Obtenemos el valor en texto
            } else {
                alert("ZASCA!")
            }
        }
    }

    xmlHttpAltaPedido.open('GET', URL_DESTINO + RECURSO, true)
    xmlHttpAltaPedido.send(null)



/**
     * En esta parte le damos la funcionalidad al primer button
     * Funciona
     */

recargarDatosForm.addEventListener("click", function () {

    platosPrincipales.innerHTML = ""
    diasTotalesDiv.innerHTML = ""
    procesarRespuesta(xmlHttpAltaPedido.responseText)

})


/**
     * En esta parte le damos la funcionalidad al segundo button
     * Funciona pero no como debería
     */
procesarDiasTotales.addEventListener("click", function () {

    calcularDias()
})

/**
     * En esta parte le damos la funcionalidad al tercer button
     * Funciona
     */
altaPedido.addEventListener("click", function () {

    enviarPedido()
})


}