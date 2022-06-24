import Todo from "./model";
import axios, {AxiosResponse} from "axios";

export default function getRequest(){
    return (
        axios.get('/api/kanban')
            .then((response: AxiosResponse<Todo[], any>) => response.data)
    )
}

export function getForIdRequest(id: string){
    return (
        axios.get(`/api/kanban/${id}`)
            .then((response: AxiosResponse<Todo, any>) => response.data)
    )
}

export function postRequest(input: Todo){
    return(
        axios.post(`/api/kanban/`, input)
    )
}

export function putRequest(urlInput: String, input: Todo){
    return(
        axios.put(`/api/kanban/${urlInput}`, input)
    )
}

export function deleteRequest(urlInput: String){
    return(
        axios.delete(`/api/kanban/${urlInput}`)
    )
}