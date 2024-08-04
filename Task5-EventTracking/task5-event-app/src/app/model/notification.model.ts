export interface Notification{
    id:number;
    username:string;
    message:string;
    eventId:number;
    timestamp:Date;
    type:string;
    responded: boolean;
}