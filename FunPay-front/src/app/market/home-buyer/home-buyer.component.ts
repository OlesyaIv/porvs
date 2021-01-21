import {Component, OnInit} from '@angular/core';
import {ApiService} from 'src/app/service/api.service';
import {Item} from 'src/app/models/system/item';
import {Buyer} from 'src/app/models/actors/buyer';
import {Bet} from 'src/app/models/system/bet';
import {MatDialog} from '@angular/material';
import {ModalWindowComponent} from '../modal-window/modal-window.component';
import {Ask} from 'src/app/models/system/ask';
import {Order} from 'src/app/models/system/order';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home-buyer',
  templateUrl: './home-buyer.component.html',
  styleUrls: ['./home-buyer.component.css']
})
export class HomeBuyerComponent implements OnInit {

  buyer: Buyer;
  items: Item[];
  asks: Ask[];

  public tabs: Array<string> = ['ITEMS', 'BETS', 'ORDERS'];
  public currentTab: string;

  constructor(private apiService: ApiService, public dialog: MatDialog, private router: Router) {
  }

  ngOnInit() {
    this.currentTab = this.tabs[0];
    this.buyer = history.state.user;
    this.apiService
      .loadLowestAsks()
      .subscribe((asks: Ask[]) => {
        this.asks = asks;
      }, error => console.log(error));
    this.apiService
      .loadItems()
      .subscribe((items: Item[]) => {
        this.items = items;
      }, error => console.log(error));
    this.apiService
      .loadOrders(this.buyer.id)
      .subscribe((orders: Order[]) => {
        this.buyer.orders = orders;
      }, error => console.log(error));
  }

  changeTab(tab: string) {
    this.currentTab = tab;
  }

  placeBet(item: Item): void {
    let money: number;
    const dialogRef = this.dialog.open(ModalWindowComponent, {
      width: '300px',
      data: {text: 'Please enter your bet', bet: money}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let bet = new Bet(item, result, this.buyer);
        this.apiService.placeBet(bet).subscribe(id => {
          bet.id = id;
          this.buyer.bets.push(bet);
        });
      }
    });
  }

  exit(): void {
    this.router.navigate(['/login']);
  }
}
