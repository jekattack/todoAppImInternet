import Todo, {LoginResponse} from "./model";
import axios, {AxiosResponse} from "axios";

const authHeader = {
    headers: {
        Authorization: `Bearer ${localStorage.getItem('jwt')}`
    }
}


export default function getRequest(){
    return (
        axios.get('/api/kanban', authHeader)
            .then((response: AxiosResponse<Todo[], any>) => response.data)
    )
}

export function getForIdRequest(id: string){
    return (
        axios.get(`/api/kanban/${id}`, authHeader)
            .then((response: AxiosResponse<Todo, any>) => response.data)
    )
}

export function postRequest(input: Todo){
    return(
        axios.post(`/api/kanban/`, input, authHeader)
    )
}

export function putRequest(urlInput: String, input: Todo){
    return(
        axios.put(`/api/kanban/${urlInput}`, input, authHeader)
    )
}

export function deleteRequest(urlInput: String) {
    return (
        axios.delete(`/api/kanban/${urlInput}`, authHeader)
    )
}

export function login(username: string, password: string){
    return axios.post("/api/login", {username: username, password: password}, authHeader)
        .then((response: AxiosResponse<LoginResponse>) => response.data);
}

export function register(username: string, password: string){
    return axios.post("/api/user", {username: username, password: password}, authHeader);
}
