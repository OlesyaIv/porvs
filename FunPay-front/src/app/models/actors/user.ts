import { UserStatus } from './user-status.enum';
import { AbstractModel } from '../abstract-model';

export class User extends AbstractModel {
    username: string;
    password: string;
    userStatus?: UserStatus;

    constructor(username: string, password: string, id?: number) {
        super(id);
        this.username = username;
        this.password = password;
    }
}
