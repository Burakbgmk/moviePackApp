import React, {Component, useEffect, useState} from 'react';
import {Movie, Photo} from '../types';
function MovieCard(movie : Movie) {


    const [photo,setPhoto] = useState<Photo>();

    const fetchImage = async () => {
        await fetch('http://localhost:8080/photoz/'+movie.photoId, {
            method: 'GET',
        })
            .then((res) => res.json())
            .then((data) => {
                setPhoto(data);
            })
            .catch((err) => {
                console.log(err);
            })
    }

    useEffect(() => {
        fetchImage();
    },[])


    return (
        <div
            className="m-4 min-h-60 min-w-50 max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
            <div className="m-7 mr-10">
                <h1 className="mt-5 mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-2xl lg:text-3xl"><span
                    className="text-transparent bg-clip-text bg-gradient-to-r to-fuchsia-400 from-sky-400">{movie.name}</span></h1>
                <div>
                    <h5 className="font-bold dark:text-white">Description:<small
                        className="ml-2 font-semibold text-gray-500 dark:text-gray-400">{movie.description}</small>
                    </h5>
                    <h5 className="font-bold dark:text-white">Release Date:<small
                        className="ml-2 font-semibold text-gray-500 dark:text-gray-400">{movie.releaseDate}</small>
                    </h5>
                    <h5 className="font-bold dark:text-white">Rating:<small
                        className="ml-2 font-semibold text-gray-500 dark:text-gray-400">{movie.rating}</small>
                    </h5>
                </div>
                <div>
                    <img src={`data:${photo?.contentType};base64,${photo?.data}`} />
                </div>
            </div>
        </div>
    )
}

export default MovieCard;
