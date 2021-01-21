import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { MatDialog } from '@angular/material';
import { Seller } from 'src/app/models/actors/seller';
import { Item } from 'src/app/models/system/item';
import { ModalWindowComponent } from '../modal-window/modal-window.component';
import { Ask } from 'src/app/models/system/ask';
import { Bet } from 'src/app/models/system/bet';
import {Router} from '@angular/router';
import {CreateItemComponent} from '../create-item/create-item.component';

@Component({
  selector: 'app-home-seller',
  templateUrl: './home-seller.component.html',
  styleUrls: ['./home-seller.component.css']
})
export class HomeSellerComponent implements OnInit {

  seller: Seller;
  items: Item[];
  bets: Bet[];

  constructor(private apiService: ApiService, public dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    this.seller = history.state.user;
    this.apiService
      .loadHighestBets()
      .subscribe((bets: Bet[]) => {
        this.bets = bets;
      }, error => console.log(error));
    this.apiService
      .loadItems()
      .subscribe((items: Item[]) => {
        this.items = items;
      }, error => console.log(error));
  }

  placeAsk(item: Item): void {
    let money: number;
    const dialogRef = this.dialog.open(ModalWindowComponent, {
      width: '300px',
      data: { text: "Please enter your ask", ask: money }
    });
    dialogRef.afterClosed().subscribe(result => {
      let ask = new Ask(item, result, this.seller);
      this.apiService.placeAsk(ask).subscribe(id => {
        ask.id = id;
        this.seller.asks.push(ask);
      });
    });
  }

  createItem(): void {
    const modalRef = this.dialog.open(CreateItemComponent, {
      width: '300px',
      data: {
        text: "Please enter your item name and size",
        name: '',
        server: '',
        id: ''
      }
    });
    modalRef.afterClosed().subscribe(result => {
      console.log('SAVE_ITEM', result);
      let item = new Item(result.name, result.server, result.id);
      this.apiService.createItem(item).subscribe(id => {
        item.id = result.id;
        this.items.push(item);
    });
  });
  }

  exit(): void {
    this.router.navigate(['/login']);
  }

}
