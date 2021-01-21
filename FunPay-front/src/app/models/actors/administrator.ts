import { User } from './user';
import { UserStatus } from './user-status.enum';
import { Deal } from '../system/deal';

export class Administrator extends User {
    deals: Deal[];

    constructor(id: number, username: string, password: string,
                userStatus: UserStatus, deals: Deal[]) {
        super(username, password, id);
        this.userStatus = userStatus;
        this.deals = deals;
    }
}
