import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './css/main.css';



class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      incidencias: []
    };
  }

  componentDidMount() {
    axios.get('/incidencias')
      .then(res => {
        this.setState({ incidencias: res.data });
        console.log(this.state.incidencias);
      });
  }

  render() {
    return (
      <div class="wrapper">
      <div class="container">
       
            <table class="table table-stripe">
              <thead>
                <tr>                  
			      <th><h1>Nombre del Proyecto</h1></th>
                  <th><h1>Descripción de la incidencia</h1></th>
		          <th><h1>Prioridad</h1></th>                  
                </tr>
              </thead>
              <tbody>
                {this.state.incidencias.map(i =>
                  <tr>                    
                    <td><Link to={`/details/${i.id}`}>{i.proyecto}</Link></td>
                    <td>{i.description}</td>                    
                    <td>{i.prioridad}</td>
                  </tr>
                )}
              </tbody>
            </table>         
      </div>
     </div>
    );
  }
}

export default App;
