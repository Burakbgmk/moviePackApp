import {type} from "os";
import React, {Dispatch, SetStateAction, useState} from "react";
import {User} from "../types";
import {useNavigate} from "react-router-dom";

interface IProps {
    // myVar: boolean;
    setLogin: Dispatch<SetStateAction<boolean>>;
    setCurrentUser: Dispatch<SetStateAction<User>>;
}
function Signup() {

    const navigate = useNavigate();

    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");

    type signUser = {
        username: string,
        password: string
    }

    const signup = async () => {
        await fetch('http://localhost:8080/signup', {
            method: 'POST',
            body: JSON.stringify({
                username : username,
                password : password
            }),
            headers: {'Content-Type': 'application/json'} })
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                navigate("/home")
            })
            .catch((err) => {
                console.log(err);
            })
    }

    return(
        <div className="flex justify-center">
            <div className="bg-black flex flex-col items-center " >
                <div
                    className="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <div className="m-7 mr-10">
                        <h1 className="ml-2 mt-5 mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"><span
                            className="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">MoviePACK</span></h1>
                        <label htmlFor="username-input" className="ml-2 mt-2 mb-2 text-sm font-medium text-gray-900 dark:text-white">Username</label>
                        <input id="username-input" onChange={e => setUsername(e.target.value)} className="m-2 h-8 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"/>
                        <label htmlFor="password-input" className="ml-2 mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                        <input id="password-input" onChange={e => setPassword(e.target.value)} className="ml-2 mb-4 h-8 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"/>
                        <button onClick={signup}className="ml-2 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                                <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                    Sign Up
                                </span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Signup;