import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './css/main.css';

class TableRow extends Component {

  constructor(props) {
        super(props);
        this.delete = this.delete.bind(this);
    }
    delete() {
        axios.get('http://localhost:3005/video/delete/'+this.props.obj._id)
            .then(console.log('Eliminado'))
             window.location.reload() 
            .catch(err => console.log(err))
    }
  render() {
    return (
        <tr>
          <td>
            {this.props.obj.platform_name}
          </td>
          <td>
            {this.props.obj.video_title}
          </td>
          <td>
            {this.props.obj.description}
          </td>
              
          <td>                        
            <a href={this.props.obj.url} className="btn btn-success">Ver</a>                      
          </td>                
          <td>
            <Link to={"/edit/"+this.props.obj._id} className="btn btn-primary">Editar</Link>
          </td>
          <td>            
            <button onClick={this.delete} className="btn btn-danger">Borrar</button>
          </td>
        </tr>
    );
  }
}


export default TableRow;
