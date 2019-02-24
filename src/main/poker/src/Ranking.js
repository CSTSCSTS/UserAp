import React, { Component } from 'react';
import './ranking.css';
import './App.css';
import { withRouter } from 'react-router';
import {BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Navbar } from 'reactstrap';
import Modal from "react-modal";

class Ranking extends Component{

	render() {
    const ranking = [];
    const moneyRanking = this.props.ranking.moneyRanking.moneyRankingList;
    // ランキング表示要素作成
    for(let i = 1; i < moneyRanking.length + 1; i++) {
    	ranking.push(
    	  <RankingItem
          rankingItem={moneyRanking[i - 1]}
    	    rank={i}
        />
    	)
    }

    return (
      <div id="ranking">
        <table border="1" ailgn="center" width="1000" height="100">
          <th>順位</th>
          <th>ユーザー名</th>
          <th>所持金</th>
          {ranking}
        </table>
	    </div>
    );
  }
}

class CloseButton extends Component{

	handleToClose() {
		this.props.handleToClose();
	}

	render() {
    return (
    		<Button id="closeBunnton"  onClick={this.handleToClose.bind(this)}>閉じる</Button>
    );
  }

}

	class RankingItem extends Component{
		render() {
	    return (
	        <tr>
	          <td>{this.props.rank}</td>
	          <td>{this.props.rankingItem.username}</td>
	          <td>{this.props.rankingItem.money}</td>
	        </tr>
	    );
	  }
}

	export default Ranking;