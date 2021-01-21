import { Bet } from '../system/bet';
import { User } from './user';
import { UserStatus } from './user-status.enum';
import { Order } from '../system/order';

export class Buyer extends User {
    bets: Bet[];

    constructor(id: number, username: string, password: string,
                userStatus: UserStatus, bets: Bet[], public orders: Order[]) {
        super(username, password, id);
        this.userStatus = userStatus;
        this.orders = orders;
        this.bets = bets;
    }
}
