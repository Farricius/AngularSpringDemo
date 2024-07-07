package farri.inventarios.service;

import farri.inventarios.model.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listProducts();

    public Producto searchProductById (Integer idProduct);

    public Producto saveProduct (Producto producto);

    public void deleteProductById(Integer idProduct);
}
