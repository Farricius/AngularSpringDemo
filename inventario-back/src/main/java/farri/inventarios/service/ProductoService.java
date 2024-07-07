package farri.inventarios.service;
import farri.inventarios.model.Producto;
import farri.inventarios.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listProducts() {
        return this.productoRepository.findAll();
    }

    @Override
    public Producto searchProductById(Integer idProduct) {
        Producto producto = this.productoRepository.findById(idProduct).orElse(null);
        return producto;
    }

    @Override
    public Producto saveProduct(Producto producto) {
        return this.productoRepository.save(producto);
    }

    @Override
    public void deleteProductById(Integer idProduct) {
    this.productoRepository.deleteById(idProduct);
    }
}
