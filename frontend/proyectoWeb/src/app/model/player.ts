import { Room } from "./room";

export class Player 
{
    constructor(
        public id : number,
        public name : string,
        public last_updated : string,
        public attack_level : number,
        public defence_slash : number,
        public size : number,
        public hitpoints : number,
        public maxWeight : number,
        public clock : number,
        public max_time : number,
        public idRoom : Room,
        public items : any,
    ) {}
}
