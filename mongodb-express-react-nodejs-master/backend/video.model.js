const mongoose = require('mongoose');
const Schema = mongoose.Schema;
 
let Video = new Schema({
  platform_name: {
    type: String
  },
  video_title: {
    type: String
  },
  description: {
    type: String
  },
  url: {
    type: String
  }
},{
    collection: 'video'
});

module.exports = mongoose.model('Video', Video);
