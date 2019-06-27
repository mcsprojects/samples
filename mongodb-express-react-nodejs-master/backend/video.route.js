// video.route.js

const express = require('express');
const videoRoutes = express.Router();

let Video = require('./video.model');

// Añadir o guardar la información
videoRoutes.route('/add').post(function (req, res) {
  let video = new Video(req.body);
  video.save()
    .then(video => {
      res.status(200).json({'video': 'Datos almacenados con éxito'});
    })
    .catch(err => {
      res.status(400).send("No es posible guardar la información en la base de datos");
    });
});

// Obtener y listar los datos
videoRoutes.route('/').get(function (req, res) {
    Video.find(function(err, videos){
    if(err){
      console.log(err);
    }
    else {
      res.json(videos);
    }
  });
});

// Editar
videoRoutes.route('/edit/:id').get(function (req, res) {
  let id = req.params.id;
  Video.findById(id, function (err, video){
      res.json(video);
  });
});

//  Actualizar 
videoRoutes.route('/update/:id').post(function (req, res) {
    Video.findById(req.params.id, function(err, video) {
    if (!video)
      res.status(404).send("Datos no encontardos");
    else {
        video.platform_name = req.body.platform_name;
        video.video_title = req.body.video_title;
        video.description = req.body.description;
        video.url = req.body.url;
        video.save().then(video => {
        res.json('Actualización realizada');
        })
        .catch(err => {
          res.status(400).send("No es posible actualizar la información en la base de datos");
        });
    }
  });
});

// Borrar
videoRoutes.route('/delete/:id').get(function (req, res) {
    Video.findByIdAndRemove({_id: req.params.id}, function(err, video){
        if(err) res.json(err);
        else res.json('Operación realizada con éxito');
    });
});

module.exports = videoRoutes;
