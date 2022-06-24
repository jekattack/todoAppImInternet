import './GalleryItem.css';
import Todo from "../service/model";
import {useNavigate} from "react-router-dom";
import {deleteRequest, putRequest} from "../service/ApiService";
import {useState} from "react";

interface GalleryItemProps{
    todo: Todo;
    onTaskChange: () => void;
}

export default function GalleryItem(props: GalleryItemProps) {

    const nav = useNavigate();

    const [errorMsg, setErrorMsg] = useState<String>("");

    const sendPutNext = () => {
        putRequest("next", props.todo)
            .then(() => props.onTaskChange())
            .catch(() => setErrorMsg('Status change failed.'));
    }
    const sendPutPrev = () => {
        putRequest("prev", props.todo)
            .then(() => props.onTaskChange())
            .catch(() => setErrorMsg('Status change failed.'));
    }
    const sendDelete = () => {
        deleteRequest(props.todo.id!)
            .then(() => props.onTaskChange())
            .catch(() => setErrorMsg('Deleting task failed.'));
    }

    return (
        <div className="gallery-item-border-wrapper">
            <div className="gallery-item-wrapper">
                <div className="todo-info-wrapper">
                    {props.todo.task}
                </div>
                <div className="todo-info-wrapper">
                    {props.todo.description}
                </div>
                <div className="todo-button-wrapper">
                    {
                        (props.todo.status !== "OPEN") &&
                        <button onClick={sendPutPrev} data-testid={"prev-button"}>Prev</button>
                    }
                        <button onClick={()=>nav(`/${props.todo.id}`)} data-testid={"edit-button"}>Edit</button>
                    {
                        (props.todo.status !== "DONE") &&
                        <button onClick={sendPutNext} data-testid={"next-button"}>Next</button>
                    }
                    {
                        (props.todo.status === "DONE") &&
                        <button onClick={sendDelete} data-testid={"delete-button"}>Delete</button>
                    }
                </div>
            </div>
            <br/>
            <div>{errorMsg}</div>
        </div>
    );
}