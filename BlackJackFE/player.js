class Player {
    name = "Játékos";
    bankRoll = 5000;
    
    hand = [];
    points = 0;
    bet = 0;   
    stack = 0;
    doubleDownNumOfCards = 0;

    constructor(name, bankRoll){
        if (name) {
            this.name = name;
        } else {
            this.name = this.name + (Math.random()*100).toFixed;
        }
        if (bankRoll) { 
            this.bankRoll = bankRoll
        };
        this.stack = this.bankRoll;
    }

    countPoints(){
        let cardValues = [];
        this.points = 0;
        this.hand.map(card => {
            if((ranks[card.rank] == 11) & (this.points + 11 > 21)){
                this.points += 1;
                cardValues.push(1);
            } else {
                this.points += ranks[card.rank];
                cardValues.push(ranks[card.rank]);
            } 
            if(this.points > 21){
                let ace = cardValues.findIndex(value => value == 11);
                if(ace > -1){
                    cardValues[ace] = 1;
                    this.points -=10;
                }
            }
        });
    }

    raiseBet(amount){
        if(this.stack >= amount) {
            this.bet += amount;
            this.stack -= amount;
        } else {
            this.bet += this.stack;
            this.stack = 0;
        }
    }

    nextCard(card){
        this.hand.push(card);
        this.countPoints();
    }

    deal(cards){
        this.hand = cards;
        this.countPoints();
    }

    newHand(){
        this.bet = 0;
        this.hand = [];
        this.points = 0;
        this.doubleDownNumOfCards = 0;
    }

    newGame(){        
        this.newHand();
        this.stack = this.bankRoll;
    }

    doubleDown(){
        this.raiseBet(this.bet);
        this.doubleDownNumOfCards = this.hand.length + 1;
    }

    win(amount){
        this.stack += amount;
    }
}