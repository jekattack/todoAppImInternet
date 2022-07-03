import {FormEvent, useState} from "react";
import {login} from "../service/ApiService";
import {useNavigate} from "react-router-dom";

export default function LoginPage(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const nav = useNavigate();

    const sendLogin = (ev: FormEvent) => {
        ev.preventDefault();
        login(username, password)
            .then(response => localStorage.setItem('jwt', response.token))
            .then(() => nav('/kanban'))
    }

    return(
        <div>
            <form onSubmit={sendLogin}>
                <div className={"form-wrapper"}>
                    <label htmlFor={"username"}>Username</label>
                    <input id={"username"} type={"text"} value={username} onChange={(ev) => setUsername(ev.target.value)} />
                    <label htmlFor={"password"}>Password</label>
                    <input id={"password"} type={"password"} value={password} onChange={(ev) => setPassword(ev.target.value)} />
                    <input type={"submit"} value={"Login"}/>
                </div>
            </form>
            <a href={"/register"}>Register now!</a>
        </div>
    )

}