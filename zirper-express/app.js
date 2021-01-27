const express = require('express');
const log4js = require('log4js');
const config = require('dotenv-extended').load({
    schema: '.env.schema',
    errorOnMissing: true,
    errorOnExtra: false
});

const logger = log4js.getLogger('App');
logger.level = 'debug';

const app = express();



app.listen(config.PORT, () => {
    logger.info(`Server started on Port ${config.PORT}`);
});