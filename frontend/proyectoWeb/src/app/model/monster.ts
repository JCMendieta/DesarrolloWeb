import { MonsterType } from "./monster-type";
import { Room } from "./room";

export class Monster
{
    constructor(
        public id : number,
        public hitpoints : number,
        public idRoom : Room,
        public idMonsterType : MonsterType,
    ) {}
}
