import React, { Component } from 'react';
import './pokerStart.css';
import logo from './logo.svg';
import './App.css';
import PokerField from './pokerField';
import { withRouter } from 'react-router';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input } from 'reactstrap';

class App extends Component {
  render() {
    return (
      <BrowserRouter>
          <div>
            <Switch>
              <Route exact path={'/'} component={PokerStart}/>
              <Route exact path={'/play'} component={PokerField}/>
            </Switch>
          </div>
        </BrowserRouter>
    );
  }
}

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
    const url = window.location + 'config';
    request
    .post(url)
    .responseType('text')
    .type('form')
    .send({jokerIncluded: this.state.jokerIncluded})
    .then(res => {
      this.handleToPlay(res.body, this.state.jokerIncluded);

    });
  }
    handleToPlay = (body, jokerIncluded) => {
    	this.props.history.push({
    		pathname: '/play',
    		state: {fieldInfo: body, jokerIncluded: jokerIncluded}
    	})
    }

  handleChange(event) {
	const jokerIncluded = event.target.value == 'included' ? true : false;
    this.setState({jokerIncluded: jokerIncluded});
  }

  render() {
    return (
      <div>
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

export default App;
