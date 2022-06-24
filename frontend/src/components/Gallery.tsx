import './Gallery.css';
import './GalleryColumn.css';
import GalleryColumn from "./GalleryColumn";
import React, {useEffect, useState} from "react";
import Todo from "../service/model";
import Form from "./Form";
import getRequest from "../service/ApiService";

export default function Gallery() {

    const [todos, setTodos] = useState<Todo[]>([]);
    const [errorMsg, setErrorMsg] = useState<String>("");

    useEffect(() => {
        fetchTasks()
    }, []);

    const openTodos = todos.filter(todo => todo.status === "OPEN")
    const inProgressTodos = todos.filter(todo => todo.status === "IN_PROGRESS")
    const doneTodos = todos.filter(todo => todo.status === "DONE")

    const fetchTasks = () => {
        getRequest().then((todos: Todo[]) => {
            setTodos(todos)
        })
            .catch(() => setErrorMsg('The Tasks could not be loaded.'));
    }


    return (
        <div>
            <div className={"error-wrapper"}>
                {errorMsg}
            </div>
            <div className="form-wrapper">
                <Form onTaskCreation={fetchTasks}/>
            </div>
            <div className="gallery-column-wrapper">
                <div className="single-column-wrapper">
                    <div>OPEN</div>
                    <GalleryColumn todos={openTodos} onTaskChange={fetchTasks} data-testid={"open-column"}/>
                </div>
                <div className="single-column-wrapper">
                    <div>IN PROGRESS</div>
                    <GalleryColumn todos={inProgressTodos} onTaskChange={fetchTasks} data-testid={"in-progress-column"}/>
                </div>
                <div className="single-column-wrapper">
                    <div>DONE</div>
                    <GalleryColumn todos={doneTodos} onTaskChange={fetchTasks} data-testid={"done-column"}/>
                </div>
            </div>
        </div>
    )
}