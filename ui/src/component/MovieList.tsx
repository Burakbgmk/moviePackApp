import React, {useEffect, useState} from "react";
import {Movie, User} from "../types";
import MovieCard from "./MovieCard";

function MovieList(props: {user : User, isAuthorized : boolean}) {
    const [movies,setMovies] = useState<Movie[]>([]);
    const [displayMovies, setDisplayMovies] = useState<string>("");
    const [show,setShow] = useState(false);
    const [isAuth,setIsAuth] = useState(false);
    const [currentUser,setCurrentUser] = useState(props.user);
    const [rateInput, setRateInput] = useState("");

    useEffect( () => {
        setIsAuth(props.isAuthorized);
        setCurrentUser(props.user);
        setShow(false);
        fetchMovies().catch(console.error);
    },[displayMovies, props.isAuthorized])


    const fetchMovies = async () => {
        await fetch('http://localhost:8080/movie', {
            method: 'GET',
        })
            .then((res) => res.json())
            .then((data) => {
                setMovies(data);
            })
            .catch((err) => {
                console.log(err);
            })
    }

    const deleteMovie = async (id : string) => {
        await fetch('http://localhost:8080/movie/'+id, {
            method: 'DELETE',
        })
        .catch((err) => {
            console.log(err);
        })
        await fetchMovies();
    }

    const rateMovie = async (movieId : string, rating : string) => {
        await fetch("http://localhost:8080/movie/rate",{
          method: 'POST',
            body: JSON.stringify({
                id : movieId,
                rate : rating,
            }),
            headers: {'Content-Type': 'application/json; */*'}
        })
        .catch((err) => {
            console.log(err);
        })
        await fetchMovies();

    }


    return (
        <div className="w-max flex-col items-center " >
            <div className="w-max flex items-center ">
                <input className="w-4/5 m-5 w-3/6 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500   p-1.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" onChange={e => setDisplayMovies(e.target.value)} placeholder="You can search any movie!"/>
                <button onClick={() => setShow(true)} className="m-2 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                    <span className=" relative px-10 py-1 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        Search
                    </span>
                </button>
            </div>
            <div className="flex-col items-center">
                {
                    show && displayMovies &&
                    movies.filter(movie => { return(
                        movie.name.includes(displayMovies)
                    )})
                        .filter(movie => {
                            if(isAuth == true){
                                return(
                                    movie.userId == currentUser.id
                                )
                            }
                            else return true;
                        })
                        .map(movie => { return(
                            <div className="ml-4" key={movies.indexOf(movie).toString()}>
                                <MovieCard id={movie.id}
                                           name={movie.name}
                                           description={movie.description}
                                           rating={movie.rating}
                                           releaseDate={movie.releaseDate}
                                           userId={currentUser.id}
                                           photoId={movie.photoId}
                                />
                                <div className="flex justify-center">
                                    {
                                        isAuth &&
                                        <button hidden={!isAuth} onClick={() => deleteMovie(movie.id)} className="ml-4 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                                            <span className=" relative px-10 py-1 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                                Delete
                                            </span>
                                        </button>

                                    }
                                    <input id="rate-input" type="number" max="10" min="0" onChange={e => setRateInput(e.target.value)} className="min-w-2 m-2 h-8 bg-gray-50 border border-gray-300 text-gray-900  rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"/>
                                    <button onClick={() => rateMovie(movie.id,rateInput)} className="ml-4 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                                        <span className=" relative px-10 py-1 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                            Rate
                                        </span>
                                    </button>
                                </div>
                            </div>
                        )})
                }
            </div>


        </div>

    );
}

export default MovieList;