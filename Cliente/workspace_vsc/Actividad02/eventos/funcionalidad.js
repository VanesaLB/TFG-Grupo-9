
function crearInputsIngredientes(arrayIngredientes){
    console.log(arrayIngredientes)
    let cabeceraIngredientes = document.createElement("h3")
    let nodoTextoCabeceraIngredientes = document.createTextNode("Ingredientes")
    divIngredientes.appendChild(cabeceraIngredientes);
    divIngredientes.appendChild(nodoTextoCabeceraIngredientes);
    let nodoSaltoDeLinea = document.createElement("br")
    divIngredientes.appendChild(nodoSaltoDeLinea);

    for(let ingrediente of arrayIngredientes){
        let inputIngrediente = document.createElement("input")//<input/>
        inputIngrediente.setAttribute("type", "checkbox")//<input type="checkbox"/>
        //Seria totalmente equivalente hacer:
        //inputIngrediente.type = "checkbos"
        inputIngrediente.setAttribute("name", "ingredientes")//<input type="checkbox" name="ingredientes"/>
        inputIngrediente.setAttribute("value", ingrediente.precio)//<input type="checkbox" name="ingredientes" value="1"/>
        console.log(inputIngrediente)

        let nodoTextoIngrediente = document.createTextNode(ingrediente.nombre)
        console.log(nodoTextoIngrediente)

        divIngredientes.appendChild(inputIngrediente);
        divIngredientes.appendChild(nodoTextoIngrediente);

        let nodoSaltoDeLinea = document.createElement("br")
        divIngredientes.appendChild(nodoSaltoDeLinea);
    }
}

function crearInputsTamaños(arrayTamaños){
    console.log(arrayTamaños)
    let cabeceraTamaños = document.createElement("h3")
    let nodoTextoCabeceraTamaños = document.createTextNode("Tamaños")
    divTamaños.appendChild(cabeceraTamaños);
    divTamaños.appendChild(nodoTextoCabeceraTamaños);
    let nodoSaltoDeLinea = document.createElement("br")
    divTamaños.appendChild(nodoSaltoDeLinea);

    for(let tamaño of arrayTamaños){
        let inputRadio = document.createElement("input")//<input/>
        inputRadio.setAttribute("type", "radio")//<input type="radio"/>
        //Seria totalmente equivalente hacer:
        inputRadio.type = "radio"
        inputRadio.setAttribute("name", "tamaños")//<input type="radio" name="radios"/>
        inputRadio.setAttribute("value", tamaño.precio)//<input type="radio" name="radios" value="5"/>
        console.log(inputRadio)

        let nodoTextoTamaño = document.createTextNode(tamaño.nombre)
        console.log(nodoTextoTamaño)

        divTamaños.appendChild(inputRadio);
        divTamaños.appendChild(nodoTextoTamaño);

        let nodoSaltoDeLinea = document.createElement("br")
        divTamaños.appendChild(nodoSaltoDeLinea);
    }
}

function procesarRespuesta(mensaje){
    console.log(mensaje)
    //Todo lo que se trabaje aquí será DOM
    //El mensaje que llega es un String. Mejor convertirlo a objetos
    var pizzeria = JSON.parse(mensaje)
    console.log(pizzeria)
    //Puedo acceder a los ingredientes
    console.log(pizzeria.tamaños)
    console.log(pizzeria.ingredientes)
    //Creamos los inputs radio con los tamaños
    crearInputsTamaños(pizzeria.tamaños)
    //Creamos los inputs checkbox con los ingredientes
    crearInputsIngredientes(pizzeria.ingredientes)


}

function pedirDatosPizzeria(recurso,tipoPetion){
    console.log("pedirDatosPizzeria -> Entrando con tipo de peticion " + tipoPetion)
    let xmlHttp = new XMLHttpRequest()

    xmlHttp.onreadystatechange = function(){        
        if( this.readyState == 4){
            if(this.status == 200){//OK
                if(tipoPetion == 1){
                    procesarRespuesta(this.responseText)//ESTO SE HARA MANIPULANDO EL DOM
                }else if(tipoPetion == 2){
                    calcularPrecioAjax(this.responseText)
                }else{
                    console.log("Tipo de peticion no reconocida")
                }                     
            }else if(this.status == 404){
                console.log("El recurso solicitado NO existe: 404")
                console.log(this.responseText)
            }else {
                alert("ZASCA!")
            }
        }
    }

    //localhost:5500/Actividad02/pizzeria.json
    xmlHttp.open('GET',recurso, true)//Accedemos de manera relativa
    //readyState = 2, y se ejecutará la función de callback
    xmlHttp.send(null)
    console.log("pedirDatosPizzeria -> Saliendo")
}

function actualizarDatos(){            
    console.log("actualizarDatos -> Actualizando la información...")
    divTamaños.innerHTML = ""
    divIngredientes.innerHTML = ""
    pedirDatosPizzeria('pizzeria.json',1);
}

function calcularPrecio(){
    console.log("calcularPrecio -> Calculando precio")
    let precio = 0;
    let tamaños = document.getElementsByName("tamaños")
    for(tamaño of tamaños){
        if(tamaño.checked){
            console.log(tamaño.name + "-" + tamaño.value)
            precio = parseInt(tamaño.value)
            break;
        }
    }

    let ingredientes = document.getElementsByName("ingredientes")
    for(ingrediente of ingredientes){
        if(ingrediente.checked){
            console.log(ingrediente.name + "-" + ingrediente.value)
            precio += parseInt(ingrediente.value)
        }
    }
    console.log("precio: " + precio)
    let hPrecio = document.createElement("h4")//<h4></h4>
    let tPrecio = document.createTextNode("Precio: " + precio)//Precio: 6
    hPrecio.appendChild(tPrecio)//<h4>Precio: 6</h4>

    divPrecioPizza.innerHTML = ""
    divPrecioPizza.appendChild(hPrecio)
}

function calcularPrecioAjax(mensaje){
    console.log(mensaje)

    var pizzeria = JSON.parse(mensaje)

    console.log("calcularPrecioAjax -> Calculando precio")
    let precio = 0;
    let tamaños = document.getElementsByName("tamaños")
    let indice = 0;
    for(tamaño of tamaños){
        if(tamaño.checked){
            console.log(pizzeria.tamaños[indice].nombre + "-" + pizzeria.tamaños[indice].precio)
            precio = parseInt(pizzeria.tamaños[indice].precio)
            break;
        }
        indice++
    }

    indice = 0;
    let ingredientes = document.getElementsByName("ingredientes")
    console.log("Ingredientes AJAX" + JSON.stringify(pizzeria.ingredientes))
    for(ingrediente of ingredientes){
        if(ingrediente.checked){
            console.log(pizzeria.ingredientes[indice].nombre + "-" + pizzeria.ingredientes[indice].precio)
            precio += parseInt(pizzeria.ingredientes[indice].precio)
        }
        indice++
    }//of in if for while :
    console.log("precio: " + precio)

    let hPrecio = document.createElement("h4")//<h4></h4>
    let tPrecio = document.createTextNode("Precio: " + precio)//Precio: 6
    hPrecio.appendChild(tPrecio)//<h4>Precio: 6</h4>

    divPrecioPizza.innerHTML = ""
    divPrecioPizza.appendChild(hPrecio)
}

botonCalcularPrecio.onclick = calcularPrecio
botonCalcularPrecioAjax.onclick = function (){
    pedirDatosPizzeria('pizzeria.json',2)
}
botonActualizarDatos.onclick = function(){
    actualizarDatos()
}
pedirDatosPizzeria('pizzeria.json',1);

//Cuando se haya cargado la página en su totalidad, ejecutamos pedirDatosPizzeria
/*
window.onload = function(){
    botonCalcularPrecio.onclick = calcularPrecio
    botonCalcularPrecioAjax.onclick = function (){
        pedirDatosPizzeria('pizzeria.json',2)
    }
    //NO haria falta usar una función anonima para añadir la función
    //al evento onclick ya que la función NO tiene parametros de entrada
    //pero, podemos seguir haciendolo
    botonActualizarDatos.onclick = function(){
        actualizarDatos()
    }
    //Podemos invocar funciones
    pedirDatosPizzeria('pizzeria.json',1);
    //Otra funcion...
}*/