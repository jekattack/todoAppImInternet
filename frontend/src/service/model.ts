export default interface Todo {
    id?: string;
    status?: string;
    task: string;
    description: string;
}

export interface LoginResponse {
    token: string;
}