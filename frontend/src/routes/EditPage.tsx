import {useEffect, useState} from "react";
import Header from "../components/Header";
import Todo from "../service/model";
import {useNavigate, useParams} from "react-router-dom";
import './EditPage.css';
import {getForIdRequest, putRequest} from "../service/ApiService";


export default function EditPage(){

    const {id} = useParams();
    const nav = useNavigate();

    const [newTask, setNewTask] = useState('');
    const [newDescription, setNewDescription] = useState('');
    const [todoToEdit, setTodoToEdit] = useState<Todo>();

    useEffect( () => {
        if(id){
            getForIdRequest(id)
                .then((todo: Todo) => {
                    setTodoToEdit(todo);
                    setNewTask(todo.task);
                    setNewDescription(todo.description)
                })
        }
    } , [id])

    const sendPutRequest = (e: any) => {
        e.preventDefault();
        putRequest('http://localhost:8080/api/kanban', {
            task: newTask,
            description: newDescription,
            status: todoToEdit?.status,
            id: todoToEdit?.id
        }).then(() => nav('/'));
    }
    
    return (
        <div className="edit-wrapper">
           <form onSubmit={sendPutRequest}>
                <div className="edit-page-wrapper">
                    <Header />
                    <div className="edit-form-wrapper">
                        <label htmlFor="edit-input">Task:</label>
                        <input type="text" id="edit-input" value={newTask} onChange={ev => setNewTask(ev.target.value)} />
                        <label htmlFor="description-input">Description:</label>
                        <input type="text" id="description-input" value={newDescription} onChange={ev => setNewDescription(ev.target.value)} />
                        <div className="edit-spacer">==============================</div>
                        <button id="edit-send-button" type={"submit"}>Edit</button>
                    </div>
                </div>
           </form>
        </div>
    )
}