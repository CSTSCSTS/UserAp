import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Container } from 'reactstrap';
import { Link } from 'react-router-dom';


class SystemError extends Component {

  render() {
    return (
      <div>
      	<h1 id="title">茶 圓 ポ ー カ ー システムエラー</h1>
      	<Container id="form">
	        <p>予期せぬエラーが発生しました</p>
	        <p>恐れ入りますが、もう一度ログインしなおしてください。</p>
	        <Link to="/">ログイン画面へ</Link>
	      </Container>
	    </div>
    );
  }
}

export default withRouter(SystemError);