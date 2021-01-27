const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const zirpSchema = new Schema(
    {
        username: {
            type: String,
            minlength: 3,
            maxlength: 15
        },
        zirp: {
            type: String,
            minlength: 1,
            maxlength: 280
        },
        date: {
            type: Date
        }
    },
    {
        collection: 'zirps'
    }
);

zirpSchema.virtual('id').get(function() {
    return this._id.toHexString();
});

zirpSchema.set('toJSON', {
    virtuals: true
});

module.exports = mongoose.model('Zirp', zirpSchema);