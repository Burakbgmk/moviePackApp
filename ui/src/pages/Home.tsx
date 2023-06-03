import React, {Dispatch, SetStateAction, useEffect, useState} from "react";
import MovieList from "../component/MovieList";
import CreateMovie from "../component/CreateMovie";
import {SetLogin, User} from "../types";
import {useNavigate} from "react-router-dom";



function Home(props: {setIsAuthorize: Dispatch<SetStateAction<boolean>>,  isAuthorized : boolean, user: User}) {
    const [currentUser,setCurrentUser] = useState(props.user);
    const navigate = useNavigate();

    useEffect(() => {
        setCurrentUser(props.user);
    },[props.user])

    return(
        <div className="flex justify-center w-full">
            <div className="bg-black flex justify-end " >
                <div>
                    {
                        props.isAuthorized &&
                        <button onClick={() => {
                            navigate("/home");
                            props.setIsAuthorize(false);
                        }} className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                Log out
                            </span>
                        </button>
                    }
                </div>
                <div>
                    {
                        !props.isAuthorized &&
                        <button onClick={() => navigate("/signup")} className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                Signup
                            </span>
                        </button>
                    }
                </div>
                <div className="flex-col bg-center m-20">
                    <div className="flex justify-center">
                        <h1 className=" mt-5 mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"><span
                            className="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">MoviePACK</span></h1>
                    </div>
                    <MovieList user={props.user} isAuthorized={props.isAuthorized}/>
                </div>
                <div>
                    {
                        props.isAuthorized && <CreateMovie user={currentUser}/>
                    }
                </div>
                <div>
                    {

                        !props.isAuthorized &&
                        <button onClick={() => navigate("/login")} className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                Login
                            </span>
                        </button>
                    }
                </div>

            </div>

        </div>
    )
}

export default Home;