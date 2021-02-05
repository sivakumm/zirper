import React, { useEffect } from 'react';
import ZirpCardList from './ZirpCardList';
import ZirpCreateForm from './ZirpCreateForm';
import _ from 'lodash';
import { useDispatch, useSelector } from 'react-redux';
import doFetch from '../Util/Network';

const ZirpContainer = () => {

	const serverUrl = useSelector(state => state.url, _.isEqual);
	const zirps = useSelector(state => state.zirps, _.isEqual);

	const dispatch = useDispatch();

	const readAll = () => {
		doFetch(
			`${serverUrl}/zirps`,
			{ method: 'GET' },
			data => {
				dispatch({ type: 'ERROR', value: '' });
				dispatch({ type: 'ZIRPS/ALL', value: data });
			},
			error => dispatch({ type: 'ERROR', value: error })
		);
	}

	useEffect(readAll, [serverUrl, dispatch]);

	const createZirp = (zirp) => {
		doFetch(
			`${serverUrl}/zirps`,
			{
				method: 'POST',
				headers: new Headers({
					'Content-Type': 'application/json'
				}),
				body: JSON.stringify(zirp)
			},
			data => {
				dispatch({ type: 'ERROR', value: '' });
				dispatch({ type: 'ZIRPS/ADD', value: data });
			},
			error => dispatch({ type: 'ERROR', value: error })
		);
	};

	const updateZirp = (zirp) => {
		doFetch(
			`${serverUrl}/zirps/${zirp.id}`,
			{
				method: 'PUT',
				headers: new Headers({
					'Content-Type': 'application/json'
				}),
				body: JSON.stringify(zirp)
			},
			data => {
				dispatch({ type: 'ERROR', value: '' });
				dispatch({ type: 'ZIRPS/REPLACE', value: data });
			},
			error => dispatch({ type: 'ERROR', value: error })
		);
	}

	const deleteZirp = (zirpId) => {
		doFetch(
			`${serverUrl}/zirps/${zirpId}`,
			{ method: 'DELETE' },
			() => {
				dispatch({ type: 'ERROR', value: '' });
				dispatch({ type: 'ZIRPS/DELETE', value: zirpId });
			},
			error => dispatch({ type: 'ERROR', value: error })
		);
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