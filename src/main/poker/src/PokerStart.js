import React, { Component } from 'react';
import './pokerStart.css';
import PokerField from './pokerField';
import LoginBonusPopup from './loginBonusPopup';
import { withRouter } from 'react-router';
import CommonHeader from './commonHeader'
import { Button, Container, Form, FormGroup, Input } from 'reactstrap';
import Modal from "react-modal";


class PokerStart extends Component {

  constructor(props) {
    super(props);
    this.state = {
      jokerIncluded: true
    };
  }

  handleSubmit(e) {
	var request = require('superagent');
    e.preventDefault();
    const url = '/bet'
    request
    .get(url)
    .responseType('text')
    .then(res => {
      this.handleToBet(res.body, this.state.jokerIncluded);
    });
  }
    handleToBet = (body, jokerIncluded) => {
    	this.props.history.push({
    		pathname: '/play',
    		state: {betMoney: body, jokerIncluded: jokerIncluded}
    	})
    }

  handleChange(event) {
	const jokerIncluded = event.target.value == 'included' ? true : false;
    this.setState({jokerIncluded: jokerIncluded});
  }

  render() {
    return (
      <div>
        <CommonHeader />
        <LoginBonusPopup
          isOpen={this.props.location.state.isOpen}
          user={this.props.location.state.user}
        />
      	<h1 id="title">茶 圓 ポ ー カ ー</h1>
	    <Container id="form">
	      <Form>
	        <FormGroup>
	          <Input type="select" onChange={this.handleChange.bind(this)} name="select" id="exampleSelect">
	            <option value="included">ジョーカーを含む</option>
	            <option value="non-included">ジョーカーを含まない</option>
	          </Input>
	        </FormGroup>
	    	<Button color="primary" size="lg" block onClick={this.handleSubmit.bind(this)}>START</Button>
	      </Form>
	    </Container>
	  </div>
    );
  }
}

export default withRouter(PokerStart);