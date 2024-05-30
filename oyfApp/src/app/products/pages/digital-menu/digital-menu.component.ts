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
   * Implementa el método ngOnInit(). Aquí se inicializan los productos llamando al método getProducts()
   * del servicio ProductService y se suscribe al observable devuelto para recibir los productos.
   * También se carga el estado de los checkboxes desde el almacenamiento local.
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
   * Obtiene todos los productos del servicio y actualiza el estado de los checkboxes después de obtener la respuesta.
   */
  mostrarTodos(): void {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
      this.loadQuantityStateFromLocalStorage();
    });
  }

  /**
   * Este método se llama cuando se hace clic en el botón "Veganos".
   * Obtiene los productos veganos del servicio y restaura el estado de los checkboxes después de filtrar.
   */
  filtrarPorVeganos(): void {
    this.productService.getVeganos()
    .subscribe(products => {
      this.products = products;
    });
  }

  /**
   * Este método se llama cuando se hace clic en el botón "Sin Gluten".
   * Obtiene los productos sin gluten del servicio y restaura el estado de los checkboxes después de filtrar.
   */
  filtrarPorSinGluten(): void {
    /*Esta línea llama al método getSinGluten() del servicio ProductService para obtener los productos sin gluten.
    Se suscribe al observable devuelto para recibir los productos y ejecutar una función de devolución de llamada
    cuando los datos estén disponibles.
    Dentro de la función de devolución de llamada, los productos sin gluten obtenidos del servicio se asignan
    a la propiedad products del componente, lo que actualiza la lista de productos que se muestra en la interfaz de usuario.
     */
    this.productService.getSinGluten()
    .subscribe(products => {
      this.products = products;
    });
  }

  public saveCheckQuantityLocalStorage(event: Event, productId: number): void {
    if (event.target instanceof HTMLInputElement) {
      const quantity = parseInt(event.target.value); // Obtener la cantidad del input
      // Verificar si la cantidad es válida (puedes agregar validaciones adicionales según tus necesidades)
      localStorage.setItem(`quantityState-${productId}`, quantity.toString()); // Guardar la cantidad en el almacenamiento local
      this.mostrarMiPedido();
    }
  }

  public loadQuantityStateFromLocalStorage(): void {
    /*Este es un bucle for que itera sobre cada elemento en el array products dentro de la clase.
    El bucle se ejecuta desde i = 0 hasta i < this.products.length, lo que significa que se ejecutará
    una vez por cada elemento en el array. */
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


  public updateLocalStorageWithNewData(): void {
    // Obtén los datos actuales del local storage
    for (let p in this.productsLocalStNo) {
      localStorage.setItem((`quantityState-${p}`), "0"); // Cambia 'keyForStoredData' por la clave correcta
    }
 }


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

  resetMiPedido(): void {
    localStorage.clear();
    this.productsMiPedido = [];
    location.reload();
  }

  enviarMiPedido(): void {
    let numeroProductos = 0;
    for (let p in this.productsLocalStNo) {
      const numeroCantidad = localStorage.getItem((`quantityState-${p}`)); // Cambia 'keyForStoredData' por la clave correcta
      numeroProductos += parseInt(numeroCantidad!);
    }

    let miPedido = {
      idMesa: 1,
      cantidadProductos: numeroProductos,
      precioTotal: this.mostrarTotal(),
      fecha: new Date()
    };

    this.pedidoService.crearPedido(miPedido).pipe(
      tap(idPedido => {
          console.log(`ID del pedido recibido: ${idPedido}`);
          this.idDelPedido = idPedido;  // Asigna el valor recibido a una variable de clase
      })
    )
    .subscribe(
      () => {
        let comandasParaBack = [];
        for (let p in this.productsLocalStNo) {
          const numeroCantidad = localStorage.getItem((`quantityState-${p}`)); // Cambia 'keyForStoredData' por la clave correcta
          if (numeroCantidad !== null) {
            for (let i = 0; i < parseInt(numeroCantidad!); i++) {
              let comanda = {
                idProducto: parseInt(p),
                idPedido: this.idDelPedido,
                idMesa: 1,
                servido: "no",
              }
              comandasParaBack.push(comanda)
            }
          }
        }

        this.comandaService.crearComandas(comandasParaBack).subscribe(
          () => {},
          (error) => {
              console.error('Error al crear la comanda:', error);
          }
        );
        // Una vez enviado el pedido, limpia el local storage
        this.updateLocalStorageWithNewData();
        location.reload();
        window.alert("El pedido con sus comandas se ha dado de alta correctamente! Espere sentado mientras le traemos la comida :)");
      },
      (error) => {
          console.error('Error al crear el pedido:', error);
      }
  );
  }
}
