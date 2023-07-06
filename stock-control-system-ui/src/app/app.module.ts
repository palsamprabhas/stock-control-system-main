import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { CDBFreeModule } from 'ng-cdbangular';
import { MenuComponent } from './menu/menu/menu.component';
import { HomepageComponent } from './homepage/homepage/homepage.component';
import { SigninComponent } from './signin/signin/signin.component';
import { HeaderComponent } from './header/header/header.component';
import { SignupComponent } from './signup/signup/signup.component';
import { ItemsComponent } from './items/items/items.component';
import { SuppliersComponent } from './suppliers/suppliers/suppliers.component';
import { ConsumersComponent } from './consumers/consumers/consumers.component';
import { SignoutComponent } from './signout/signout/signout.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InventoryComponent } from './inventory/inventory/inventory.component';
import { SignupSuccessComponent } from './signup/signup-success/signup-success.component';
import { ConsumerHomeComponent } from './consumer/consumer-home/consumer-home.component';
import { SupplierHomeComponent } from './supplier/supplier-home/supplier-home.component';
import { AngularMaterialModule } from './angular-material.module';
import { NgChartsModule } from 'ng2-charts';
import { ItemRequestsComponent } from './consumer/item-requests/item-requests.component';
import { RacksComponent } from './racks/racks.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HomepageComponent,
    SigninComponent,
    HeaderComponent,
    SignupComponent,
    ItemsComponent,
    SuppliersComponent,
    ConsumersComponent,
    SignoutComponent,
    InventoryComponent,
    SignupSuccessComponent,
    ConsumerHomeComponent,
    SupplierHomeComponent,
    ItemRequestsComponent,
    RacksComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    CDBFreeModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AngularMaterialModule,
    NgChartsModule,
    NgSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
