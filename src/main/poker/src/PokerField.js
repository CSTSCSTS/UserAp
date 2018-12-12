import React, { Component } from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input } from 'reactstrap';
import App from './App'
import { withRouter } from 'react-router'
import './pokerField.css';

class PokerField extends Component {
	constructor(props) {
	  super(props);
      this.state = {
        deck: this.props.location.state.fieldInfo.deck,
        playerHands: this.props.location.state.fieldInfo.playerHands,
        computerHands: this.props.location.state.fieldInfo.computerHands,
        isFinishedChange: this.props.location.state.isFinishedChange,
        playerRole: null,
        computerRole: null,
        winner: null
	  };
	}

	stateReset = (playerHands, computerHands) => {
		this.setState({
		  playerHands: playerHands,
      computerHands: computerHands,
      isFinishedChange: false,
      playerRole: null,
      computerRole: null,
      winner: null})
	}

	handsChange = (playerHands, computerHands, isFinishedChange, playerRole, computerRole, winner) => {
	  this.setState({
	    playerHands: playerHands,
        computerHands: computerHands,
        isFinishedChange: isFinishedChange,
        playerRole: playerRole,
        computerRole: computerRole,
        winner: winner})
	}

	setHands = (hand) => {
	  hand.isChange = !hand.isChange;
	  var currentHands = this.state.playerHands;
	  const targetIndex = currentHands.findIndex((h) => {
		  return(h.type === hand.type && h.number === hand.number);
	  })
	  currentHands.splice(targetIndex, 1, hand);
	  this.setState({playerHands: currentHands})
	}

  render() {
    return (
      <div>
        <Container className="poker_field">
          <ul>
	       <CpuHands
	    	  hands={this.state.computerHands}
	    	  isFinishedChange={this.state.isFinishedChange}
	    	 />
	    	<WinOrLossJudge
	          playerRole={this.state.playerRole}
	    	  computerRole={this.state.computerRole}
	    	  winner={this.state.winner}
	    	/>
	    	<PlayerHands
	    	  playerHands={this.state.playerHands}
	        setHands={this.setHands}
	    	  isFinishedChange={this.state.isFinishedChange}
	    	/>
	       </ul>
	      </Container>
        <HandChangeButton
          deck={this.state.deck}
          playerHands={this.state.playerHands}
          computerHands={this.state.computerHands}
          isFinishedChange={this.state.isFinishedChange}
          handsChange={this.handsChange}
        />
        <AfterPokerPlayingButtons
	        stateReset={this.stateReset}
	        jokerIncluded={this.props.jokerIncluded}
	        history={this.props.history}
	        isFinishedChange={this.state.isFinishedChange}
        />
      </div>
    )
  }
}

class CpuHands extends Component {
  render() {
	if(!this.props.isFinishedChange) {
	  return (
		<div id="computer_hands">
		  <li className="behind_hand"></li>
		  <li className="behind_hand"></li>
		  <li className="behind_hand"></li>
		  <li className="behind_hand"></li>
		  <li className="behind_hand"></li>
		</div>
	  );
	}
    const cpuHands =[];
    for(var i in this.props.hands) {
      const text = this.props.hands[i].type === 'JOKER' ? 'JOKER': this.props.hands[i].type+'の'+this.props.hands[i].number;
      cpuHands.push(
        <li className="hand_item">
          <div className="hand">
            <h3>{text}</h3>
          </div>
        </li>
      )
    }
    return (
      <div id="computer_hands">
      	<ul>
      	  {cpuHands}
      	</ul>
      </div>
    )
  }
}

class WinOrLossJudge extends Component {
  render() {
	  if(this.props.playerRole === null) {
	    return null;
      } else if(this.props.winner === 'NOTHING') {
    	return (
	      <div>
	        <h3 id="computer_role">CPUの役: {this.props.computerRole.roleName}</h3>
	        <h3 id="result">引き分けです</h3>
	        <h3 id="player_role">プレイヤーの役: {this.props.playerRole.roleName}</h3>
	      </div>
	    )
      }

    return (
      <div id="win_or_loss_judge">
        <h3 id="computer_role">CPUの役: {this.props.computerRole.roleName}</h3>
        <h3 id="result">{this.props.winner}の勝利です</h3>
        <h3 id="player_role">プレイヤーの役: {this.props.playerRole.roleName}</h3>
      </div>
    )
  }
}

class PlayerHands extends Component {
  render() {
    const playerHands = this.props.playerHands.map((hand) => {
      return(
        <li className="hand_item">
          <Hand hand={hand} setHands={this.props.setHands} isFinishedChange={this.props.isFinishedChange}/>
        </li>
      );
    });
    return (
      <div id="player_hands">
      	<ul>
      	  {playerHands}
      	</ul>
      </div>
    )
  }
}

class Hand extends Component {

	changeStatus = () => {
	  this.props.setHands(this.props.hand);
	}

 render() {
	const text = this.props.hand.type === 'JOKER' ? 'JOKER': this.props.hand.type+'の'+this.props.hand.number;
	if(this.props.isFinishedChange) {
	  return (
		<div className="hand">
	      <h3>{text}</h3>
	    </div>
      )
    }
    return (
      <div className="hand">
      	<h3>{text}</h3>
      	<Button color="warning" size="lg" block onClick={this.changeStatus}> {this.props.hand.isChange == true ? '交換' : 'キープ'} </Button>
      </div>
    )
  }
}

class HandChangeButton extends Component {
	handleToChange = () => {
	  var request = require('superagent');
	  const url = window.location;
	  request
	    .post(url)
	    .type('form')
	    .send({jsonPlayerHands: JSON.stringify(this.props.playerHands),
	    	   jsonDeck: JSON.stringify(this.props.deck),
	    	   jsonComputerHands: JSON.stringify(this.props.computerHands)})
	    .then(res => {
	      const pokerInfo = res.body;
	      this.props.handsChange(
	        pokerInfo.playerHands,
	        pokerInfo.computerHands,
	        pokerInfo.finishedChange,
	        pokerInfo.playerRole,
	        pokerInfo.computerRole,
	        pokerInfo.winner);
	    });
	}

  render() {
    if(this.props.isFinishedChange) {
      return null;
    }

    return (
      <div id="hand_change">
	    <Button onClick={this.handleToChange}>
	      勝負
	    </Button>
      </div>
    )
  }
}

class AfterPokerPlayingButtons extends Component {
  render() {
    return (
      <div id="after_poker_playing_buttons">
		  <RetryButton
	        stateReset={this.props.stateReset}
	        jokerIncluded={this.props.jokerIncluded}
	        history={this.props.history}
	        isFinishedChange={this.props.isFinishedChange}
		 />
		 <RestartButton
		   history={this.props.history}
		   jokerIncluded={this.props.jokerIncluded}
		   isFinishedChange={this.props.isFinishedChange}
		 / >
	 </div>
    )
  }
}

class RetryButton extends Component {

	 handleSubmit(e) {
    	var request = require('superagent');
        e.preventDefault();
        var currentUrl = window.location.toString();
        const url = currentUrl.slice(0, -4) + 'config';
        request
        .post(url)
        .responseType('text')
        .type('form')
        .send({jokerIncluded: this.props.jokerIncluded})
        .then(res => {
          const pokerInfo = res.body;
          this.handleToRePlay(pokerInfo.playerHands, pokerInfo.computerHands);
        });
      }
        handleToRePlay = (playerHands, computerHands) => {
          this.props.stateReset(playerHands, computerHands);
        }

  render() {
    if(!this.props.isFinishedChange) {
	  return null;
	}

    return (
	    <Button onClick={this.handleSubmit.bind(this)}>
	      もう一度
	    </Button>
    )
  }
}

class RestartButton extends Component {

	handleToRestart = (body) => {
		this.props.history.push({
			pathname: '/'
		})
    }
  render() {
    if(!this.props.isFinishedChange) {
	  return null;
	}
    return (
	    <Button onClick={this.handleToRestart.bind(this)}>
	      スタートに戻る
	    </Button>
    )
  }
}

export default withRouter(PokerField);