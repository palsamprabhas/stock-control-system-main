import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConsumerHomeComponent } from './consumer/consumer-home/consumer-home.component';
import { ConsumersComponent } from './consumers/consumers/consumers.component';
import { HomepageComponent } from './homepage/homepage/homepage.component';
import { InventoryComponent } from './inventory/inventory/inventory.component';
import { ItemsComponent } from './items/items/items.component';
import { SigninComponent } from './signin/signin/signin.component';
import { SignoutComponent } from './signout/signout/signout.component';
import { SignupSuccessComponent } from './signup/signup-success/signup-success.component';
import { SignupComponent } from './signup/signup/signup.component';
import { SupplierHomeComponent } from './supplier/supplier-home/supplier-home.component';
import { SuppliersComponent } from './suppliers/suppliers/suppliers.component';
import { ItemRequestsComponent } from './consumer/item-requests/item-requests.component';
import { RacksComponent } from './racks/racks.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'signin' },
  { path: 'home', component: HomepageComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'signout', component: SignoutComponent },
  { path: 'items', component: ItemsComponent },
  { path: 'inventory', component: InventoryComponent },
  { path: 'suppliers', component: SuppliersComponent },
  { path: 'consumers', component: ConsumersComponent },
  { path: 'signup-success', component: SignupSuccessComponent },
  { path: 'consumer-home', component: ConsumerHomeComponent },
  { path: 'supplier-home', component: SupplierHomeComponent },
  { path: 'item-requests', component: ItemRequestsComponent },
  { path: 'racks', component: RacksComponent },
  { path: 'profile', component: ProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
