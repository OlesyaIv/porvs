import { AbstractModel } from '../abstract-model';
import { Bet } from './bet';
import { Ask } from './ask';
import { Administrator } from '../actors/administrator';

export class Deal extends AbstractModel {
    constructor(public ask: Ask, public bet: Bet, public administrator: Administrator) {
        super();
    }
}
