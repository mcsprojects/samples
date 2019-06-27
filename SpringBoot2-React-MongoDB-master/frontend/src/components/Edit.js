import React, { Component } from 'react';
import axios from 'axios';
import './../css/main.css';

class Edit extends Component {

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

  onChange = (e) => {
    const state = this.state.incidencia
    state[e.target.name] = e.target.value;
    this.setState({incidencia:state});
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { proyecto, description, prioridad, estado, frecuencia, reporte, solution, evaluation, category } = this.state.incidencia;

    axios.put('/incidencias/'+this.props.match.params.id, { proyecto, description, prioridad, estado, frecuencia, reporte, solution, evaluation, category })
      .then((result) => {
        this.props.history.push("/details/"+this.props.match.params.id)
      });
  }

  render() {
    return (       
            <div class="wrapper">
            <div class="row h-100 justify-content-center align-items-center">
                <div class="col-md-6">
                    <h5>Actualizar datos de la incidencia</h5>   
            <form onSubmit={this.onSubmit}>
              
     
              <div class="row">              
              <div class="col-sm-12 form-group">
                <label for="proyecto">Nombre del proyecto:</label>
                <input type="text" class="form-control" name="proyecto" value={this.state.incidencia.proyecto} onChange={this.onChange} />
              </div>
              </div>
              <div class="row">
              <div class="col-sm-12 form-group">
                <label for="description">Descripción de la incidencia:</label>
                <input type="text" class="form-control" name="description" value={this.state.incidencia.description} onChange={this.onChange} />
              </div>
              </div>
              <div class="row">
              <div class="col-sm-6 form-group">
                <label for="prioridad">Prioridad:</label>
                <input type="text" class="form-control" name="prioridad" value={this.state.incidencia.prioridad} onChange={this.onChange}  />
              </div>
              <div class="col-sm-6 form-group">
                <label for="estado">Estado de la incidencia:</label>
                <input type="text" class="form-control" name="estado" value={this.state.incidencia.estado} onChange={this.onChange} />
              </div>
              </div>
              <div class="row">
              <div class="col-sm-6 form-group">
                <label for="frecuencia">Frecuencia:</label>
                <input type="text" class="form-control" name="frecuencia" value={this.state.incidencia.frecuencia} onChange={this.onChange}  />
              </div>
              <div class="col-sm-6 form-group">
                <label for="category">Categoría:</label>
                <input type="text" class="form-control" name="category" value={this.state.incidencia.category} onChange={this.onChange} />
              </div>
              </div>
              <div class="row">
              <div class="col-sm-6 form-group">
                <label for="reporte">Fecha de notificación:</label>
                <input type="text" class="form-control" name="reporte" value={this.state.incidencia.reporte} onChange={this.onChange} />
              </div>              
              <div class="col-sm-6 form-group">              
                <label for="solution">Fecha de resolución:</label>
                <input type="text" class="form-control" name="solution" value={this.state.incidencia.solution} onChange={this.onChange}/>
              </div> 
              </div>
              <div class="row">
              <div class="col-sm-12 form-group">
                <label for="evaluation">Identificación del problema:</label>
                <input type="text" class="form-control" name="evaluation" value={this.state.incidencia.evaluation} onChange={this.onChange}  />
              </div>
              </div>
              <div class="col-submit">
              <button type="submit" class="btn btn-primary">Enviar</button>
              </div>
             
            </form>         
          </div>
         </div>
        </div>
     
    );
  }
}

export default Edit;
