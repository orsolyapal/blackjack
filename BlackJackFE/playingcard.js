class PlayingCard extends HTMLElement {
    constructor(rank, suit, back){
        super();
        this.attachShadow({mode: "open"});
        this.rank = rank;
        this.suit = suit;
        this.back = back;
    }
    
    get suit(){
        return this.getAttribute("suit");
    }

    get rank(){
        return this.getAttribute("rank");
    }

    set suit(suit){
        this.setAttribute("suit", suit);
    }

    set rank(rank){
        this.setAttribute("rank", rank);
    }

    connectedCallback(){
        this.render();
    }

    render(){
        this.shadowRoot.innerHTML = `
            <style>
                .card{
                    width: 6vw;
                    height: 16vh;
                    background:white;
                    border-radius: 5px;
                    border: 1px solid black;
                    display: flex;
                    flex-direction: column;
                }

                .back{
                    background-color: grey;
                }

                .rank-up{
                    height: 20%;
                    text-align: right;
                    padding: 5px;
                }

                .rank-down{
                    height: 20%;
                    text-align: left;
                    padding: 5px;
                }

                .suit{
                    height: 60%;
                    text-align: center;
                    display: grid;
                    place-items: center;
                }

                .red{
                    color: red;
                }

                .black{
                    color:black;
                }
            </style>
        `;

        if(this.back){
            this.shadowRoot.innerHTML += `
            <div class="card back">
            </div>
            `;
        } else {
            this.shadowRoot.innerHTML += `
                <div class="card ${this.getColor(this.suit)}">
                    <div class="rank-up">${this.rank}</div>
                    <div class="suit">${this.suit}</div>
                    <div class="rank-down">${this.rank}</div>
                </div>
            `;
        }
    }

    getColor(suit){
        return (suit == "♥" || suit == "♦")? "red":"black";
    }

}

customElements.define("playing-card", PlayingCard);