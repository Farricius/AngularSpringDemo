import { NgModule } from '@angular/core';
import {
  BrowserModule,
  provideClientHydration,
} from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddProductComponent } from './add-product/add-product.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './product-list/product-list.component';
import { EditProductComponent } from './edit-product/edit-product.component';

@NgModule({
  declarations: [AppComponent, ProductListComponent, AddProductComponent, EditProductComponent],
  imports: [BrowserModule, HttpClientModule, AppRoutingModule, FormsModule],
  providers: [provideClientHydration()],
  bootstrap: [AppComponent],
})
export class AppModule {}
