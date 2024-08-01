export class Event {
    id: number;
    name: string;
    location: string;
    date: Date;
    author: string;
    participants: string[];

    constructor() {
        this.id = 0;
        this.name = '';
        this.location = '';
        this.date = new Date();
        this.author = '';
        this.participants = [];
      }
}
