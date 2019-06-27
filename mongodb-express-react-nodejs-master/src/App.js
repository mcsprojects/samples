import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

import Create from './components/create.component';
import Edit from './components/edit.component';
import Index from './components/index.component';
import Home from './components/home.component';
import logo from "./logo.png";

class App extends Component {
  render() {
    return (
      <Router>
        <div className="container">
          <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
           <div className="navbar-brand">
              <img src={logo} width="20" height="20" alt="" />    <span id="red">Videoteca</span><span id="grey">Online</span></div>            
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
              <ul className="navbar-nav mr-auto">               
                <li className="nav-item">
                  <Link to={'/'} className="nav-link">Inicio</Link>
                </li>
                 <li className="nav-item">
                  <Link to={'/index'} className="nav-link">Lista de vídeos</Link>
                </li>
                <li className="nav-item">
                  <Link to={'/create'} className="nav-link">Añadir vídeos</Link>
                </li>
               
              </ul>
            </div>
          </nav>
          <Switch>
              <Route exact path='/create' component={ Create } />
              <Route path='/edit/:id' component={ Edit } />              
              <Route path='/index' component={ Index } />              
              <Route path='/' component={ Home } /> 
              
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
