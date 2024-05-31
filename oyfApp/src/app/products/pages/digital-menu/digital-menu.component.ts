import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from '../../interfaces/product.interface';
import { ProductService } from '../../services/product.service';
import { PedidoService } from '../../services/pedido.service';
import { tap } from 'rxjs';
import { ComandaService } from '../../services/comanda.service';


//Decorador que proporciona metadatos sobre el componente
@Component({
  selector: 'app-digital-menu',
  templateUrl: './digital-menu.component.html',
  styleUrl: './digital-menu.component.css'
})

/**
 * Clase DigitalMenuComponent, que implementa la interfaz OnInit.
 * Esto significa que el método ngOnInit() se ejecutará cuando se inicialice el componente.
 */

export class DigitalMenuComponent implements OnInit{

  //Propiedad de entrada decorada con @Input(). Permite que el componente reciba un objeto Product del componente padre.
  //@Input()
  public product!: Product;

  //@Output()
  public idDelPedido: number = 0;

  public products: Product[] = []; //Array products para almacenar los productos
  public productsTodos: Product[] = [];
  public productsMiPedido: Product[] = []; // Array para mantener el estado de los type number

  public selectedElements: number[] = []; //Array para almacenar cantidad

  public isQuantityMap: { [idProducto: number]: number} = {};
  public productsLocalStNo: { [idProducto: number]: number} = {};

  constructor(
    private productService: ProductService,
    private pedidoService: PedidoService,
    private comandaService: ComandaService,
    private changeDetectorRef: ChangeDetectorRef
   ){ }

  /**
   * Implementa el método ngOnInit(). Aquí se inicializan las variables "products" y "productsTodos" iniciadas al principio, llamando al método getProducts()
   * del servicio ProductService y se suscribe al observable devuelto para recibir los productos.
   * También se carga el valor de los input de los productos de nuestro html en base al localstorage
   * Finalmente ejecuta la función que se encarga de mostrarnos en nuestra página nuestro pedido.
   */

  ngOnInit(): void {
    this.productService.getProducts()
      .subscribe(
        products => {
          this.products = products;
          this.productsTodos = products;
          this.loadQuantityStateFromLocalStorage();
          this.mostrarMiPedido();
        },
        error => {
          console.error('Error al cargar los productos:', error);
        }
      );
  }

  /**
   * Este método se llama cuando se hace clic en el botón "Todos".
   * Obtiene un array de objetos Json de tipo Producto(La respuesta de nuestra API) 
   * con el método getProducts del servicio productService, y actualiza la variable 
   * products inicializada al principio con los productos obtenidos. 
   * */

  mostrarTodos(): void {

    /*Esta línea llama al método getProducts() del servicio ProductService para obtener todos los productos.
    Se suscribe al observable devuelto para recibir los productos y ejecutar una función de devolución de llamada
    cuando los datos estén disponibles.
    Dentro de la función de devolución de llamada, todos los productos obtenidos del servicio se asignan
    a la propiedad products del componente, lo que actualiza la lista de productos que se muestra en la interfaz de usuario.
     */

    this.productService.getProducts()
    .subscribe(products => {
      this.products = products;
    });
  }

   /**
   * Este método se llama cuando se hace clic en el botón "Veganos".
   * Obtiene un array de objetos Json de tipo Producto(La respuesta de nuestra API) 
   * con el método getProducts del servicio productService, y actualiza la variable 
   * products inicializada al principio con los productos obtenidos. 
   * */
  
  filtrarPorVeganos(): void {
    this.productService.getVeganos()
    .subscribe(products => {
      this.products = products;
    });
  }

   /**
   * Este método se llama cuando se hace clic en el botón "Sin Gluten".
   * Obtiene un array de objetos Json de tipo Producto(La respuesta de nuestra API) 
   * con el método getProducts del servicio productService, y actualiza la variable 
   * products inicializada al principio con los productos obtenidos. 
   * */

  filtrarPorSinGluten(): void {
    
    this.productService.getSinGluten()
    .subscribe(products => {
      this.products = products;
    });
  }

/**
 * Este método se encarga de captar un evento change, recuperar el value del input correspondiente del html, y utilizando
 * el value del input, crear una variable en el local storage o actualizar una variable ya existente. La clave de la variable del localstorage
 * creada o actualizada utiliza el productId recibido para poder formar la estructura del string de todos los objetos de tipo producto 
 * del localstorage (`quantityState-${productId}`). El valor es el value del input recuperado en formato String.
 * 
 * @param event contiene información sobre el evento change generado. Tiene propiedades como el target, en él nos viene información en referencia 
 * a la instancia que generó el evento, como su value. En este caso nuestra instancia es un input del html.
 * @param productId hace referencia al atributo Id del objeto producto
 */

  public saveCheckQuantityLocalStorage(event: Event, productId: number): void {
    if (event.target instanceof HTMLInputElement) {
      const quantity = parseInt(event.target.value); // Obtener la cantidad del input
      // Verificar si la cantidad es válida (puedes agregar validaciones adicionales según tus necesidades)
      localStorage.setItem(`quantityState-${productId}`, quantity.toString()); // Guardar la cantidad en el almacenamiento local
      this.mostrarMiPedido();
    }
  }

  /**
   * Esta método se encarga de comprobar los items del localstorage nombrados con la estructura: `quantityState-${productId}`. 
   * Iteramos sobre cada variable de un bucle que contiene todos los productos, por lo que podemos acceder en cada iteración
   * al productId, de esa manera podemos en cada iteración realizar un "localstorage.getItem(), pasarle por parámetro la estructura 
   * necesaria con el Id y asignar el resultado de este método a una variable.
   * En cada iteración finalizamos con una asignación a un array de objetos clave valor de tipo number(Nuestra variable isQuantityMap del principio). 
   * Para ello comprobamos que nuestra variable formada por localStorage.getItem() es nula o no. En caso de ser nula, el valor que asignamos será 0, 
   * y si tiene valor, el número correspondiente parseado como number. La clave siempre será el productId
   * 
   * EXTRA REUSAR: Después ejecutamos la función loadQuantityStateFromLocalStorage(), 
    que se encarga de actualizar todos los input de la página html 
    en base a los datos de nuestro localStorage.
   */

  public loadQuantityStateFromLocalStorage(): void {
    
    /*
    Este es un bucle for que itera sobre cada elemento en el array products dentro de la clase.
    El bucle se ejecuta desde i = 0 hasta i < this.products.length, lo que significa que se ejecutará
    una vez por cada elemento en el array. 
    
    */

    for (let i = 0; i < this.productsTodos.length; i++) {
      /*En cada iteración del bucle, se obtiene el identificador único (idProducto) del producto actual
      en la posición i del array products y se almacena en la variable productId. */
     const productId = this.productsTodos[i].idProducto;
      /*Se utiliza el método getItem del objeto localStorage para recuperar el estado guardado del checkbox
      para el producto actual. Se utiliza la clave única generada concatenando 'checkboxState-'
      con el productId del producto actual. */
     const savedQuantity = localStorage.getItem(`quantityState-${productId}`);
      /*Aquí se asigna la cantidad recuperada al objeto isQuantityMap. Si savedQuantity es una cadena no nula
      y no vacía (es decir, si la cantidad del producto fue guardada anteriormente en el almacenamiento local),
      se convierte la cadena en un número entero utilizando parseInt() y se asigna a isQuantityMap[productId].
      Si savedQuantity es nulo o vacío, se asigna 0 a isQuantityMap[productId]. */
      this.isQuantityMap[productId] = savedQuantity ? parseInt(savedQuantity) : 0;
   }
  }

/**
 * Este método se encarga de iterar sobre nuestra variable this.productsLocalStNo. Itera sobre las claves del objeto de tipo clave-valor,
 * por eso se utiliza "in", en vez de "of". En cada iteración realiza un localStorage.setItem a la variable del localstorage cuya clave utiliza la clave de
 * nuestro objeto productsLocalStNo más una estructura particular ( `quantityState-${p}` ). A esta variable del localstorage le asigna el string "0". De esta manera
 * reiniciamos a 0 todas las variables del localStorage con clave estructurada como: `quantityState-${p}`
 */
  public updateLocalStorageWithNewData(): void {
    for (let p in this.productsLocalStNo) {
      localStorage.setItem((`quantityState-${p}`), "0");
    }
 }

 /**
  * Este método se encarga de mostrarnos en nuestra página el pedido actual que se está realizando.
  * El método comienza ejecutando el método loadQuantityStateFromLocalStorage() para refrescar los input number del html
  * con las variables del localStorage correspondientes. Iteramos sobre la variable productsTodos generada inicialmente
  * con un bucle for clásico, para asignarle valores a nuestra variable  "productsLocalStNo" de tipo objeto clave valor.A cada clave
  * le asignamos el valor de la variable idProducto de la iteración, y como value ponemos el valor de la variable del localStorage la cuál
  * en su clave contiene el mismo productId, además de su estructura correspondiente (`quantityState-${productId}`)
  * 
  * Como este método lo llamamos en el método "saveCheckQuantityLocalStorage(event: Event, productId: number): void",
  * el cual se ejecuta cada vez que cambia cualquier input number de nuestra página html, en todo momento vemos nuestro pedido
  * actualizado acorde a las variables de nuestro localStorage
  */

 mostrarMiPedido(): void {
   // Carga los estados de cantidad desde localStorage
  this.loadQuantityStateFromLocalStorage();
   // Itera sobre cada producto en el array de productos
   for (let i = 0; i < this.productsTodos.length; i++) {
     /*En cada iteración del bucle, se obtiene el identificador único (idProducto) del producto actual
     en la posición i del array products y se almacena en la variable productId. */
     // Obtiene el identificador único del producto actual
     const productId = this.productsTodos[i].idProducto;
     // Recupera el estado de cantidad guardado en localStorage para el producto actual
     const cantidadPr = localStorage.getItem(`quantityState-${productId}`);
     // Si hay una cantidad guardada y no es 0
     if (cantidadPr && parseInt(cantidadPr) !== 0) {
       // Asigna la cantidad al objeto productsLocalStNo, convirtiéndola a entero
       this.productsLocalStNo[productId] = cantidadPr ? parseInt(cantidadPr) : 0;
     } else {
       // Si no hay cantidad guardada o es cero, eliminar la entrada del objeto productsLocalStNo
       delete this.productsLocalStNo[productId];
     }
   }
   // Filtra los productos para obtener solo aquellos con cantidades mayores a 0 en localStorage
   this.productsMiPedido = this.productsTodos.filter(pr => {
     return this.productsLocalStNo[pr.idProducto] && this.productsLocalStNo[pr.idProducto] > 0;
   });

   // Asegurarse de que Angular detecte los cambios
   this.changeDetectorRef.detectChanges();
   this.mostrarTotal();
 }

 /**
  * Este método se encarga de asignar a nuestra variable "productsMiPedido" 
  * todos los productos dentro de la variable "productsTodos" (array de Productos)
  * que cumplen el filtro que tiene la condición de que exista un producto en la variable
  * "productsLocalStNo" con el mismo idProducto que el correspondiente idProducto del objeto
  * Producto de nuestra variable "productsTodos", y que su valor sea diferente de 0. Con esto, en la variable "productsMiPedido"
  * tenemos todos los productos que le corresponden a nuestro pedido. A continuación realizamos 
  * un bucle for con el que conseguimos que se tenga en cuenta el precio de cada producto y su cantidad, y 
  * en base a eso concatenemos el total de todos los precios en la variable nombrada "total"
  * 
  * @returns devuelve la variable que almacena el total de los precios de todos los productos seleccionados
  */

 mostrarTotal(): number {
   let total = 0;

   // Filtrar los productos que tienen una cantidad en localStorage mayor que 0
   this.productsMiPedido = this.productsTodos.filter(pr => {
     return this.productsLocalStNo[pr.idProducto] && this.productsLocalStNo[pr.idProducto] > 0;
   });

   // Calcular el total sumando los precios multiplicados por las cantidades
   for (let i = 0; i < this.productsMiPedido.length; i++) {
     const product = this.productsMiPedido[i];
     const cantidad = this.productsLocalStNo[product.idProducto];
     if (product && product.precio && cantidad) {
       total += product.precio * cantidad;
     }
   }

   return total;
 }

 /**
  * Este método se encarga de resetear el value de las variables del localStorage con clave con la siguiente estructura: (`quantityState-${productId}`)
  * gracias a ejecutar el método updateLocalStorageWithNewData()
  */

 resetMiPedido(): void {
   this.updateLocalStorageWithNewData();
   //localStorage.clear();
   //this.productsMiPedido = [];
   location.reload();
 }

 /**
  * Este método se encarga de, en base a los productos seleccionados en la página html, y sus respectivas cantidades, recoger todos los
  * datos de estos productos y dar de alta en la base de datos los pedidos y las comandas correspondientes, utilizando estos datos de los productos, 
  * usando los métodos de pedidoService y comandaService. 
  */

 enviarMiPedido(): void {

//Aquí calculamos la cantidad de productos del pedido
  let numeroProductos = 0;
   for (let p in this.productsLocalStNo) {
     const numeroCantidad = localStorage.getItem((`quantityState-${p}`)); 
     numeroProductos += parseInt(numeroCantidad!);
   }
//Creamos el objeto Json con los datos del pedido
   let miPedido = {
     idMesa: 1,
     cantidadProductos: numeroProductos,
     precioTotal: this.mostrarTotal(),
     fecha: new Date()
   };
//Aquí creamos el pedido, usando el método ".pedidoService.crearPedido" pasandole por parámetro nuestro objeto Json recién creado con los datos del pedido
   this.pedidoService.crearPedido(miPedido).pipe(
     tap(idPedido => {
         console.log(`ID del pedido recibido: ${idPedido}`);
         this.idDelPedido = idPedido;  // Guardamos  el valor recibido en nuestra variable de clase "idDelPedido"
     })
   )
   .subscribe(
     () => {
//Generamos las comandas
      let comandasParaBack = [];
       for (let p in this.productsLocalStNo) {
         const numeroCantidad = localStorage.getItem((`quantityState-${p}`));
         if (numeroCantidad !== null) {
//Este segundo for itera en base a la cantidad de productos
           for (let i = 0; i < parseInt(numeroCantidad!); i++) {
//Aquí se crea la comanda de cada producto
            let comanda = {
               idProducto: parseInt(p),
               idPedido: this.idDelPedido,
               idMesa: 1,
               servido: "no",
             }
//Almacenamos cada una en un array, generando un array de objetos Json
             comandasParaBack.push(comanda)
           }
         }
       }
/*Aquí creamos todas las comandas, usando el método "comandaService.crearComandas"
pasandole por parámetro nuestro array de objetos Json recién creado con los datos de las comandas
*/
       this.comandaService.crearComandas(comandasParaBack).subscribe(
         () => {},
         (error) => {
             console.error('Error al crear la comanda:', error);
         }
       );
       // Una vez enviado el pedido, limpiamos el valor de las variables del localStorage que en su clave contienen los Id de los productos dados de alta
       // Además recargamos la página y lanzamos una alerta por pantalla
       this.updateLocalStorageWithNewData();
       location.reload();
       window.alert("El pedido con sus comandas se ha dado de alta correctamente! Espere sentado mientras le traemos la comida :)");
     },
//Si falla la creación del pedido por cualquier cosa, lanzamos un error
     (error) => {
         console.error('Error al crear el pedido:', error);
     }
 );
 }
}

