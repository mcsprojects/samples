import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './../css/main.css';

class Details extends Component {

  constructor(props) {
    super(props);
    this.state = {
      incidencia: {}
    };
  }

  componentDidMount() {
    axios.get('/incidencias/'+this.props.match.params.id)
      .then(res => {
        this.setState({ incidencia: res.data });
        console.log(this.state.incidencia);
      });
  }

  delete(id){
    console.log(id);
    axios.delete('/incidencias/'+id)
      .then((result) => {
        this.props.history.push("/")
      });
  }
  
  render() {
    return (     
             <div class="wrapper">
             <div class="row h-100 justify-content-center align-items-center">
              <div class="col-md-6">
                    <h5>Detalles de la incidencia</h5>   
               <dl>
              <dt>Proyecto:</dt>
              <dd>{this.state.incidencia.proyecto}</dd>
              <dt>Descripción de la incidencia:</dt>
              <dd>{this.state.incidencia.description}</dd>
              <dt>Prioridad:</dt>
              <dd>{this.state.incidencia.prioridad}</dd>
              <dt>Estado de la incidencia:</dt>
              <dd>{this.state.incidencia.estado}</dd>
              <dt>Frecuencia:</dt>
              <dd>{this.state.incidencia.frecuencia}</dd>
              <dt>Fecha de notificación:</dt>
              <dd>{this.state.incidencia.reporte}</dd>
              <dt>Fecha de resolución:</dt>
              <dd>{this.state.incidencia.solution}</dd>
              <dt>Identificación del problema:</dt>
              <dd>{this.state.incidencia.evaluation}</dd>
              <dt>Categoría:</dt>
              <dd>{this.state.incidencia.category}</dd>              
              </dl>
              <Link to={`/edit/${this.state.incidencia.id}`} class="btn btn-success" id="btn-custom">Editar</Link>&nbsp;              
              <button onClick={this.delete.bind(this, this.state.incidencia.id)} id="btn-custom" class="btn btn-danger">Eliminar</button> 
                      
         </div>
        </div>
       </div>
    );
  }
}

export default Details;
  
