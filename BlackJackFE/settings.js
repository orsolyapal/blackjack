let playerResults = {
    name: "Játékos",
    email: "minta@email.com",
    hands: 0,
    games: 0,
    handsWon: 0,
    gamesWon: 0,
    wonFromHands: 0,
    wonFromGames: 0,
    lostFromHands: 0,
    lostFromGames: 0,
    blackJacks: 0,
    doubleDowns: 0,
    splits: 0
}

let registeredPlayers = [];
const btnOKSettings = document.getElementById("btnOKSettings");
const btnCancelSettings = document.getElementById("btnCancelSettings");
const addPlayerForm = document.getElementById("addPlayerForm");
const activePlayerName = document.getElementById("activePlayerName");
const registeredPlayersTableBody = document.getElementById("registeredPlayersTableBody");

let selectedRowName;
let settingsPlayerName;
let settingsPlayerEmail;

function settings(){
    settingsPlayerName = localStorage.getItem("settings_player_name");
    settingsPlayerEmail = localStorage.getItem("settings_player_email");
    registeredPlayers = JSON.parse(localStorage.getItem("registeredPlayers"));
    registeredPlayers ? null : registeredPlayers=[];
    displayActivePlayer();
    displayRegdPlayers();
}

function displayRegdPlayers(){    
    registeredPlayersTableBody.innerHTML = "";
    for(let player in registeredPlayers) {
        let trRegdPlayerRow = document.createElement("tr");
        trRegdPlayerRow.setAttribute("data-name", registeredPlayers[player].name);
        trRegdPlayerRow.setAttribute("data-email", registeredPlayers[player].email);
        if(settingsPlayerName == registeredPlayers[player].name){
            trRegdPlayerRow.classList.add("selected-row");
        }
        let tdRegdPlayerName = document.createElement("td");
        tdRegdPlayerName.innerHTML = registeredPlayers[player].name;
        let tdRegdPlayerEmail = document.createElement("td");
        tdRegdPlayerEmail.innerHTML = registeredPlayers[player].email;
        trRegdPlayerRow.appendChild(tdRegdPlayerName);
        trRegdPlayerRow.appendChild(tdRegdPlayerEmail);
        registeredPlayersTableBody.appendChild(trRegdPlayerRow);
    }
}

function displayActivePlayer(){
    activePlayerName.innerHTML = (settingsPlayerName ? settingsPlayerName : "Nincs beállított játékos");
}

registeredPlayersTableBody.addEventListener("click", (event) => {
    const clickedRow = event.target.closest("tr");
    const previouslyClickedRow = document.querySelector(".selected-row");
    previouslyClickedRow? previouslyClickedRow.classList.remove("selected-row") : null;
    clickedRow.classList.add("selected-row");
    settingsPlayerName = clickedRow.dataset.name;
    settingsPlayerEmail = clickedRow.dataset.email;
    displayActivePlayer();
});

addPlayerForm.addEventListener("submit", (event) => {
    event.preventDefault();
    settingsPlayerName = document.getElementById("playerName").value;
    settingsPlayerEmail = document.getElementById("playerEmail").value;
    registeredPlayers.push({name: settingsPlayerName, email: settingsPlayerEmail});
    displayRegdPlayers();
});


btnCancelSettings.addEventListener("click", () => {
    window.location.href = "index.html";  
});

btnOKSettings.addEventListener("click", () =>{
    if(settingsPlayerName){
        localStorage.setItem("settings_player_name", settingsPlayerName);
        localStorage.setItem("settings_player_email", settingsPlayerEmail);
    } 
    localStorage.setItem("registeredPlayers", JSON.stringify(registeredPlayers)); 
    window.location.href = "index.html";  
});

settings();
