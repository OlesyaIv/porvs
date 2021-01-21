import { Component, OnInit } from '@angular/core';
import { Deal } from 'src/app/models/system/deal';
import { Item } from 'src/app/models/system/item';
import { Administrator } from 'src/app/models/actors/administrator';
import { ApiService } from 'src/app/service/api.service';
import {Router} from '@angular/router';
import {CreateItemComponent} from '../create-item/create-item.component';
import {ModalWindowComponent} from '../modal-window/modal-window.component';
import {Ask} from '../../models/system/ask';
import {Bet} from '../../models/system/bet';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css']
})
export class HomeAdminComponent implements OnInit {

  administrator: Administrator;
  deals: Deal[];
  items: Item[];

  constructor(public apiService: ApiService, private router: Router) {
  }

  ngOnInit() {
    this.administrator = history.state.user;
    this.apiService
      .loadDeals(this.administrator.id)
      .subscribe((deals: Deal[]) => {
        this.deals = deals;
      }, error => console.log(error));

    this.apiService
      .loadItems()
      .subscribe((items: Item[]) => {
        this.items = items;
      }, error => console.log(error));
  }

  approveDeal(deal: Deal): void {
    this.apiService.approveDeal(deal).subscribe(() => {
      this.deals = this.deals.filter((item: Deal) => {
        return item.id !== deal.id;
      });
    }, () => {
      this.deals = this.deals.filter((item: Deal) => {
        return item.id !== deal.id;
      });
    });
  }

  deleteItem(item: Item): void {
    console.log('index', item.id);
    this.apiService.deleteItem(item).subscribe(() => {
      this.items = this.items.filter(() => {
        return this.items.splice(item.id, 1);
      });
    }, () => {
        console.log('fail', item.id);
      }
    );
  }

  exit(): void {
    this.router.navigate(['/login']);
  }
}
