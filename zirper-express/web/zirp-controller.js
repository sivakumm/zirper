const Zirp = require('../domain/zirp');
const log4js = require('log4js');

const logger = log4js.getLogger('Controller');

exports.findAll = (req, res) => {
    Zirp.find((err, zirps) => {
        if (err) {
            logger.error('findAll failed', err);
            res.status(500).send('database error');
        } else {
            res.status(200).json(zirps);
        }
    });
};

exports.findById = (req, res) => {
    Zirp.findById(req.params.id, (err, zirp) => {
        if (err) {
            res.status(404).send('Zirp not found');
        } else {
            res.status(200).json(zirp);
        }
    });
};

exports.create = (req, res) => {
    const zirp = new Zirp();
    zirp.username = req.body.username;
    zirp.zirp = req.body.zirp;
    zirp.date = new Date().toISOString();
    zirp.save((err, savedZirp) => {
        if (err) {
            res.status(412).send('Precondition failed');
        } else {
            res.status(201).json(savedZirp);
        }
    });
};

exports.update = (req, res) => {
    const zirp = new Zirp();
    Zirp.findById(req.params.id, (err, zirp) => {
        if (err) {
            res.status(404).send('Zirp not found');
        } else {
            zirp.save((err, updatedZirp) => {
                if (err) {
                    res.status(412).send('Precondition failed');
                } else {
                    res.status(200).json(updatedZirp);
                }
            });
        }
    });
};

exports.delete = (req, res) => {
    Zirp.findByIdAndDelete(req.params.id, (err, zirp) => {
        if (err) {
            res.status(404).send('Zirp not found');
        } else {
            res.status(204).send();
        }
    });
};
