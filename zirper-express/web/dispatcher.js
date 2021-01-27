const dispatcher = require('express').Router();
const zirpController = require('./zirp-controller');

dispatcher.route('/').get((req, res) => res.redirect(`${req.baseUrl}/zirps`));

dispatcher.route('/zirps')
    .get(zirpController.findAll)
    .post(zirpController.create);

dispatcher.route('/zirps/:id')
    .get(zirpController.findById)
    .put(zirpController.update)
    .delete(zirpController.delete);

module.exports = dispatcher;