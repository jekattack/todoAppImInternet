import Gallery from "../components/Gallery";
import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

export default function MainPage(){

    const nav = useNavigate();

    useEffect(() => {
            if(localStorage.getItem('jwt')==null){nav("/")}
        }
    , [])

    const logout = () => {
        localStorage.clear()
        nav("/")
    }


    return(
        <div>
            <Gallery />
            <button onClick={logout}>Logout</button>
        </div>
    )
}