import {FormEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import {register} from "../service/ApiService";

export default function RegisterPage(){

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const nav = useNavigate();

    const createUser = (ev: FormEvent) => {
        ev.preventDefault();
        register(username, password)
            .then(() => nav('/'))
    }

    return(
        <div className={"form-wrapper"}>
            <form onSubmit={createUser}>
                <div className={"form-wrapper"}>
                    <label htmlFor={"username"}>Username</label>
                    <input id={"username"} type={"text"} value={username} onChange={(ev) => setUsername(ev.target.value)}/>
                    <label htmlFor={"password"}>Password</label>
                    <input id={"password"} type={"password"} value={password} onChange={(ev) => setPassword(ev.target.value)}/>
                    <input type={"submit"} value={"Register"}/>
                </div>
            </form>
            <a href={"/register"}>Already registered?</a>
        </div>
    )

}