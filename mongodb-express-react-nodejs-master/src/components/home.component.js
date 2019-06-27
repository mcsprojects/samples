import React, {Component} from 'react';
import './css/main.css';
import image from "./images/image.png";


class Home extends Component {
  render() {     
    return (
      <div class="body">
      <div class="landing">
		

		 
				<section id="banner">
					<div class="content">
						<div class="header">
							<h2><span>Videoteca</span>Online</h2>
							<p>Ejemplo de React CRUD + Bootstrap 4</p>
						</div>
						<img class="image" src={image} width="20" height="20" alt="" /> 
					</div>
					
				</section>		
        </div>
		
	</div>
    )
  
}
}

export default Home;


