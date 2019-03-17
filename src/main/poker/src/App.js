import React, { Component } from 'react';
import './pokerStart.css';
import logo from './logo.svg';
import './App.css';
import PokerField from './pokerField';
import PokerStart from './pokerStart';
import UserRegister from './userRegister'
import SystemError from './systemError'
import SessionTimeOut from './sessionTimeOut'
import CommonHeader from './commonHeader'
import Bet from './bet';
import { withRouter } from 'react-router';
import {BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import { Button, Container, Row, Col, Form, FormGroup, Input } from 'reactstrap';
import * as PokerConstNumber from './PokerConstNumber.js';
import * as PokerConstMessage from './PokerConstMessage.js';

class App extends Component {
  render() {
    return (
      <BrowserRouter>
          <div>
            <Switch>
              <Route exact path={'/'} component={Login}/>
              <Route exact path={'/user'} component={UserRegister}/>
              <Route exact path={'/start'} component={PokerStart}/>
              <Route exact path={'/play'} component={PokerField}/>
              <Route exact path={'/error'} component={SystemError}/>
              <Route exact path={'/session-timeout'} component={SessionTimeOut}/>
            </Switch>
          </div>
        </BrowserRouter>
    );
  }
}

class Login extends Component {

  constructor(props) {
    super(props);
    this.state = {
    		userName: '',
        password: '',
        errorMessage: []
    };
  }

  handleSubmit(e) {
    // リクエスト前に必須チェックを実施
  	var errorList = [];

  	this.NullOrEmptyCheck(errorList, this.state.userName, PokerConstMessage.userNameNotInputMessage);
  	this.NullOrEmptyCheck(errorList, this.state.password, PokerConstMessage.passwordNotInputMessage);

  	if(errorList.length != 0) {
      this.setState({errorMessage: errorList});
      return;
    }

	  var request = require('superagent');
    e.preventDefault();
    const url = window.location + '/login';
    request
      .post(url)
      .responseType('text')
      .type('form')
      .send({userName: this.state.userName, password: this.state.password})
      .then(res => {
      	this.props.history.push({
      		pathname: '/start',
      		// ログインがその日初めてかどうか情報をサーバー側で返してもらう
      		state: {isOpen: res.body.isFirstLogin, userName: res.body.userName}
      	});
      })
      .catch(err => {
      	if(err.response.body.status === PokerConstNumber.UN_EXPECTED_ERROR_CODE) {
        	// システムエラー画面へ遷移
      		this.props.history.push({
  				  pathname: '/error'
  			  })
      	}
        errorList.push(err.response.body.message)
        this.setState({errorMessage: errorList})
			});

  }
    usernameHandleChange(event) {
      this.setState({userName: event.target.value});
    }

  	passwordHandleChange(event) {
      this.setState({password: event.target.value});
    }

  // 必須チェック
  NullOrEmptyCheck(errorList, value, errorMessage) {
		if(!(value)) {
			errorList.push(errorMessage);
		}
		return errorList;
	}

  render() {
    return (
      <div>
        <CommonHeader />
      	<h1 id="title">茶 圓 ポ ー カ ーログイン</h1>
	    <Container>
	      <div>
          {this.state.errorMessage.map((item) => (
            <p class="alert alert-danger">{item}</p>
          ))}
        </div>
	      <Form>
	        <Row>
            <Col sm="12" md={{ size: 6, offset: 3 }}>
	            <FormGroup>
                <label>ユーザー名入力</label>
                <Input type="text" onChange={this.usernameHandleChange.bind(this)}></Input>
              </FormGroup>
            </Col>
          </Row>
          <Row>
            <Col sm="12" md={{ size: 6, offset: 3 }}>
            	<FormGroup>
            		<label>パスワード入力</label>
            		<Input type="password" onChange={this.passwordHandleChange.bind(this)}></Input>
              </FormGroup>
            </Col>
          </Row>
          <Row>
            <Col sm="12" md={{ size: 6, offset: 3 }}>
	    	      <Button color="primary" size="lg" block onClick={this.handleSubmit.bind(this)}>ログイン</Button>
	    	    </Col>
	        </Row>
	        <Row>
            <Col sm="12" md={{ size: 6, offset: 3 }}>
	            <Link to="/user">ユーザー登録はこちら</Link>
	          </Col>
	  	    </Row>
	      </Form>
	    </Container>
	  </div>
    );
  }

}

export default App;
