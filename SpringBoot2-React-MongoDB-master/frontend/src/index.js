import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Switch, Link  } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import './css/App.css';
import Edit from './components/Edit';
import Create from './components/Create';
import Details from './components/Details';
import Home from './components/Home';


ReactDOM.render(
  <Router>
       <div className="container-3">
          <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
           <div className="navbar-brand">
              <span class="custom-1">GESTOR DE INCIDENCIAS</span></div>            
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
              <ul className="navbar-nav mr-auto">
                <li className="nav-item">
                  <Link to={'/'} className="nav-link">Inicio</Link>
                </li>               
                <li className="nav-item">
                  <Link to={'/App'} className="nav-link">Lista de incidencias</Link>
                </li>
                 <li className="nav-item">
                  <Link to={'/create'} className="nav-link">Añadir incidencia</Link>
                </li>               
              </ul>
            </div>
          </nav>
          <Switch>
              <Route exact path='/App' component={App} />
              <Route path='/edit/:id' component={Edit} />
             <Route path='/create' component={Create} />
             <Route path='/details/:id' component={Details} />
              <Route path='/' component={ Home } />               
          </Switch>
        </div>      
  </Router>,
  document.getElementById('root')
);
