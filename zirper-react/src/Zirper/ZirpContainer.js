import React, { useState, useEffect } from 'react';
import ZirpCardList from './ZirpCardList';
import ZirpCreateForm from './ZirpCreateForm';
import _ from 'lodash';

const ZirpContainer = ({ defaultZirps }) => {

	const [zirps, setZirps] = useState(defaultZirps);

	useEffect(() => {
		setZirps(_.orderBy(defaultZirps, ['date'], ['desc']));
	}, [defaultZirps]);

	const createZirp = (zirp) => {
		zirp.id = 'xyz' + _.size(zirps) + 1;
		zirp.date = new Date().toISOString();
		setZirps(_.orderBy(_.concat(zirps, zirp), ['date'], ['desc']));
	};

	const deleteZirp = (zirpId) => {
		setZirps(_.reject(zirps, { id: zirpId}));
	};

	return (
		<div>
			<ZirpCreateForm createFn={ createZirp } ></ZirpCreateForm>
			<hr />
			<ZirpCardList zirps={ zirps } deleteFn={ deleteZirp } ></ZirpCardList>
		</div>
	);
};

export default ZirpContainer;

ZirpContainer.defaultProps = {
  defaultZirps: [
    { id: 'xyz0', username: 'defaultUser0', zirp: 'Message to share with the world!', date: '2021-01-25T08:51:41.217Z' },
    { id: 'xyz1', username: 'defaultUser0', zirp: 'Message to share with the world!', date: '2021-01-25T09:51:41.217Z' },
    { id: 'xyz2', username: 'defaultUser1', zirp: 'Message to share with the world!', date: '2021-01-25T10:51:41.217Z' },
    { id: 'xyz3', username: 'defaultUser1', zirp: 'Message to share with the world!', date: '2021-01-25T11:51:41.217Z' },
  ]
};
