import React, { Component } from 'react';
import './commonHeader.css';
import './ranking.css';
import logo from './logo.svg';
import './App.css';
import { withRouter } from 'react-router';
import {BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input } from 'reactstrap';
import Modal from "react-modal";


class CommonHeader extends Component {

	constructor(props) {
    super(props);
    this.state = {
      isOpen: false,
      ranking: null
    };
  }

	// ランキング情報を取得する
	handleToRanking(e) {
		var request = require('superagent');
	    e.preventDefault();
	    const url = '/ranking';
	    request
	    .get(url)
	    .then(res => {
	    	this.setState({isOpen: true, ranking: res.body});
	    });

	  }
	// ランキングポップアップを閉じる
	handleToClose(e) {
		this.setState({isOpen: false});
	}

	render() {
    return (
      <div>
        <div id="common-header">
	        <Button id="rankingButton" onClick={this.handleToRanking.bind(this)}>ランキング表示をする</Button>
	      </div>
	      <Modal
	        isOpen={this.state.isOpen}
	      >
	        <h2 id="user_register_title">茶 圓 ポ ー カ ー ランキング</h2>
	        <Ranking
	          ranking={this.state.ranking}
	        />
	        <Button id="closeBunnton"  onClick={this.handleToClose.bind(this)}>閉じる</Button>
	      </Modal>
	    </div>
    );
  }

}

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
        <table border="1">
          <th>順位</th>
          <th>ユーザー名</th>
          <th>所持金</th>
          {ranking}
        </table>
	    </div>
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

export default withRouter(CommonHeader);