/*
GAME RULES:

- The game has 2 players, playing in rounds
- In each turn, a player rolls a dice as many times as he whishes. Each result get added to his ROUND score
- BUT, if the player rolls a 1, all his ROUND score gets lost. After that, it's the next player's turn
- The player can choose to 'Hold', which means that his ROUND score gets added to his GLBAL score. After that, it's the next player's turn
- The first player to reach 100 points on GLOBAL score wins the game

*/




var scores, roundScore, activePlayer, gamePlaying;




function init() {

    
    scores = [0, 0];
    activePlayer = 0;
    roundScore = 0;
    gamePlaying = true;
    
    
    //Hiding the dice image initially
    document.querySelector('.dice').style.display = 'none';

    //Setting values to zero
    document.getElementById('score-0').textContent = '0';
    document.getElementById('score-1').textContent = '0';
    document.getElementById('current-0').textContent = '0';
    document.getElementById('current-1').textContent = '0';
    document.getElementById('name-0').textContent = 'Player 1';
    document.getElementById('name-1').textContent = 'Player 2';
    //removing winner class panel for both players
    document.querySelector('.player-0-panel').classList.remove('winner');
    document.querySelector('.player-1-panel').classList.remove('winner');
    
    document.querySelector('.player-0-panel').classList.remove('active');
    document.querySelector('.player-1-panel').classList.remove('active');
    
    document.querySelector('.player-0-panel').classList.add('active');
    
}


init();

//Rolling dice

//2nd argument is the function which is called on click event, using anonymous function here
document.querySelector('.btn-roll').addEventListener('click', function(){
    
    if(gamePlaying){
        var dice = Math.floor(Math.random() * 6) + 1;
    
    var diceDOM = document.querySelector('.dice');// this is done to avoid selecting the same element over and over again 
    diceDOM.style.display = 'block'; 
    diceDOM.src = 'dice-' + dice +'.png'; //changing image using src of image
    
    
    //Updating score
    if(dice !== 1){
        roundScore += dice;
        document.querySelector('#current-'+activePlayer).textContent = roundScore;
    }else{
        
        nextPlayer();
    }
    }
    
}); 


function nextPlayer(){
        activePlayer === 0 ? activePlayer = 1 : activePlayer = 0;
        roundScore = 0;
        
        document.getElementById('current-0').textContent = 0;
        document.getElementById('current-1').textContent = 0;
        
        /*
        document.querySelector('.player-0-panel').classList.remove('active');
        document.querySelector('.player-1-panel').classList.add('active');
        */
        document.querySelector('.player-0-panel').classList.toggle('active');
        document.querySelector('.player-1-panel').classList.toggle('active');
        //Instead of add and remove we use toggle which adds if not present and removes if present
        
        document.querySelector('.dice').style.display = 'none';
}


//Adding current score to total score on clicking button Hold
document.querySelector('.btn-hold').addEventListener('click',function(){
    if(gamePlaying){
        //updating score
    scores[activePlayer] += roundScore;
    
    //Updating score in UI
    document.querySelector('#score-' + activePlayer).textContent = scores[activePlayer];
    
    
    if(scores[activePlayer] >= 20) {
        
        document.querySelector('#name-' + activePlayer).textContent = 'Winner!';
        document.querySelector('.dice').style.display = 'none';
        //it is not suggestive way to change css style through JS
        document.querySelector('.player-' + activePlayer + '-panel').classList.add('winner');
        document.querySelector('.player-' + activePlayer + '-panel').classList.remove('active');
        gamePlaying = false;
        
        
    } else {
        nextPlayer();
        }
    }  
});


document.querySelector('.btn-new').addEventListener('click', init);









/*
var dice ;
dice = Math.floor(Math.random() * 6) + 1;
console.log(dice);
*/

//Now place this value in the current box 

//document.querySelector('#current-0').textContent = dice;
//document.querySelector('#current-' + activePlayer).textContent = dice;
//document.querySelector('#current-' + activePlayer).innerHTML = '<em>' + dice + '</em>';
