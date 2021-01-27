const express = require('express');
const mongoose = require('mongoose');
const log4js = require('log4js');
const config = require('dotenv-extended').load({
    schema: '.env.schema',
    errorOnMissing: true,
    errorOnExtra: false
});

const logger = log4js.getLogger('App');
logger.level = 'debug';

mongoose.Promise = global.Promise;
mongoose.connect(`mongodb://${config.MONGO_HOST}/${config.MONGO_DATABASE}`, { useNewUrlParser: true, useUnifiedTopology: true });

const app = express();



app.listen(config.PORT, () => {
    logger.info(`Server started on Port ${config.PORT}`);
});