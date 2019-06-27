import React, { Component } from 'react';
import axios from 'axios';

export default class Edit extends Component {
  constructor(props) {
    super(props);
    this.onChangePlatformName = this.onChangePlatformName.bind(this);
    this.onChangeVideoTitle = this.onChangeVideoTitle.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.onChangeUrl = this.onChangeUrl.bind(this);

    this.onSubmit = this.onSubmit.bind(this);

    this.state = {
      platform_name: '',
      video_title:'',
      description: '', 
      url: ''
    }
  }

  componentDidMount() {
      axios.get('http://localhost:3005/video/edit/'+this.props.match.params.id)
          .then(response => {
              this.setState({ 
                platform_name: response.data.platform_name, 
                video_title: response.data.video_title,
                description: response.data.description,
                url: response.data.url });
          })
          .catch(function (error) {
              console.log(error);
          })
    }

  onChangePlatformName(e) {
    this.setState({
      platform_name: e.target.value
    });
  }
  onChangeVideoTitle(e) {
    this.setState({
      video_title: e.target.value
    })  
  }
  onChangeDescription(e) {
    this.setState({
      description: e.target.value
    })  
  }
  onChangeUrl(e) {
    this.setState({
      url: e.target.value
    })
  }

  onSubmit(e) {
    e.preventDefault();
    const obj = {
      platform_name: this.state.platform_name,
      video_title: this.state.video_title,
      description: this.state.description,
      url: this.state.url
    };
    axios.post('http://localhost:3005/video/update/'+this.props.match.params.id, obj)
        .then(res => console.log(res.data));
    window.location.reload()
    this.props.history.push('/index');
  }
 
  render() {
    return (
        <div class="form-container">

            <h3 class="red" align="center">Actualizar datos</h3>
            <form onSubmit={this.onSubmit}>
                <div className="form-group">
                    <label>Nombre del sitio web:  </label>
                    <input 
                      type="text" 
                      className="form-control" 
                      value={this.state.platform_name}
                      onChange={this.onChangePlatformName}
                      />
                </div>
                <div className="form-group">
                    <label>Título del vídeo: </label>
                    <input type="text" 
                      className="form-control"
                      value={this.state.video_title}
                      onChange={this.onChangeVideoTitle}
                      />
                </div>
                <div className="form-group">
                    <label>Descripción: </label>
                    <input type="text" 
                      className="form-control"
                      value={this.state.description}
                      onChange={this.onChangeDescription}
                      />
                </div>
                <div className="form-group">
                    <label>URL: </label>
                    <input type="url" 
                      className="form-control"
                      value={this.state.url}
                      onChange={this.onChangeUrl}
                      />
                </div>
                <div className="form-group">
                    <input type="submit" 
                      value="Enviar" 
                      className="btn btn-primary"/>
                </div>
            </form>
        </div>
    )
  }
}
