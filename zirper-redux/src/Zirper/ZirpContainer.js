import React, { useEffect } from 'react';
import ZirpCardList from './ZirpCardList';
import ZirpCreateForm from './ZirpCreateForm';
import _ from 'lodash';
import { useDispatch, useSelector } from 'react-redux';

const ZirpContainer = () => {

	const serverUrl = useSelector(state => state.url, _.isEqual);
	const zirps = useSelector(state => state.zirps, _.isEqual);

	const dispatch = useDispatch();

	const readAll = () => {
		fetch(`${serverUrl}/zirps`)
			.then(response => response.json())
			.then(json => dispatch({ type: 'ZIRPS', value: sortZirps(json) }));
	}

	useEffect(readAll, [serverUrl, dispatch]);

	const createZirp = (zirp) => {
		fetch(`${serverUrl}/zirps`, {
			method: 'POST',
			headers: new Headers({
				'Content-Type': 'application/json'
			}),
			body: JSON.stringify(zirp)
		})
		.then(response => response.json())
		.then(saved => dispatch({ type: 'ZIRPS', value: sortZirps(_.concat(zirps, saved)) }));
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
		.then(updated => dispatch({ type: 'ZIRPS', value: _.map(zirps, z => z.id === zirp.id ? updated : z) }));
	}

	const deleteZirp = (zirpId) => {
		fetch(`${serverUrl}/zirps/${zirpId}`, {
			method: 'DELETE'
		})
		.then(response => {
			if (response.ok) {
				dispatch({ type: 'ZIRPS', value: _.reject(zirps, { id: zirpId }) });
			}
		});
	};

	const sortZirps = (zirpList) => {
		return _.sortBy(zirpList, (zirp) => new Date(zirp.date)).reverse();
	}

	return (
		<div>
			<ZirpCreateForm createFn={ createZirp } ></ZirpCreateForm>
			<hr />
			<ZirpCardList zirps={ zirps } deleteFn={ deleteZirp } updateFn={ updateZirp }></ZirpCardList>
		</div>
	);
};

export default ZirpContainer;