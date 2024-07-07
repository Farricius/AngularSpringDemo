import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
})
export class ProductListComponent {
  products: Product[];

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit() {
    // Load all the products first
    this.obtainProducts();
  }

  private obtainProducts() {
    // Subscribe to the observable data
    return this.productService.getProductsList().subscribe((data) => {
      this.products = data;
    });
  }

  editProduct(id: number) {
    this.router.navigate(['edit-product', id]);
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe({
      next: (data) => this.obtainProducts(),
      error: (errors) => console.log(errors),
    });
  }
}
