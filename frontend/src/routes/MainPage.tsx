import Gallery from "../components/Gallery";
import React, {useEffect} from "react";
import {useNavigate} from "react-router-dom";

export default function MainPage(){

    const nav = useNavigate();

    useEffect(() => {
            if(localStorage.getItem('jwt')==null){
                backToLogin()
            }
        }
    , [])

    const logout = () => {
        localStorage.clear()
        backToLogin()
    }

    const backToLogin = () => {
        return nav("/")
    }


    return(
        <div>
            <Gallery />
            <button onClick={logout}>Logout</button>
        </div>
    )
}