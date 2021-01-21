import { Ask } from '../system/ask';
import { UserStatus } from './user-status.enum';
import { User } from './user';

export class Seller extends User {
    asks: Ask[];

    constructor(id: number, username: string, password: string,
                userStatus: UserStatus, asks: Ask[]) {
        super(username, password, id);
        this.userStatus = userStatus;
        this.asks = asks;
    }
}
