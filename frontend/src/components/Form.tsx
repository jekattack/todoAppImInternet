import './Form.css';
import {useEffect, useState} from "react";
import {postRequest} from "../service/ApiService";

interface FormProps {
    onTaskCreation: () => void;
}

export default function Form(props: FormProps) {

    const [newTodo, setNewTodo] = useState(localStorage.getItem("instanceTodo") ?? '');
    const [newDescription, setNewDescription] = useState(localStorage.getItem("instanceDescription") ?? '');
    const [errorMsg, setErrorMsg] = useState<String>('');

    useEffect(() => {localStorage.setItem("instanceTodo", newTodo)}, [newTodo])
    useEffect(() => {localStorage.setItem("instanceDescription", newDescription)}, [newDescription])

    const sendPostRequest = (e: any) => {
        e.preventDefault();
        postRequest({
            task: newTodo,
            description: newDescription
        })
            .then(() => props.onTaskCreation())
            .then(() => {
                setNewTodo('');
                setNewDescription('')
            })
            .catch(() => setErrorMsg('The Task could not be added.'));
    }

    return (
        <form onSubmit={sendPostRequest}>
            <div className="form-wrapper">
                <label htmlFor="task-input">Task:</label>
                <input type="text" id="task-input" data-testid={"task-input"} value={newTodo}
                       onChange={ev => setNewTodo(ev.target.value)}/>
                <label htmlFor="description-input">Description:</label>
                <input type="text" id="description-input" data-testid={"description-input"} value={newDescription}
                       onChange={ev => setNewDescription(ev.target.value)}/>
                <br/>
                <button id="form-send-button" type="submit" data-testid={"send-button"}>Add Todo</button>
                <br/>
                <div>{errorMsg}</div>
            </div>
        </form>
    )
}