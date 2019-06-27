const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const PORT = 3005;
const cors = require('cors');
const mongoose = require('mongoose');
const config = require('./database.js');
const videoRoute = require('./video.route');

mongoose.Promise = global.Promise;
mongoose.connect(config.database, { useNewUrlParser: true }).then(
  () => {console.log('Database is connected') },
  err => { console.log('Can not connect to the database'+ err)}
);

app.use(cors());
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

app.use('/video', videoRoute);

app.listen(PORT, function(){
  console.log('Server is running on Port:',PORT);
});
