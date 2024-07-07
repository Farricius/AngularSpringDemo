package farri.inventarios.controller;

import farri.inventarios.exception.ResourceNotFoundException;
import farri.inventarios.model.Producto;
import farri.inventarios.service.ProductoService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")

public class ProductoController {
    private static final Logger logger =
            LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    //http://localhost:8080/inventarios-app/products
    @GetMapping("/products")
    public List<Producto> getProducts() {
        List<Producto> products = this.productoService.listProducts();
        logger.info("Productos obtenidos:");
        products.forEach((product -> logger.info(product.toString())));
        return products;
    }

    @PostMapping("/products")
    public Producto addProduct(@RequestBody Producto producto) {
        logger.info("Product to add: " + producto);
        return this.productoService.saveProduct(producto);
    }

    @GetMapping("/products/{id}")
        public ResponseEntity<Producto> getProductById(@PathVariable int id) {
        Producto producto = this.productoService.searchProductById(id);
        if(producto !=null)
            return ResponseEntity.ok(producto);
        else
            throw new ResourceNotFoundException("Not found with that ID!");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Producto> updateProduct (
            @PathVariable int id,
            @RequestBody Producto productoRecibido)
    {
        Producto producto = this.productoService.searchProductById(id);
        if(producto == null)
            throw new ResourceNotFoundException("Not Found!");
        producto.setDescription(productoRecibido.getDescription());
        producto.setPrice(productoRecibido.getPrice());
        producto.setStock(productoRecibido.getStock());
        this.productoService.saveProduct(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>>
            deleteProduct (@PathVariable int id) {
        Producto producto = productoService.searchProductById(id);
        if (producto == null)
            throw new ResourceNotFoundException("Not found with that ID! " + id);
        this.productoService.deleteProductById(producto.getIdProduct());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}