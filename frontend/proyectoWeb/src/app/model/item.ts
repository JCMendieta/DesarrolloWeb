import { Player } from "./player";

export class Item 
{
    constructor(
        public id : number,
        public name : string,
        public last_updated : string,
        public cost : number,
        public weight : number,
        public examine : string,
        public wiki_url : string,
        public idRoom : any,
        public idPlayer : Player,
    ) {}
}
