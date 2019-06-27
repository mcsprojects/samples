import React, { Component } from 'react';
import axios from 'axios';
import TableRow from './TableRow';
import './css/main.css';

export default class Index extends Component {

  constructor(props) {
      super(props);
      this.state = {video: []};
    }
    componentDidMount(){
      axios.get('http://localhost:3005/video')
        .then(response => {
          this.setState({ video: response.data });
        })
        .catch(function (error) {
          console.log(error);
        })
    }
    tabRow(){
      return this.state.video.map(function(object, i){
          return <TableRow obj={object} key={i} />;
      });
    }

    render() {
      return (
        <div>          
          <table className="table table-striped table-dark" style={{ marginTop: 35 }}>
            <thead>
              <tr>
                <th>Sitio web</th>
                <th>Título del vídeo</th>
                <th>Descripción</th>                
                <th colSpan="2" >Acción a ejecutar</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              { this.tabRow() }
            </tbody>
          </table>
        </div>
      );
    }
  }
