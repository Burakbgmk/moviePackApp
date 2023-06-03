import React, {useEffect, useState} from "react";
import {Photo, User} from "../types";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;

function CreateMovie(props: {user : User}) {

    const [movieName,setMovieName] = useState("");
    const [description,setDescription] = useState("");
    const [userId, setUserId] = useState(props.user.id);
    const [isAdded,setIsAdded] = useState<boolean>();
    const [fileUpload, setFileUpload] = React.useState<any>();
    const [photo,setPhoto] = useState<Photo>();

    useEffect(() => {
        setUserId(props.user.id)
    },[props.user.id,isAdded])

    const uploadFile = async () => {
        let formData = new FormData();
        formData.append("data", fileUpload);
        await fetch('http://localhost:8080/photoz', {
            method: "POST",
            body: formData
        }).then(response => response.json())
            .then(data => {
                setPhoto(data);
                console.log(data)
            })
            .catch(error => console.log(error));
    }

    const createMovie = async () => {
        await fetch('http://localhost:8080/movie', {
            method: 'POST',
            body: JSON.stringify({
                name : movieName,
                description : description,
                userId : userId,
                photoId : photo?.id
            }),
            headers: {'Content-Type': 'application/json; */*'} })
    .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setIsAdded(true);
            })
            .catch((err) => {
                console.log(err);
            })
    }

    return (
        <div
            className="ml-4 w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
            <div className="m-7 mr-10">
                <label htmlFor="movie-name" className="ml-2 mt-2 mb-2 text-sm font-medium text-gray-900 dark:text-white">Movie Name</label>
                <input id="movie-name" type="text" onChange={(e) => setMovieName(e.target.value)} className="m-2 h-8 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"/>
                <label htmlFor="movie-description" className="ml-2 mt-2 mb-2 text-sm font-medium text-gray-900 dark:text-white" >Description</label>
                <input id="movie-description" type="text" onChange={(e) => setDescription(e.target.value)} className="m-2 h-8 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"/>
                <div className="flex">
                    <input id="fileUpload" type="file" onChange={
                        (event: React.FormEvent) => {
                            const files = (event.target as HTMLInputElement).files;
                            if (files && files.length > 0) {
                                setFileUpload(files[0]);
                            }
                        }} />
                    <button id="upload-button" onClick={uploadFile} className="h-8 ml-2 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                            <span className="min-h-8 relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                Upload
                            </span>
                    </button>
                </div>
                <button onClick={createMovie} className=" ml-2 relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
                            <span className="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                Add Movie
                            </span>
                </button>
            </div>
        </div>
    )
}

export default CreateMovie;