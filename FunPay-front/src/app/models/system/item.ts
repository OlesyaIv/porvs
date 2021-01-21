import { AbstractModel } from '../abstract-model';

export class Item extends AbstractModel {
    constructor(public name: string, public server: string, public id?: number) {
        super(id);
    }
}
