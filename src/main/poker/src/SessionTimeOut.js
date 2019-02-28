import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Link } from 'react-router-dom';
import { Form } from 'reactstrap';


class SessionTimeOut extends Component {

  render() {
    return (
      <div class="center-block">
      	<Form>
        	<h4>セッションがタイムアウトしました。</h4>
      	  <h4>恐れ入りますが、もう一度ログインしなおしてください。</h4>
      	  <Link to="/">ログイン画面へ</Link>
	      </Form>
	    </div>
    );
  }
}

export default withRouter(SessionTimeOut);