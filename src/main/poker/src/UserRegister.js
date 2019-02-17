import React, { Component } from 'react';
import './userRegister.css'
import { withRouter } from 'react-router';
import CommonHeader from './commonHeader'
import {BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input } from 'reactstrap';


class UserRegister extends Component {

	constructor(props) {
    super(props);
    this.state = {
      userName: '',
      password: '',
      confirmationPassword: '',
      errorMessage: []
    };
  }

	usernameHandleChange(event) {
    this.setState({userName: event.target.value});
  }

	passwordHandleChange(event) {
    this.setState({password: event.target.value});
  }

	confirmationPasswordHandleChange(event) {
    this.setState({confirmationPassword: event.target.value});
  }

	handleSubmit(e) {

		// リクエスト前に必須チェック・文字数チェック・パスワード一致チェックを実施
		var errorList = [];
		// 必須チェック
		this.nullOrEmptyCheck(errorList, this.state.userName, "ユーザー名が未入力です。");
		this.nullOrEmptyCheck(errorList, this.state.password, "パスワードが未入力です。");
		this.nullOrEmptyCheck(errorList, this.state.confirmationPassword, "パスワード(確認)が未入力です。");
		// 文字数チェック
		this.lengthOverCheck(errorList, this.state.userName, 255, "ユーザー名が255文字を超えています。");
		this.lengthOverCheck(errorList, this.state.password, 255, "パスワードが255文字を超えています。");
		// パスワード一致チェック
		this.passwordSameCheck(errorList, this.state.password, this.state.confirmationPassword);

    if(errorList.length != 0) {
      this.setState({errorMessage: errorList});
      return;
    }

		var request = require('superagent');
	    e.preventDefault();
	    const url = window.location + '';
	    request
	    .post(url)
	    .type('form')
	    .send({userName: this.state.userName, password: this.state.password})
	    .then(res => {
	    	this.props.history.push({
				  pathname: '/'
			  })
	    })
			.catch(err => {
        errorList.push(err.response.body.message)
        this.setState({errorMessage: errorList})
			});

	  }

	// キャンセルボタンをクリック
	handleCancel() {
		this.props.history.push({
			pathname: '/'
		});
	}

	// 必須チェック
	nullOrEmptyCheck(errorList, value, errorMessage) {
		if(!(value)) {
			errorList.push(errorMessage);
		}
		return errorList;
	}

  // 文字数チェック
	lengthOverCheck(errorList, value, limit, errorMessage) {
		if(value.length > limit) {
      errorList.push(errorMessage);
		}
		return errorList;
	}

	// パスワード一致チェック
	passwordSameCheck(errorList, password, confirmationPassword) {
    if(password !== confirmationPassword){
      errorList.push("パスワードとパスワード(確認)が一致していません。");
    }
    return errorList;
	}



	render() {
    return (
      <div>
        <CommonHeader />
      	<h2 id="title">茶 圓 ポ ー カ ー ユーザー登録</h2>
      	<Container id="form">
      	  <div>
            {this.state.errorMessage.map((item) => (
              <p class="text-danger">{item}</p>
             ))}
          </div>
	      <Form id="frame">
  	      <FormGroup>
            <label>ユーザー名入力</label>
            <Input type="text" onChange={this.usernameHandleChange.bind(this)}></Input>
          </FormGroup>
          <FormGroup>
            <label>パスワード入力</label>
            <Input type="password" onChange={this.passwordHandleChange.bind(this)}></Input>
          </FormGroup>
          <FormGroup>
            <label>パスワード(確認)入力</label>
            <Input type="password" onChange={this.confirmationPasswordHandleChange.bind(this)}></Input>
          </FormGroup>
          <div>
  	    	  <Button color="primary" size="lg" block onClick={this.handleSubmit.bind(this)}>登録</Button>
  	    	  <Button color="primary" size="lg" block onClick={this.handleCancel.bind(this)}>キャンセル</Button>
  	    	</div>
	      </Form>
	    </Container>
	  </div>
    );
  }

}

export default withRouter(UserRegister);