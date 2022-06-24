import Todo from "./model";
import axios, {AxiosResponse} from "axios";
import {Runtime} from "inspector";

export default function getRequest(){
    return (
        axios.get('http://localhost:8080/api/kanban')
            .then((response: AxiosResponse<Todo[], any>) => response.data)
    )
}

export function getForIdRequest(id: string){
    return (
        axios.get(`http://localhost:8080/api/kanban/${id}`)
            .then((response: AxiosResponse<Todo, any>) => response.data)
    )
}

export function postRequest(input: Todo){
    return(
        axios.post(`http://localhost:8080/api/kanban/`, input)
    )
}

export function putRequest(urlInput: String, input: Todo){
    return(
        axios.put(`http://localhost:8080/api/kanban/${urlInput}`, input)
    )
}

export function deleteRequest(urlInput: String){
    return(
        axios.delete(`http://localhost:8080/api/kanban/${urlInput}`)
    )
}