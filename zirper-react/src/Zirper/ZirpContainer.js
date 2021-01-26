import React, { useState, useEffect } from 'react';
import ZirpCardList from './ZirpCardList';
import ZirpCreateForm from './ZirpCreateForm';
import _ from 'lodash';

const ZirpContainer = ({ defaultZirps, serverUrl }) => {

	const [zirps, setZirps] = useState(defaultZirps);

	useEffect(() => readAll(), []);

	const readAll = () => {
		fetch(`${serverUrl}/zirps`)
			.then(response => response.json())
			.then(json => setZirps(_.orderBy(json, ['date'], ['desc'])));
	}

	const createZirp = (zirp) => {
		fetch(`${serverUrl}/zirps`, {
			method: 'POST',
			headers: new Headers({
				'Content-Type': 'application/json'
			}),
			body: JSON.stringify(zirp)
		})
		.then(response => response.json())
		.then(saved => setZirps(_.orderBy(_.concat(zirps, saved), ['date'], ['desc'])));
	};

	const updateZirp = (zirp) => {
		fetch(`${serverUrl}/zirps/${zirp.id}`, {
			method: 'PUT',
			headers: new Headers({
				'Content-Type': 'application/json'
			}),
			body: JSON.stringify(zirp)
		})
		.then(response => response.json())
		.then(updated => setZirps(_.map(zirps, z => z.id === zirp.id ? updated : z)));
	}

	const deleteZirp = (zirpId) => {
		fetch(`${serverUrl}/zirps/${zirpId}`, {
			method: 'DELETE'
		})
		.then(response => {
			if (response.ok) {
				setZirps(_.reject(zirps, { id: zirpId}))
			}
		});
	};

	return (
		<div>
			<ZirpCreateForm createFn={ createZirp } ></ZirpCreateForm>
			<hr />
			<ZirpCardList zirps={ zirps } deleteFn={ deleteZirp } updateFn={ updateZirp }></ZirpCardList>
		</div>
	);
};

export default ZirpContainer;

ZirpContainer.defaultProps = {
  serverUrl: 'http://localhost:8080/zirper-rest'
};
