import { AbstractModel } from '../abstract-model';
import { Item } from './item';
import { Buyer } from '../actors/buyer';

export class Bet extends AbstractModel{
    constructor(public item: Item, public bet: number, public buyer: Buyer){
        super();
    }
}
