import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeBuyerComponent } from './market/home-buyer/home-buyer.component';
import { HomeSellerComponent } from './market/home-seller/home-seller.component';
import { HomeAdminComponent } from './market/home-admin/home-admin.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home-buyer', component: HomeBuyerComponent },
  {path: 'home-seller', component: HomeSellerComponent},
  {path: 'home-admin', component: HomeAdminComponent},
  { path: '', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
