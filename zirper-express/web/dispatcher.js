const dispatcher = require('express').Router();
const zirp_controller = require('./zirp-controller');

dispatcher.route('/').get((req, res) => res.redirect(`${req.baseUrl}/zirps`));

dispatcher.route('/zirps')
    .get(zirp_controller.findAll)
    .post(zirp_controller.create);

dispatcher.route('/zirps/:id')
    .get(zirp_controller.findById)
    .put(zirp_controller.update)
    .delete(zirp_controller.delete);

module.exports = dispatcher;