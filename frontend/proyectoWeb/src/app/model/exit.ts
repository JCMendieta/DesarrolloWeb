import { Room } from "./room";

export class Exit 
{
    constructor(
        public id : number,
        public idFRoom : Room,
        public idSRoom : Room,
    ) {}
}