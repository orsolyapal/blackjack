const colors = ["red", "black"];
const suits = ["♥", "♠", "♦", "♣"];
const ranks = {
    "2":2,
    "3":3,
    "4":4,
    "5":5,
    "6":6,
    "7":7,
    "8":8,
    "9":9,
    "10":10,
    "J":10,
    "D":10,
    "K":10,
    "A":11 
};

const deck = [];

const maxNumOfCards = 11;
const minimumBet = 100;
const bankRoll = 1000;

const humanPlayer = new Player("Humán", bankRoll);
const dealerPlayer = new Player("Osztó", bankRoll);

const btnSettings = document.getElementById("btn-settings");

function createDeck(){
    deck.splice(0,deck.length);
    let rankNames = Object.keys(ranks);
    for(suit in suits){
        for(rank in rankNames){
            let card = {
                    "suit": suits[suit],
                    "rank": rankNames[rank]
                };
            deck.push(card);
        }
    }
}

function shuffle(){
    for(let i = 0; i < 51; i++){
        let j = (Math.random()*51).toFixed(0);
        let temp = deck[j];
        deck[j] = deck[i];
        deck[i] = temp;
    }
}

function deal(){
    if(deck.length < 4){
        createDeck();
        shuffle();
    }    
    humanPlayer.deal(deck.splice(0,2));
    dealerPlayer.deal(deck.splice(0,2));
}

function newGame(){
    let savedPlayerName = localStorage.getItem("settings_player_name");
    displayInfo("player", "name", savedPlayerName?savedPlayerName:"Játékos");
    displayInfo("player", "gameover", "");    
    humanPlayer.newGame();
    dealerPlayer.newGame();
    newHand();    
}

function newHand(){
    document.getElementById("btn-newhand").disabled=true;
    const player = document.getElementById("player");
    const playerCardBox = player.querySelector(".card-box");
    playerCardBox.innerHTML = "";
    playerCardBox.classList.add("hide-box");
    const dealer = document.getElementById("dealer");
    const dealerCardBox = dealer.querySelector(".card-box");
    dealerCardBox.innerHTML = "";
    const playerBetBox= player.querySelector(".bet-box");
    playerBetBox.classList.remove("hide-box");
    
    humanPlayer.newHand();
    dealerPlayer.newHand();

    displayInfo("player", "points", "Pontszám: ");
    displayInfo("dealer", "points", "Pontszám: ");
    displayInfo("player", "bet", "Tét: ");
    displayInfo("dealer", "bet", "Tét: ");
    displayInfo("player", "result", "");    
    displayInfo("player", "stack", "Bank: " + humanPlayer.stack);
    displayInfo("dealer", "stack", "Bank: " + dealerPlayer.stack);

    document.getElementById("btn-hit").disabled=true;
    document.getElementById("btn-stand").disabled=true;
    document.getElementById("btn-split").disabled=true;
    document.getElementById("btn-double").disabled=true;
}

function placeBet(){
    const betInput = document.getElementById("bet-amount");
    const betAmount = Number(betInput.value);
    if (betAmount < minimumBet || betAmount > humanPlayer.stack) {
        alert("Kérlek, érvényes tétet adj meg! (" + minimumBet + " és " + humanPlayer.stack + " közötti érték. )");
        return;
    }
    
    humanPlayer.raiseBet(betAmount);
    dealerPlayer.raiseBet(Number ((Math.random()*100+100).toFixed()));

    
    displayInfo("player", "bet", "Tét: " + humanPlayer.bet);
    displayInfo("dealer", "bet", "Tét: " + dealerPlayer.bet);

    displayInfo("player", "stack", "Bank: " + humanPlayer.stack);
    displayInfo("dealer", "stack", "Bank: " + dealerPlayer.stack);

    const player = document.getElementById("player");
    const playerBetBox= player.querySelector(".bet-box");
    playerBetBox.classList.add("hide-box");   
    const playerCardBox = player.querySelector(".card-box");
    playerCardBox.classList.remove("hide-box");

    document.getElementById("btn-hit").disabled=false;
    document.getElementById("btn-stand").disabled=false;
    document.getElementById("btn-split").disabled=true;
    document.getElementById("btn-double").disabled=false;

    start();
}

function start(){
    createDeck();
    shuffle();
    deal();
    displayHands();
    displayInfo("player", "points", "Pontszám: " + humanPlayer.points);
    displayInfo("dealer", "points", "");
}

function hit(){
    if(canDoubleDown(humanPlayer)){
        document.getElementById("btn-double").disabled=true;  
    }
    if(canGetCard(humanPlayer)){
        if(deck.length <=1){
            createDeck();
            shuffle();
        }
        humanPlayer.nextCard((deck.splice(0,1))[0]);
        displayPlayerHand();
        displayInfo("player", "points", "Pontszám: " + humanPlayer.points);
    } 
    if(!canGetCard(humanPlayer)){
        stand();
    }    
}

function canGetCard(who){
    return who.hand.length < (who.doubleDownNumOfCards ? who.doubleDownNumOfCards : maxNumOfCards);
}

function doubleDown(){
    if (canDoubleDown(humanPlayer)) {
        humanPlayer.doubleDown();
        displayInfo("player", "bet", "Tét: " + humanPlayer.bet);
        displayInfo("player", "stack", "Bank: " + humanPlayer.stack);
    }    
    document.getElementById("btn-double").disabled=true;  
}

function canDoubleDown(who){
    return (!who.doubleDownNumOfCards && who.hand.length == 2 && who.points <21);
}

function stand(){
    while(dealerPlayer.points < 16){
        if(deck.length <=1){
            createDeck();
            shuffle();
        }
        dealerPlayer.nextCard((deck.splice(0,1))[0]);
    }
    finishGame();
}

function finishGame(){
    
    displayDealerHand(true);
    displayInfo("dealer", "points", "Pontszám: " + dealerPlayer.points);

    displayInfo("player", "result", getFinalResult());
    displayInfo("dealer", "stack", "Bank: " + dealerPlayer.stack);    
    displayInfo("player", "stack", "Bank: " + humanPlayer.stack);    

    if (gameOver()){ 
        if(humanPlayer.stack < minimumBet){
            displayInfo("player", "gameover", "Vége a játéknak! " + "<br>" + "Az osztó győzőtt.");    
        } else {
            displayInfo("player", "gameover", "Vége a játéknak! " + "<br>" + "GYŐZTÉL!");    
        }
        
    } else {
        document.getElementById("btn-newhand").disabled=false;
    }

    document.getElementById("btn-hit").disabled=true;
    document.getElementById("btn-stand").disabled=true;
    document.getElementById("btn-split").disabled=true;
    document.getElementById("btn-double").disabled=true;    
}

function getFinalResult(){
    if(humanPlayer.points == dealerPlayer.points || (humanPlayer.points>21 & dealerPlayer.points>21)) {
        humanPlayer.win(humanPlayer.bet);
        dealerPlayer.win(dealerPlayer.bet);
        return "DÖNTETLEN";
    }
    
    if(humanPlayer.points>21){
        dealerPlayer.win(humanPlayer.bet + dealerPlayer.bet);
        return "VESZTETTÉL"; 
    }else if(dealerPlayer.points>21){
        humanPlayer.win(humanPlayer.bet + dealerPlayer.bet);
        return "NYERTÉL"; 
    }else{
        if(humanPlayer.points>dealerPlayer.points){
            humanPlayer.win(humanPlayer.bet + dealerPlayer.bet);
            return "NYERTÉL";
        }else{
            dealerPlayer.win(humanPlayer.bet + dealerPlayer.bet);
            return "VESZTETTÉL";
        }
    }
}

function gameOver(){
    if(humanPlayer.stack < minimumBet || dealerPlayer.stack < minimumBet) {
        return true;
    } else {
        return false;
    }
}

function displayHands(){
    displayPlayerHand();
    displayDealerHand();
}

function displayInfo(whose, infoType, content){
    const player = document.getElementById(whose);
    const playerInfoBox = player.querySelector("#"+whose+"-"+infoType);
    playerInfoBox.innerHTML = content;
}

function displayPlayerHand(){
    const player = document.getElementById("player");
    const playerCardBox = player.querySelector(".card-box");
    playerCardBox.innerHTML = "";   
    for(let cardData of humanPlayer.hand){
        let playingCard = new PlayingCard(cardData.rank, cardData.suit);
        playerCardBox.appendChild(playingCard);
    }
}

function displayDealerHand(finished){
    const dealer = document.getElementById("dealer");
    const dealerCardBox = dealer.querySelector(".card-box");
    dealerCardBox.innerHTML = "";
    let i = 0;
    for(let cardData of dealerPlayer.hand){
        if(finished){
            let playingCard = new PlayingCard(cardData.rank, cardData.suit);
            dealerCardBox.appendChild(playingCard);
        } else {
            if (i == 0){
                    let playingCard = new PlayingCard(cardData.rank, cardData.suit,true);
                    dealerCardBox.appendChild(playingCard);
            } else {
                    let playingCard = new PlayingCard(cardData.rank, cardData.suit);
                    dealerCardBox.appendChild(playingCard);
            }
            i += 1;     
        }
    }
}

btnSettings.addEventListener("click", (event) => {
    window.location.href="./settings.html";
});

newGame();

