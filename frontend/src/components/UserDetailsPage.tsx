import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { getUsername } from "../service/ApiService"

export default function UserDetails() {

    const [username, setUsername] = useState('');

    const nav = useNavigate();

    useEffect(() => {
        getUsername()
            .then(user => setUsername(user))
            .catch(() => nav('/'))
    }, [nav])

    const continueToKanban = () => {
        nav("/kanban")
    }

    return (
        <div>
            <div>You are logged in as: {username}</div>
            <button onClick={continueToKanban}>Continue to Kanban-Board</button>
        </div>
    )
}