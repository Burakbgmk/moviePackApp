import React, {useEffect, useState} from 'react';
import './App.css';
import MovieList from "./component/MovieList";
import CreateMovie from "./component/CreateMovie";
import {BrowserRouter, Route, Routes, Navigate} from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import {User} from "./types";



function App() {
    const [isLoggedIn,setIsLoggedIn] = useState<boolean>(false);
    const [currentUser,setCurrentUser] = useState<User>(Object);

    useEffect(() => {
        // if(isLoggedIn == false) setCurrentUser(Object);
        console.log("User: "+currentUser.username);
    },[isLoggedIn,currentUser])


    return (
        <div className="bg-black w-full flex justify-center content-center" >
            <BrowserRouter>
                <Routes>
                    <Route path="/*" element={ <Navigate to="/home" /> } />
                    <Route path="/login" element={<Login setLogin={setIsLoggedIn} setCurrentUser={setCurrentUser}/>}/>
                    <Route path="/signup" element={<Signup />} />
                    <Route path="/home" element={<Home setIsAuthorize={setIsLoggedIn} isAuthorized={isLoggedIn} user={currentUser} />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
