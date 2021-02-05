import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import _ from 'lodash';
import ZirpCard from './ZirpCard';
import doFetch from '../Util/Network';

const ZirpCardList = () => {

	const serverUrl = useSelector(state => state.url, _.isEqual);
	const zirps = useSelector(state => state.zirps, _.isEqual);

	const dispatch = useDispatch();

	const readAll = () => {
		doFetch(
			`${serverUrl}/zirps`,
			{ method: 'GET' },
			'ZIRPS/ALL',
			'Loading all zirps from server',
			dispatch
		);
	}

	useEffect(readAll, [serverUrl, dispatch]);

	return (
		<div>
			{ zirps.map(zirp => <ZirpCard key={ zirp.id } originalZirp={ zirp } />) }
		</div>
	);
};

export default ZirpCardList;