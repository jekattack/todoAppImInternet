import Todo, {LoginResponse} from "./model";
import axios, {AxiosResponse} from "axios";


export default function getRequest(){
    return (
        axios.get('/api/kanban', {headers: {Authorization: `Bearer ${localStorage.getItem('jwt')}`}})
            .then((response: AxiosResponse<Todo[], any>) => response.data)
    )
}

export function getForIdRequest(id: string){
    return (
        axios.get(`/api/kanban/${id}`, {headers: {Authorization: `Bearer ${localStorage.getItem('jwt')}`}})
            .then((response: AxiosResponse<Todo, any>) => response.data)
    )
}

export function postRequest(input: Todo){
    return(
        axios.post(`/api/kanban/`, input, {headers: {Authorization: `Bearer ${localStorage.getItem('jwt')}`}})
    )
}

export function putRequest(urlInput: String, input: Todo){
    return(
        axios.put(`/api/kanban/${urlInput}`, input, {headers: {Authorization: `Bearer ${localStorage.getItem('jwt')}`}})
    )
}

export function deleteRequest(urlInput: String) {
    return (
        axios.delete(`/api/kanban/${urlInput}`, {headers: {Authorization: `Bearer ${localStorage.getItem('jwt')}`}})
    )
}

export function login(username: string, password: string){
    return axios.post("/api/login", {username: username, password: password}, {headers: {Authorization: `Bearer ${localStorage.getItem('jwt')}`}})
        .then((response: AxiosResponse<LoginResponse>) => response.data);
}

export function register(username: string, password: string){
    return axios.post("/api/user", {username: username, password: password}, {headers: {Authorization: `Bearer ${localStorage.getItem('jwt')}`}});
}
