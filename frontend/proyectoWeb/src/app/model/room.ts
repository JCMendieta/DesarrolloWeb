import { Monster } from "./monster";

export class Room 
{
    constructor(
        public id : number,
        public rItems : any,
        public idDecorativeItem : any,
        public rMonster : Monster,
        public rExits : any,
        public rPlayers : any,
    ) {}
}
