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

    return (
        <div>Who am I: {username}</div>
    )
}