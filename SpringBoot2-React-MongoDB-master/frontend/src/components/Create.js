import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import './../css/main.css';

class Create extends Component {

  constructor() {
    super();
    this.state = {
      proyecto: '',
      description: '',
      prioridad: '',
      estado: '', 
      frecuencia: '',
      reporte: '',
      solution: '',
      evaluation: '',
      category: ''      
    };
  }
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { proyecto, description, prioridad, estado, frecuencia, reporte, solution, evaluation, category } = this.state;

    axios.post('/incidencias', { proyecto, description, prioridad, estado, frecuencia, reporte, solution, evaluation, category })
      .then((result) => {
        this.props.history.push("/")
      });
  }

  render() {
    const { proyecto, description, prioridad, estado, frecuencia, reporte, solution, evaluation, category } = this.state;
    return (
            <div class="wrapper">      
            <div class="row h-100 justify-content-center align-items-center">
            <div class="col-md-6">
                    <h5>Añadir nueva incidencia</h5>  
            <form onSubmit={this.onSubmit}>
              <div class="row">              
              <div class="col-sm-12 form-group">
                <label for="proyecto">Nombre del Proyecto:</label>
                <input type="text" class="form-control" name="proyecto" value={proyecto} onChange={this.onChange} placeholder="Nombre del proyecto" />
              </div>
              </div>
              <div class="row">              
              <div class="col-sm-12 form-group">
                <label for="description">Descripción de la incidencia:</label>
                <input type="text" class="form-control" name="description" value={description} onChange={this.onChange} placeholder="Descripción"/>
              </div>
              </div>
              <div class="row">
              <div class="col-sm-6 form-group">
                <label for="prioridad">Prioridad:
                <select id="white" name="prioridad" value={prioridad} onChange={this.onChange}>
                  <option>--Elegir--</option>
                  <option>Baja</option>
                  <option>Media</option>
                  <option>Alta</option>
                  <option>Urgente</option>                
                </select>
               </label>
              </div>              
              <div class="col-sm-6 form-group">
                <label for="estado">Estado de la inicidencia:                
                 <select id="white" name="estado" value={estado} onChange={this.onChange}>
                  <option>--Elegir--</option>
                  <option>Nueva</option>                  
                  <option>En análisis</option>
                  <option>Pendiente de prueba</option>
                  <option>Resuelta</option>
                </select>
              </label>
              </div>
              </div>
              <div class="row">
              <div class="col-sm-6 form-group">
                <label for="frecuencia">Frecuencia:
                <select id="white" name="frecuencia" value={frecuencia} onChange={this.onChange}>
                 <option>--Elegir--</option>
                 <option>Siempre</option>
                 <option>Aleatoria</option>
                 <option>A veces</option>
                 <option>Primera vez</option>                        
               </select>
               </label>
              </div>
              <div class="col-sm-6 form-group"> 
                <label for="category">Categoría:
                <select id="white" name="category" value={category} onChange={this.onChange} >
                  <option>--Elegir--</option>
                  <option>General</option>
                  <option>Funcional</option>
                  <option>Técnica</option>
                  <option>Interfaz gráfica</option>
                </select>
               </label>
              </div>
              </div>
              <div class="row">
              <div class="col-sm-6 form-group">
                <label for="reporte">Fecha de notificación:</label>
                <input type="text" class="form-control" name="reporte" value={reporte} onChange={this.onChange} placeholder="aaaa/mm/dd" />
              </div>
              <div class="col-sm-6 form-group">
                <label for="solution">Fecha de resolución:</label>
                <input type="text" class="form-control" name="solution" value={solution} onChange={this.onChange} placeholder="aaaa/mm/dd" />
              </div>
              </div>
              <div class="row">
              <div class="col-sm-12 form-group">
                <label for="evaluation">Identificación del problema:</label>
                <input type="text" class="form-control" name="evaluation" value={evaluation} onChange={this.onChange} placeholder="Descripción del bug " />
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

export default Create;
