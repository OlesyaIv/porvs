import { AbstractModel } from '../abstract-model';
import { Item } from './item';
import { Seller } from '../actors/seller';

export class Ask extends AbstractModel {
    constructor(public item: Item, public ask: number, public seller: Seller) {
        super();
    }
}
