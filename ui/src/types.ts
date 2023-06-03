import React from "react";

export type Movie = {
    id: string
    name: string;
    description: string;
    rating: number;
    releaseDate: string;
    userId: string,
    photoId: string

}
export type SetLogin = {
    setState: (val: boolean) => void;
}

export type User = {
    id: string,
    username: string,
    role: string,
}

export type Photo = {
    id: string,
    data: string,
    contentType: string

}