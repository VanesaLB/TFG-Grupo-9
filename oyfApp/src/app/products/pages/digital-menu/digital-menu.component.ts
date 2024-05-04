import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../interfaces/product.interface';
import { ProductService } from '../../services/product.service';

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
  @Input()
  public product!: Product;

  public products: Product[] = []; //Array products para almacenar los productos
  public isChecked: boolean[] = []; // Array para mantener el estado de los checkboxes

  //Declara un objeto checkboxStates para almacenar los estados de los checkboxes por ID de producto
  public checkboxStates: { [productId: number]: boolean } = {};
  //Declara un objeto isCheckedMap para almacenar el estado de los checkboxes por idProducto.
  public isCheckedMap: { [idProducto: number]: boolean } = {};

  //Inyectamos el ProductService que hemos importado en el constructor del componente
  constructor( private productService: ProductService ){ }

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
          this.loadCheckboxStateFromLocalStorage();
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
      this.loadCheckboxStateFromLocalStorage();
    });
  }

  /**
   * Este método se llama cuando se hace clic en el botón "Veganos".
   * Obtiene los productos veganos del servicio y restaura el estado de los checkboxes después de filtrar.
   */
  filtrarPorVeganos(): void {
    // Mantener una copia de los estados de los checkboxes antes de filtrar
    const prevCheckboxState = this.isChecked.slice(0);

    this.productService.getVeganos()
    .subscribe(products => {
      this.products = products;
      // Restaurar los estados de los checkboxes después de filtrar
      this.isChecked = prevCheckboxState;
    });
  }

  /**
   * Este método se llama cuando se hace clic en el botón "Sin Gluten".
   * Obtiene los productos sin gluten del servicio y restaura el estado de los checkboxes después de filtrar.
   */
  filtrarPorSinGluten(): void {
    //Esta línea crea una copia del estado actual de los checkboxes antes de aplicar el filtro por sin gluten.
    //Se utiliza slice(0) para realizar una copia profunda de la matriz isChecked.
    const prevCheckboxState = this.isChecked.slice(0);

    /*Esta línea llama al método getSinGluten() del servicio ProductService para obtener los productos sin gluten.
    Se suscribe al observable devuelto para recibir los productos y ejecutar una función de devolución de llamada
    cuando los datos estén disponibles.
    Dentro de la función de devolución de llamada, los productos sin gluten obtenidos del servicio se asignan
    a la propiedad products del componente, lo que actualiza la lista de productos que se muestra en la interfaz de usuario.
     */
    this.productService.getSinGluten()
    .subscribe(products => {
      this.products = products;

      // Restaurar los estados de los checkboxes después de filtrar
      /*Después de actualizar la lista de productos, se restaura el estado anterior de los checkboxes asignando
      la copia previamente almacenada de los estados de los checkboxes (prevCheckboxState) a la matriz isChecked.
      Esto asegura que los checkboxes mantengan su estado previo después de aplicar el filtro por sin gluten. */
      this.isChecked = prevCheckboxState;
    });
  }

  /**
   * Este método se llama cuando se cambia el estado de un checkbox.
   * Guarda el estado del checkbox en el almacenamiento local utilizando el idProducto como clave.
   * @param event, que representa el evento de cambio que activó la función (change)
   * @param productId, el identificador del producto asociado al checkbox
   */
  public saveCheckLocalStorage(event: Event, productId: number): void {
    /*Esta línea verifica si el objetivo del evento (event.target) es una instancia de un elemento
    de entrada HTML (HTMLInputElement). Esto es importante para asegurarse de que el evento provenga
    de un checkbox, ya que este método está diseñado para manejar eventos de cambio de checkboxes. */
    if (event.target instanceof HTMLInputElement) {
      /*Se obtiene el estado del checkbox que generó el evento de cambio. La propiedad checked
      de un elemento de entrada HTML indica si el checkbox está marcado o desmarcado. */
      const checked = event.target.checked;
      /*Esta línea guarda el estado del checkbox en el almacenamiento local del navegador.
      Utiliza el método setItem del objeto localStorage para almacenar el estado del checkbox con una clave
      única que incluye el identificador del producto (productId). Antes de guardarlo, se convierte
      el valor booleano checked a una cadena de texto utilizando JSON.stringify(). */
      localStorage.setItem(`checkboxState-${productId}`, JSON.stringify(checked));
      /*Se actualiza el objeto isCheckedMap, que mantiene el estado de los checkboxes por productId,
      con el estado actual del checkbox. Esto es útil para mantener el estado de los checkboxes en memoria
      y actualizar la interfaz de usuario en consecuencia. */
      this.isCheckedMap[productId] = checked;
    }
  }

  /**
   * Este método carga los estados de los checkboxes desde el almacenamiento local y los asigna
   * al objeto isCheckedMap utilizando el idProducto como clave.
   */
  loadCheckboxStateFromLocalStorage(): void {
    /*Este es un bucle for que itera sobre cada elemento en el array products dentro de la clase.
    El bucle se ejecuta desde i = 0 hasta i < this.products.length, lo que significa que se ejecutará
    una vez por cada elemento en el array. */
    for (let i = 0; i < this.products.length; i++) {
      /*En cada iteración del bucle, se obtiene el identificador único (idProducto) del producto actual
      en la posición i del array products y se almacena en la variable productId. */
      const productId = this.products[i].idProducto;
      /*Se utiliza el método getItem del objeto localStorage para recuperar el estado guardado del checkbox
      para el producto actual. Se utiliza la clave única generada concatenando 'checkboxState-'
      con el productId del producto actual. */
      const savedState = localStorage.getItem(`checkboxState-${productId}`);
      /*Aquí se asigna el estado recuperado del checkbox al objeto isCheckedMap. Si savedState es una cadena
      no nula y no vacía (es decir, si el estado del checkbox fue guardado anteriormente en el almacenamiento
      local), se utiliza JSON.parse() para convertir la cadena en un valor booleano. Este valor booleano se
      asigna a isCheckedMap[productId]. Si savedState es nulo o vacío, se asigna false al isCheckedMap[productId]. */
      this.isCheckedMap[productId] = savedState ? JSON.parse(savedState) : false;
    }
  }

}
