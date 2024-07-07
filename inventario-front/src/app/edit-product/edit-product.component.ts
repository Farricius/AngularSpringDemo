import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css',
})
export class EditProductComponent {
  product: Product = new Product();
  id: number;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    // Obtain value id from the route of my browser
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe({
      next: (data) => (this.product = data),
      error: (errors: any) => console.log(errors),
    });
  }

  onSubmit() {
    this.saveProduct();
  }

  saveProduct() {
    this.productService.editProduct(this.id, this.product).subscribe({
      next: (data) => this.goBackToProductList(),
      error: (errors) => console.log(errors),
    });
  }

  goBackToProductList() {
    this.router.navigate(['/products']);
  }
}
