import React, { Component } from 'react';
import { Button, Container, Form, FormGroup, Input } from 'reactstrap';
import { withRouter } from 'react-router';
import InputNumber from "rmc-input-number";
import CommonHeader from './commonHeader'
import './bet.css'

class Bet extends Component {
	constructor(props) {
	  super(props);
      this.state = {
        betMoney: '',
        errorMessage: []
	  };
	}

	betMoneyHandleChange(event) {
		this.setState({betMoney: event.target.value});
	}

	handleSubmit(e) {
	// リクエスト前に必須チェック・0円チェックを実施
		var errorList = [];
		// 必須チェック
		this.nullOrEmptyCheck(errorList, this.state.betMoney, "ベット額が未入力です。");
		// 0円チェック
		this.inputValueIsZeroCheck(errorList, this.state.betMoney, "ベット額は1円以上になるように入力してください。");
	  // 正規表現チェック(数字以外が存在しないか)
		this.onlyNumberCheck(errorList, this.state.betMoney, "数字以外が入力されています。");

    if(errorList.length != 0) {
      this.setState({errorMessage: errorList});
      return;
    }

    var request = require('superagent');
	    e.preventDefault();
	    const url = '/config';
	    request
	    .post(url)
	    .responseType('text')
	    .type('form')
	    .send({betMoney: this.state.betMoney ,jokerIncluded: this.props.jokerIncluded})
	    .then(res => {
	    	this.props.pokerPrepare(res.body);
	    	this.props.pokerPhaseChange('HANDCHANGE');
	    	this.props.updateBetMoney(this.state.betMoney);
	    })
	    .catch(err => {
	    	if(err.response.body.status === 401) {
	    		this.props.history.push({
	      		pathname: '/session-timeout'
	      	})
	      	return;
	    	}
	    	if(err.response.body.status === 500) {
	    	// システムエラー画面へ遷移
      		this.props.history.push({
  				  pathname: '/error'
  			  })
      	}
        errorList.push(err.response.body.message)
        this.setState({errorMessage: errorList})
			});

	  }

	// 必須チェック
	nullOrEmptyCheck(errorList, value, errorMessage) {
		if(!(value)) {
			errorList.push(errorMessage);
		}
		return errorList;
	}

  // 0円チェック
	inputValueIsZeroCheck(errorList, value, errorMessage) {
		if((value) && value == 0) {
      errorList.push(errorMessage);
		}
		return errorList;
	}

	// 正規表現チェック(数字以外が存在しないか)
	onlyNumberCheck(errorList, value, errorMessage) {
		const pattern = /^[0-9]*$/;
		if(!pattern.test(value)) {
			errorList.push(errorMessage);
		}
		return errorList
	}

  render() {
    return (
      <div>
        <CommonHeader />
        <h1 id="title">茶 圓 ポ ー カ ー プレイ</h1>
        <Container id="form">
        <Form>
        <div>
        {this.state.errorMessage.map((item) => (
        		<p class="alert alert-danger">{item}</p>
        ))}
        </div>
        <FormGroup>
        	<h2 class="text-center">ベット金額を入力してください</h2>
        </FormGroup>
        <FormGroup>
          <MoneyInfo
            money={this.props.money}
          />
        </FormGroup>
        <FormGroup>
          <Input type="text" min={0} value={this.state.betMoney} onChange={this.betMoneyHandleChange.bind(this)}/>
        </FormGroup>
  	    <Button block onClick={this.handleSubmit.bind(this)}>ベット</Button>
      </Form>
      </Container>
      </div>
    )
  }
}


class MoneyInfo extends Component {
  render() {
    return (
      <div class="center-block">
        現在の所持金: {this.props.money}円
      </div>
    );
  }
}

export default withRouter(Bet);