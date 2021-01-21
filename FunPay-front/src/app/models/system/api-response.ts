import { User } from '../actors/user';

export interface ApiResponse {
    status: number,
    message: string,
    result: User
}
